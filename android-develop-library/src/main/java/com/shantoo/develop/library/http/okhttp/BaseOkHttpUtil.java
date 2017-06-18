package com.shantoo.develop.library.http.okhttp;

import com.google.gson.Gson;
import com.shantoo.develop.library.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 作者: shantoo on 2017/3/30 16:39.
 */

@SuppressWarnings("unused")
public class BaseOkHttpUtil {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    private static OkHttpClient client = new OkHttpClient.Builder().build();

    public static Response createSyncGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        return client.newCall(request).execute();
    }

    public static <T> void createPostRequest(String url, Map map, BaseCallBack<T> callBack) {
        OkHttpUtils
                .postString()
                .url(url)
                .content(new Gson().toJson(map))
                .mediaType(JSON)
                .build()
                .execute(callBack);
    }

    public static <T> void createPostRequest(String url, String json, BaseCallBack<T> callBack) {
        OkHttpUtils
                .postString()
                .url(url)
                .content(json)
                .mediaType(JSON)
                .build()
                .execute(callBack);
    }

    public static <T> void createPostRequest(String url, String json, StringCallback callBack) {
        OkHttpUtils
                .postString()
                .url(url)
                .content(json)
                .mediaType(JSON)
                .build()
                .execute(callBack);
    }

    public static <T> void createGetRequest(String url, BaseCallBack<T> callBack) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(callBack);
    }

    public static void sendMultipart(String url, String filename) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", filename)
                .addFormDataPart("image", filename + ".jpg",
                        RequestBody.create(MEDIA_TYPE_PNG, new File("/sdcard/" + filename + ".jpg")))
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        /*mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("wangshu", response.body().string());
            }
        });*/
    }

    public static void okHttp_postFromParameters1(final String url, final String username, final String pass, final String verify) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 请求完整url：http://api.k780.com:88/?app=weather.future&weaid=1&&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json
                    //String url = "http://api.k780.com:88/";
                    OkHttpClient okHttpClient = new OkHttpClient();
                    //String json = "{'userName':'"+username+"','pass':'"+pass+"','verify':'"+verify+"','format':'json'}";
                    RequestBody formBody = new FormBody.Builder()
                            .add("userName", username)
                            .add("pass", pass)
                            .add("verify", verify)
                            .add("format", "json")
                            .build();
                    Request request = new Request.Builder()
                            .url(url)
                            .post(formBody)
                            .build();
                    Response response = okHttpClient
                            .newCall(request)
                            .execute();
                    LogUtils.e("BaseOkHttpUtil", response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void okHttp_postFromParameters(final String url, final String username, final String pass, final String verify, Callback callback) {
        FormBody.Builder params = new FormBody.Builder();
        params.add("userName", username);
        params.add("pass", pass);
        params.add("verify", verify);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(params.build())
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}

