package com.kevin.vension.apt_processor.utils;

import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * ===================================================================
 *
 * @author: Created by Vension on 2019/1/16 11:45.
 * @email: 250685***4@qq.com
 * @update: update by *** on 2019/1/16 11:45
 * @desc: character determines attitude, attitude determines destiny
 * ===================================================================
 */
public class ElementUtils {

    //获取包名
    public static String getPackageName(Elements elementUtils, TypeElement typeElement) {
        return elementUtils.getPackageOf(typeElement).getQualifiedName().toString();
    }

    //获取顶层类类名
    public static String getEnclosingClassName(TypeElement typeElement) {
        return typeElement.getSimpleName().toString();
    }

    //获取静态内部类类名
    public static String getStaticClassName(TypeElement typeElement) {
        return getEnclosingClassName(typeElement) + "Holder";
    }

}
