package com.example.pradeoga.myapplication.network;

import android.content.Context;

import com.example.pradeoga.myapplication.R;



public class LiveApiService extends BaseApiServiceProvider {

    public LiveApiService(final Context context) {
        super(context);
    }

    @Override
    protected String getBaseUrl() {
        return mContext.getString(R.string.base_url);
    }
}
