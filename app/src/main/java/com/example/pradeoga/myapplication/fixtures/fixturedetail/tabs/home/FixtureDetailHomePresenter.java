package com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.home;

import com.example.pradeoga.myapplication.Navigator;
import com.example.pradeoga.myapplication.analytics.Tracker;
import com.example.pradeoga.myapplication.base.errors.BaseErrorDisplayPresenter;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.FixtureDetailContract;
import com.example.pradeoga.myapplication.fixtures.model.AllMatches;
import com.example.pradeoga.myapplication.network.ErrorCode;

public class FixtureDetailHomePresenter extends BaseErrorDisplayPresenter<String>
        implements FixtureDetailHomeContract.Presenter {

    private final FixtureDetailHomeContract.View mFixtureDetailHomeContractView;
    private final Tracker mTracker;
    private final Navigator mNavigator;
    private final FixtureDetailContract.Presenter mFixtureDetailPresenter;
    AllMatches mAllMatches;

    public FixtureDetailHomePresenter(final FixtureDetailHomeContract.View view,
                                      final Tracker tracker,
                                      final Navigator navigator,
                                      final FixtureDetailContract.Presenter fixtureDetailPresenter) {
        super(view);
        mFixtureDetailHomeContractView = view;
        mFixtureDetailPresenter = fixtureDetailPresenter;
        mTracker = tracker;
        mNavigator = navigator;
    }

    @Override
    public void start() {
        mAllMatches = mFixtureDetailPresenter.getAllMatchesResponse();
        mFixtureDetailHomeContractView.show(mAllMatches.getHomeTeamEvents());
    }


    @Override
    protected void displayContent(String response) {

    }

    @Override
    public void retryLastCall() {

    }

    @Override
    public boolean isInViewPager() {
        return false;
    }
}
