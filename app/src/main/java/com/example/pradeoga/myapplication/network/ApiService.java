package com.example.pradeoga.myapplication.network;

import android.support.annotation.Nullable;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;


public interface ApiService {
    Single<String> getAllMatches();
}
