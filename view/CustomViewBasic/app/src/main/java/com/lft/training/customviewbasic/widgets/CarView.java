package com.lft.training.customviewbasic.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import timber.log.Timber;

/**
 * Created by laaptu on 12/9/15.
 */
public class CarView extends View {
    private int minimumDimension = 500;
    private final Rect mainRect = new Rect();
    private final Rect bodyRect = new Rect();
    private final Rect frontTyreRect = new Rect();
    private final Rect backTyreRect = new Rect();
    private final Rect frontHoodRect = new Rect();
    private final Rect backHoodRect = new Rect();
    private final Rect mainBodyRect = new Rect();

    private final Paint bodyPaint = new Paint();
    private final Paint tyrePaint = new Paint();

    private final Paint carTextPaint = new Paint();

    public CarView(Context context) {
        this(context, null);
    }

    public CarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bodyPaint.setColor(Color.RED);
        bodyPaint.setStyle(Paint.Style.FILL);
        tyrePaint.setColor(Color.BLACK);
        tyrePaint.setStyle(Paint.Style.STROKE);
        tyrePaint.setStrokeWidth(20);

        carTextPaint.setColor(Color.WHITE);
        carTextPaint.setTextSize(80);
        carTextPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(resolveSize(minimumDimension, widthMeasureSpec),
//                resolveSize(minimumDimension, heightMeasureSpec));
        setMeasuredDimension(findAppropriateSize(minimumDimension, widthMeasureSpec),
                findAppropriateSize(minimumDimension, heightMeasureSpec));
    }

    private int findAppropriateSize(int measureSpec, int minimumSize) {
        int mainSize = 0;
        int mode = View.MeasureSpec.getMode(measureSpec);
        int size = View.MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
                //mainSize = Math.max(size, minimumSize);
                mainSize = size;
                break;
            case MeasureSpec.AT_MOST:
                mainSize = Math.max(size, minimumSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                mainSize = minimumSize;
                break;
        }
        return mainSize;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mainRect.set(getPaddingLeft(), getPaddingTop(), w - getPaddingRight(), h - getPaddingBottom());
        bodyRect.set(mainRect.left, mainRect.top, mainRect.right, mainRect.height() / 2);
        Timber.d("BodyRect Width  %d", bodyRect.width());

        frontHoodRect.set(bodyRect.left, bodyRect.height() / 2, (int) (bodyRect.width() * 0.3), bodyRect.bottom);
        mainBodyRect.set(frontHoodRect.right, bodyRect.top, frontHoodRect.right + (int) (bodyRect.width() * 0.4), bodyRect.bottom);
        backHoodRect.set(mainBodyRect.right, bodyRect.height() / 2, mainBodyRect.right + (int) (bodyRect.width() * 0.3), bodyRect.bottom);


        frontTyreRect.set(frontHoodRect.left, frontHoodRect.bottom, frontHoodRect.right, frontHoodRect.bottom + (int) (bodyRect.width() * 0.3));
        backTyreRect.set(backHoodRect.left, backHoodRect.bottom, backHoodRect.right, frontHoodRect.bottom + (int) (bodyRect.width() * 0.3));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(new RectF(frontTyreRect), 0, 360, false, tyrePaint);
        canvas.drawArc(new RectF(backTyreRect), 0, 360, false, tyrePaint);
        canvas.drawRect(frontHoodRect, bodyPaint);
        canvas.drawRect(mainBodyRect, bodyPaint);
        canvas.drawRect(backHoodRect, bodyPaint);
        canvas.drawText("Ferrari",mainBodyRect.centerX(),mainBodyRect.centerY(),carTextPaint);

//        canvas.drawRect(frontHoodRect, bodyPaint);
//        canvas.drawRect(backHoodRect, bodyPaint);
//        canvas.drawRect(mainBodyRect, bodyPaint);
    }
}
