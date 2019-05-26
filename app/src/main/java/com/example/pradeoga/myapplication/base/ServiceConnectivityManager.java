package com.example.pradeoga.myapplication.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;


public class ServiceConnectivityManager extends BroadcastReceiver {

    private static final String GOOGLE_MAIN_DNS = "8.8.8.8";
    private static final int DNS_PORT = 53;
    private static final int DNS_TIMEOUT = 1500;
    private static final int INTERNET_CONNECTION_DELAY = 2;

    private static Subject<Boolean> mConnectivityObservable;
    private static Subject<Boolean> mInternetObservable;
    private static boolean sIsConnected;
    private static boolean sIsOnline;

    public ServiceConnectivityManager() {
        mConnectivityObservable = BehaviorSubject.create();
        mInternetObservable = BehaviorSubject.create();
    }

    @Override
    public void onReceive(final Context context, final Intent intent) {
        sIsConnected = isConnectedToNetwork(context);
        mConnectivityObservable.onNext(sIsConnected);
        Observable.defer((Callable<ObservableSource<Boolean>>) () -> Observable.just(sIsConnected && isConnectedToInternet()))
                .delay(INTERNET_CONNECTION_DELAY, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hasInternet -> {
                    sIsOnline = hasInternet;
                    mInternetObservable.onNext(sIsOnline);
                });

    }

    public static Observable<Boolean> getConnectivityObservable() {
        return mConnectivityObservable;
    }

    public static Observable<Boolean> getInternetObservable() {
        return mInternetObservable;
    }

    private static boolean isConnectedToNetwork(final Context context) {
        final ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        final boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    private static boolean isConnectedToInternet() {
        try {
            final Socket socket = new Socket();
            final SocketAddress socketAddress = new InetSocketAddress(GOOGLE_MAIN_DNS, DNS_PORT);

            socket.connect(socketAddress, DNS_TIMEOUT);
            socket.close();
            return true;
        } catch (final IOException e) {
            return false;
        }
    }

    public static boolean isConnectedToNetwork() {
        return sIsConnected;
    }

    public static boolean isOnline() {
        return sIsOnline;
    }
}
