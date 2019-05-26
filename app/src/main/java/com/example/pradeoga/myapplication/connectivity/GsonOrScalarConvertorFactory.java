package com.example.pradeoga.myapplication.connectivity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by pdeogade on 05-10-2017.
 */

public class GsonOrScalarConvertorFactory extends Converter.Factory {

    private final Converter.Factory scalarFactory= ScalarsConverterFactory.create();
    private final Converter.Factory gsonFactory=GsonConverterFactory.create();

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        for (Annotation annotation : annotations) {

            if (annotation.annotationType() == scalar.class) {
                return scalarFactory.responseBodyConverter(type, annotations, retrofit);
            }

            if (annotation.annotationType() == gson.class) {
                return gsonFactory.responseBodyConverter(type, annotations, retrofit);
            }

        }
        return null;
        //return super.responseBodyConverter(type, annotations, retrofit);
    }
}

