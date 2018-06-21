package com.project.dmitry.retrofit2.Example.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public final class ApiKeyInterceptor implements Interceptor {

    @NonNull
    public static Interceptor create() {
        return new ApiKeyInterceptor();
    }

    private ApiKeyInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build();
        request = request.newBuilder().url(url).build();

        //или так: (вроде одно и тоже?)
//        request = request.newBuilder().addHeader("api_key", BuildConfig.API_KEY).build();

        return chain.proceed(request);
    }
}
