package com.training.lft.touchgesture;

import android.view.MotionEvent;

import timber.log.Timber;

/**
 * Created by laaptu on 12/14/15.
 */
public class DataHolder {

    public static final String DISPATCH_TOUCH_BEFORE = "dispatchTouch()-->before :",
            DISPATCH_TOUCH_AFTER = "dispatchTouch()-->after :",
            INTERCEPT_TOUCH_BEFORE = "interceptTouch()-->before :", INTERCEPT_TOUCH_AFTER = "interceptTouch()-->after :", ON_TOUCH_EVENT = "onTouchEvent() :", SEPARATOR_START = "**********************************", SEPARATOR_END = "####################################";
    public static final String CONSUME_TOUCH = "ConsumeTouch", DISPATCH_TOUCH = "DispatchTouch", INTERCEPT_TOUCH = "InterceptTouch";

    public static String getMotionEvent(String tag, MotionEvent event, String touchConsume, boolean consumeTouch) {
        String motionEvent = "";
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                motionEvent = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                motionEvent = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_POINTER_UP:
                motionEvent = "ACTION_POINTER_UP";
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                motionEvent = "ACTION_POINTER_DOWN";
                break;
            case MotionEvent.ACTION_UP:
                motionEvent = "ACTION_UP";
                break;

        }
        Timber.tag(tag).d("MotionEvent = %s, %s = %b", motionEvent, touchConsume, consumeTouch);
        return motionEvent;
    }

    public static final String ACTION_DOWN = "ACTION_DOWN", ACTION_MOVE = "ACTION_MOVE", ACTION_UP = "ACTION_UP", ACTION_CANCEL = "ACTION_CANCEL";
}
