package com.lft.training.customviewbasic.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lft.training.customviewbasic.DataHolder;
import com.lft.training.customviewbasic.ViewSize;

import timber.log.Timber;

/**
 * Created by laaptu on 11/30/15.
 */
public class CustomTextView extends TextView {
    ViewSize viewSize;


    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        Timber.d(DataHolder.ON_MEASURE_BEFORE +" (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        Timber.d(DataHolder.ON_MEASURE_AFTER+" (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
//    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Timber.d(DataHolder.ON_MEASURE_BEFORE + " (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
        viewSize = DataHolder.findViewSize(widthMeasureSpec);
        Timber.d(DataHolder.VIEW_MODE + " :: " + DataHolder.VIEW_SIZE + " = %s :: %d", viewSize.viewMode, viewSize.viewSizeInPx);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewSize = DataHolder.findViewSize(widthMeasureSpec);
        Timber.d(DataHolder.ON_MEASURE_AFTER + " (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
        Timber.d(DataHolder.VIEW_MODE + " :: " + DataHolder.VIEW_SIZE + " = %s :: %d", viewSize.viewMode, viewSize.viewSizeInPx);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Timber.d(DataHolder.ON_LAYOUT_BEFORE + " left::top::right::bottom = %d::%d::%d::%d", l, t, r, b);
        super.onLayout(changed, l, t, r, b);
        Timber.d(DataHolder.ON_LAYOUT_AFTER + " left::top::right::bottom = %d::%d::%d::%d", l, t, r, b);
    }
}
