package com.training.lft.touchgesture.touchpropagation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;

import com.training.lft.touchgesture.DataHolder;
import com.training.lft.touchgesture.R;

import timber.log.Timber;


/**
 * First show the coordinates like
 * 1: where 0,0 starts and where it ends and how status bar and action bar are taking the space as well
 * 2: Illustrate view getLeft(),right() ,top() and bottom() and what value does it take on
 * 3: Illustrate what x,y values are giving you and how you need to correspond it to the global value
 */
public class TouchPropagationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Timber.d(DataHolder.SEPARATOR_START);
        Timber.d(DataHolder.DISPATCH_TOUCH_BEFORE);
        boolean dispatch = super.dispatchTouchEvent(ev);
        Timber.d(DataHolder.DISPATCH_TOUCH_AFTER);
        Timber.d(DataHolder.SEPARATOR_END);
        return dispatch;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Timber.d(DataHolder.ON_TOUCH_EVENT);
        return super.onTouchEvent(event);
    }
}
