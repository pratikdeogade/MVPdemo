package com.example.pradeoga.myapplication.network.imagedownloader;

import android.content.Context;
import android.widget.ImageView;

import com.example.pradeoga.myapplication.base.FifaApp;
import com.example.pradeoga.myapplication.utils.NetworkUtils;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import timber.log.Timber;


public class PicassoImageLoader implements ImageDownloader {

    private final Context mContext;
    private final Picasso mPicasso;

    public PicassoImageLoader(final FifaApp context) {
        mContext = context;
        final Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.downloader(new OkHttp3Downloader(context, NetworkUtils.CACHE_50MB));
        builder.listener((picasso, uri, exception) -> Timber.e(exception, "URL: %s", uri.toString()));
        mPicasso = builder.build();
        Picasso.setSingletonInstance(mPicasso);

    }


    @Override
    public void loadImage(String url, ImageView imageView, OnImageDownloadListener listener) {

    }

    @Override
    public void loadImage(String url, ImageView imageView, OnImageDownloadListener listener, ImageSize imageSize) {

    }

    @Override
    public void getCachedBitmap(String url, Target target) {

    }
}
