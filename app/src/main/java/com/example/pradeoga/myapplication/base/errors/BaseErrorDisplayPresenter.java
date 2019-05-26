package com.example.pradeoga.myapplication.base.errors;

import android.view.View;

import com.example.pradeoga.myapplication.network.ErrorCode;
import com.example.pradeoga.myapplication.network.NetworkException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public abstract class BaseErrorDisplayPresenter<T extends Object> implements BaseErrorPresenter {

    private static boolean sIsConnected;
    private static boolean sViewPagerUpdated;
    private boolean mIsContentCached;

    protected final BaseErrorView mView;

    private final View.OnClickListener mRetryClickListener = view -> {
        retryLastCall();
    };

    protected final SingleObserver<T> apiCachedResponseSubscriber = new SingleObserver<T>() {

        @Override
        public void onSubscribe(final Disposable disposable) {
            //not in use
        }

        @Override
        public void onSuccess(final T responseObject) {
            mView.hideLoading(true);
          displayContent(responseObject);
            showHideErrorMessage(true, null, false);
            if (isInViewPager() && !sViewPagerUpdated) {
                sViewPagerUpdated = true;
                refreshPagerFragment();
            }
            mView.hideLoading(true);
        }

        @Override
        public void onError(final Throwable throwable) {
            mView.hideLoading(true);
            if (isContentCached()) {
                showHideErrorMessage(true, null, false);
            } else {
                if (throwable instanceof NetworkException) {
                    sViewPagerUpdated = false;
                    processErrorCode(((NetworkException) throwable).getErrorCode());
                }
            }
            mView.hideLoading(true);

        }
    };


    public BaseErrorDisplayPresenter(final BaseErrorView errorView) {
        mView = errorView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public boolean isContentCached() {
        return mIsContentCached;
    }

    @Override
    public boolean isConnected() {
        return sIsConnected;
    }

    @Override
    public void refresh() {
        mView.showLoading();
    }

    @Override
    public void showHideErrorMessage(final boolean isCurrentContentCached, final ErrorCode errorCode, final boolean update) {
        setIsContentCached(isCurrentContentCached);
        showHideErrorMessage(errorCode, isConnected(), update);
    }

    @Override
    public void refreshPagerFragment() {
        //empty method to be overridden
    }

//    public void updateEventTracker() {
//        if (mView instanceof EventTrackerErrorListener) {
//            ((EventTrackerErrorListener) mView).retryEventTracker();
//        }
//    }

    public void setIsConnected(final boolean isConnected) {
        sIsConnected = isConnected;
    }

    private void showHideErrorMessage(final ErrorCode errorCode, final boolean isConnected, final boolean update) {
        if (isCurrentView()) { // Multiple views can be in memory - ignore others
            if (isConnected) {
                if (isContentCached()) {
                    mView.hideNoConnectionMessage();
                    mView.hideNoConnectionView();
                    if (update) {
                        //updateEventTracker();
                    }
                }
            } else {
                if (isContentCached()) {
                    mView.showNoConnectionMessage(errorCode);
//                    if (mView instanceof EventTrackerErrorListener) {
//                        ((EventTrackerErrorListener) mView).showEventTrackerNoConnection();
//                    }
                } else {
                    setIsContentCached(false); // this is a backup measure
                    showFullErrorView(errorCode);
                    mView.hideNoConnectionMessage();
                }
            }
        }
    }

    private void processErrorCode(final ErrorCode errorCode) {
        showHideErrorMessage(errorCode, isConnected(), false);
    }

    private boolean isCurrentView() {
        return mView.isFragmentShowing();
    }

    protected void showFullErrorView(final ErrorCode errorCode) {
        mView.showNoConnectionViewWithRetry(mRetryClickListener, errorCode, isInViewPager());
    }

    protected void setIsContentCached(final boolean isContentCached) {
        mIsContentCached = isContentCached;
    }

    protected abstract void displayContent(T response);
}
