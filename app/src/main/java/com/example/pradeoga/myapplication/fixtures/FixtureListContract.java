package com.example.pradeoga.myapplication.fixtures;

import com.example.pradeoga.myapplication.base.errors.BaseErrorPresenter;
import com.example.pradeoga.myapplication.base.errors.BaseErrorView;
import com.example.pradeoga.myapplication.fixtures.model.AllMatches;

import java.util.List;

public class FixtureListContract {

    interface View extends BaseErrorView<Presenter> {
        void showMatches(List<AllMatches> matchesList);
        void showDate(List<String> dateList);
    }

    interface Presenter extends BaseErrorPresenter {
        void onItemClicked(AllMatches allMatches);
        void onDateItemClicked(String date);
    }
}
