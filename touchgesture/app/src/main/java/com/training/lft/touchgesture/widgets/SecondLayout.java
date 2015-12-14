package com.training.lft.touchgesture.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.training.lft.touchgesture.DataHolder;

import timber.log.Timber;

/**
 * Created by laaptu on 12/14/15.
 */
public class SecondLayout extends FrameLayout {
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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Timber.d(DataHolder.DISPATCH_TOUCH_BEFORE);
        boolean dispatch = super.dispatchTouchEvent(ev);
        Timber.d(DataHolder.DISPATCH_TOUCH_AFTER);
        return dispatch;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Timber.d(DataHolder.INTERCEPT_TOUCH_BEFORE);
        boolean intercept = super.onInterceptTouchEvent(ev);
        Timber.d(DataHolder.INTERCEPT_TOUCH_AFTER);
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Timber.d(DataHolder.ON_TOUCH_EVENT);
        boolean onTouch = super.onTouchEvent(event);
        return onTouch;
    }
}
