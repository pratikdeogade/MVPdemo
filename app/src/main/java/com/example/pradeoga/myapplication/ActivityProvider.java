package com.example.pradeoga.myapplication;

import android.support.design.widget.BottomNavigationView;
import android.view.View;

import com.example.pradeoga.myapplication.network.ErrorCode;
import com.example.pradeoga.myapplication.network.imagedownloader.ImageDownloader;
import com.example.pradeoga.myapplication.widget.NoNetworkConnectionView;

public interface ActivityProvider {
    BottomNavigationView getBottomBar();

    ImageDownloader getImageDownloader();

    void showErrorSnackbar(ErrorCode errorCode);

    void dismissErrorSnackbar();

    void showNoConnectionErrorView(View.OnClickListener retryClickListener, ErrorCode errorCode, boolean inViewPager);

    void showNoConnectionErrorView(NoNetworkConnectionView.OnCloseListener onCloseListener, ErrorCode errorCode, boolean inViewPager);

    void dismissNoConnectionErrorView();

    void showProgressInErrorRetry();

    void hideProgressInErrorRetry(boolean closeInstantly);
}
