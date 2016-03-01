package com.training.lft.touchgesture.singletouch;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.training.lft.touchgesture.DataHolder;

/**
 */
public class FirstLayout extends FrameLayout {
    public static final String TAG = "FirstLayout";

    public FirstLayout(Context context) {
        this(context, null);
    }

    public FirstLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FirstLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setBackgroundColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DataHolder.printEventXY(TAG,event,this);
        return true;
    }
}
