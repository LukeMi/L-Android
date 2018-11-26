package com.lukemi.android.tutorial.recommend.api;

import com.lukemi.android.common.util.Logcat;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecommendRetrofitManager {

    private static RecommendRetrofitManager retrofitManager;
    public static String baseUrl = "http://ic.snssdk.com/";
    private Retrofit retrofit;

    private OkHttpClient.Builder builder = new OkHttpClient.Builder();

    private RecommendRetrofitManager() {
        Gson gson = new Gson();
        OkHttpClient okHttpClient = builder
                .addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        Logcat.log("message : " + message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(60000, TimeUnit.SECONDS)
                .readTimeout(60000, TimeUnit.SECONDS)
                .build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Retrofit getTTRetrofit() {
        return retrofit;
    }

    public static RecommendRetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RecommendRetrofitManager.class) {
                retrofitManager = new RecommendRetrofitManager();
            }
        }
        return retrofitManager;
    }
}
