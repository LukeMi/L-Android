package com.lukemi.android.tutorial.weather.view;


import com.lukemi.android.tutorial.weather.model.WeatherBean;

public interface WeatherView {

    void loading();

    void hindLoading();

    void onArticleSuccess(WeatherBean articleBean);

    void onArticleError(Throwable throwable);
}
