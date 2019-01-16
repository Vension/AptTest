package com.kevin.vension.apt_annotation.preferences;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ===================================================================
 *
 * @author: Created by Vension on 2019/1/16 11:43.
 * @email: 250685***4@qq.com
 * @update: update by *** on 2019/1/16 11:43
 * @desc: character determines attitude, attitude determines destiny
 * ===================================================================
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface Preferences {
}
