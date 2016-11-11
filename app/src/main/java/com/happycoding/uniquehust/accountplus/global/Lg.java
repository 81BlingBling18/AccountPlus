package com.happycoding.uniquehust.accountplus.global;

import android.support.compat.BuildConfig;
import android.util.Log;

/**
 * Created by qimeng on 16-11-5.
 */

public class Lg {
    private static String TAG = "holo";
    public static void d(String message) {
        if(!BuildConfig.DEBUG)
        Log.d(TAG, message);
    }
}
