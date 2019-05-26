package com.example.pradeoga.myapplication.fixtures.fixturedetail;


import com.example.pradeoga.myapplication.Navigator;
import com.example.pradeoga.myapplication.analytics.Tracker;
import com.example.pradeoga.myapplication.base.BaseErrorDisplayFragment;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.away.FixtureDetailAwayFragment;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.away.FixtureDetailAwayPresenter;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.home.FixtureDetailHomeFragment;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.home.FixtureDetailHomePresenter;
import com.example.pradeoga.myapplication.fixtures.model.AllMatches;
import com.example.pradeoga.myapplication.network.ApiService;

public class FixtureDetailFragmentFactoryImp implements FixtureDetailFragmentFactory {


    private final Navigator mNavigator;
    private final ApiService mApiService;
    private final Tracker mTracker;
    private final FixtureDetailContract.Presenter mFixtureDetailPresenter;
    private FixtureDetailHomeFragment mFixtureDetailHomeFragment;
    private FixtureDetailAwayFragment mFixtureDetailAwayFragment;


    public FixtureDetailFragmentFactoryImp(final Navigator navigator,
                                           final ApiService apiService,
                                           final Tracker tracker,
                                           final FixtureDetailContract.Presenter presenter) {
        mNavigator = navigator;
        mApiService = apiService;
        mTracker = tracker;
        mFixtureDetailPresenter = presenter;

    }

    @Override
    public BaseErrorDisplayFragment getHomeFragment() {
        if (mFixtureDetailHomeFragment == null) {
            mFixtureDetailHomeFragment = FixtureDetailHomeFragment.newInstance();
            new FixtureDetailHomePresenter(mFixtureDetailHomeFragment, mTracker, mNavigator, mFixtureDetailPresenter);
        }
        return mFixtureDetailHomeFragment;
    }

    @Override
    public BaseErrorDisplayFragment getAwayFragment() {
        if (mFixtureDetailAwayFragment == null) {
            mFixtureDetailAwayFragment = FixtureDetailAwayFragment.newInstance();
            new FixtureDetailAwayPresenter(mFixtureDetailAwayFragment, mTracker, mNavigator, mFixtureDetailPresenter);
        }
        return mFixtureDetailAwayFragment;
    }

    @Override
    public BaseErrorDisplayFragment getStatsFragment() {
        return null;
    }
}
