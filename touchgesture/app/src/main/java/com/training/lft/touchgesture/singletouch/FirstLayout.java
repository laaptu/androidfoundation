package com.training.lft.touchgesture.singletouch;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by laaptu on 12/14/15.
 */
public class FirstLayout extends FrameLayout {
    public static final String TAG = "FirstLayout";

    public FirstLayout(Context context) {
        this(context, null);
    }

    public FirstLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FirstLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setBackgroundColor(Color.RED);
    }
}
