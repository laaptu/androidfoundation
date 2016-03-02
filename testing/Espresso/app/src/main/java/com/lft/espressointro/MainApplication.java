package com.lft.espressointro;

import android.app.Application;
import android.content.Context;

/**
 * Created by laaptu on 3/2/16.
 */
public class MainApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
