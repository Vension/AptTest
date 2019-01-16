package com.vension.apttest;

import android.app.Activity;

import java.lang.reflect.Method;

/**
 * ========================================================
 * 作 者：Vension
 * 日 期：2019/1/16 12:06
 * 更 新：2019/1/16 12:06
 * 描 述：
 * ========================================================
 */

public class ButterKnife {

    public static void bind(Activity activity) {
        Class clazz = activity.getClass();
        try {
            Class bindViewClass = Class.forName(clazz.getName() + "ViewBinding");
            Method method = bindViewClass.getMethod("bind", activity.getClass());
            method.invoke(bindViewClass.newInstance(), activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
