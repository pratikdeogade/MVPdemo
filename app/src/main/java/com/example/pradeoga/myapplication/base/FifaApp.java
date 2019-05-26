package com.example.pradeoga.myapplication.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v7.app.AppCompatDelegate;

import com.example.pradeoga.myapplication.BuildConfig;
import com.example.pradeoga.myapplication.di.app.AppModule;
import com.example.pradeoga.myapplication.di.app.DaggerAppComponent;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

public class FifaApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mActivityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        initDependencyInjection();
        initTimber();
        initConnectionManager();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initConnectionManager() {
        final IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new ServiceConnectivityManager(), intentFilter);
    }

    private void initDependencyInjection() {
        DaggerAppComponent.builder().create(this).inject(this);
    }
}
