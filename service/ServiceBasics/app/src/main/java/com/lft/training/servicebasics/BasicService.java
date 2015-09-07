package com.lft.training.servicebasics;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.lft.training.servicebasics.bound.BindService;
import com.lft.training.servicebasics.data.Extras;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

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

        } else if (intent.hasExtra(Extras.ULR_REGION)) {
            url = intent.getStringExtra(Extras.ULR_REGION);
            Intent intent1 = new Intent(this, BindService.class);
            bindService(intent1, newServiceConnection, BIND_AUTO_CREATE);

        } else {
            System.out.println("INTENT HAS NO DATA");
        }

        return START_STICKY;
    }

    private String url;
    private ServiceConnection newServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println(TAG + "@onServiceConnected");
            BindService.FirstBinder firstBinder = (BindService.FirstBinder) service;
            firstBinder.downloadContent(url, responseHandler);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println(TAG + "@onServiceDisconnected");

        }
    };

    private AsyncHttpResponseHandler responseHandler = new JsonHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, org.apache.http.Header[] headers, JSONObject response) {
            onDataFetch(true, response.toString());
            super.onSuccess(statusCode, headers, response);
        }

        @Override
        public void onFailure(int statusCode, org.apache.http.Header[] headers, String responseString, Throwable throwable) {
            onDataFetch(false, responseString);
            super.onFailure(statusCode, headers, responseString, throwable);
        }
    };

    private void onDataFetch(boolean success, String response) {
        System.out.println(TAG + (success ? " @onSuccess" : "@onFailure ") + response);
        unbindService(newServiceConnection);
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
