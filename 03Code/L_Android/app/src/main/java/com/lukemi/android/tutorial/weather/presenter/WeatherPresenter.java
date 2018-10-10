package com.lukemi.android.tutorial.weather.presenter;


import com.lukemi.android.tutorial.weather.model.WeatherBean;
import com.lukemi.android.tutorial.weather.model.WeatherModel;
import com.lukemi.android.tutorial.weather.view.WeatherView;

import java.lang.ref.WeakReference;

public class WeatherPresenter implements WeatherModel.ArticleListener {
    private final WeatherModel articleModel;
    private WeatherView articleView;
    private WeakReference<WeatherView> weakReference;

    public WeatherPresenter(WeatherView articleView) {
        weakReference = new WeakReference<>(articleView);
        this.articleView = articleView;
        articleModel = new WeatherModel();
    }

    public void getArticle(String path, int dev) {
        if (articleView != null) {
            articleView.loading();
            articleModel.getArticle(path, dev, this);
        }
    }

    @Override
    public void onArticleBefore() {

    }

    @Override
    public void onArticleSuccess(WeatherBean articleBean) {
        if (articleView != null) {
            articleView.hindLoading();
            articleView.onArticleSuccess(articleBean);
        }
    }

    @Override
    public void onArticleError(Throwable throwable) {
        if (articleView != null) {
            articleView.hindLoading();
            articleView.onArticleError(throwable);
        }

    }

    public void onDetchPresenter() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
        if (articleView != null) {
            articleView = null;
        }
    }
}
