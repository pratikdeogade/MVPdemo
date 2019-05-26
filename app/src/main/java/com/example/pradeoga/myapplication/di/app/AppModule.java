package com.example.pradeoga.myapplication.di.app;

import android.app.Application;

import com.example.pradeoga.myapplication.base.FifaApp;
import com.example.pradeoga.myapplication.base.MainActivity;
import com.example.pradeoga.myapplication.base.MainActivityModule;
import com.example.pradeoga.myapplication.di.scope.PerActivity;
import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Module(includes = AndroidSupportInjectionModule.class)
public abstract class AppModule<T> {

    @Binds
    abstract Application application(FifaApp app);

    @PerActivity
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivityInjector();


}
