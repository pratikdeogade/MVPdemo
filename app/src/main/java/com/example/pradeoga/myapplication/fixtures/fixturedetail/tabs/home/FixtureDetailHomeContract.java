package com.example.pradeoga.myapplication.fixtures.fixturedetail.tabs.home;

import com.example.pradeoga.myapplication.base.errors.BaseErrorPresenter;
import com.example.pradeoga.myapplication.base.errors.BaseErrorView;
import com.example.pradeoga.myapplication.fixtures.fixturedetail.FixtureDetailContract;
import com.example.pradeoga.myapplication.fixtures.model.AllMatches;
import com.example.pradeoga.myapplication.fixtures.model.TeamEvent;

import java.util.List;

public class FixtureDetailHomeContract {
    interface View extends BaseErrorView<Presenter> {
        void show(List<TeamEvent> teamEventList);
    }

    interface Presenter extends BaseErrorPresenter {


    }
}
