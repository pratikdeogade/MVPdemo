package com.example.pradeoga.myapplication.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;


import com.example.pradeoga.myapplication.R;
import com.example.pradeoga.myapplication.analytics.Tracker;

import java.lang.ref.WeakReference;

import static android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;

public class BaseNavigator {

    protected final WeakReference<FragmentManager> mFragmentManager;
    protected final WeakReference<Activity> mActivity;
    protected final Tracker mTracker;


    public BaseNavigator(final FragmentManager fragmentManager,
                         final Activity activity,
                         final Tracker tracker) {
        mFragmentManager = new WeakReference<>(fragmentManager);
        mActivity = new WeakReference<>(activity);
        mTracker=tracker;

    }

    protected void showFragmentWithAnimation(final Fragment fragment, final String fragmentTag, final boolean addToBackStack) {
        showFragmentWithTag(fragment, addToBackStack, fragmentTag, true);
    }

    protected void showFragmentWithTag(final Fragment fragment, final boolean addToBackStack, @NonNull final String fragmentTag, final boolean withAnimation) {
        if (mFragmentManager.get() != null) {
            if (isFragmentOnTop(fragmentTag)) {
                if (fragment instanceof BaseFragment) {
                    ((BaseFragment) fragment).reset();
                }
            } else {
                final FragmentTransaction fragmentTransaction = mFragmentManager.get()
                        .beginTransaction();
                if (withAnimation) {
                    fragmentTransaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out,
                            R.anim.slide_left_in, R.anim.slide_right_out);
                }
                fragmentTransaction.replace(R.id.activity_main_fragment_container, fragment, fragmentTag);
                if (addToBackStack) {
                    fragmentTransaction.addToBackStack(fragmentTag);
                }
                fragmentTransaction.commitAllowingStateLoss();
            }
        }
    }

    private Fragment findFragmentByTag(final String tag) {
        Fragment fragment = null;
        if (mFragmentManager.get() != null) {
            fragment = mFragmentManager.get().findFragmentByTag(tag);
        }
        return fragment;
    }

    private boolean isFragmentOnTop(@NonNull final String fragmentTag) {
        final FragmentManager fragmentManager = mFragmentManager.get();
        boolean fragmentOnTop = false;
        if (fragmentManager != null && !TextUtils.isEmpty(fragmentTag)) {
            final int count = fragmentManager.getBackStackEntryCount();
            if (count > 0) {
                final String tag = fragmentManager.getBackStackEntryAt(count - 1).getName();
                fragmentOnTop = (tag != null && tag.equals(fragmentTag));
            } else {
                final Fragment fragment = fragmentManager.findFragmentById(R.id.activity_main_fragment_container);
                fragmentOnTop = (null != fragment && fragmentTag.equals(fragment.getTag()));
            }
        }
        return fragmentOnTop;
    }

    protected String getTopFragmentTag() {
        String tag = null;
        if (mFragmentManager.get() != null) {
            final int count = mFragmentManager.get().getBackStackEntryCount();
            if (count > 0) {
                final FragmentManager.BackStackEntry entry = mFragmentManager.get().getBackStackEntryAt(count - 1);
                tag = entry.getName();
            }
        }
        return tag;
    }

    protected Fragment findFragment(final String tag) {
        Fragment fragment = null;
        if (mFragmentManager.get() != null) {
            fragment = mFragmentManager.get().findFragmentByTag(tag);
        }
        return fragment;
    }

    public void goBack() {
        if (mFragmentManager.get() != null) {
            mFragmentManager.get().popBackStack();
        }
    }



    public void clearStackFromScreen(final String tag) {
        if (mFragmentManager.get() != null) {
            mFragmentManager.get().popBackStack(tag, POP_BACK_STACK_INCLUSIVE);
        }
    }
}
