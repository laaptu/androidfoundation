package com.training.lft.touchgesture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.training.lft.touchgesture.singletouch.SingleTouchActivity;
import com.training.lft.touchgesture.touchintercept.InterceptTouchActivity;
import com.training.lft.touchgesture.touchpropagation.TouchPropagationActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        goToModule2();
    }

    private void goToModule1() {
        startActivity(new Intent(this, TouchPropagationActivity.class));
        this.finish();
    }

    private void goToModule3() {
        startActivity(new Intent(this, SingleTouchActivity.class));
        this.finish();
    }

    private void goToModule2() {
        startActivity(new Intent(this, InterceptTouchActivity.class));
        this.finish();
    }
}
