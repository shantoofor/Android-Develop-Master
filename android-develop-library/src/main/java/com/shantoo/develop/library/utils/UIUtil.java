package com.shantoo.develop.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class UIUtil {

    private static Handler handler = new Handler(Looper.getMainLooper());

    public static View inflate(Context context, int layout, ViewGroup parent){
        return  LayoutInflater.from(context).inflate(layout, parent, false);
    }

    /**
     * 获取Res目录下的String资源
     *
     * @param id 资源ID
     */
    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    /**
     * 获取Res目录下的Drawable资源
     *
     * @param id 资源ID
     */
    public static Drawable getDrawable(Context context, int id) {
        return context.getResources().getDrawable(id);
    }

    /**
     * 获取Res目录下的StringArray资源
     *
     * @param id 资源ID
     */
    public static String[] getStringArray(Context context, int id) {
        return context.getResources().getStringArray(id);
    }

    /**
     * 将dip转换成px
     * dip--->px   1dp = 1px   1dp = 2px
     *
     * @param dip dip值
     */
    public static int dip2px(Context context, int dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5);
    }

    /**
     * 将px转换成dip
     * dip--->px   1dp = 1px   1dp = 2px
     *
     * @param px px值
     */
    public static int px2dip(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

    /**
     * 判断是否是主线程
     */
    private static boolean isRunInMainThread(int mainThreadId) {
        return mainThreadId == android.os.Process.myTid();
    }

    /**
     * 保证当前的UI操作在主线程里面运行
     */
    public static void runInMainThread(Runnable runnable, int mainThreadId) {
        if (isRunInMainThread(mainThreadId)) {
            //如果当前线程是主线程，就直接运行run方法
            runnable.run();
        } else {
            //否则将其传递到主线程中运行
            handler.post(runnable);
        }
    }

    /**
     * java代码区设置颜色选择器的方法
     */
    public static ColorStateList getColorStateList(Context context, int mTabTextColorResId) {
        return context.getResources().getColorStateList(mTabTextColorResId);
    }

    public static View inflate(Context context, int resId) {
        return LayoutInflater.from(context).inflate(resId, null);
    }

    /**
     * 根据dimens中提供的id，将其对应的dp值转换成相应的像素值大小
     */
    public static int getDimens(Context context, int id) {
        return context.getResources().getDimensionPixelSize(id);
    }

    public static void postDelayed(Runnable runnable, long delayTime) {
        handler.postDelayed(runnable, delayTime);
    }

    //移除在当前handler中维护的任务(传递进来的任务)
    public static void removeCallback(Runnable runnable) {
        //移除在当前handler中维护的任务(传递进来的任务)
        handler.removeCallbacks(runnable);
    }

    public static int getColor(Context context, int id) {
        return context.getResources().getColor(id);
    }

    public static void displayDrawable(Context context, int drawableId) {
        Drawable drawable = context.getResources().getDrawable(drawableId);
        //第一0是距左边距离，第二0是距上边距离，40分别是长宽
        drawable.setBounds(0, 0, 10, 10);
    }

    /**
     * 获取手机屏幕高度
     *
     * @return int 手机屏幕高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获得屏幕高度
     *
     * @param context 上下文
     * @return int 屏幕宽度
     */
    public static int getScreenHeightB(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取手机屏幕宽度
     *
     * @return int 手机屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context 上下文
     * @return int 屏幕高度
     */
    public static int getScreenWidthB(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得状态栏的高度
     *
     * @param context 上下文
     * @return int 状态栏的高度
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusHeight;
        //return px2dip(context, statusHeight);
    }

    /**
     * 获取当前屏幕截图，包含状态栏
     *
     * @param activity Activity
     * @return Bitmap 当前屏幕截图，包含状态栏
     */
    public static Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    /**
     * 获取当前屏幕截图，不包含状态栏
     *
     * @param activity Activity
     * @return Bitmap 当前屏幕截图，不包含状态栏
     */
    public static Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    /**资源id 转Uri*/
    public static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}