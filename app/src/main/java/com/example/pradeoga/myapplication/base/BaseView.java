package com.example.pradeoga.myapplication.base;

import android.support.annotation.StringRes;


public interface BaseView<T> {

    void setPresenter(T presenter);

    void showLoading();

    void showLoading(@StringRes final int message);

    void showLoading(final String message);

    void hideLoading();
}
