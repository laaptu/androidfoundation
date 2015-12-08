package com.lft.training.customviewbasic;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by laaptu on 11/30/15.
 */
public class DataHolder {

    public static final String ON_MEASURE_BEFORE = "onMeasure()->before";
    public static final String ON_MEASURE_AFTER = "onMeasure()->after";
    public static final String ON_LAYOUT_BEFORE = "onLayout()->before";
    public static final String ON_LAYOUT_AFTER = "onLayout()->after";
    public static final String ON_DRAW_BEFORE = "onDraw()->before";
    public static final String ON_DRAW_AFTER = "onDraw()->after";
    public static final String ON_DRAW="onDraw()";
    public static final String DISPATCH_DRAW_BEFORE = "dispatchDraw()->before";
    public static final String DISPATCH_DRAW_AFTER = "dispatchDraw()->after";
    public static final String SEPARATOR_START = "--------------------------------------------";
    public static final String SEPARATOR_END = "**********************************************";
    public static final String UNSPECIFIED = "UNSPECIFIED", AT_MOST = "AT_MOST", EXACTLY = "EXACTLY";
    public static final String VIEW_MODE = "ViewMode", VIEW_SIZE = "ViewSizeInPx";

    private static int[] screenDimen = new int[2];

    public static int[] getScreenDimension() {
        if (screenDimen[0] == 0) {
            WindowManager windowManager = (WindowManager) MainApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            screenDimen[0] = displayMetrics.widthPixels;
            screenDimen[1] = displayMetrics.heightPixels;
        }
        return screenDimen;
    }

    public static ViewSize findViewSize(int sizeSpec) {
        ViewSize viewSize = new ViewSize();
        int mode = View.MeasureSpec.getMode(sizeSpec);
        int size = View.MeasureSpec.getSize(sizeSpec);
        viewSize.viewSizeInPx = size;
        switch (mode) {
            case View.MeasureSpec.UNSPECIFIED:
                viewSize.viewMode = UNSPECIFIED;
                break;
            case View.MeasureSpec.AT_MOST:
                viewSize.viewMode = AT_MOST;
                break;
            case View.MeasureSpec.EXACTLY:
                viewSize.viewMode = EXACTLY;
                break;
        }


        return viewSize;
    }


}
