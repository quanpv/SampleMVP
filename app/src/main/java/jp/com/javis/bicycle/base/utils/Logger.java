package jp.com.javis.bicycle.base.utils;

import android.util.Log;

/**
 * Created by QuanPV on 3/20/18.
 */

public class Logger {

    static String pri_log = "Bicycle::";
    public static final boolean DEVELOPER_MODE = true;

    public static void e(String TAG, String msg) {
        if(DEVELOPER_MODE) {
            Log.e(pri_log + TAG, msg);
        }
    }

    public static void v(String TAG, String msg) {
        if(DEVELOPER_MODE) {
            Log.v(pri_log + TAG, msg);
        }
    }

    public static void i(String TAG, String msg) {
        if(DEVELOPER_MODE) {
            Log.i(pri_log + TAG, msg);
        }
    }

    public static void w(String TAG, String msg) {
        if(DEVELOPER_MODE) {
            Log.w(pri_log + TAG, msg);
        }
    }

    public static void d(String TAG, String msg) {
        if(DEVELOPER_MODE) {
            Log.d(pri_log + TAG, msg);
        }
    }
}
