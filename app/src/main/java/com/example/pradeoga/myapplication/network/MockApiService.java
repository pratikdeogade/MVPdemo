package com.example.pradeoga.myapplication.network;

import android.content.Context;

import com.example.pradeoga.myapplication.R;



public class MockApiService extends BaseApiServiceProvider {

    public MockApiService(final Context context, final String apiKey, final boolean retryOnConnectionFailure) {
        super(context);
    }

    @Override
    protected String getBaseUrl() {
        return mContext.getString(R.string.base_url);
    }
}
