package com.example.pradeoga.myapplication.base;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.pradeoga.myapplication.ActivityProvider;
import com.example.pradeoga.myapplication.Navigator;
import com.example.pradeoga.myapplication.R;
import com.example.pradeoga.myapplication.analytics.Tracker;
import com.example.pradeoga.myapplication.base.errors.BaseErrorDisplayPresenter;
import com.example.pradeoga.myapplication.network.ErrorCode;
import com.example.pradeoga.myapplication.network.imagedownloader.ImageDownloader;
import com.example.pradeoga.myapplication.widget.NoConnectionSnackBar;
import com.example.pradeoga.myapplication.widget.NoNetworkConnectionView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseActivity implements ActivityProvider,ConnectivityProvider,
        BottomNavigationView.OnNavigationItemSelectedListener
        ,FragmentManager.OnBackStackChangedListener {

    private static final String TAG = "Main Activity";

    private static boolean sIsConnected;

    private static final String BUILD_TYPE_RELEASE = "release";

    @BindView(R.id.activity_main_bottom_navigation)
    BottomNavigationViewEx mBottomNavigationView;

    @BindView(R.id.activity_main_error_snackbar)
    NoConnectionSnackBar mWarningBar;

    @BindView(R.id.activity_main_no_network_view)
    NoNetworkConnectionView mNoNetworkConnectionView;

    @BindView(R.id.activity_main_progress)
    ProgressBar mProgressbar;

    @Inject
    Tracker mTracker;

    @Inject
    Navigator mNavigator;

    @Inject
    ImageDownloader mImageDownloader;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mProgressbar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        initNavigationBar();
        ServiceConnectivityManager.getInternetObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(isConnected -> {
                    if (sIsConnected != isConnected) { //ensures this only happens once per change
                        sIsConnected = isConnected;
                        showHideErrorMessage();
                    }
                    if (sIsConnected) {
                        mNavigator.launchFixturesList(true);
                    }
                })
                .subscribe();
    }

    public void showHideErrorMessage() {
        final BaseErrorDisplayPresenter baseErrorDisplayPresenter = getPresenterOfCurrentFragmentShowing();
        if (null != baseErrorDisplayPresenter) {
            baseErrorDisplayPresenter.setIsConnected(sIsConnected);
            baseErrorDisplayPresenter.showHideErrorMessage(baseErrorDisplayPresenter.isContentCached(), null, true);
        }
    }

    private BaseErrorDisplayPresenter getPresenterOfCurrentFragmentShowing() {
        BaseErrorDisplayPresenter baseErrorDisplayPresenter = null;
        final List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for (final Fragment fragment : fragmentList) {
            if (fragment instanceof BaseErrorDisplayFragment) {
                final BaseErrorDisplayFragment targetFragment = (BaseErrorDisplayFragment) fragment;
                if (targetFragment.isFragmentShowing()) {
                    baseErrorDisplayPresenter = targetFragment.getPresenter();
                    break;
                }
            }
        }
        return baseErrorDisplayPresenter;
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void init() {
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().removeOnBackStackChangedListener(this);
    }

    @Override
    public void showErrorSnackbar(@Nullable final ErrorCode errorCode) {
        if (!mWarningBar.isBarShown()) {
            mWarningBar.show(errorCode);
        }
    }

    @Override
    public void dismissErrorSnackbar() {
        if (mWarningBar.isBarShown()) {
            mWarningBar.dismiss();
        }
    }

    @Override
    public void showNoConnectionErrorView(final View.OnClickListener retryClickListener, final ErrorCode errorCode, final boolean inViewPager) {
        if (!mNoNetworkConnectionView.isNoConnectionViewShown()) {
            mNoNetworkConnectionView.show(retryClickListener, errorCode);
        } else if (!inViewPager) {
            mNoNetworkConnectionView.setOnClickListener(retryClickListener);
        }
    }

    @Override
    public void showNoConnectionErrorView(final NoNetworkConnectionView.OnCloseListener onCloseListener, final ErrorCode errorCode, final boolean inViewPager) {
        if (!mNoNetworkConnectionView.isNoConnectionViewShown()) {
            mNoNetworkConnectionView.show(onCloseListener, errorCode);
        } else if (!inViewPager) {
            mNoNetworkConnectionView.setOnCloseListener(onCloseListener);
        }
    }

    @Override
    public void dismissNoConnectionErrorView() {
        if (mNoNetworkConnectionView.isNoConnectionViewShown()) {
            mNoNetworkConnectionView.dismiss();
        }
    }

    @Override
    public void showProgressInErrorRetry() {
        if (mNoNetworkConnectionView.isVisible()) {
            mNoNetworkConnectionView.showProgressBar();
        } else {
            mProgressbar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgressInErrorRetry(final boolean closeInstantly) {
        if (mNoNetworkConnectionView.isVisible()) {
            mNoNetworkConnectionView.hideProgressBar(closeInstantly);
        } else {
            mProgressbar.setVisibility(View.GONE);
        }
    }

    private void initNavigationBar() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
       // mBottomNavigationView.enableItemShiftingMode(false);
//        mBottomNavigationView.setTextVisibility(true);
//        mBottomNavigationView.enableShiftingMode(false);
//        mBottomNavigationView.enableAnimation(false);

        mTracker.trackScreen(this, "");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_fixture: {
                mNavigator.launchFixturesList(false);
                break;
            }
            case R.id.action_dummy: {
                break;
            }
           /* case R.id.action_video: {
                mTracker.trackScreen(this, Tracker.ScreenName.VIDEO);
                mNavigator.launchVideos();
                break;
            }
            case R.id.action_races: {
                mTracker.trackScreen(this, Tracker.ScreenName.RACING);
                mNavigator.launchRaces();
                break;
            }
            case R.id.action_standings: {
                mTracker.trackScreen(this, Tracker.ScreenName.STANDINGS);
                mNavigator.launchStandings();
                break;
            }
            case R.id.action_more: {
                mTracker.trackScreen(this, Tracker.ScreenName.MORE);
                mNavigator.launchMore();
                break;
            }*/
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public BottomNavigationView getBottomBar() {
        return mBottomNavigationView;
    }

     @Override
    public ImageDownloader getImageDownloader() {
        return mImageDownloader;
    }

    @Override
    public void onBackStackChanged() {
        if (mFragmentManager != null) {
            final int count = mFragmentManager.getBackStackEntryCount();
            if (count > 0) {
                final String tag = mFragmentManager.getBackStackEntryAt(count - 1).getName();
                if (tag != null) {
                    switch (tag) {
                        /*case LatestScreenFragment.FRAGMENT_TAG: {
                            updateNavigationBarState(R.id.action_latest);
                            break;
                        }
                        case VideosFragment.FRAGMENT_TAG: {
                            updateNavigationBarState(R.id.action_video);
                            break;
                        } */
                    }
                }
            } else {
                //updateNavigationBarState(R.id.action_latest);
            }
        }
    }

    private void updateNavigationBarState(final int actionId) {
        final Menu menu = mBottomNavigationView.getMenu();
        for (int i = 0, size = menu.size(); i < size; i++) {
            final MenuItem item = menu.getItem(i);
            final int itemId = item.getItemId();
            if (itemId == actionId) {
                item.setChecked(true);
            }
        }
    }

    @Override
    public boolean isConnected() {
        return sIsConnected;
    }
}
