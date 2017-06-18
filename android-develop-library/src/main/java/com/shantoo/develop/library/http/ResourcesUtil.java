package com.shantoo.develop.library.http;

import android.content.Context;

/**
 * 作者: shantoo on 2017/5/18 16:15.
 */

public class ResourcesUtil {

    public static int getResourcesColor(Context context,int colorResId){
        return context.getResources().getColor(colorResId);
    }
}
