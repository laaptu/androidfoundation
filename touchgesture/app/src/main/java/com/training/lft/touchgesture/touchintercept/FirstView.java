package com.training.lft.touchgesture.touchintercept;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.training.lft.touchgesture.DataHolder;

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


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Timber.d(DataHolder.ON_TOUCH_EVENT);
        boolean onTouch = super.onTouchEvent(event);
        /**
         * If we do this, onIntercept of parent will be only called for ON_DOWN,
         * after that for the particular touch sequence no parent's onIntercept() is called
         */
        //getParent().requestDisallowInterceptTouchEvent(true);
        //onTouch = true;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Timber.d(DataHolder.ACTION_DOWN);
                break;
            case MotionEvent.ACTION_UP:
                Timber.d(DataHolder.ACTION_UP);
                break;
            case MotionEvent.ACTION_MOVE:
                Timber.d(DataHolder.ACTION_MOVE);
                break;
            case MotionEvent.ACTION_CANCEL:
                Timber.d(DataHolder.ACTION_CANCEL);
                break;
        }
        return true;
    }
}
