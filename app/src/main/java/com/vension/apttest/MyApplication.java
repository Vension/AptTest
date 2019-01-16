package com.vension.apttest;

import android.app.Application;
import com.kevin.vension.apt_annotation.preferences.PreferencesManager;
import com.tencent.mmkv.MMKV;

/**
 * ========================================================
 * 作 者：Vension
 * 日 期：2019/1/16 12:02
 * 更 新：2019/1/16 12:02
 * 描 述：
 * ========================================================
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this);
        PreferencesManager.getInstance().setPreferencesHolder(new PreferencesMMKVHolder());
    }

}
