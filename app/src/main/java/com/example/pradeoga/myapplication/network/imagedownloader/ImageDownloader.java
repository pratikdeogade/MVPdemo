package com.example.pradeoga.myapplication.network.imagedownloader;

import android.widget.ImageView;

import com.squareup.picasso.Target;


public interface ImageDownloader {

    enum ImageSize {
        THUMBNAIL,
        GALLERY,
        LARGE,
        DYNAMIC
    }

    interface OnImageDownloadListener {

        void onFailure();

        void onSuccess();
    }

    void loadImage(String url, ImageView imageView, OnImageDownloadListener listener);

    void loadImage(String url, ImageView imageView, OnImageDownloadListener listener, ImageSize imageSize);

    void getCachedBitmap(String url, Target target);
}
