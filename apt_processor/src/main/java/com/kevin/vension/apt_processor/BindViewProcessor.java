package com.kevin.vension.apt_processor;

import com.google.auto.service.AutoService;
import com.kevin.vension.apt_annotation.BindView;
import com.kevin.vension.apt_processor.utils.ElementUtils;
import com.kevin.vension.apt_processor.utils.StringUtils;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ========================================================
 * 作 者：Vension
 * 日 期：2019/1/16 10:49
 * 更 新：2019/1/16 10:49
 * 描 述：
 * ========================================================
 */

@AutoService(Processor.class)
public class BindViewProcessor extends AbstractProcessor {

    private Elements elementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elementUtils = processingEnv.getElementUtils();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> hashSet = new HashSet<>();
        hashSet.add(BindView.class.getCanonicalName());
        return hashSet;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 要自动生成 findViewById() 方法，
     * 则需要获取到控件变量的引用以及对应的 viewid，
     * 所以需要先遍历出每个 Activity 包含的所有注解对象
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //获取所有包含BindView注解的元素
        Set<? extends Element> elementSet = roundEnv.getElementsAnnotatedWith(BindView.class);
        Map<TypeElement,Map<Integer, VariableElement>> typeElementMapMap = new HashMap<>();

        for (Element eelement : elementSet) {
            //因为BindView的作用对象是FIELD,因此element可以直接转化为VariableElement
            VariableElement variableElement = (VariableElement) eelement;
            //getEnclosingElement()方法返回封装此Element的最里层元素
            //如果 Element 直接封装在另一个元素的声明中，则返回该封装元素
            //此处表示的即 Activity 类对象
            TypeElement typeElement = (TypeElement) variableElement.getEnclosingElement();
            Map<Integer,VariableElement> variableElementMap = typeElementMapMap.get(typeElement);
            if (variableElementMap == null){
                variableElementMap = new HashMap<>();
                typeElementMapMap.put(typeElement,variableElementMap);
            }
            //获取注解值，即 ViewId
            BindView bindAnnotation = variableElement.getAnnotation(BindView.class);
            int viewId = bindAnnotation.value();
            //将每个包含了 BindView 注解的字段对象以及其注解值保存起来
            variableElementMap.put(viewId,variableElement);
        }

        for (TypeElement key : typeElementMapMap.keySet()) {
            Map<Integer,VariableElement> elementMap = typeElementMapMap.get(key);
            String packageName = ElementUtils.getPackageName(elementUtils, key);
            JavaFile javaFile = JavaFile.builder(packageName,generateCodeByPote(key,elementMap)).build();
            try{
                javaFile.writeTo(processingEnv.getFiler());
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
        return false;
    }

    /**
     *  生成 Java 类
     *
     * @param typeElement  注解对象上层元素对象，即 Activity 对象
     * @param variableElementMap Activity包含的注解对象以及注解的目标对象
     * @return
     */
    private TypeSpec generateCodeByPote(TypeElement typeElement, Map<Integer, VariableElement> variableElementMap) {
        //自动生成的文件以 Activity名 + ViewBinding 进行命名
        return TypeSpec.classBuilder(ElementUtils.getEnclosingClassName(typeElement) + "ViewBinding")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(generateMethodByPoet(typeElement,variableElementMap))
                .build();
    }


    /**
     * 生成方法
     *
     * @param typeElement        注解对象上层元素对象，即 Activity 对象
     * @param variableElementMap Activity 包含的注解对象以及注解的目标对象
     * @return
     */
    private MethodSpec generateMethodByPoet(TypeElement typeElement, Map<Integer, VariableElement> variableElementMap) {
        ClassName className = ClassName.bestGuess(typeElement.getQualifiedName().toString());
        //方法参数名
        String parameter = "_" + StringUtils.toLowerCaseFirstChar(className.simpleName());
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("bind")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(className, parameter);
        for (int viewId : variableElementMap.keySet()) {
            VariableElement element = variableElementMap.get(viewId);
            //被注解的字段名
            String name = element.getSimpleName().toString();
            //被注解的字段的对象类型的全名称
            String type = element.asType().toString();
            String text = "{0}.{1}=({2})({3}.findViewById({4}));";
            methodBuilder.addCode(MessageFormat.format(text, parameter, name, type, parameter, String.valueOf(viewId)));
        }
        return methodBuilder.build();
    }

}
