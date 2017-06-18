package com.shantoo.develop.library.http.volley;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.shantoo.develop.library.http.HttpCallBack;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 作者: shantoo on 2017/5/13 18:22.
 */

public class VolleyHelper {

    private static String mTag;
    private static RequestQueue mRequestQueue;

    private static VolleyHelper instance = new VolleyHelper();

    public static VolleyHelper register(RequestQueue requestQueue, String tag) {
        mTag = tag;
        mRequestQueue = requestQueue;
        return instance;
    }

    public static void cancelAll(String tag) {
        mRequestQueue.cancelAll(tag);
    }

    public void doPostRequest(String url, final HttpCallBack callBack){


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        };

        StringRequest request = new StringRequest(Request.Method.GET, url, null,errorListener);
        mRequestQueue.add(request).setTag(mTag);
    }

    public static Type getType(Class<?> clazz) {
        Type mySuperClassType = clazz.getGenericSuperclass();
        Type[] types = ((ParameterizedType) mySuperClassType).getActualTypeArguments();
        if (types != null && types.length > 0) {
            //T
            return types[0];
        }
        return null;
    }
}
