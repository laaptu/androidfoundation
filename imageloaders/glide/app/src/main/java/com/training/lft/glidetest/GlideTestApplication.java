package com.training.lft.glidetest;

import android.app.Application;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import timber.log.Timber;

/**
 * Created by laaptu on 11/25/15.
 */
public class GlideTestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //need to find its usage
        //one usage is illustrated here
        //https://github.com/JakeWharton/timber/blob/master/timber-sample/src/main/java/com/example/timber/ExampleApp.java
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
        else
            Timber.plant(new SomeTree());

    }

    public class SomeTree extends Timber.Tree {

        public static final int HIGH_PRIORITY = 1, LOW_PRIORITY = 2;

        @Override
        protected void log(int priority, String tag, String message, Throwable t) {

            // Timber.d("This is from Som")

            if (priority == Log.DEBUG)
                DataHolder.messageLogDebug.add("Time : " + getCurrentTime() + "tag :: " + tag + ":: " + message);
            else if (priority == Log.ERROR)
                DataHolder.messageLogError.add("Time : " + getCurrentTime() + "tag :: " + tag + ":: " + message);


        }

        private String getCurrentTime() {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            return new SimpleDateFormat("hh:mm:ss a").format(calendar.getTime());
        }
    }
}
