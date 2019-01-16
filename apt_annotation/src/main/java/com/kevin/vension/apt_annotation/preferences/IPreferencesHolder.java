package com.kevin.vension.apt_annotation.preferences;

/**
 * ===================================================================
 *
 * @author: Created by Vension on 2019/1/16 11:41.
 * @email: 250685***4@qq.com
 * @update: update by *** on 2019/1/16 11:41
 * @desc: character determines attitude, attitude determines destiny
 * ===================================================================
 */
public interface IPreferencesHolder {

    //序列化
    String serialize(String key,Object src);

    //反序列化
    <T> T deserialize(String key,Class<T> classOfT);

    //移除制定对象
    void remove(String key);

}
