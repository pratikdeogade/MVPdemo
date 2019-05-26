package com.example.pradeoga.myapplication.fixtures;

import com.example.pradeoga.myapplication.Navigator;
import com.example.pradeoga.myapplication.analytics.Tracker;
import com.example.pradeoga.myapplication.base.BaseObserver;
import com.example.pradeoga.myapplication.base.errors.BaseErrorDisplayPresenter;
import com.example.pradeoga.myapplication.fixtures.model.AllMatches;
import com.example.pradeoga.myapplication.network.ApiService;
import com.example.pradeoga.myapplication.utils.DateTimeUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.joda.time.LocalDateTime;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FixturePresenter extends BaseErrorDisplayPresenter<String> implements FixtureListContract.Presenter {

    HashMap<String, List<AllMatches>> mHAshMap;
    FixtureListContract.View fixtureView;

    private final ApiService mApiService;
    private final Tracker mTracker;
    private final Navigator mNavigator;

    public FixturePresenter(final FixtureListContract.View view,
                            final ApiService apiService,
                            final Tracker tracker,
                            final Navigator navigator) {
        super(view);
        mApiService = apiService;
        mTracker = tracker;
        mNavigator = navigator;

    }

    @Override
    public void start() {
        fetchFixtData();
    }

    private void fetchFixtData() {
        mApiService.getAllMatches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(apiCachedResponseSubscriber);
    }

    @Override
    protected void displayContent(String response) {
        Type type = new TypeToken<List<AllMatches>>() {
        }.getType();
        final List<AllMatches> allMatches = new Gson().fromJson(response, type);
        mHAshMap = new HashMap<>();
        for (AllMatches matches : allMatches) {
            String date = DateTimeUtils.getDate(matches.getDatetime());
            if (mHAshMap.get(date) == null) {
                List<AllMatches> dateWiseMatches = new ArrayList<>();
                dateWiseMatches.add(matches);
                mHAshMap.put(date, dateWiseMatches);
            } else {
                mHAshMap.get(date).add(matches);
            }
        }
        fixtureView = (FixtureListContract.View) mView;
        List<String> dateList = new ArrayList<>(mHAshMap.keySet());
        Collections.sort(dateList);
        fixtureView.showDate(dateList);
        }

    @Override
    public void retryLastCall() {
    }

    @Override
    public boolean isInViewPager() {
        return false;
    }

    @Override
    public void onItemClicked(AllMatches allMatches) {
        mNavigator.launchFixturesDetail(false,allMatches);
        }

    @Override
    public void onDateItemClicked(String date) {
        fixtureView.showMatches(mHAshMap.get(date));
    }

}
