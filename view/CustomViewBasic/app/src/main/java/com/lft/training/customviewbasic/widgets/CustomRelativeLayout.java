package com.lft.training.customviewbasic.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.lft.training.customviewbasic.DataHolder;
import com.lft.training.customviewbasic.ViewSize;

import timber.log.Timber;

/**
 * Created by laaptu on 11/30/15.
 * 1. Make a custom linearlayout ,add custombutton,customtextview,customview and illustrate that first onMeasure() is called
 * from parent and it finally ends @ parent as well i.e. it cycles all its child
 * 2. Use different layout width and height to find what params are stored in widthMeasureSpec
 *   a. CustomLinearLayout: match_parent,match_parent, all view : wrap_content, wrap_content
 *
 */
public class CustomRelativeLayout extends RelativeLayout {
    private static final String TAG = "CustomLinearLayout";
    ViewSize viewSize;

    public CustomRelativeLayout(Context context) {
        this(context, null);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //setOrientation(LinearLayout.HORIZONTAL);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Timber.d(DataHolder.SEPARATOR_START);
        Timber.d(DataHolder.ON_MEASURE_BEFORE + " (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
        viewSize = DataHolder.findViewSize(widthMeasureSpec);
        Timber.d(DataHolder.VIEW_MODE+" :: "+DataHolder.VIEW_SIZE +" = %s :: %d",viewSize.viewMode,viewSize.viewSizeInPx);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewSize = DataHolder.findViewSize(widthMeasureSpec);
        Timber.d(DataHolder.ON_MEASURE_AFTER + " (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
        Timber.d(DataHolder.VIEW_MODE+" :: "+DataHolder.VIEW_SIZE +" = %s :: %d",viewSize.viewMode,viewSize.viewSizeInPx);
        Timber.d(DataHolder.SEPARATOR_END);
    }
}
