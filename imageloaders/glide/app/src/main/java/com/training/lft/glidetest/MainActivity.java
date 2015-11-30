package com.training.lft.glidetest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    RequestManager requestManager;

    private String[] imageUrls = {
            "http://organicthemes.com/demo/profile/files/2012/12/profile_img.png"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.img_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initGlide();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImage();
            }
        });
        Timber.tag("MainActivity");
        //here first is for string and if it gets some param used for others
        Timber.d("sometest");
        String someName = "SomeName",otherName="hello";
        Timber.tag("MainActivity1").d("This is argument test %-12s%-12s",otherName,someName);

        System.out.println("Hello");
        Log.i("Hello","Hello1");


        goToSimpleGlideTest();

    }

    @OnClick({R.id.btn_simple_glide})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple_glide:
                goToSimpleGlideTest();
                break;
        }
    }

    private void goToSimpleGlideTest() {
        Intent intent = new Intent(this, SimpleGlideTest.class);
        startActivity(intent);
        this.finish();
    }

    private void initGlide() {
        requestManager = Glide.with(this);

    }

    private void loadImage() {

//https:github.com/bumptech/glide/wiki/Transformations
        requestManager.load(imageUrls[0])
                .override(150, 150)
                //.placeholder(android.R.drawable.progress_indeterminate_horizontal)
                .animate(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left))


                //.transform(new TestTransformation(this))
                .into(imageView);


    }

    private class TestTransformation extends BitmapTransformation {
        public TestTransformation(Context context) {
            super(context);
        }


        @Override
        protected Bitmap transform(BitmapPool mBitmapPool, Bitmap source, int outWidth, int outHeight) {
            outWidth = 300;
            outHeight = 300;
            Bitmap result = mBitmapPool.get(outWidth, outHeight, Bitmap.Config.ARGB_8888);
            if (result == null)
                result = Bitmap.createBitmap(outWidth, outHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            BitmapShader bitmapShader = new BitmapShader(Bitmap.createScaledBitmap(source, outWidth, outHeight, false), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(bitmapShader);


            //canvas.drawRoundRect(0, 0, outWidth, outHeight, 50, 50, paint);
            canvas.drawRoundRect(new RectF(0, 0, outWidth, outHeight), 50, 50, paint);
            return result;
        }

        @Override
        public String getId() {
            //this if constant, the transformation will always be saved, so that later the transformation need not to be done
            return String.valueOf(Math.random() * 1000);
            //return "SquareTransformation5";
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
