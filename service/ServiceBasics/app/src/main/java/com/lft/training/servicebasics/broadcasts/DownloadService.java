package com.lft.training.servicebasics.broadcasts;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.lft.training.servicebasics.data.Extras;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by laaptu on 9/4/15.
 */
public class DownloadService extends Service {

    private static final String TAG = "DownloadService";

    private AsyncHttpClient asyncHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        asyncHttpClient = new AsyncHttpClient();
        System.out.println(TAG + " @onCreate()");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println(TAG + " @onStartCommand()");
        startDownload(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void startDownload(Intent intent) {
        if (intent == null || !intent.hasExtra(Extras.DATA))
            stopSelf();
        ArrayList<String> urlList = intent.getStringArrayListExtra(Extras.DATA);
        for (String url : urlList) {
            CustomResponseHandler1 handler = new CustomResponseHandler1(url);
            asyncHttpClient.get(url, handler);
        }

    }

    public void onFetchSuccess(boolean success, String url, String result) {
        Intent intent = new Intent(Extras.EVENT_DOWNLOADCOMPLETE);
        intent.putExtra(Extras.DATA, result);
        intent.putExtra(Extras.URL, url);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }


    public class CustomResponseHandler1 extends JsonHttpResponseHandler {
        private String url;

        public CustomResponseHandler1(String url) {
            this.url = url;
        }

        @Override
        public void onSuccess(int statusCode, org.apache.http.Header[] headers, JSONObject response) {
            onFetchSuccess(true, url, response.toString());
            super.onSuccess(statusCode, headers, response);
        }

        @Override
        public void onFailure(int statusCode, org.apache.http.Header[] headers, String responseString, Throwable throwable) {
            onFetchSuccess(false, url, responseString);
            super.onFailure(statusCode, headers, responseString, throwable);
        }
    }


}
