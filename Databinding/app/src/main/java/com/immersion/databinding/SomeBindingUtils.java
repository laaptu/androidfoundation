package com.immersion.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.immersion.databinding.databinders.MeroImageCallback;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by laaptu on 1/5/16.
 */
public class SomeBindingUtils {

    @BindingAdapter({"bind:imageDeu", "bind:laCallbackPaniHalidim"})
    public static void thenLoadImage(ImageView imageView, final String imageUrl, final MeroImageCallback callback) {
        callback.onImageLoading(imageUrl);
        Picasso.with(imageView.getContext()).load(imageUrl).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                callback.onImageLoaded(imageUrl);
            }

            @Override
            public void onError() {
                callback.onImageLoadError(imageUrl);
            }
        });
    }
}
