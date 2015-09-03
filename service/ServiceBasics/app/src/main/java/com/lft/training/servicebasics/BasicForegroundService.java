package com.lft.training.servicebasics;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;

import com.lft.training.servicebasics.data.Extras;

import java.util.ArrayList;

/**
 * Created by laaptu on 9/3/15.
 */
public class BasicForegroundService extends Service {


    private static final String TAG = "BasicForegroundService";

    @Override
    public void onCreate() {
        someArrList = new ArrayList<>();
        System.out.println(TAG + " @onCreate()");
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.hasExtra(Extras.DATA)) {
            String playlist = intent.getStringExtra(Extras.DATA);
            System.out.println(TAG + " @onStartCommand playlist: " + playlist);
//            if (playlist.equals("ThirdCall")) {
//                stop();
//            } else {
            playMusic(playlist);

//            }
        } else {
            System.out.println(TAG + " @onStartCommand playist: ");
        }

        return START_STICKY;
    }

    private void playMusic(String playlist) {
        //https://gist.github.com/kristopherjohnson/6211176
        // Create intent that will bring our app to the front, as if it was tapped in the app
        // launcher
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                simulateLowMemory();

            }
        }, 100);
        Intent intent = new Intent(this, BasicServiceActivity.class);
        intent.setAction(Intent.ACTION_MAIN);
        intent.setAction(Intent.CATEGORY_LAUNCHER);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification =
                new NotificationCompat.Builder(this).setContentTitle("Playing")
                        .setContentText(playlist)
                        .setSmallIcon(R.drawable.ic_stat_av_play_circle_outline)
                        .setWhen(System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .build();

        //notification = new NotificationCompat.Builder(this).build();
        //http://stackoverflow.com/questions/10962418/startforeground-without-showing-notification
        //startForeground(0,notification)
        //100 = notification ID, give any int you want
        startForeground(100, notification);
    }

    @Override
    public void onDestroy() {
        //stop();
        System.out.println(TAG + " @onDestroy");
        super.onDestroy();
    }

    private void stop() {
        System.out.println(TAG + " @stop()");
        stopForeground(false);
    }


    private ArrayList<Integer> someArrList;

    private void simulateLowMemory() {
        for (int i = 0; i < 999999; i++)
            someArrList.add(new Integer(i));
    }
}
