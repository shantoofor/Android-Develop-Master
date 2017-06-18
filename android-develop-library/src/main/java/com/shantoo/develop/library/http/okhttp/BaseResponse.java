package com.shantoo.develop.library.http.okhttp;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {

    @SerializedName("status")
    private int status;

    @SerializedName("msg")
    private String msg;

    @SerializedName("data")
    private T data;

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }
}
