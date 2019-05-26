package com.example.pradeoga.myapplication.fixtures.fixturedetail;


import com.example.pradeoga.myapplication.base.BaseErrorDisplayFragment;
import com.example.pradeoga.myapplication.base.errors.BaseErrorPresenter;
import com.example.pradeoga.myapplication.base.errors.BaseErrorView;
import com.example.pradeoga.myapplication.fixtures.model.AllMatches;


public class FixtureDetailContract {
    interface View extends BaseErrorView<Presenter> {
        void updateViewPager();

        BaseErrorDisplayFragment getCurrentFragment();
    }

    public interface Presenter extends BaseErrorPresenter {

        FixtureDetailFragmentFactory getFixtureDetailFragmentFactory();

        AllMatches getAllMatchesResponse();

        void updateFragments();
    }
}
