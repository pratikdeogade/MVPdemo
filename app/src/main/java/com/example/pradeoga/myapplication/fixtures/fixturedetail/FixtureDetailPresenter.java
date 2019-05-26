package com.example.pradeoga.myapplication.fixtures.fixturedetail;



import com.example.pradeoga.myapplication.Navigator;
import com.example.pradeoga.myapplication.analytics.Tracker;
import com.example.pradeoga.myapplication.base.errors.BaseErrorDisplayPresenter;
import com.example.pradeoga.myapplication.fixtures.model.AllMatches;
import com.example.pradeoga.myapplication.network.ApiService;

import java.util.HashMap;
import java.util.Map;


public class FixtureDetailPresenter extends BaseErrorDisplayPresenter
        implements FixtureDetailContract.Presenter {

    private final Navigator mNavigator;
    private final ApiService mApiService;
    private final Tracker mTracker;
    private FixtureDetailFragmentFactoryImp mFixtureDetailFragmentFactoryImp;
    private final FixtureDetailContract.View mStandingsView;
    private AllMatches matches;

    public FixtureDetailPresenter(final FixtureDetailContract.View view,
                                  final Navigator navigator,
                                  final ApiService apiService,
                                  final Tracker tracker,
                                  AllMatches matches) {
        super(view);
        mNavigator = navigator;
        mApiService = apiService;
        mTracker = tracker;
        mStandingsView = (FixtureDetailContract.View) mView;
        this.matches=matches;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    protected void displayContent(final Object response) {
        // call update fragments
    }

    @Override
    public void retryLastCall() {
        //retry last call in the visible fragment
    }

    @Override
    public boolean isContentCached() {
        return mStandingsView.getCurrentFragment().getPresenter().isContentCached();
    }

    @Override
    public boolean isInViewPager() {
        return false;
    }

    @Override
    public FixtureDetailFragmentFactory getFixtureDetailFragmentFactory() {
        if (mFixtureDetailFragmentFactoryImp == null) {
            mFixtureDetailFragmentFactoryImp = new FixtureDetailFragmentFactoryImp(mNavigator, mApiService, mTracker, this);
        }
        return mFixtureDetailFragmentFactoryImp;
    }

    @Override
    public AllMatches getAllMatchesResponse() {
        return  matches;
    }


    @Override
    public void updateFragments() {
        mStandingsView.updateViewPager();
    }
}

