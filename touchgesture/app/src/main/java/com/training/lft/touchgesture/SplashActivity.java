package com.training.lft.touchgesture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.training.lft.touchgesture.multitouch.MultiTouchExampleActivity;
import com.training.lft.touchgesture.multitouchimplfirst.MultiTouchCircleActivity;
import com.training.lft.touchgesture.singletouch.SingleTouchActivity;
import com.training.lft.touchgesture.touchintercept.InterceptTouchActivity;
import com.training.lft.touchgesture.touchpropagation.TouchPropagationActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //understandBasicMutliTouch();
        multitouchCircleImplementation();
    }

    private void understandHowTouchPropagates() {
        startActivity(new Intent(this, TouchPropagationActivity.class));
        this.finish();
    }

    private void understandHowTouchXYWorks() {
        startActivity(new Intent(this, SingleTouchActivity.class));
        this.finish();
    }

    private void understandHowTouchInterceptWorks() {
        startActivity(new Intent(this, InterceptTouchActivity.class));
        this.finish();
    }

    private void understandBasicMutliTouch() {
        startActivity(new Intent(this, MultiTouchExampleActivity.class));
        this.finish();
    }

    private void multitouchCircleImplementation() {
        startActivity(new Intent(this, MultiTouchCircleActivity.class));
        this.finish();
    }
}
