package com.timecat.xposed;

import android.util.Log;

import com.time.cat.BuildConfig;

import de.robv.android.xposed.XposedBridge;


public class Logger {

    private static boolean sEnable = BuildConfig.DEBUG;
    private static final String TAG = "XposedTimeCat";

    public static void e(String tag, String msg) {
        if (sEnable) {
            Log.e(TAG + ":" + tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (sEnable) {
            Log.d(TAG + ":" + tag, msg);
            XposedBridge.log(TAG + ":" + msg);
        }
    }

    public static void logClass(String tag, Class c) {
        if(sEnable) {
            d(tag, "class: " + c.getName());
            if (c.getSuperclass() != null) {
                Log.e(TAG + ":" + tag, c.getCanonicalName());
            }
        }
    }
}
