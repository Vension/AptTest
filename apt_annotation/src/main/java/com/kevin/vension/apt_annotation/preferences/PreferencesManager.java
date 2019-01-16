package com.kevin.vension.apt_annotation.preferences;

/**
 * ===================================================================
 *
 * @author: Created by Vension on 2019/1/16 11:44.
 * @email: 250685***4@qq.com
 * @update: update by *** on 2019/1/16 11:44
 * @desc: character determines attitude, attitude determines destiny
 * ===================================================================
 */
public class PreferencesManager {

    private IPreferencesHolder preferencesHolder;

    private PreferencesManager() {
    }

    public static PreferencesManager getInstance() {
        return PreferencesManagerHolder.INSTANCE;
    }

    private static class PreferencesManagerHolder {
        private static PreferencesManager INSTANCE = new PreferencesManager();
    }

    public void setPreferencesHolder(IPreferencesHolder preferencesHolder) {
        this.preferencesHolder = preferencesHolder;
    }

    public IPreferencesHolder getPreferencesHolder() {
        return preferencesHolder;
    }
}
