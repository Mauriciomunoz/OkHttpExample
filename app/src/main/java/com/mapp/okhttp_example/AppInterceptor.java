package com.mapp.okhttp_example;

import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

class AppInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);
        switch (response.code()){
            case 404:
                Log.i("ERROR: ", "" + response.code());
                return response;
        }

        return null;
    }
}
