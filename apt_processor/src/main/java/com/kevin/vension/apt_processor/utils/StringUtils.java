package com.kevin.vension.apt_processor.utils;

/**
 * ===================================================================
 *
 * @author: Created by Vension on 2019/1/16 11:46.
 * @email: 250685***4@qq.com
 * @update: update by *** on 2019/1/16 11:46
 * @desc: character determines attitude, attitude determines destiny
 * ===================================================================
 */
public class StringUtils {

    //将首字母转为小写
    public static String toLowerCaseFirstChar(String text) {
        if (text == null || text.length() == 0) {
            return "";
        }
        if (Character.isLowerCase(text.charAt(0))) {
            return text;
        }
        return String.valueOf(Character.toLowerCase(text.charAt(0))) + text.substring(1);
    }

    //将首字母转为大写
    public static String toUpperCaseFirstChar(String text) {
        if (Character.isUpperCase(text.charAt(0))) {
            return text;
        } else {
            return String.valueOf(Character.toUpperCase(text.charAt(0))) + text.substring(1);
        }
    }


}
