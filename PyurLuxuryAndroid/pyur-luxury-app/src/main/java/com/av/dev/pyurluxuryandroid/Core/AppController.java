package com.av.dev.pyurluxuryandroid.Core;

import android.app.Application;

import com.av.dev.pyurluxuryandroid.BuildConfig;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by CodeSyaona on 24/08/2017.
 */

public class AppController extends Application {

    private static AppController mInstance;
    public static synchronized AppController getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        if(!BuildConfig.DEBUG) {
            Fabric.with(this, new Crashlytics());
        }

        mInstance = this;
        PSharedPreferences.init(mInstance);


    }


}
