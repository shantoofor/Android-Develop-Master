package com.shantoo.develop.library.utils;

import android.util.Log;

/**日志打印工具类*/

public class LogUtils {

    /**
     * 日志输出级别NONE
     */
    public static final int LEVEL_NONE = 0;
    /**
     * 日志输出级别V
     */
    private static final int LEVEL_VERBOSE = 1;
    /**
     * 日志输出级别D
     */
    private static final int LEVEL_DEBUG = 2;
    /**
     * 日志输出级别I
     */
    private static final int LEVEL_INFO = 3;
    /**
     * 日志输出级别W
     */
    private static final int LEVEL_WARN = 4;
    /**
     * 日志输出级别E
     */
    private static final int LEVEL_ERROR = 5;
    /**
     * 日志输出时的TAG
     */
    private static final String TAG = "LogUtils";
    /**
     * 是否允许输出LOG
     */
    private static int mDebuggable = LEVEL_ERROR;
    /**
     * 用于记时的变量
     */
    private static long mTimeStamp = 0;
    /**
     * 写文件的锁对象
     */
    private static final Object mLogLock = new Object();

    /**
     * 以级别为 v 的形式输出LOG
     */
    public static void v(String msg) {
        if (mDebuggable >= LEVEL_VERBOSE) {
            Log.v(TAG, msg);
        }
    }

    /**
     * 以级别为 v 的形式输出LOG
     */
    public static void v(String tag,String msg) {
        if (mDebuggable >= LEVEL_VERBOSE) {
            Log.v(tag, msg);
        }
    }

    /**
     * 以级别为 d 的形式输出LOG
     */
    public static void d(String msg) {
        if (mDebuggable >= LEVEL_DEBUG) {
            Log.d(TAG, msg);
        }
    }

    /**
     * 以级别为 d 的形式输出LOG
     */
    public static void d(String tag,String msg) {
        if (mDebuggable >= LEVEL_DEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * 以级别为 i 的形式输出LOG
     */
    public static void i(String msg) {
        if (mDebuggable >= LEVEL_INFO) {
            Log.i(TAG, msg);
        }
    }

    /**
     * 以级别为 i 的形式输出LOG
     */
    public static void i(String tag,String msg) {
        if (mDebuggable >= LEVEL_INFO) {
            Log.i(tag, msg);
        }
    }

    /**
     * 以级别为 w 的形式输出LOG
     */
    public static void w(String msg) {
        if (mDebuggable >= LEVEL_WARN) {
            Log.w(TAG, msg);
        }
    }

    /**
     * 以级别为 w 的形式输出LOG
     */
    public static void w(String tag,String msg) {
        if (mDebuggable >= LEVEL_WARN) {
            Log.w(tag, msg);
        }
    }

    /**
     * 以级别为 w 的形式输出Throwable
     */
    public static void w(Throwable tr) {
        if (mDebuggable >= LEVEL_WARN) {
            Log.w(TAG, "", tr);
        }
    }

    /**
     * 以级别为 w 的形式输出LOG信息和Throwable
     */
    public static void w(String msg, Throwable tr) {
        if (mDebuggable >= LEVEL_WARN && null != msg) {
            Log.w(TAG, msg, tr);
        }
    }

    /**
     * 以级别为 w 的形式输出Throwable
     */
    public static void w(String tag,String msg,Throwable tr) {
        if (mDebuggable >= LEVEL_WARN) {
            Log.w(tag, msg, tr);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG
     */
    public static void e(String msg) {
        if (mDebuggable >= LEVEL_ERROR) {
            Log.e(TAG, msg);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG
     */
    public static void e(String tag,String msg) {
        if (mDebuggable >= LEVEL_ERROR) {
            Log.e(tag, msg);
        }
    }

    /**
     * 以级别为 e 的形式输出Throwable
     */
    public static void e(Throwable tr) {
        if (mDebuggable >= LEVEL_ERROR) {
            Log.e(TAG, "", tr);
        }
    }

    /**
     * 以级别为 e 的形式输出LOG信息和Throwable
     */
    public static void e(String msg, Throwable tr) {
        if (mDebuggable >= LEVEL_ERROR && null != msg) {
            Log.e(TAG, msg, tr);
        }
    }

    /**
     * 以级别为 e 的形式输出Throwable
     */
    public static void e(String tag,String msg,Throwable tr) {
        if (mDebuggable >= LEVEL_ERROR) {
            Log.e(tag, msg, tr);
        }
    }
}
