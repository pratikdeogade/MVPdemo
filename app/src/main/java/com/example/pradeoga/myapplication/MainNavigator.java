package com.example.pradeoga.myapplication;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.pradeoga.myapplication.analytics.Tracker;
import com.example.pradeoga.myapplication.base.BaseNavigator;
import com.example.pradeoga.myapplication.fixtures.FixtureFragment;
import com.example.pradeoga.myapplication.fixtures.FixturePresenter;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.FixtureDetailFragment;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.FixtureDetailPresenter;
import com.example.pradeoga.myapplication.fixtures.model.AllMatches;
import com.example.pradeoga.myapplication.network.ApiService;

public class MainNavigator extends BaseNavigator implements Navigator {


    private final ApiService mApiService;
//https://meet.publicisgroupe.net/marellis/VN45P5FJ

    public MainNavigator(final FragmentManager fragmentManager,
                         final Activity activity,
                         final ApiService apiService,
                         final Tracker tracker) {
        super(fragmentManager, activity, tracker);
        mApiService = apiService;
    }

    @Override
    public void launchFixturesList(final boolean addToBackStack) {
        Fragment fragment = findFragment(FixtureFragment.FRAGMENT_TAG);
        if (fragment == null) {
            fragment = FixtureFragment.newInstance();
            new FixturePresenter((FixtureFragment) fragment, mApiService, mTracker, this);
        }
        if (!addToBackStack) {
            removeFromBackStack(null);
        }
        showFragmentWithTag(fragment, addToBackStack, FixtureFragment.FRAGMENT_TAG, true);
    }

    @Override
    public void launchFixturesDetail(final boolean addToBackStack,final AllMatches matches) {

        Fragment fragment = findFragment(FixtureDetailFragment.FRAGMENT_TAG);
        if (fragment == null) {
            fragment = FixtureDetailFragment.newInstance();
            new FixtureDetailPresenter((FixtureDetailFragment) fragment,this, mApiService, mTracker,matches);
        }
        if (!addToBackStack) {
            removeFromBackStack(null);
        }
        showFragmentWithTag(fragment, addToBackStack, FixtureDetailFragment.FRAGMENT_TAG, true);
    }

    @Override
    public void removeFromBackStack(String tag) {
        final FragmentManager fragmentManager = mFragmentManager.get();
        if (fragmentManager != null) {
            fragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}