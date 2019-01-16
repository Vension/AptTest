package com.kevin.vension.apt_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ========================================================
 * 作 者：Vension
 * 日 期：2019/1/16 10:50
 * 更 新：2019/1/16 10:50
 * 描 述：注解值 value 用于声明 viewId
 * ========================================================
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface BindView {
    int value();
}
