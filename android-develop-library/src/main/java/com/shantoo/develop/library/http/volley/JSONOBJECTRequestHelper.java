package com.shantoo.develop.library.http.volley;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.type;

/**
 * 作者: shantoo on 2017/5/6 11:58.
 */

public class JSONOBJECTRequestHelper {

    private static RequestQueue mRequestQueue;
    private static JSONOBJECTRequestHelper instance = new JSONOBJECTRequestHelper();

    public static JSONOBJECTRequestHelper with(RequestQueue requestQueue) {
        mRequestQueue = requestQueue;
        return instance;
    }

    public interface JSONOBJECTSuccessListener<T> {
        void onResponse(T response);
    }

    public void cancelAll(String tag){
        mRequestQueue.cancelAll(tag);
    }

    /**
     * 以Post方式向服务器发送请求,并将数据以JSONObject对象返回
     *
     * @param url         服务器url地址
     * @param jsonRequest 携带的JSONObject格式的json参数
     * @param tag         加入到RequestQueue队列的TAG值
     * @param listener    请求成功返回的回调
     */
    public JSONOBJECTRequestHelper doPostRequest(
            String url, JSONObject jsonRequest, String tag, Response.Listener<JSONObject> listener) {

        JsonObjectRequest request = new JsonObjectRequest(url, jsonRequest, listener, Util.getErrorListener());
        mRequestQueue.add(request).setTag(tag);
        return instance;
    }

    /**
     * 以Post方式向服务器发送请求,并将数据封装到Bean对象中返回
     *
     * @param url             服务器url地址
     * @param jsonRequest     携带的JSONObject格式的json参数
     * @param tag             加入到RequestQueue队列的TAG值
     * @param type            type
     * @param successListener 请求成功返回的回调
     */
    public <T> JSONOBJECTRequestHelper doPostRequest(
            String url, JSONObject jsonRequest, String tag, final Type type,
            final JSONOBJECTSuccessListener<T> successListener) {

        final Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                T response = new Gson().fromJson(jsonObject.toString(), type);
                successListener.onResponse(response);
            }
        };
        JsonObjectRequest request = new JsonObjectRequest(url, jsonRequest, listener, Util.getErrorListener());
        mRequestQueue.add(request).setTag(tag);
        return instance;
    }

    /**
     * 以Post方式向服务器发送请求,并将数据封装到Bean对象中返回
     *
     * @param url             服务器url地址
     * @param json            携带的map格式的json参数
     * @param tag             加入到RequestQueue队列的TAG值
     * @param type            type
     * @param successListener 请求成功返回的回调
     */
    public <T> JSONOBJECTRequestHelper doPostRequest(
            String url, Map json, String tag, final Type type,
            final JSONOBJECTSuccessListener<T> successListener) {

        return doPostRequest(url, new JSONObject(json), tag, type, successListener);
    }

    /**
     * 以Post方式向服务器发送请求,并将数据封装到Bean对象中返回
     *
     * @param url             服务器url地址
     * @param json            携带的String格式的json参数
     * @param tag             加入到RequestQueue队列的TAG值
     * @param type            type
     * @param successListener 请求成功返回的回调
     */
    public <T> JSONOBJECTRequestHelper doPostRequest(
            String url, String json, String tag, final Type type,
            final JSONOBJECTSuccessListener<T> successListener) {

        try {
            return doPostRequest(url, new JSONObject().getJSONObject(json), tag, type, successListener);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 以Get方式向服务器发送请求,并将数据以JSONObject对象返回
     *
     * @param url      服务器url地址
     * @param tag      加入到RequestQueue队列的TAG值
     * @param listener 请求成功返回的回调
     */
    public JSONOBJECTRequestHelper doGetRequest(
            String url, String tag, Response.Listener<JSONObject> listener) {

        return doPostRequest(url, null, tag, listener);
    }

    /**
     * 以Get方式向服务器发送请求,并将数据封装到Bean对象中返回
     *
     * @param url             String    服务器url地址
     * @param tag             String    加入到RequestQueue队列的TAG值
     * @param type            type
     * @param successListener 请求成功返回的回调
     */
    public <T> JSONOBJECTRequestHelper doGetRequest(
            String url, String tag, final Type type, final JSONOBJECTSuccessListener<T> successListener) {

        final Response.Listener<JSONObject> listener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                T response = new Gson().fromJson(jsonObject.toString(), type);
                successListener.onResponse(response);
            }
        };
        return doPostRequest(url, null, tag, listener);
    }

    public <T> JSONOBJECTRequestHelper doPostRequest(String url, final String tag, final Map params, final Type type,
                                                     final JSONOBJECTSuccessListener<T> successListener){
        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                T data = new Gson().fromJson(response, type);
                successListener.onResponse(data);
            }
        };
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, listener, Util.getErrorListener()) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        mRequestQueue.add(stringRequest);
        return instance;
    }
}
