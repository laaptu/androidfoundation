package com.lft.training.servicebasics;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import com.lft.training.servicebasics.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by laaptu on 8/30/15.
 */
public class BoundServiceActivity extends BaseActivity {

    private BoundServiceDemo boundServiceDemo;
    private boolean isBound = false;

    @Bind(R.id.info_txt)
    TextView infoText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_boundservice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, BoundServiceDemo.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundServiceDemo.CustomLocalBinder customLocalBinder = (BoundServiceDemo.CustomLocalBinder) service;
            boundServiceDemo = customLocalBinder.getBindingService();
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    @OnClick({R.id.btn_bound})
    public void onClick(View view) {
        int count = boundServiceDemo.getCount();
        infoText.setText(String.valueOf(count));

    }
}
