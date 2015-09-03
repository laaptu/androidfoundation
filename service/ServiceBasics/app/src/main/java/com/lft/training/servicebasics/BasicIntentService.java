package com.lft.training.servicebasics;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lft.training.servicebasics.data.Extras;

/**
 * Created by laaptu on 8/31/15.
 */
public class BasicIntentService extends IntentService {

    private int count = 0;

    public static final String TAG = "BasicIntentService";

    public BasicIntentService() {
        super("BasicIntentService");
    }

    @Override
    public void onCreate() {
        System.out.println(TAG + " @onCreate()");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println(TAG + "@onDestroy()");
        super.onDestroy();
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        System.out.println(TAG + "@onHandleIntent");
        System.out.println("count = " + count);
        if (intent.hasExtra(Extras.DATA)) {
            startLoop(intent.getStringExtra(Extras.DATA));
        }
    }
}
