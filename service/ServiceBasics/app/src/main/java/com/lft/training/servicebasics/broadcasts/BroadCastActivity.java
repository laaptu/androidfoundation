package com.lft.training.servicebasics.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.TextView;

import com.lft.training.servicebasics.R;
import com.lft.training.servicebasics.base.BaseActivity;
import com.lft.training.servicebasics.data.Extras;

import butterknife.Bind;

public class BroadCastActivity extends BaseActivity {


    @Bind(R.id.info_txt)
    TextView infoText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_broad_cast;
    }

    private DownloadReceiver downloadReceiver;
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        downloadReceiver = new DownloadReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(Extras.EVENT_DOWNLOADCOMPLETE);
        intentFilter.addAction(Extras.EVENT_DOWNLOADHUDAICHA);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //LocalBroadcastManager.getInstance(this).registerReceiver(downloadReceiver, intentFilter);
        registerReceiver(downloadReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //LocalBroadcastManager.getInstance(this).unregisterReceiver(downloadReceiver);
        unregisterReceiver(downloadReceiver);
    }

    public void broadCastEvent(View view) {
        System.out.println("@activity : sendbroadcast");
        //sendLocalBroadcast();
        //sendGlobalBroadcast();
        startDownload();
    }

    private void startDownload() {
        Intent intent = new Intent(this, DownloadService.class);
        intent.putStringArrayListExtra(Extras.DATA, Extras.URL_LIST);
        startService(intent);

    }

    public void broadCastEvent1(View view) {
        //sendLocalBroadcast();
        //sendGlobalBroadcast();
        Intent intent = new Intent(Extras.EVENT_DOWNLOADHUDAICHA);
        //LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        sendBroadcast(intent);
    }

    private void sendGlobalBroadcast() {
        Intent intent = new Intent(Extras.EVENT_DOWNLOADCOMPLETE);
        //intent =new Intent();
        //    intent.setAction(Extras.EVENT_DOWNLOADCOMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        //sendBroadcast(intent);
    }

    private void sendLocalBroadcast() {
        Intent intent = new Intent(Extras.EVENT_DOWNLOADCOMPLETE);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    public class DownloadReceiver extends BroadcastReceiver {

        private static final String TAG = "DownloadReceiver";

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println(TAG + " @onReceive " + intent.getAction());
            if (intent.getAction().equals(Extras.EVENT_DOWNLOADCOMPLETE)) {
                String data = intent.getStringExtra(Extras.DATA);
                String url = intent.getStringExtra(Extras.URL);
                System.out.println(TAG + " url = " + url);
                infoText.append("\n\n url: " + url + "\n\ndata: " + data);
            }


        }
    }
}
