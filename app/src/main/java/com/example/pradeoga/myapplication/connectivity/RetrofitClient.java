package com.example.pradeoga.myapplication.connectivity;

import com.example.pradeoga.myapplication.R;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by pdeogade on 08-12-2016.
 */
public class RetrofitClient {
    private final static RetrofitClient ourInstance = new RetrofitClient();
    private  static Retrofit retrofit;

    public static RetrofitClient getInstance() {
        return ourInstance;
    }

    private RetrofitClient() {
    }

    final static OkHttpClient client=new OkHttpClient().newBuilder().build();

    public static Retrofit getClient() {
        String baseIUrl="https://ftech2k18-eval-test.apigee.net";
        retrofit = new Retrofit.Builder()
                .baseUrl(baseIUrl)
                .client(client)
                .addConverterFactory(new GsonOrScalarConvertorFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }



}
