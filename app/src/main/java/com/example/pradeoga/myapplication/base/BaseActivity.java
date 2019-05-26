package com.example.pradeoga.myapplication.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    Context mApplicationContext;

    @Inject
    protected FragmentManager mFragmentManager;

    @Inject
    DispatchingAndroidInjector<Fragment> mFragmentInjector;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mFragmentInjector;
    }
}

