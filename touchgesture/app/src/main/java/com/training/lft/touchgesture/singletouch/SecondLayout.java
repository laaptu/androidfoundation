package com.training.lft.touchgesture.singletouch;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.training.lft.touchgesture.DataHolder;

/**
 * Created by laaptu on 12/14/15.
 */
public class SecondLayout extends FrameLayout {
    public static final String TAG = "SecondLayout";

    public SecondLayout(Context context) {
        this(context, null);
    }

    public SecondLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecondLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.GREEN);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        DataHolder.printEventXY(TAG,event,this);
        return true;
    }
}
