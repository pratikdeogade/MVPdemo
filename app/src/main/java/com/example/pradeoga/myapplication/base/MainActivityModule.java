package com.example.pradeoga.myapplication.base;

import android.app.Activity;
import android.support.v4.app.FragmentManager;

import com.example.pradeoga.myapplication.MainNavigator;
import com.example.pradeoga.myapplication.Navigator;
import com.example.pradeoga.myapplication.analytics.FirebaseTracker;
import com.example.pradeoga.myapplication.analytics.Tracker;
import com.example.pradeoga.myapplication.di.scope.PerActivity;
import com.example.pradeoga.myapplication.network.ApiService;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module(includes = BaseActivityModule.class)
public abstract class MainActivityModule {

    @Binds
    @PerActivity
    abstract Activity activity(final MainActivity mainActivity);



    @Provides
    @PerActivity
    static Navigator provideNavigator(final FragmentManager fragmentManager, final Activity activity ,final ApiService apiService,
                                      final Tracker tracker) {
        return new MainNavigator(fragmentManager, activity,apiService,tracker);
    }

    @Provides
    @PerActivity
    static Tracker provideAnalytics(final FifaApp application) {
        return new FirebaseTracker(application);
    }

}