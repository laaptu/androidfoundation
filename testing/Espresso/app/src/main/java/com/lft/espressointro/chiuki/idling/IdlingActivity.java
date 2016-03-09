package com.lft.espressointro.chiuki.idling;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lft.espressointro.R;

/**
 * Created by laaptu on 3/8/16.
 */
public class IdlingActivity extends AppCompatActivity {

    public static final String URL = "https://httpbin.org/delay/10";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idling);
    }
}
