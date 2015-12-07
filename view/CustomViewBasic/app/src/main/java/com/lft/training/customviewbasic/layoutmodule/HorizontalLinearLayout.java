package com.lft.training.customviewbasic.layoutmodule;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.lft.training.customviewbasic.DataHolder;
import com.lft.training.customviewbasic.ViewSize;

import timber.log.Timber;

/**
 * Created by laaptu on 12/3/15.
 * 1. First don't add anything on onMeasure() and onLayout() just simple logs and our previous views like
 * CustomButton,CustomView and CustomTextView. The user will see blank space and only , onMeasure() and onLayout()
 * log of this class
 * <p/>
 * 2. Now let us add some logic on onMeasure i.e. let us now call onMeasure to its children
 */
public class HorizontalLinearLayout extends ViewGroup {
    ViewSize viewSize;

    public HorizontalLinearLayout(Context context) {
        this(context, null);
    }

    public HorizontalLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Timber.d(DataHolder.SEPARATOR_START);
        Timber.d(DataHolder.ON_MEASURE_BEFORE + " (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
        viewSize = DataHolder.findViewSize(widthMeasureSpec);
        Timber.d(DataHolder.VIEW_MODE + " :: " + DataHolder.VIEW_SIZE + " = %s :: %d", viewSize.viewMode, viewSize.viewSizeInPx);
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Step 2
        //measureChildren(widthMeasureSpec, heightMeasureSpec);
        //Step 3
        ourOwnLogicForChildMeasure(widthMeasureSpec, heightMeasureSpec);
        //we also need to set our width and height so that it can easily lay upon its parent
        setMeasuredDimension(
                resolveSize(parentWidth, widthMeasureSpec),
                resolveSize(parentHeight, heightMeasureSpec));
        viewSize = DataHolder.findViewSize(widthMeasureSpec);
        Timber.d(DataHolder.ON_MEASURE_AFTER + " (widthMeasureSpec :: heightMeasureSpec  = %d :: %d)", widthMeasureSpec, heightMeasureSpec);
        Timber.d(DataHolder.VIEW_MODE + " :: " + DataHolder.VIEW_SIZE + " = %s :: %d", viewSize.viewMode, viewSize.viewSizeInPx);
        Timber.d(DataHolder.SEPARATOR_END);
    }

    int parentWidth = 0, parentHeight = 0;

    private void ourOwnLogicForChildMeasure(int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        //we are ignoring padding and margin of view right now
        int childPadding = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams layoutParams = child.getLayoutParams();
            int makeChildWithSpec = getChildMeasureSpec(parentWidthMeasureSpec,
                    childPadding, layoutParams.width);

            int makeChildHeightSpec = getChildMeasureSpec(parentHeightMeasureSpec, childPadding, layoutParams.height);
            child.measure(makeChildWithSpec, makeChildHeightSpec);
            measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
            //measureChildWithMargins(child, parentWidthMeasureSpec, parentWidth, parentHeightMeasureSpec, parentHeight);
            Timber.d("Child %d width = %d ", i, child.getMeasuredWidth());
            parentWidth += child.getMeasuredWidth();
            parentHeight = Math.max(child.getMeasuredHeight(), parentHeight);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Timber.d(DataHolder.SEPARATOR_START);
        Timber.d(DataHolder.ON_LAYOUT_BEFORE + " left::top::right::bottom = %d::%d::%d::%d", l, t, r, b);
        int xPos = 0, xPos1 = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(l + xPos, t, child.getMeasuredWidth() + xPos, child.getMeasuredHeight());
            xPos += child.getMeasuredWidth();
        }
        Timber.d(DataHolder.ON_LAYOUT_AFTER + " left::top::right::bottom = %d::%d::%d::%d", l, t, r, b);
        Timber.d(DataHolder.SEPARATOR_END);
    }

//    @Override
//    public LayoutParams generateLayoutParams(AttributeSet attrs) {
//        return new MarginLayoutParams(getContext(), attrs);
//    }
//
//    @Override
//    protected LayoutParams generateDefaultLayoutParams() {
//        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//    }

//    @Override
//    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
//        final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
//
//
//        final int childWidthMeasureSpec = getChildMeasureSpec(parentWidthMeasureSpec,
//                lp.leftMargin + lp.rightMargin
//                        + widthUsed, lp.width);
//        final int childHeightMeasureSpec = getChildMeasureSpec(parentHeightMeasureSpec,
//                lp.topMargin + lp.bottomMargin
//                        + heightUsed, lp.height);
//
//        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
//    }
}
