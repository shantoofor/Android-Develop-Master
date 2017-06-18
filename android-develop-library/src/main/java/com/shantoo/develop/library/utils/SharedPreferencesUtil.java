package com.shantoo.develop.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * 作者: shantoo on 2017/5/10 18:47.
 * SharedPreferences简便使用工具类
 */

public class SharedPreferencesUtil {

    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor editor;

    /***/
    public static SharedPreferences registerSharedPreferences(Context context,String fileName){
        mSharedPreferences = context.getSharedPreferences(fileName,Context.MODE_PRIVATE);//只有当前应用程序可对SP进行读写
        editor = mSharedPreferences.edit();
        return mSharedPreferences;
    }

    /**获取一个SharedPreferences对象，并将当前活动的类名作为SharedPreferences文件的名称*/
    public static SharedPreferences registerSharePreferences(Activity activity){
        mSharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        editor = mSharedPreferences.edit();
        return mSharedPreferences;

    }

    /**获取一个SharedPreferences对象，并将当前应用的包名作为前缀来命名SharedPreferences文件*/
    public SharedPreferences registerSharePreferences(Context context){
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = mSharedPreferences.edit();
        return mSharedPreferences;
    }

    public SharedPreferences putBoolean(String key,boolean value){
        editor.putBoolean(key,value);
        return mSharedPreferences;
    }
    public SharedPreferences putFloat(String key,float value){
        editor.putFloat(key,value);
        return mSharedPreferences;
    }
    public SharedPreferences putInt(String key,int value){
        editor.putInt(key,value);
        return mSharedPreferences;
    }
    public SharedPreferences putLong(String key,long value){
        editor.putLong(key,value);
        return mSharedPreferences;
    }
    public SharedPreferences putString(String key,String value){
        editor.putString(key,value);
        return mSharedPreferences;
    }
    public SharedPreferences putStringSet(String key,Set<String> value){
        editor.putStringSet(key,value);
        return mSharedPreferences;
    }

    public SharedPreferences apply(){
        editor.apply();
        return mSharedPreferences;
    }

    public SharedPreferences getBoolean(String key){
        mSharedPreferences.getBoolean(key,false);
        return mSharedPreferences;
    }

    public SharedPreferences getFloat(String key){
        mSharedPreferences.getFloat(key,0.0f);
        return mSharedPreferences;
    }

    public SharedPreferences getInt(String key){
        mSharedPreferences.getInt(key,0);
        return mSharedPreferences;
    }

    public SharedPreferences getLong(String key){
        mSharedPreferences.getLong(key,0);
        return mSharedPreferences;
    }

    public SharedPreferences getString(String key){
        mSharedPreferences.getString(key,null);
        return mSharedPreferences;
    }

    public SharedPreferences getStringSet(String key){
        mSharedPreferences.getStringSet(key,null);
        return mSharedPreferences;
    }

    public SharedPreferences clear(){
        editor.clear();
        return mSharedPreferences;
    }

    public SharedPreferences commit(){
        editor.commit();
        return mSharedPreferences;
    }
}
