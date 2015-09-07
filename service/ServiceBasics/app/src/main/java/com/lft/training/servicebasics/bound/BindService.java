package com.lft.training.servicebasics.bound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Created by laaptu on 9/4/15.
 */
public class BindService extends Service {



    /**
     * This works only if the client and service are in the same application and process
     * The reason the service and client must be in the same application is so the client
     * can cast the returned object and properly call its APIs. The service and client
     * must also be in the same process, because this technique does not perform any
     * marshalling across processes.
     * <p/>
     * You can implement methods which will call methods in the service or the bound activity,
     * or just return the service itself within a binder.
     */
    public class FirstBinder extends Binder {

        AsyncHttpClient asyncHttpClient;

        public FirstBinder() {
            asyncHttpClient = new AsyncHttpClient();
        }

        public void downloadContent(String url, AsyncHttpResponseHandler handler) {
            asyncHttpClient.get(url, handler);
        }

        public Service getBindingService() {
            // do work calling the method of this service  or do all the work here in binder
            return BindService.this;
        }
    }

    private IBinder binder;


    @Override
    public void onCreate() {
        super.onCreate();
        binder = new FirstBinder();
        System.out.println(TAG + " @onCreate");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(TAG + " @onstartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    private static final String TAG = "BindService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println(TAG + " @onBind ");
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println(TAG + " @onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        System.out.println(TAG + " onRebind()");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        System.out.println(TAG + " @onDestroy ");
        super.onDestroy();
    }


}
