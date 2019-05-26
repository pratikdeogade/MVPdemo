package com.example.pradeoga.myapplication.di.app;

import com.example.pradeoga.myapplication.base.FifaApp;
import com.example.pradeoga.myapplication.network.NetworkModule;
import com.example.pradeoga.myapplication.network.imagedownloader.ImageModule;


import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;

@Singleton
@Component(modules = {AppModule.class,NetworkModule.class, ImageModule.class})
public interface AppComponent extends AndroidInjector<FifaApp> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<FifaApp> {
    }
}