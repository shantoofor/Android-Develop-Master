package com.shantoo.develop.library.utils;

import java.io.UnsupportedEncodingException;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * 作者: shantoo on 2017/4/8 18:22.
 */

public class BASE64Util {

    private final static String ENCODE = "utf-8";

    // 加密
    public static String getBASE64(String str) {
        byte[] b = null;
        String result = null;
        try {
            b = str.getBytes(ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            result = new BASE64Encoder().encode(b);
        }
        return result;
    }

    // 加密
    public static String getBASE64(byte[] data) {
        return new BASE64Encoder().encode(data);
    }

    // 解密
    public static String getFromBASE64(String data) {
        byte[] b = null;
        String result = null;
        if (data != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(data);
                result = new String(b, ENCODE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // 解密
    public static String getFromBASE64(byte[] data){
        String result = "";
        try {
            result = new String(data, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
