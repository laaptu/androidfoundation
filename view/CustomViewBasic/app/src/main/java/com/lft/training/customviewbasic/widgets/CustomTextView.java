package com.lft.training.customviewbasic.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.lft.training.customviewbasic.DataHolder;

import timber.log.Timber;

/**
 * Created by laaptu on 11/30/15.
 */
public class CustomTextView extends TextView {
    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Timber.d(DataHolder.ON_MEASURE_BEFORE +" (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Timber.d(DataHolder.ON_MEASURE_AFTER+" (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
    }
}
