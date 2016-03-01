package com.training.lft.touchgesture.multitouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import timber.log.Timber;

/**
 * Created by laaptu on 12/14/15.
 */
public class FirstView extends View {
    public static final String TAG = "FirstView";

    public Rect mainRect;

    public Paint mainPaint;

    public int minimumSize = 500;

    public FirstView(Context context) {
        this(context, null);
    }

    public FirstView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FirstView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        mainRect = new Rect();
        mainPaint = new Paint();
        mainPaint.setColor(getBackgroundColor());
        mainPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(
                resolveSize(minimumSize, widthMeasureSpec),
                resolveSize(minimumSize, heightMeasureSpec)
        );
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mainRect.set(getPaddingLeft(), getPaddingTop(),
                w - getPaddingRight(), h - getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mainRect, mainPaint);
    }

    public int getBackgroundColor() {
        return Color.BLUE;
    }

    /**
     * For Single touch, both event.getAction() and event.getActionMasked() return the
     * same value.
     * Event.getActionMasked() value is different in comparison to event.getAction()
     * in case of multitouch
     * ####
     * When we only use event.Action() for switch case , we won't be able to get
     * what other touch events are registered.
     * Meaning ACTION_POINTER_DOWN : indicates that there is another
     * finger involved in touch i.e. there are two or more fingers now
     * But this information can't be extracted from event.getAction()
     * So, it is hard to know for other touch events .
     * We can only get the other finger involved if we use
     * event.getAction() & MotionEvent.ACTION_MASK or simply
     * event.getActionMasked();
     * ####
     * event.getAction() contains information packed : a motion type and pointer info
     * To get the pointer info we need to mask or extract it out using
     * event.getAction() & ACTION_MASK
     * but if we use event.getActionMasked() we are able to identify the pointer info
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Timber.d("Event.getAction() =%d", event.getAction());
        //Timber.d("Event.getActionMasked() =%d", event.getActionMasked());
        //Timber.d("ACTION_UP =%d", MotionEvent.ACTION_UP);
        gettingInfoFromPointer(event);
        return true;
    }

    public static final String ACTION_DOWN = "ACTION_DOWN",
            ACTION_POINTER_DOWN = "ACTION_POINTER_DOWN",
            ACTION_UP = "ACTION_UP", ACTION_POINTER_UP = "ACTION_POINTER_UP",
            ACTION_MOVE = "ACTION_MOVE", ACTION_CANCEL = "ACTION_CANCEL";

    /**
     * Now when multitouch occurs ( look for the same concept on single touch as well)
     * Pointers store information: Need to understand what is pointer index and id
     * Index = in which location pointer is present ( maybe in some array)
     * id = unique id of the pointer, this is needed to identify which finger
     * ####
     */
    private void gettingInfoFromPointer(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                //logPointerIndex("ACTION_DOWN", event);
                logOnlyTouchedPointer(ACTION_DOWN, event);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                //logPointerIndex("ACTION_POINTER_DOWN", event);
                logOnlyTouchedPointer(ACTION_POINTER_DOWN, event);
                break;
            case MotionEvent.ACTION_MOVE:
                //Timber.d("ACTION_MOVE: Pointer Id =%d",event.getActionIndex());
                break;
            case MotionEvent.ACTION_POINTER_UP:
                //logPointerIndex("ACTION_POINTER_UP", event);
                logOnlyTouchedPointer(ACTION_POINTER_UP, event);
                break;
            case MotionEvent.ACTION_UP:
                //logPointerIndex("ACTION_UP", event);
                logOnlyTouchedPointer(ACTION_UP, event);
                break;
            case MotionEvent.ACTION_CANCEL:
                //logPointerIndex("ACTION_CANCEL", event);
                logOnlyTouchedPointer(ACTION_CANCEL, event);
                break;
        }
    }

    /**
     * ####
     * to get the pointer Id
     * we need to do bitwise right shifting to get the pointer index
     * then from pointer index  we can easily find out the pointer Id
     * but we only need pointer index to get X and y values
     * http://stackoverflow.com/questions/6517494/get-motionevent-getrawx-getrawy-of-other-pointers
     */
    private void logOnlyTouchedPointer(String value, MotionEvent event) {
        Timber.d("******************************");
        //this is enough for getting the id
        int pointerIndex = event.getAction() >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        //this also return the same value as above
        //int pointerIndex = event.getActionIndex();
        //int pointerIndex1 = (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        //Timber.d("Pointer 1: Pointer2 =%d:%d", pointerIndex, pointerIndex1);
        int pointerId = event.getPointerId(pointerIndex);
        final float xPos = event.getX(pointerIndex);
        final float yPos = event.getY(pointerIndex);
        Timber.d("%s: PointerIndex =%d,Pointer Id =%d", value, pointerIndex, pointerId);
        Timber.d("X:Y position =%f:%f", xPos,yPos);
        int[] locationInScreen = new int[2];
        getLocationOnScreen(locationInScreen);
        Timber.d("Location In Screen Raw X:Y =%f:%f", locationInScreen[0]+xPos, locationInScreen[1]+yPos);
        //Timber.d("Raw X:Y position =%f:%f", )
        Timber.d("################################");

    }

    /**
     * logPointerIndex(two touch)
     * finger A down = ACTION_DOWN : pointer index 0  id 0
     * finger B down = ACTION_POINTER_DOWN: pointer index 1  id 1
     * finger A up = ACTION_POINTER_UP: ( when this is executed though it shows two pointers,
     * after this execution, the pointer count will be 1 as it will be removed)
     * pointer index 0   id 0
     * finger B up = ACTION_UP:   pointer index 0  id 1
     * All event calculation value is extracted from pointer Index
     */
    private void logPointerIndex(String value, MotionEvent event) {
        Timber.d("**********************************");
        Timber.d("%s: Total Pointer count =%d ", value, event.getPointerCount());
        for (int i = 0; i < event.getPointerCount(); i++) {
            int index = i;
            int id = event.getPointerId(index);
            Timber.d("Pointer Index =%d and Id = %d", index, id);
            Timber.d(" X and Y =%f and %f", event.getX(index), event.getY(index));
        }
        Timber.d("#############################");
    }

    /**
     * How event is registered on multitouch
     * 1. ACTION_DOWN
     * 2. ACTION_POINTER_DOWN (for other fingers)
     * 3. ACTION_POINTER_DOWN
     * ### now once we begin to lift up finger
     * 4. ACTION_POINTER_UP
     * 5. ACTION_POINTER_UP
     * 6. ACTION_UP
     * It doesn't mean order is preserved, meaning
     * if A finger is down first = ACTION_DOWN
     * B finger is down  = ACTION_POINTER_DOWN
     * C finger is down  = ACTION_POINTER_DOWN
     * A finger us up = ACTION_POINTER_UP
     * C finger is up = ACTION_POINTER_UP
     * B finger is up = ACTION_UP
     * ####
     * http://stackoverflow.com/questions/13710967/android-multitouch-and-getactionmasked
     * http://stackoverflow.com/questions/17384983/android-what-is-the-difference-between-getaction-and-getactionmasked-in-moti
     */
    private void diffBetweenGetActionAndGetActionMasked(MotionEvent event) {
        //switch(event.getAction()){
        //switch (event.getAction() & MotionEvent.ACTION_MASK) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Timber.d("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Timber.d("ACTION_POINTER_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Timber.d("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Timber.d("ACTION_POINTER_UP");
                break;
            case MotionEvent.ACTION_UP:
                Timber.d("ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Timber.d("ACTION_CANCEL");
                break;
        }
    }
}
