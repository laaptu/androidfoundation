package com.training.lft.touchgesture.singletouch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.training.lft.touchgesture.R;


/**
 * First show the coordinates like
 * 1: where 0,0 starts and where it ends and how status bar and action bar are taking the space as well
 * 2: Illustrate view getLeft(),right() ,top() and bottom() and what value does it take on
 * 3: Illustrate what x,y values are giving you and how you need to correspond it to the global value
 */
public class SingleTouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
