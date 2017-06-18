package com.shantoo.develop.library;

import android.content.Context;

/**
 * 作者: shantoo on 2017/6/18 14:32.
 */

public class Developer{

    private static Context mDeveloperContext;

    public static void register(Context context) {
        if (null != context) {
            mDeveloperContext = context;
        }
    }

    public static Context getContext(){
        return mDeveloperContext;
    }

}
