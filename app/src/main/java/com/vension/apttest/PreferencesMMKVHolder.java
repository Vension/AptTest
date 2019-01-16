package com.vension.apttest;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.kevin.vension.apt_annotation.preferences.IPreferencesHolder;
import com.tencent.mmkv.MMKV;

/**
 * ========================================================
 * 作 者：Vension
 * 日 期：2019/1/16 12:03
 * 更 新：2019/1/16 12:03
 * 描 述：
 * ========================================================
 */

public class PreferencesMMKVHolder implements IPreferencesHolder {

    @Override
    public String serialize(String key, Object src) {
        String json = new Gson().toJson(src);
        MMKV kv = MMKV.defaultMMKV();
        kv.putString(key, json);
        return json;
    }

    @Override
    public <T> T deserialize(String key, Class<T> classOfT) {
        MMKV kv = MMKV.defaultMMKV();
        String json = kv.decodeString(key, "");
        if (!TextUtils.isEmpty(json)) {
            return new Gson().fromJson(json, classOfT);
        }
        return null;
    }

    @Override
    public void remove(String key) {
        MMKV kv = MMKV.defaultMMKV();
        kv.remove(key);
    }

}