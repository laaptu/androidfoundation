package com.training.lft.alarmmanager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by laaptu on 11/23/15.
 */
public class Extras {

    public static final String ALARM_MESSAGE = "alarmMessage";

    public static final String TIME_FORMAT = "hh:mm:ss a";
    public static final String RTC = "RTC",RTC1="RTC1";
    public static final String RTC_WAKEUP = "RTC_WAKEUP";
    public static final String ELAPSED = "ELAPSED";
    public static final String ELAPSED_WAKEUP = "ELAPSED_WAKEUP";


    public static final String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return new SimpleDateFormat(TIME_FORMAT).format(calendar.getTime());
    }
}
