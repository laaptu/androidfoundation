package com.training.lft.myapplication;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by laaptu on 12/10/15.
 */
public class MainApplication extends Application {

    private static MainApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.DebugTree());

        //Perform Injectsion

    }

    public static MainApplication getInstance() {
        return instance;
    }
}
