package com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.away;

import com.example.pradeoga.myapplication.Navigator;
import com.example.pradeoga.myapplication.analytics.Tracker;
import com.example.pradeoga.myapplication.base.errors.BaseErrorDisplayPresenter;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.FixtureDetailContract;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.home.FixtureDetailHomeContract;
import com.example.pradeoga.myapplication.fixtures.model.AllMatches;

public class FixtureDetailAwayPresenter extends BaseErrorDisplayPresenter<String>
        implements FixtureDetailAwayContract.Presenter {

    private final FixtureDetailAwayContract.View mFixtureDetailAwayContractView;
    private final Tracker mTracker;
    private final Navigator mNavigator;
    private final FixtureDetailContract.Presenter mFixtureDetailPresenter;
    AllMatches mAllMatches;

    public FixtureDetailAwayPresenter(final FixtureDetailAwayContract.View view,
                                      final Tracker tracker,
                                      final Navigator navigator,
                                      final FixtureDetailContract.Presenter fixtureDetailPresenter) {
        super(view);
        mFixtureDetailAwayContractView = view;
        mFixtureDetailPresenter = fixtureDetailPresenter;
        mTracker = tracker;
        mNavigator = navigator;
    }

    @Override
    public void start() {
        mAllMatches = mFixtureDetailPresenter.getAllMatchesResponse();
        mFixtureDetailAwayContractView.show(mAllMatches.getAwayTeamEvents());
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
