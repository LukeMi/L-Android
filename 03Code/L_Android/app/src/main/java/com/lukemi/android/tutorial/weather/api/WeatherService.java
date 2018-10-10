package com.lukemi.android.tutorial.weather.api;


import com.lukemi.android.tutorial.weather.model.WeatherBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherService {
//
//    @GET("{article}/today")
//    Observable<WeatherBean> getArticle(@Path("article") String path, @Query("dev") int dev);

    @GET("{article}/weather/listWeather")
    Observable<WeatherBean> getArticle(@Path("article") String path, @Query("cityIds") int dev);




}
