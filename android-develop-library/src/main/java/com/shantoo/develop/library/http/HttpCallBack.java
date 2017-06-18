package com.shantoo.develop.library.http;

/**
 * 作者: shantoo on 2017/5/13 17:45.
 */

public abstract class HttpCallBack<T> {

    public abstract void onSuccess(T data);

    public abstract void onFail(String msg);
}