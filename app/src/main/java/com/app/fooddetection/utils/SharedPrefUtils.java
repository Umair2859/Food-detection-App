package com.app.fooddetection.utils;

import android.app.Activity;
import android.content.SharedPreferences;

import com.app.fooddetection.info.Info;


public class SharedPrefUtils {

    public static void putStringSharedPrefs(Activity context, String value, String key) {
        SharedPreferences mPrefs = context.getSharedPreferences("SharedPrefs", 0);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringSharedPrefs(Activity context, String key) {
        SharedPreferences mPrefs = context.getSharedPreferences("SharedPrefs", 0);
        return mPrefs.getString(key, "");
    }

    public static String getToken(Activity context) {
        return "Token " + SharedPrefUtils.getStringSharedPrefs(context, Info.TOKEN);
    }
}
