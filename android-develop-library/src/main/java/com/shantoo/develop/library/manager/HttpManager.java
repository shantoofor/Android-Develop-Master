package com.shantoo.develop.library.manager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by shantoo on 2017/2/28
 */
public class HttpManager {

    private Gson mGson;
    private JsonObject mJsonObject;
    private static HttpManager instance;
    //private OkHttpCallBack mOkHttpCallBack;

    private HttpManager() {
    }

    /**
     * 初始化HttpManager
     */
    public static HttpManager init() {
        if (instance == null) {
            instance = new HttpManager();
        }
        return instance;
    }

    /**
     * 使用okhttp3 发送一个网络请求
     *
     * @param url 服务器的URL地址
     */
    /*public HttpManager sendOkHttpRequest(String url){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(mOkHttpCallBack);
        return this;
    }*/
    public HttpManager sendOkHttpRequest(String url, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request
                .Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
        return this;
    }

    /*public interface OkHttpCallBack extends okhttp3.Callback{
        void onFailure(Call call, IOException e);
        void onResponse(Call call, Response response);
    }

   public void onFailure(Call call,IOException e){
       mOkHttpCallBack.onFailure(call,e);
   }

    public void onResponse(Call call,Response response){
        mOkHttpCallBack.onResponse(call,response);
    }*/


    /**
     * 将一个Response对象解析成一个Gson对象
     */
    public <T> T getGsonObject(Response response, Class<T> clazz) {
        String jsonData = response.body().toString();
        T t = initGson().fromJson(jsonData, clazz);
        return t;
    }

    /**
     * 将一个String解析成一个Gson对象
     */
    public <T> T getGsonObject(String jsonData, Class<T> clazz) {
        T t = initGson().fromJson(jsonData, clazz);
        return t;
    }

    /**
     * 将一个Response对象解析成一个GsonArray
     */
    public <T> List<T> getGsonArray(Response response, Class<T> clazz) {
        String jsonData = response.body().toString();
        List<T> list = initGson().fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }

    /**
     * 将一个String解析成一个GsonArray
     */
    public <T> List<T> getGsonArray(String jsonData, Class<T> clazz) {
        List<T> list = initGson().fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
        return list;
    }

    /**
     * 初始化Gson
     */
    private Gson initGson() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }

    /**
     * 初始化JsonObject
     */
    private JsonObject initJsonObject() {
        if (mJsonObject == null) {
            mJsonObject = new JsonObject();
        }
        return mJsonObject;
    }

}
