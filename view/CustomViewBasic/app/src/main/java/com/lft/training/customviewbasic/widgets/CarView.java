package com.lft.training.customviewbasic.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.lft.training.customviewbasic.DataHolder;

import java.lang.ref.WeakReference;

import timber.log.Timber;

/**
 * Created by laaptu on 12/9/15.
 * 1. Try to show animate with only one line executing like
 * index =1 draw Arc only
 * index =2 draw other only
 * to illustrate that once draw is called, the user is presented with a clean slate i.e. all other drawing are gone
 *
 * 2. Always remember that view considers the co-ordinates starting from 0,0 as view is taking its own coordinate
 * space. That position is not the screen coordinates. Meaning, once draw is called view own co-ordinate system starts
 *
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

    private int index = 0;
    private AnimateHandler animateHandler;

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

        animateHandler = new AnimateHandler(this);
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
        Timber.d(DataHolder.ON_SIZE_CHANGED+" left:top:right:bottom %d:%d:%d:%d", mainRect.left, mainRect.top, mainRect.right, mainRect.bottom);

        frontHoodRect.set(bodyRect.left, bodyRect.height() / 2, (int) (bodyRect.width() * 0.3), bodyRect.bottom);
        mainBodyRect.set(frontHoodRect.right, bodyRect.top, frontHoodRect.right + (int) (bodyRect.width() * 0.4), bodyRect.bottom);
        backHoodRect.set(mainBodyRect.right, bodyRect.height() / 2, mainBodyRect.right + (int) (bodyRect.width() * 0.3), bodyRect.bottom);


        frontTyreRect.set(frontHoodRect.left, frontHoodRect.bottom, frontHoodRect.right, frontHoodRect.bottom + (int) (bodyRect.width() * 0.3));
        backTyreRect.set(backHoodRect.left, backHoodRect.bottom, backHoodRect.right, frontHoodRect.bottom + (int) (bodyRect.width() * 0.3));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        animateDraw1(canvas);


//        canvas.drawRect(frontHoodRect, bodyPaint);
//        canvas.drawRect(backHoodRect, bodyPaint);
//        canvas.drawRect(mainBodyRect, bodyPaint);
    }

    private void normalDraw(Canvas canvas) {
        canvas.drawArc(new RectF(frontTyreRect), 0, 360, false, tyrePaint);
        canvas.drawArc(new RectF(backTyreRect), 0, 360, false, tyrePaint);
        canvas.drawRect(frontHoodRect, bodyPaint);
        canvas.drawRect(mainBodyRect, bodyPaint);
        canvas.drawRect(backHoodRect, bodyPaint);
        canvas.drawText("Ferrari", mainBodyRect.centerX(), mainBodyRect.centerY(), carTextPaint);
    }


    private void chechIndex() throws Exception {
        passedIndex++;
        if (passedIndex > index)
            throw new Exception("Break the line of code");
    }

    private int passedIndex;

    private void animateDraw1(Canvas canvas) {
        try {
            passedIndex = 0;
            chechIndex();
            canvas.drawArc(new RectF(frontTyreRect), 0, 360, false, tyrePaint);
            chechIndex();
            canvas.drawArc(new RectF(backTyreRect), 0, 360, false, tyrePaint);
            chechIndex();
            canvas.drawRect(frontHoodRect, bodyPaint);
            chechIndex();
            canvas.drawRect(mainBodyRect, bodyPaint);
            chechIndex();
            canvas.drawRect(backHoodRect, bodyPaint);
            chechIndex();
            canvas.drawText("Ferrari", mainBodyRect.centerX(), mainBodyRect.centerY(), carTextPaint);
        } catch (Exception e) {

        }
    }


    private void animateDraw(Canvas canvas) {
        if (index == 1)
            canvas.drawArc(new RectF(frontTyreRect), 0, 360, false, tyrePaint);
        if (index == 2) {
            canvas.drawArc(new RectF(backTyreRect), 0, 360, false, tyrePaint);
            canvas.drawArc(new RectF(frontTyreRect), 0, 360, false, tyrePaint);
        }
        if (index == 3) {
            canvas.drawArc(new RectF(frontTyreRect), 0, 360, false, tyrePaint);
            canvas.drawArc(new RectF(backTyreRect), 0, 360, false, tyrePaint);
            canvas.drawRect(frontHoodRect, bodyPaint);
        }

        if (index == 4) {
            canvas.drawArc(new RectF(frontTyreRect), 0, 360, false, tyrePaint);
            canvas.drawArc(new RectF(backTyreRect), 0, 360, false, tyrePaint);
            canvas.drawRect(frontHoodRect, bodyPaint);
            canvas.drawRect(mainBodyRect, bodyPaint);
        }

        if (index == 5) {
            canvas.drawArc(new RectF(frontTyreRect), 0, 360, false, tyrePaint);
            canvas.drawArc(new RectF(backTyreRect), 0, 360, false, tyrePaint);
            canvas.drawRect(frontHoodRect, bodyPaint);
            canvas.drawRect(mainBodyRect, bodyPaint);
            canvas.drawRect(backHoodRect, bodyPaint);
        }

        if (index == 6) {
            normalDraw(canvas);
        }

        //canvas.save();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                beginAnimate();
            }
        }, 800);
    }

    private void animateCanvas() {
        index++;
        invalidate();
        animateHandler.sendEmptyMessageDelayed(AnimateHandler.ANIMATE, 400);
    }

    private void beginAnimate() {
        animateHandler.removeAllMessages();
        animateHandler.sendEmptyMessage(AnimateHandler.ANIMATE);

    }

    static class AnimateHandler extends Handler {

        final WeakReference<CarView> carViewWeakReference;
        public static final int ANIMATE = 0x1, STOP_ANIMATE = 0x2;

        public AnimateHandler(CarView carView) {
            carViewWeakReference = new WeakReference<>(carView);
        }

        public void removeAllMessages() {
            removeMessages(ANIMATE);
            removeMessages(STOP_ANIMATE);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (carViewWeakReference.get().index >= 6 || msg.what == STOP_ANIMATE) {
                carViewWeakReference.get().index = 0;
                removeAllMessages();
                return;
            }
            carViewWeakReference.get().animateCanvas();
        }
    }
}
