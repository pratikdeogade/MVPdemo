package com.example.pradeoga.myapplication.network;

import com.example.pradeoga.myapplication.connectivity.scalar;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface BackendApi {

    @GET("/ftech_world_cup_2018_all_matches/") @scalar
    Single<String> getAllMatches();

//    @GET("/video/xhyty5/related") @scalar
//    Single<String> getAllMatches();

}