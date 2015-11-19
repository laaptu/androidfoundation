package com.training.lft.clippath;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

//http://stackoverflow.com/questions/22499964/android-semi-circle-progress-bar

public class SemiCircleProgressBarView extends View {

    private Path mClippingPath;
    private Context mContext;
    private Bitmap mBitmap;
    private float mPivotX;
    private float mPivotY;
    private Paint somePaint;

    public SemiCircleProgressBarView(Context context) {
        super(context);
        mContext = context;
        initilizeImage();
    }

    public SemiCircleProgressBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initilizeImage();
    }

    private void initilizeImage() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mClippingPath = new Path();

        somePaint = new Paint();
        somePaint.setColor(Color.RED);
        somePaint.setStyle(Paint.Style.FILL);

        //Top left coordinates of image. Give appropriate values depending on the position you wnat image to be placed
        mPivotX = getScreenGridUnit();
        mPivotY = 0;

        //Adjust the image size to support different screen sizes
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.circle);
        int imageWidth = (int) (getScreenGridUnit() * 30);
        int imageHeight = (int) (getScreenGridUnit() * 30);
        imageWidth = 450;
        imageHeight = 450;
        mBitmap = Bitmap.createScaledBitmap(bitmap, imageWidth, imageHeight, false);
    }

    public void setClipping(float progress) {

        //Convert the progress in range of 0 to 100 to angle in range of 0 180. Easy math.
        float angle = (progress * 360) / 100;
        mClippingPath.reset();
        //Define a rectangle containing the image
        RectF oval = new RectF(mPivotX, mPivotY, mPivotX + mBitmap.getWidth(), mPivotY + mBitmap.getHeight());
        //Move the current position to center of rect
        mClippingPath.moveTo(oval.centerX(), oval.centerY());
        //Draw an arc from center to given angle
        mClippingPath.addArc(oval, 180, angle);
        //Draw a line from end of arc to center
        mClippingPath.lineTo(oval.centerX(), oval.centerY());
        //Redraw the canvas
        invalidate();
    }

    private int progress = 0;


    public void setProgress() {
        mClippingPath.reset();
        progress=0;
        handler.removeMessages(0);
        handler.sendEmptyMessage(0);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (progress < 100) {
                progress++;
                setClipping(progress);
                sendEmptyMessageDelayed(0, 1);
            } else {
                removeMessages(0);
            }

        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Clip the canvas
        canvas.clipPath(mClippingPath);
        canvas.drawBitmap(mBitmap, mPivotX, mPivotY, null);

    }

    private float getScreenGridUnit() {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels / 32;
    }
}
