package com.shantoo.develop.library.http.volley;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

/**
 * 作者: shantoo on 2017/5/6 14:37.
 */

public class Util {

    public static Response.ErrorListener getErrorListener(){
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                VolleyLog.e(volleyError.getMessage());
            }
        };
    }
}
