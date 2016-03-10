package com.lft.espressointro.chiuki.idling;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lft.espressointro.R;
import com.lft.espressointro.ResourceUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by laaptu on 3/8/16.
 */
public class IdlingActivity extends AppCompatActivity {

    public static final String URL = "https://httpbin.org/delay/10";
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idling);
        textView = (TextView) findViewById(R.id.info_txt);
    }

    public void btnClick(View view) {
        AsyncHttpClient  asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.setTimeout(15000);

        asyncHttpClient.get(this, URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                onResult(ResourceUtils.getString(R.string.info_load_success));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                onResult(ResourceUtils.getString(R.string.info_load_failure));

            }
        });

    }

    private void onResult(String messsage) {
        textView.setText(messsage);

    }
}
