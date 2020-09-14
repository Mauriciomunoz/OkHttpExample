package com.mapp.okhttp_example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.mapp.okhttp_example.databinding.ActivityMainBinding;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String responseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMain(this);
    }

    public void startRequest(){
        //This is an asyncronous call. It looks like synchronous calls throws NetworkOnMainThreadException
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://www.google.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                binding.setResponseData("Error: " +  e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                binding.setResponseData("SUCCESS!");
            }
        });
    }

    public void requestParameters(){
        //Parameters can be added to the URL
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://www.youtube.com/watch").newBuilder();
        urlBuilder.addQueryParameter("v", "lF67ct9xflU");
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                binding.setResponseData("Error: " +  e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                binding.setResponseData("SUCCESS!");
            }
        });
    }

    public void requestHeaders(){
        //You can add headers to send token for Authorization or something else.
        Request request = new Request.Builder()
                .header("Authorization", "your token")
                .url("https://api.github.com/users/MauricioMunoz")
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                binding.setResponseData("Error: " +  e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                binding.setResponseData("SUCCESS!");
            }
        });
    }

    public void appInterceptor(){

        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new AppInterceptor());

        Request request = new Request.Builder()
                .url("https://www.google.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                binding.setResponseData("Error: " +  e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                binding.setResponseData("SUCCESS!");
            }
        });

    }

    public void netInterceptor(){
        OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new CacheControlInterceptor());

        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                binding.setResponseData("Error: " +  e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                binding.setResponseData("SUCCESS!");
            }
        });

    }

    public void cacheControl(){
        int cacheSize = 10 * 1024 * 1024; // 10MB

        OkHttpClient client = new OkHttpClient();
        client.setCache(new Cache(getApplicationContext().getCacheDir(),cacheSize));

        Request request = new Request.Builder()
            .url("http://publicobject.com/helloworld.txt")
            .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                binding.setResponseData("Error: " +  e.toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                binding.setResponseData("SUCCESS!");
            }
        });
    }
}