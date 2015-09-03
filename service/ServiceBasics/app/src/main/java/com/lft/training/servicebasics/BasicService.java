package com.lft.training.servicebasics;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lft.training.servicebasics.data.Extras;

import java.util.ArrayList;

/**
 * Created by laaptu on 8/31/15.
 */
public class BasicService extends Service {

    private int count = 0;

    public static final String TAG = "BasicService";

    @Override
    public void onCreate() {
        System.out.println(TAG + " @onCreate()");
        someArrList = new ArrayList<>();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println(TAG + "@onDestroy()");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(TAG + "@onStartCommand");
        System.out.println("count = " + count);
        if (intent.hasExtra(Extras.DATA)) {
            System.out.println("INTENT DATA " + intent.getStringExtra(Extras.DATA));
            //startLoop(intent.getStringExtra(Extras.DATA));
            String data = intent.getStringExtra(Extras.DATA);
            int delay = data.equals("FourthCall") ? 100 : 100;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    simulateLowMemory();

                }
            }, delay);
        } else {
            System.out.println("INTENT HAS NO DATA");
        }

        return START_STICKY;
    }

    private void startLoop(String value) {
        count++;
        if (value == null)
            return;
        int length = value.length();
        for (int i = 0; i < length; i++) {
            System.out.println("Char " + value.charAt(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<Integer> someArrList;

    private void simulateLowMemory() {
        for (int i = 0; i < 999999; i++)
            someArrList.add(new Integer(i));
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
