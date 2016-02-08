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
        DataHolder.getMotionEvent(TAG, ev, DataHolder.DISPATCH_TOUCH, dispatch);
        //boolean dispatch = true;
        Timber.d(DataHolder.DISPATCH_TOUCH_AFTER);
        return dispatch;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Timber.d(DataHolder.INTERCEPT_TOUCH_BEFORE);
        boolean intercept = super.onInterceptTouchEvent(ev);
        //intercept = true;
        DataHolder.getMotionEvent(TAG, ev, DataHolder.INTERCEPT_TOUCH, intercept);
        Timber.d(DataHolder.INTERCEPT_TOUCH_AFTER);
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Timber.d(DataHolder.ON_TOUCH_EVENT);
        boolean onTouch = super.onTouchEvent(event);
        /**if intercept =true, it comes here and if it wants to consume the touch, it must say yes*
         * here, else no other touch event is given  to it after DOWN and again touch is given
         * after finger is lifted up
         */

        //onTouch=true;
        DataHolder.getMotionEvent(TAG, event, DataHolder.CONSUME_TOUCH, onTouch);
        return onTouch;
    }
}
