package com.training.lft.touchgesture;

import android.view.MotionEvent;

/**
 * Created by laaptu on 12/14/15.
 */
public class DataHolder {

    public static final String DISPATCH_TOUCH_BEFORE = "dispatchTouch()-->before :",
            DISPATCH_TOUCH_AFTER = "dispatchTouch()-->after :",
            INTERCEPT_TOUCH_BEFORE = "interceptTouch()-->before :", INTERCEPT_TOUCH_AFTER = "interceptTouch()-->after :", ON_TOUCH_EVENT = "onTouchEvent() :", SEPARATOR_START = "**********************************", SEPARATOR_END = "####################################";

    public static String getMotionEvent(MotionEvent event) {
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
                break
            case MotionEvent.ACTION_UP:
                motionEvent = "ACTION_UP";
                break;

        }
        return motionEvent;
    }
}
