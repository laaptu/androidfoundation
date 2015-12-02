package com.lft.training.customviewbasic.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.lft.training.customviewbasic.DataHolder;
import com.lft.training.customviewbasic.ViewSize;

import timber.log.Timber;

/**
 * Created by laaptu on 11/30/15.
 * 1. Make a custom linearlayout ,add custombutton,customtextview,customview and illustrate that first onMeasure() is called
 * from parent and it finally ends @ parent as well i.e. it cycles all its child
 * 2. Use different layout width and height to find what params are stored in widthMeasureSpec
 * a. CustomLinearLayout: match_parent,match_parent, all view : wrap_content, wrap_content
 * 3. Explain why setMeasuredDimension is important on custom view, with example of scrollview
 * Under scrollview add custom linear layout and other views mainly custom  view and show the difference between
 * custom view with setMeasuredDimension and without it
 * 4. SetMeasuredDimension is like telling that you will take that much space and will notify the parent, then parent
 * will make necessary changes and gives new dimension or size other view wants to take
 * 5. Also make necessary changes on how to identify the size on Custom View , the size is identified by the mode and spec
 * About Size now depends upon your requirement. So as per requirement you can now manipulate the size
 * <p/>
 * Second Class onLayout()
 * 1. Add a log on onLayout() to validate the idea that after onMeasure(), onLayout will be called
 * 2. Create a custom viewgroup with different rectangles and behave them like a linearlayout horizontal now move on to package
 *   layoutmodule
 */
public class CustomLinearLayout extends LinearLayout {
    private static final String TAG = "CustomLinearLayout";
    ViewSize viewSize;

    public CustomLinearLayout(Context context) {
        this(context, null);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(LinearLayout.HORIZONTAL);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Timber.d(DataHolder.SEPARATOR_START);
        Timber.d(DataHolder.ON_MEASURE_BEFORE + " (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
        viewSize = DataHolder.findViewSize(widthMeasureSpec);
        Timber.d(DataHolder.VIEW_MODE + " :: " + DataHolder.VIEW_SIZE + " = %s :: %d", viewSize.viewMode, viewSize.viewSizeInPx);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewSize = DataHolder.findViewSize(widthMeasureSpec);
        Timber.d(DataHolder.ON_MEASURE_AFTER + " (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
        Timber.d(DataHolder.VIEW_MODE + " :: " + DataHolder.VIEW_SIZE + " = %s :: %d", viewSize.viewMode, viewSize.viewSizeInPx);
        Timber.d(DataHolder.SEPARATOR_END);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Timber.d(DataHolder.SEPARATOR_START);
        Timber.d(DataHolder.ON_LAYOUT_BEFORE + " left::top::right::bottom = %d::%d::%d::%d", l, t, r, b);
        super.onLayout(changed, l, t, r, b);
        Timber.d(DataHolder.ON_LAYOUT_AFTER + " left::top::right::bottom = %d::%d::%d::%d", l, t, r, b);
        Timber.d(DataHolder.SEPARATOR_END);
    }
}
