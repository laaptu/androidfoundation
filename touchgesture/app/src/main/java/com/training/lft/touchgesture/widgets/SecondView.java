package com.training.lft.touchgesture.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

/**
 * Created by laaptu on 12/14/15.
 */
public class SecondView extends FirstView {
    public SecondView(Context context) {
        super(context);
    }

    public SecondView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SecondView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getBackgroundColor() {
        return Color.YELLOW;
    }
}
