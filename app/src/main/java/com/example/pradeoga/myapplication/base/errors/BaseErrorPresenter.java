package com.example.pradeoga.myapplication.base.errors;

import com.example.pradeoga.myapplication.base.BasePresenter;
import com.example.pradeoga.myapplication.network.ErrorCode;


public interface BaseErrorPresenter extends BasePresenter {

    boolean isContentCached();

    void retryLastCall();

    boolean isInViewPager();

    void refresh();

    void showHideErrorMessage(boolean isCurrentContentCached, ErrorCode errorCode, boolean update);

    void refreshPagerFragment();

    boolean isConnected();
}
