package com.lukemi.android.tutorial.weather.api;

import com.lukemi.android.common.util.Logcat;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRetrofit {

    private static String url = "https://interface.meiriyiwen.com";

    public WeatherRetrofit() {
    }

    public static Retrofit getRetrofit() {

        url = "http://aider.meizu.com/";

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS);

        if (true) {
            builder.addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Logcat.log("message : " + message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY));
        }

        OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit =
                new Retrofit
                        .Builder()
                        .baseUrl(url)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create(new Gson()))
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();

        return retrofit;
    }

}
