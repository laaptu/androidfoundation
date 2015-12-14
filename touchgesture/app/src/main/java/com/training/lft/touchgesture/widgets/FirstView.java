package com.training.lft.touchgesture.widgets;

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
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Timber.d(DataHolder.DISPATCH_TOUCH_BEFORE);
        boolean dispatch = super.dispatchTouchEvent(ev);
        Timber.d(DataHolder.DISPATCH_TOUCH_AFTER);
        return dispatch;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Timber.d(DataHolder.ON_TOUCH_EVENT);
        boolean onTouch = super.onTouchEvent(event);
        return onTouch;
    }
}
