package com.training.lft.touchgesture.touchintercept;

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
        intercept = consumeTouch(ev);
        Timber.d(DataHolder.INTERCEPT_TOUCH_AFTER);
        return intercept;
    }

    private boolean consumeTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Timber.d(DataHolder.ACTION_DOWN);
                return false;
            case MotionEvent.ACTION_UP:
                Timber.d(DataHolder.ACTION_UP);
                return true;
            case MotionEvent.ACTION_MOVE:
                Timber.d(DataHolder.ACTION_MOVE);
                return  true;
            case MotionEvent.ACTION_CANCEL:
                Timber.d(DataHolder.ACTION_CANCEL);
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Timber.d(DataHolder.ON_TOUCH_EVENT);
        boolean onTouch = super.onTouchEvent(event);
        onTouch = consumeTouch(event);
        return onTouch;
    }
}
