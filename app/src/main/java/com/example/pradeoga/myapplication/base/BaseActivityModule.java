package com.example.pradeoga.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.pradeoga.myapplication.di.scope.PerActivity;
import com.example.pradeoga.myapplication.di.scope.PerFragment;
import com.example.pradeoga.myapplication.fixtures.FixtureFragment;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.FixtureDetailFragment;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.away.FixtureDetailAwayFragment;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.home.FixtureDetailHomeFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class BaseActivityModule {

    @Binds
    @PerActivity
    abstract Context activityContext(final Activity activity);

    @Provides
    @PerActivity
    static FragmentManager activityFragmentManager(final Activity activity) {
        return ((AppCompatActivity) activity).getSupportFragmentManager();
    }

    @PerFragment
    @ContributesAndroidInjector(modules = BaseFragmentModule.class)
    abstract FixtureFragment fixtureFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = BaseFragmentModule.class)
    abstract FixtureDetailFragment fixtureDetailFragment();

    @PerFragment
    @ContributesAndroidInjector(modules = BaseFragmentModule.class)
    abstract FixtureDetailHomeFragment fixtureDetailHomeFragment();


    @PerFragment
    @ContributesAndroidInjector(modules = BaseFragmentModule.class)
    abstract FixtureDetailAwayFragment fixtureDetailAwayFragment();


}