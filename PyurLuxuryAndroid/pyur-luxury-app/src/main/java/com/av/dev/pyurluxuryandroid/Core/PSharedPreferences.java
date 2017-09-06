package com.av.dev.pyurluxuryandroid.Core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by CodeSyaona on 24/08/2017.
 */

public class PSharedPreferences {

    private static SharedPreferences mSharedPreferences;
    private static Context mContext;
    private static final String APP_PREFS= "APP_SETTINGS";

    private PSharedPreferences() {}

    public static void init(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    public static String getSomeStringValue(Context context, String key) {
        return mSharedPreferences.getString(key , "");
    }

    public static void setSomeStringValue(Context context, String key, String newValue) {
        final SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key , newValue);
        editor.apply();
    }

    public static void clearAllPreferences() {
        mSharedPreferences.edit().clear().apply();
    }

}
