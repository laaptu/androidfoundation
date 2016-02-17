package com.lft.fileinputstreamtest;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by laaptu on 2/17/16.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
