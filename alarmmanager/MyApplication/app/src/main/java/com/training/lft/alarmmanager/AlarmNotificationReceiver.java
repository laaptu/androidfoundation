package com.training.lft.alarmmanager;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

/**
 * Created by laaptu on 11/23/15.
 */
public class AlarmNotificationReceiver extends BroadcastReceiver {

    private final Uri soundURI = Uri.parse("android:resource//com.training.lft.alarmmanager/" + R.raw.alarm_rooster);
    private final long[] vibratePattern = {0, 200, 100, 500};

    private Intent notificationIntent;
    private PendingIntent pendingIntent;
    private static final String TICKER_TEXT = "New alarm received";
    private static final String CONTENT_TILE = "New alarm title";


    @Override
    public void onReceive(Context context, Intent intent) {
        notificationIntent = new Intent(context, MainActivity.class);
        String message = "";
        if (intent.hasExtra(Extras.ALARM_MESSAGE)) {
            message = intent.getStringExtra(Extras.ALARM_MESSAGE);
            message += " :received at " + Extras.getCurrentTime();
        }

        System.out.println("Alarm Received at "+message);
        notificationIntent.putExtra(Extras.ALARM_MESSAGE, message);
        pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setTicker(TICKER_TEXT)
                .setSmallIcon(android.R.drawable.stat_sys_speakerphone)
                .setAutoCancel(true)
                .setContentTitle(CONTENT_TILE)
                .setWhen(System.currentTimeMillis())
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentIntent(pendingIntent);
                        //.setSound(soundURI)
                //.setVibrate(vibratePattern);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = (int) (Math.random() * 1000);
        notificationManager.notify(notificationId, notificationBuilder.build());

    }
}
