package com.immersion.databinding.databinders;

/**
 * Created by laaptu on 1/5/16.
 */
public interface MeroImageCallback {
    void onImageLoading(String imageUrl);

    void onImageLoaded(String imageUrl);

    void onImageLoadError(String imageUrl);
}
