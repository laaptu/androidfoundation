package com.training.lft.touchgesture.multitouchimplfirst;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

/**
 * Created by laaptu on 12/14/15.
 */
public class FirstView extends View {
    public static final String TAG = "FirstView";

    public Rect mainRect;

    public Paint mainPaint;

    public int minimumSize = 500;
    private final int radius = 150;

    private HashMap<Integer, PointPaint> pointFHashMap;

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

        pointFHashMap = new HashMap<>();
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
        //canvas.drawRect(mainRect, mainPaint);
        for (Map.Entry<Integer, PointPaint> pointPaintEntry : pointFHashMap.entrySet()) {
            canvas.drawCircle(
                    pointPaintEntry.getValue().pointF.x,
                    pointPaintEntry.getValue().pointF.y,
                    radius,
                    pointPaintEntry.getValue().paint
            );
        }
    }

    public int getBackgroundColor() {
        return Color.BLUE;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gettingInfoFromPointer(event);
        return true;
    }

    public static final String ACTION_DOWN = "ACTION_DOWN",
            ACTION_POINTER_DOWN = "ACTION_POINTER_DOWN",
            ACTION_UP = "ACTION_UP", ACTION_POINTER_UP = "ACTION_POINTER_UP",
            ACTION_MOVE = "ACTION_MOVE", ACTION_CANCEL = "ACTION_CANCEL";

    private void gettingInfoFromPointer(MotionEvent event) {
        int pointerIndex = event.getAction() >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
        int actionIndex = event.getActionIndex();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                Timber.d(ACTION_DOWN);
                Timber.d("Pointer Index =%d ,Action Index=%d", pointerIndex, actionIndex);
                //register
                pointFHashMap.put(event.getPointerId(pointerIndex), new PointPaint(event.getX(pointerIndex), event.getY(pointerIndex)));
                break;
            case MotionEvent.ACTION_MOVE:
                Timber.d(ACTION_MOVE);
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int pointerId = event.getPointerId(i);
                    pointFHashMap.get(pointerId).setPoints(event.getX(i),event.getY(i));
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                Timber.d(ACTION_UP);
                Timber.d("Pointer Index =%d ,Action Index=%d", pointerIndex, actionIndex);
                pointFHashMap.remove(event.getPointerId(pointerIndex));
                //remove
                break;
        }
        invalidate();
    }


}
