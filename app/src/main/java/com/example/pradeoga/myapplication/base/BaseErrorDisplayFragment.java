package com.example.pradeoga.myapplication.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.pradeoga.myapplication.base.errors.BaseErrorDisplayPresenter;
import com.example.pradeoga.myapplication.base.errors.BaseErrorPresenter;


public abstract class BaseErrorDisplayFragment extends BaseFragment  {

    protected BaseErrorDisplayPresenter mPresenter;
    protected boolean mIsViewRefreshing;

    @Override
    public void onViewCreated(final @NonNull View view, final @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initEdgeGlowScrollView();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (shouldParentStartPresenter()) {
            mPresenter.start();
        }

        mPresenter.setIsConnected(((ConnectivityProvider) mActivityContext).isConnected());
    }

    protected boolean shouldParentStartPresenter() {
        return true;
    }

    protected void setPresenter(final BaseErrorPresenter presenter) {
        mPresenter = (BaseErrorDisplayPresenter) presenter;
    }

    public void hideLoading(final boolean closeInstantly) {
        mIsViewRefreshing = false;
        mActivityProvider.hideProgressInErrorRetry(closeInstantly);
    }

    @Override
    public void showLoading() {
        mActivityProvider.showProgressInErrorRetry();
    }

    @Override
    public void hideLoading() {
        hideLoading(true);
    }

    public BaseErrorDisplayPresenter getPresenter() {
        return mPresenter;
    }

    protected void initEdgeGlowScrollView() {
    }
}