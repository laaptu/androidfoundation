package com.lft.training.servicebasics;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by laaptu on 8/30/15.
 */
public class BoundServiceDemo extends Service {

    private final IBinder customLocalBinder = new CustomLocalBinder();
    private final String TAG = "BoundServiceDemo";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println(TAG + " @onBind");
        return customLocalBinder;
    }

    public int getCount() {
        int count = 0;
        int sum = 0;
        while (count < 10) {
            sum += count;
            count++;
        }

        return sum;
    }

    public class CustomLocalBinder extends Binder {

        BoundServiceDemo getBindingService() {
            System.out.println(TAG + " @CustomLocalBinder @getBindingService()");
            return BoundServiceDemo.this;
        }

    }
}
