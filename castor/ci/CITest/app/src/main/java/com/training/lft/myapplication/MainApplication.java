package com.training.lft.myapplication;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by laaptu on 12/7/15.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
