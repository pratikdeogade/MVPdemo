package com.example.pradeoga.myapplication.base;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.pradeoga.myapplication.ActivityProvider;
import com.example.pradeoga.myapplication.R;
import com.example.pradeoga.myapplication.network.ErrorCode;
import com.example.pradeoga.myapplication.widget.NoNetworkConnectionView;


import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.AndroidSupportInjection;
import dagger.android.support.HasSupportFragmentInjector;


public class BaseFragment extends Fragment implements HasSupportFragmentInjector {

    protected static final int NO_CHANGE_COLOR = -1;

    @Inject
    protected Context mActivityContext;

    protected ActivityProvider mActivityProvider;
    protected boolean mHideBottomNavigation = false;
    protected boolean mIsFragmentOnTop;

    @Inject
    DispatchingAndroidInjector<Fragment> mChildFragmentInjector;

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ActivityProvider) {
            mActivityProvider = (ActivityProvider) activity;
        }
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        hideBottomBarIfNeeded();
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsFragmentOnTop = true;
        init();
    }

    @Override
    public void onStop() {
        super.onStop();
        showBottomBarIfNeeded();
    }

    @Override
    public void onPause() {
        super.onPause();
        mIsFragmentOnTop = false;
    }

    protected void hideBottomNavigationForFragment() {
        mHideBottomNavigation = true;
    }

    private void hideBottomBarIfNeeded() {
        final View mBottomBar = mActivityProvider.getBottomBar();
        if (mHideBottomNavigation && mBottomBar.getVisibility() == View.VISIBLE) {
            mBottomBar.setVisibility(View.GONE);
        }
    }

    protected void showBottomBarIfNeeded() {
        final View mBottomBar = mActivityProvider.getBottomBar();
        if (mHideBottomNavigation && mBottomBar.getVisibility() == View.GONE) {
            mBottomBar.setVisibility(View.VISIBLE);
        }
    }

    protected void showBottomBar() {
        final View mBottomBar = mActivityProvider.getBottomBar();
        if (mBottomBar.getVisibility() == View.GONE) {
            mBottomBar.setVisibility(View.VISIBLE);
        }
    }

    protected void init() {
        initStatusBar();
    }

    protected void initStatusBar() {
        final int actionBarColor = getActionBarColor();
        if (actionBarColor != NO_CHANGE_COLOR) {
            final Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getActionBarColor());
        }
        final String actionBarTitle = getActionBarTitle();
        final Toolbar toolbar = getToolbar();
        if (!TextUtils.isEmpty(actionBarTitle) && toolbar != null) {
            //toolbar.setTitleTextAppearance(mActivityContext, R.style.ActionBarTitle);
            toolbar.setTitle(actionBarTitle);

            final AppCompatActivity appCompatActivity = ((AppCompatActivity) getActivity());
            appCompatActivity.setSupportActionBar(toolbar);
        }
    }

    protected int getActionBarColor() {
        return getResources().getColor(R.color.colorPrimaryDark);
    }

    protected String getActionBarTitle() {
        return null;
    }

    protected Toolbar getToolbar() {
        return null;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return mChildFragmentInjector;
    }

    public void showLoading() {
    }

    public void showLoading(final int message) {
    }

    public void showLoading(final String message) {
    }

    public void hideLoading() {
    }

    public void hideNoConnectionMessage() {
        mActivityProvider.dismissErrorSnackbar();
    }

    public void showNoConnectionMessage(@Nullable final ErrorCode errorCode) {
        mActivityProvider.showErrorSnackbar(errorCode);
    }

    public void showNoConnectionViewWithRetry(final View.OnClickListener retryClickListener, final ErrorCode errorCode, final boolean inViewPager) {
        mActivityProvider.showNoConnectionErrorView(retryClickListener, errorCode, inViewPager);
    }

    public void showNoConnectionViewWithClose(final NoNetworkConnectionView.OnCloseListener onCloseListener, final ErrorCode errorCode, final boolean inViewPager) {
        mActivityProvider.showNoConnectionErrorView(onCloseListener, errorCode, inViewPager);
    }

    public void hideNoConnectionView() {
        mActivityProvider.dismissNoConnectionErrorView();
    }

    public boolean isFragmentShowing() {
        return mIsFragmentOnTop;
    }

    protected void requestFocus(final EditText editText) {

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    showKeyboard();
                }
            }
        });
        editText.requestFocus();
    }

    private void showKeyboard() {
        final InputMethodManager inputMethodManager = (InputMethodManager) mActivityContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    protected void showKeyboard(final View view) {
        final InputMethodManager inputMethodManager = (InputMethodManager) mActivityContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    protected void hideKeyboard(final View view) {
        final InputMethodManager inputMethodManager = (InputMethodManager) mActivityContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void hideKeyboard() {
        final View view = getActivity().getWindow().getCurrentFocus();
        if (view != null) {
            hideKeyboard(view);
        }
    }

    public void reset() {
    }
}
