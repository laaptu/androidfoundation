package com.training.lft.touchgesture.multitouchimplfirst;

import android.graphics.Paint;
import android.graphics.PointF;

import java.util.Random;

/**
 * Created by laaptu on 3/1/16.
 */
public class PointPaint {
    public final Paint paint;
    public PointF pointF;

    public PointPaint() {
        paint = new Paint();
        Random random = new Random();
        paint.setARGB(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        paint.setStyle(Paint.Style.FILL);
    }

    public PointPaint(float x, float y) {
        this();
        setPoints(x, y);
    }

    public void setPoints(float x, float y) {
        if (pointF == null)
            pointF = new PointF();
        pointF.set(x, y);
    }

}
