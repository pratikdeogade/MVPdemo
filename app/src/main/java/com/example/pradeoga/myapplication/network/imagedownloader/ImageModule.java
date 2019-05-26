package com.example.pradeoga.myapplication.network.imagedownloader;

import com.example.pradeoga.myapplication.base.FifaApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

    @Singleton
    @Provides
    static ImageDownloader provideImageLoader(final FifaApp application) {
        return new PicassoImageLoader(application);
    }
}
