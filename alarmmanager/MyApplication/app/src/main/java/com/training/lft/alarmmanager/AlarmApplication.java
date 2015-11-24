package com.training.lft.alarmmanager;

import android.app.AlarmManager;
import android.app.Application;
import android.content.Context;

/**
 * Created by laaptu on 11/23/15.
 */
public class AlarmApplication extends Application {

    private static Context context;
    private static AlarmManager alarmManager;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

    public static AlarmManager getAlarmManager() {
        if (alarmManager == null)
            alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        return alarmManager;
    }
}
