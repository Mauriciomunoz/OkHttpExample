package com.mapp.okhttp_example;

import android.net.NetworkInfo;

import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import java.io.IOException;

class CacheControlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.cacheControl(CacheControl.FORCE_CACHE);

        return chain.proceed(builder.build());
    }
}
