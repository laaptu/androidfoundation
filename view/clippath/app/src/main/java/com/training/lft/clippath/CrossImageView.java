package com.training.lft.clippath;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Using Clipping to add reveal effect for Animation of Cross ImageView or R.drawable.img_x.
 * Since clipping data is taken from pixel points, this class will only work for xxxhdpi devices or
 * to say image of res/drawable-xxxhdpi. For other devices, the points won't map correctly and will
 * show weird behaviour
 * * TODO: This and RightTickView have lot of thing in common, can be combined into single class or extend from same parent
 */
public class CrossImageView extends View {

    private final Rect mainRect = new Rect();
    private final Bitmap crossImageBitmap;
    private final Path path = new Path();
    private final AnimateHandler animateHandler;
    private static final int DELAY = 40;
    private int index = 0;
    private boolean sendBroadcast = false;
    //These points will change if image changes
    //TODO find efficient way to find these points
    /**
     * These points are like rectangle, meaning each Point[] forms a rectangle. So all combination of
     * rectangle gives the effect of revealing. This is a tedious process, so if any efficient method
     * or any mathematical formula if applicable, would make it more usable
     */
    private final ArrayList<Point[]> pathPointList = new ArrayList<Point[]>() {{
        //left cross points
        add(new Point[]{new Point(70, 30), new Point(30, 70), new Point(50, 95), new Point(95, 50)});
        add(new Point[]{new Point(95, 50), new Point(50, 95), new Point(65, 110), new Point(110, 65)});
        add(new Point[]{new Point(110, 65), new Point(65, 110), new Point(80, 120), new Point(120, 80)});

        add(new Point[]{new Point(120, 80), new Point(80, 120), new Point(104, 135), new Point(132, 110)});
        add(new Point[]{new Point(132, 110), new Point(104, 135), new Point(130, 160), new Point(153, 130)});


        add(new Point[]{new Point(153, 130), new Point(130, 160), new Point(150, 210), new Point(190, 165)});
        add(new Point[]{new Point(190, 165), new Point(150, 210), new Point(200, 250), new Point(250, 200)});

        //right cross points
        add(new Point[]{new Point(230, 70), new Point(180, 20), new Point(160, 40), new Point(215, 90)});
        add(new Point[]{new Point(215, 90), new Point(160, 40), new Point(140, 65), new Point(190, 110)});
        add(new Point[]{new Point(190, 110), new Point(140, 65), new Point(100, 110), new Point(145, 155)});
        add(new Point[]{new Point(145, 155), new Point(100, 110), new Point(75, 135), new Point(130, 185)});
        add(new Point[]{new Point(130, 185), new Point(75, 135), new Point(50, 150), new Point(100, 200)});

        add(new Point[]{new Point(100, 200), new Point(50, 150), new Point(30, 165), new Point(90, 215)});
        add(new Point[]{new Point(90, 215), new Point(30, 165), new Point(10, 200), new Point(70, 245)});


    }};

    public CrossImageView(Context context) {
        this(context, null);
    }

    public CrossImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Our main image is R.drawable.img_x and our AnimateHandler does all the process of revealing
     */
    public CrossImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        crossImageBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.img_x);
        animateHandler = new AnimateHandler(this);
    }

    /**
     * We are only concerned with the width and height of our image, which is the actual
     * width and height of this view as well
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(crossImageBitmap.getWidth(), crossImageBitmap.getHeight());
    }

    /**
     * Setting our rect size which is basically the size of our image width and height
     * excluding the padding it takes
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mainRect.set(getPaddingLeft(), getPaddingTop(), w - getPaddingRight(), h - getPaddingBottom());
    }

    /**
     * Initially our index is 0 and our path won't have any points in it
     * So it will be basically a blank view. But later when animateHandler comes into
     * play, the index will increase and it will successively add points from pathPointList
     * to our path. So this gives revealing effect
     */
    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        for (int i = 0; i < index; i++) {
            Point[] points = pathPointList.get(i);
            path.moveTo(points[0].x, points[0].y);
            for (int j = 1; j < points.length; j++) {
                path.lineTo(points[j].x, points[j].y);
            }
            path.close();
        }

        canvas.clipPath(path);
        canvas.drawBitmap(crossImageBitmap, null, mainRect, null);

    }

    /**
     * Method to initiate reveal animation using animateHandler
     */
    public void animateView() {
        index = 0;
        animateHandler.removeMessages(0);
        animateHandler.sendEmptyMessage(0);
        //start or play the haptic effect
        //playIVTEffect(HapticConstants.TRANSITION_FAILURE, 0, true);
    }

    /**
     * Method invoked when all revealing is done or to say index reaches the size
     * of pathPointList
     */
    private void broadcastAnimationComplete() {
        //end
        sendBroadcast = true;
        // broadcasting event to change fragment to CashEntryFragment
        //EventBusRegister.postEvent(new PaymentEvents.NavigateToLandingScreen());
    }


    private void playIVTEffect(String effectName, int uhlFallbackEffect, boolean play) {
        //EventBusRegister.postEvent(new PaymentEvents.IVTPlaybackEvent(effectName, uhlFallbackEffect, play));
        Log.d("TactilePayLog", "Non-number counting haptic calls: " + effectName + " being called");
    }

    /**
     * Handler responsible for increasing index to add points to our path
     * and as well as responsible for sending broadcast complete event
     */
    static class AnimateHandler extends Handler {

        private static final int ONE_SECOND_DELAY = 1500;
        private static final int BROADCAST_MSG = 10;
        private static final int SEND_MSG = 0;

        private final WeakReference<CrossImageView> crossImageViewWeakReference;

        public AnimateHandler(CrossImageView CrossImageView) {
            crossImageViewWeakReference = new WeakReference<>(CrossImageView);
        }

        @Override
        public void handleMessage(Message msg) {
            //simply increasing index for reveal effect
            if (crossImageViewWeakReference.get().pathPointList.size() > crossImageViewWeakReference.get().index) {
                crossImageViewWeakReference.get().index++;
                crossImageViewWeakReference.get().invalidate();
                sendEmptyMessageDelayed(SEND_MSG, CrossImageView.DELAY);
            } else {
                sendEmptyMessageDelayed(BROADCAST_MSG, ONE_SECOND_DELAY);
                removeMessages(SEND_MSG);
            }
            //sending broadcast event
            if (msg.what == BROADCAST_MSG) {
                crossImageViewWeakReference.get().broadcastAnimationComplete();
                removeMessages(BROADCAST_MSG);
            }
        }

        //cancel all messages
        public void removeAll() {
            removeMessages(SEND_MSG);
            removeMessages(BROADCAST_MSG);
        }


    }

    /**
     * Method to cancel animateHandler or detached from window
     * as well as stop playing any ivt effect
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        if (!sendBroadcast)
//            playIVTEffect(HapticConstants.TRANSITION_FAILURE, 0, false);
//        sendBroadcast = false;
        if (animateHandler != null) {
            animateHandler.removeAll();
        }
    }
}
