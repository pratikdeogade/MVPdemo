package com.example.pradeoga.myapplication.base.errors;

import android.view.View;

import com.example.pradeoga.myapplication.base.BaseView;
import com.example.pradeoga.myapplication.network.ErrorCode;
import com.example.pradeoga.myapplication.widget.NoNetworkConnectionView;



public interface BaseErrorView<T> extends BaseView<T> {

    void showNoConnectionMessage(ErrorCode errorCode);

    void hideNoConnectionMessage();

    void showNoConnectionViewWithRetry(View.OnClickListener retryClickListener, ErrorCode errorCode, boolean inViewPager);

    void showNoConnectionViewWithClose(NoNetworkConnectionView.OnCloseListener onCloseListener, ErrorCode errorCode, boolean inViewPager);

    void hideNoConnectionView();

    boolean isFragmentShowing();

    void hideLoading(boolean closeInstantly);
}
