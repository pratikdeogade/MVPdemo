package com.example.pradeoga.myapplication.network;



import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import timber.log.Timber;

public class RxErrorHandlingCallAdapterFactory extends CallAdapter.Factory {

    private final RxJava2CallAdapterFactory mOriginalCallAdapterFactory;

    private RxErrorHandlingCallAdapterFactory() {
        mOriginalCallAdapterFactory = RxJava2CallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new RxErrorHandlingCallAdapterFactory();
    }

    @Override
    public CallAdapter get(final Type returnType, final Annotation[] annotations, final Retrofit retrofit) {
        return new RxCallAdapterWrapper(mOriginalCallAdapterFactory.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapterWrapper implements CallAdapter {

        private final CallAdapter mWrappedCallAdapter;

        RxCallAdapterWrapper(final CallAdapter wrapped) {
            mWrappedCallAdapter = wrapped;
        }

        @Override
        public Type responseType() {
            return mWrappedCallAdapter.responseType();
        }

        @Override
        public Object adapt(final Call call) {
            final Object adaptedCall = mWrappedCallAdapter.adapt(call);

            if (adaptedCall instanceof Completable) {
                return ((Completable) adaptedCall).onErrorResumeNext(
                        throwable -> Completable.error(asRetrofitException(throwable)));
            }

            if (adaptedCall instanceof Single) {
                return ((Single) adaptedCall).onErrorResumeNext(
                        throwable -> Single.error(asRetrofitException((Throwable) throwable)));
            }

            if (adaptedCall instanceof Observable) {
                return ((Observable) adaptedCall).onErrorResumeNext(
                        (Function<? super Throwable, ? extends ObservableSource>)
                                throwable -> Observable.error(asRetrofitException(throwable)));
            }

            throw new RuntimeException("Observable Type not supported");
        }

        private NetworkException asRetrofitException(final Throwable throwable) {
            // We had non-200 http error
            if (throwable instanceof HttpException) {
                return new NetworkException(ErrorCode.mapErrorCode(((HttpException) throwable).code()));
            }
            // A network error happened
            if (throwable instanceof IOException) {
                return new NetworkException(ErrorCode.IO_EXCEPTION);
            }
            Timber.e(throwable);
            return new NetworkException(ErrorCode.OTHER);
        }
    }
}