package com.lft.espressointro.chiuki.toolbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lft.espressointro.R;

public class ToolbarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.my_title);
    }
}