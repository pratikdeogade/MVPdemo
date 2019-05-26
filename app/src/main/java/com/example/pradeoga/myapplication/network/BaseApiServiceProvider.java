package com.example.pradeoga.myapplication.network;

import android.content.Context;

import com.example.pradeoga.myapplication.connectivity.GsonOrScalarConvertorFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static com.example.pradeoga.myapplication.utils.NetworkUtils.CACHE_50MB;

public abstract class BaseApiServiceProvider implements ApiService {

    private static final int READ_TIMEOUT_SECONDS = 60;
    private static final int WRITE_TIMEOUT_SECONDS = 60;
    private static final int CONNECT_TIMEOUT_SECONDS = 60;

    protected final Context mContext;
    private final BackendApi mServices;
    private final BackendApi mNoCacheServices;

    BaseApiServiceProvider(final Context context) {

        final Cache dataCache = new Cache(context.getCacheDir(), CACHE_50MB);

        mContext = context;


        final Retrofit.Builder builder = new Retrofit.Builder().baseUrl(getBaseUrl()).
                addConverterFactory(new GsonOrScalarConvertorFactory()).
               // addCallAdapterFactory(RxJava2CallAdapterFactory.create()).
                addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create());

        final OkHttpClient.Builder client = new OkHttpClient.Builder()
                .cache(dataCache)
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        //client.retryOnConnectionFailure(retryOnConnectionFailure);


        final OkHttpClient.Builder noCacheClient = new OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        //noCacheClient.retryOnConnectionFailure(retryOnConnectionFailure);

        builder.client(client.build());
        mServices = builder.build().create(BackendApi.class);

        builder.client(noCacheClient.build());
        mNoCacheServices = builder.build().create(BackendApi.class);
    }

    protected abstract String getBaseUrl();

    @Override
    public Single<String> getAllMatches() {
        return mServices.getAllMatches();
    }
}