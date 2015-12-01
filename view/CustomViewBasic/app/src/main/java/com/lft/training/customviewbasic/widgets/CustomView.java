package com.lft.training.customviewbasic.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.lft.training.customviewbasic.DataHolder;
import com.lft.training.customviewbasic.ViewSize;

import timber.log.Timber;

/**
 * Created by laaptu on 11/30/15.
 */
public class CustomView extends View {

    private final Rect rect = new Rect();
    private final Paint paint = new Paint();
    private int minimumSize = 200;

    ViewSize viewSize;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        // rect.set(0, 0, getWidth(), getWidth());
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Timber.d(DataHolder.ON_MEASURE_BEFORE + " (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Timber.d(DataHolder.ON_MEASURE_AFTER + " (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Timber.d(DataHolder.ON_MEASURE_BEFORE + " (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
        viewSize = DataHolder.findViewSize(widthMeasureSpec);
        Timber.d(DataHolder.VIEW_MODE + " :: " + DataHolder.VIEW_SIZE + " = %s :: %d", viewSize.viewMode, viewSize.viewSizeInPx);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewSize = DataHolder.findViewSize(widthMeasureSpec);
        //setMeasuredDimension(viewSize.viewSizeInPx,viewSize.viewSizeInPx);
        //setMeasuredDimension(200, 200);
        int width = findAppropriateSize(widthMeasureSpec, minimumSize);
        int height = findAppropriateSize(heightMeasureSpec, minimumSize);
        setMeasuredDimension(width, height);
        //this is also the helper method which does some calculation to find the size
        //resolveSize(minimumSize,widthMeasureSpec);

        Timber.d(DataHolder.ON_MEASURE_AFTER + " (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
        Timber.d(DataHolder.VIEW_MODE + " :: " + DataHolder.VIEW_SIZE + " = %s :: %d", viewSize.viewMode, viewSize.viewSizeInPx);

    }

    private int findAppropriateSize(int measureSpec, int minimumSize) {
        int mainSize = 0;
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
                //mainSize = Math.max(size, minimumSize);
                mainSize = size;
                break;
            case MeasureSpec.AT_MOST:
                mainSize = Math.max(size, minimumSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                mainSize = minimumSize;
                break;
        }
        return mainSize;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //rect.set(0, 0, w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        rect.set(0, 0, getWidth(), getHeight());
        canvas.drawRect(rect, paint);
    }
}
