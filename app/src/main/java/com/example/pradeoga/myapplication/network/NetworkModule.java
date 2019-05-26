package com.example.pradeoga.myapplication.network;

import android.content.res.Resources;

import com.example.pradeoga.myapplication.BuildConfig;
import com.example.pradeoga.myapplication.R;
import com.example.pradeoga.myapplication.base.FifaApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class NetworkModule {

    @Singleton
    @Provides
    static ApiService provideApiService(final FifaApp application) {
       // final boolean retryOnConnectionFailure = application.getResources().getBoolean(R.bool.retry_on_connection_failure);
//        if (BuildConfig.MOCK) {
//            final String mockApiKey = application.getString(R.string.backend_mock_api_key);
//            return new MockApiService(application, mockApiKey, retryOnConnectionFailure);
//        } else {
//            return new RepositoryServiceProvider(application, retryOnConnectionFailure);
//        }

        return new RepositoryServiceProvider(application);
    }


}
