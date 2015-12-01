package com.lft.training.customviewbasic;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

/**
 * Created by laaptu on 11/30/15.
 */
public class MainApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
        else
            Timber.plant(new ReleaseTree());
    }

    public static Context getContext() {
        return context;
    }

    static class ReleaseTree extends Timber.Tree {

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {

        }
    }
}
