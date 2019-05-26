package com.example.pradeoga.myapplication.network;

import android.content.Context;

import io.reactivex.Single;


public class RepositoryServiceProvider implements ApiService {

    private final LiveApiService mLiveApiService;

    public RepositoryServiceProvider(final Context context) {

        mLiveApiService = new LiveApiService(context);
    }


    @Override
    public Single<String> getAllMatches() {
        return mLiveApiService.getAllMatches();
    }
}