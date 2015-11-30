package com.lft.training.customviewbasic;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by laaptu on 11/30/15.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
        else
            Timber.plant(new ReleaseTree());
    }

    static class ReleaseTree extends Timber.Tree {

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {

        }
    }
}
