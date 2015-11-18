package com.training.lft.clippath;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;

/**
 * Created by laaptu on 11/19/15.
 */
public class RoundRectView extends BasicView {

    public RoundRectView(Context context) {
        super(context);
    }

    public RoundRectView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private BitmapShader bitmapShader;
    private Paint paint;
    private float radius = 40;

    @Override
    public void init(Context context) {
        super.init(context);
        //http://stackoverflow.com/questions/2459916/how-to-make-an-imageview-with-rounded-corners
        Bitmap bitmap = Bitmap.createScaledBitmap(mainBitmap, dimension, dimension, false);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(bitmapShader);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            path.addRoundRect(mainRect.left, mainRect.top, mainRect.right, mainRect.bottom, 40, 40, Path.Direction.CW);
            canvas.clipPath(path);
            canvas.drawBitmap(mainBitmap, null, mainRect, null);
        } else {
            canvas.drawRoundRect(new RectF(mainRect), radius, radius, paint);
        }
    }
}
