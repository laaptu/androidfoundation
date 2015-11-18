package com.training.lft.clippath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * Created by laaptu on 11/19/15.
 */
public class CircleView extends BasicView {
    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.addCircle(mainRect.centerX(), mainRect.centerY(), mainRect.width() / 2, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(mainBitmap, null, mainRect, null);
    }

}
