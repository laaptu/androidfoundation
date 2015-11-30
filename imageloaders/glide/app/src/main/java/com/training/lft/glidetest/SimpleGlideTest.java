package com.training.lft.glidetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.Bind;
import butterknife.ButterKnife;
import timber.log.Timber;

public class SimpleGlideTest extends AppCompatActivity {

    private RequestManager requestManager;

    @Bind(R.id.img_fix)
    ImageView imgFixedDimen;

    @Bind(R.id.img_wrap)
    ImageView imgWrapDimen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_glide_test);
        ButterKnife.bind(this);
//        //Seems like there need to be only one tag in activity or class where we call Timber for log
//        Timber.tag("SimpleGlideActivity");
//
//        //string value
//        Log.d("Some Tag","String value");
//        Timber.d("THis is first add string %s ,add integer value %d","hello",10);
//        Timber.log(10,"hello");
//        //Log.e("Print)
//        System.out.println("sfdljksad");

        requestManager = Glide.with(this);
        Timber.e("Some error %s ","hello");
        Timber.d("Hello");
        simpleLoad();

    }

    private void simpleLoad() {

        /**
         * While doing resize (override ) for fixed size views i.e width=height=x dp
         * it resizes the image to that dimension and then stretches to fit the width and height
         * Further centerCrop and fitCenter also doesn't seem to work here. So in a fixed size,
         * it downloads the images and tries to fit it with height or width ( height > width)
         * and if same dimension( height =width) simply stretches height and width*/
        //requestManager.load(DataHolder.urlList[1]).override(10, 10).into(imgFixedDimen);
        //this only thing that seems working with simple load into imageview for fixed size
        requestManager.load(DataHolder.urlList[2]).into(imgFixedDimen);
        //simple loading
        //requestManager.load(DataHolder.urlList[0]).into(imgWrapDimen);
        //loading with resize
        requestManager.load(DataHolder.urlList[0]).override(200, 200).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                Timber.d("Image downloaded eror %s ", model);
                Timber.e("Some error %s ",model);
                //Timber.log(GlideTestApplication.SomeTree.LOW_PRIORITY, "SimpleGlideTest", "failure", null);
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                //Timber.tag("OnResourceReady1");
                Timber.d("Image downloaded success %s ", model);
                Timber.e("Some success %s ",model);
                //Timber.log(GlideTestApplication.SomeTree.HIGH_PRIORITY, "SimpleGlideTest", "Success", null);

                imgWrapDimen.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                return false;
            }
        }).into(imgWrapDimen);

    }

    public void showlog(View view) {
        System.out.println("DEBUG DATA");
        for (String str : DataHolder.messageLogDebug) {
            System.out.println(str);
        }

        System.out.println("ERROR DATA");
        for (String str : DataHolder.messageLogError) {
            System.out.println(str);
        }
    }
}
