package com.training.lft.clippath;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by laaptu on 11/19/15.
 */
public class BasicView extends View {

    public Rect mainRect;
    public Path path;
    public Bitmap mainBitmap;
    public int dimension=450;

    public BasicView(Context context) {
        this(context, null);
    }

    public BasicView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        mainRect = new Rect();
        path = new Path();
        mainBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.profile_pic);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(dimension,dimension);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mainRect.set(getPaddingLeft(), getPaddingTop(), w - getPaddingRight(), h - getPaddingBottom());
    }
}
