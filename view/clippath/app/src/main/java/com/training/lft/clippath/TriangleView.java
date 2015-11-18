package com.training.lft.clippath;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * Created by laaptu on 11/19/15.
 */
public class TriangleView extends BasicView {
    public TriangleView(Context context) {
        super(context);
    }

    public TriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TriangleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.moveTo(mainRect.centerX(),mainRect.top);
        path.lineTo(mainRect.left,mainRect.bottom);
        path.lineTo(mainRect.right,mainRect.bottom);
        path.close();
        canvas.clipPath(path);
        canvas.drawBitmap(mainBitmap,null,mainRect,null);
    }
}
