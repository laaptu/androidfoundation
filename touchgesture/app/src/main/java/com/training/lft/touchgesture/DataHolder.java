package com.training.lft.touchgesture;

import android.view.MotionEvent;
import android.view.View;

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

    /**
     * Event propagation is always
     * DOWN
     * MOVE
     * UP/CANCEL
     * ####
     * Event getX,getY always print x and y relative to the view
     * i.e. it considers the view starting as 0,0. Meaning even
     * if the view is at middle of the screen and touch is
     * starting, the x and y is considered 0,0 from view starting
     * <p/>
     * #####
     * Where as getRawX() and getRawY() gives the value relative to
     * screen x, and y. Verify the rawX and rawY() values to the value
     * printed on developer options pointer show X and Y at status bar
     * <p/>
     * ###
     * If we need to convert the coordinate to in relative to its parent
     * i.e. we want to know where in the parent the x and y position are
     * then we need to do<p/>
     * view.getLeft() + event.getX() == XPos inside parent
     * view.getTop() +event.getY() == YPos inside parent
     * <p/>
     * view.getLeft() == distance between left edge of child to the left edge of parent
     * or to say how far the child is from parent's left edge
     * view.getTop() == distance between top edge of child to the top edge of parent
     * or to say how far the child is from the parent's top edge
     * view.getRight() == distance between child's right edge and paren't left edge
     * or to say how far the child's right edge is from parent's left edge
     * view.getBottom() == distance between child's bottom edge and parent's top edge
     * or to say how far the child's bottom edge is from parent's top edge.
     * <p/>
     * So all these measurements are always from parent's top and left to child's
     * left,top, right and bottom
     */
    public static void printEventXY(String tag, MotionEvent event, View view) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Timber.tag(tag).d("#######################");
//                Timber.tag(tag).d(ACTION_DOWN + " parent's coordinate  x=%f :: y=%f", view.getLeft() + event.getX(), view.getTop() + event.getY());
//                Timber.tag(tag).d(ACTION_DOWN + " raw  x=%f :: y=%f", event.getRawX(), event.getRawY());
                Timber.tag(tag).d(ACTION_DOWN + " x=%f :: y=%f", event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
//                Timber.tag(tag).d(ACTION_MOVE + " parent's coordinate x=%f :: y=%f", view.getLeft() + event.getX(), view.getTop() + event.getY());
//                Timber.tag(tag).d(ACTION_MOVE + " raw x=%f :: y=%f", event.getRawX(), event.getRawY());
                Timber.tag(tag).d(ACTION_MOVE + " x=%f :: y=%f", event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
//                Timber.tag(tag).d(ACTION_UP + " parent's coordinate  x=%f :: y=%f", view.getLeft() + event.getX(), view.getTop() + event.getY());
//                Timber.tag(tag).d(ACTION_UP + " raw x=%f :: y=%f", event.getRawX(), event.getRawY());
                Timber.tag(tag).d(ACTION_UP + " x=%f :: y=%f", event.getX(), event.getY());
                Timber.tag(tag).d("***************************");
                break;
            case MotionEvent.ACTION_CANCEL:
//                Timber.tag(tag).d(ACTION_CANCEL + " parent's coordinate x=%f :: y=%f", view.getLeft() + event.getRawX(), view.getTop() + event.getRawY());
//                Timber.tag(tag).d(ACTION_CANCEL + " raw x=%f :: y=%f", event.getRawX(), event.getRawY());
                Timber.tag(tag).d(ACTION_CANCEL + " x=%f :: y=%f", event.getX(), event.getY());
                Timber.tag(tag).d("***************************");
                break;
        }
    }

    public static final String ACTION_DOWN = "ACTION_DOWN", ACTION_MOVE = "ACTION_MOVE", ACTION_UP = "ACTION_UP", ACTION_CANCEL = "ACTION_CANCEL";
}
