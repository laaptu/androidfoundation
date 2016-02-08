package com.training.lft.touchgesture.singletouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by laaptu on 12/14/15.
 */
public class SecondView extends View {

    public static final String TAG = "SecondView";

    public Rect mainRect;

    public Paint mainPaint;

    public int minimumSize = 500;

    public SecondView(Context context) {
        this(context, null);
    }

    public SecondView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecondView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    public int getBackgroundColor() {
        return Color.YELLOW;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
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
}
