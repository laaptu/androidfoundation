package com.lft.training.servicebasics.bound;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.lft.training.servicebasics.BasicService;
import com.lft.training.servicebasics.BasicServiceActivity;
import com.lft.training.servicebasics.R;
import com.lft.training.servicebasics.base.BaseActivity;
import com.lft.training.servicebasics.data.Extras;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

/**
 * Created by laaptu on 9/4/15.
 */
public class BindServiceActivity extends BaseActivity {

    private static final String TAG = "BindServiceActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_bindservice;
    }

    public void serviceBind(View view) {
        Intent intent = new Intent(this, BindService.class);
        bindService(intent, activityServiceConnection, Context.BIND_AUTO_CREATE);

//        Intent inten1 = new Intent(this, BasicService.class);
//        inten1.putExtra(Extras.ULR_            REGION, Extras.ULR_REGION);
//        startService(inten1);

    }

    public void serviceNormal(View view) {
        Intent intent = new Intent(this, BindService.class);
        startService(intent);
    }

    public void serviceUnbind(View view) {
        //unbindService(activityServiceConnection);
        startActivity(new Intent(this, BasicServiceActivity.class));
        this.finish();
    }


    private BindService.FirstBinder firstBinder;

    private ServiceConnection activityServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println(TAG + " @onServiceConnected " + name.getShortClassName());
            firstBinder = (BindService.FirstBinder) service;
            firstBinder.downloadContent(Extras.URL_VENUE, responseHandler);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println(TAG + " @onServiceDisconnected " + name.getShortClassName());
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

    }
}
