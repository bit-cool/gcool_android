package com.example.lenovo.myapplication.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.example.lenovo.myapplication.base.BaseApplication;

/**
 * Created by lenovo on 2016/4/23.
 */
public class UIUtils {

    public static Context getContext() {
        return BaseApplication.getContext();
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    public static Thread getMainThread() {
        return BaseApplication.getMainThread();
    }

    public static Handler getMainHandler() {
        return BaseApplication.getMainHandler();
    }

    public static void post(Runnable task) {
        getMainHandler().post(task);
    }

    public static void postDelayed(Runnable task, long delayMillis) {
        getMainHandler().postDelayed(task, delayMillis);
    }

    public static void removeCallbacks(Runnable task) {
        getMainHandler().removeCallbacks(task);
    }

    public static String getPackageName() {
        return getContext().getPackageName();
    }

    public static int dip2px(int dip) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dip * scale + .5f);
    }

    public static int dx2dip(int px) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (px / scale + .5f);
    }

    public static int getScreenWidth() {
        Log.e("test",String.valueOf(getContext()));
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }
}
