package com.lukemi.android.tutorial.weather.model;


import android.support.annotation.NonNull;

import com.lukemi.android.tutorial.weather.api.WeatherRetrofit;
import com.lukemi.android.tutorial.weather.api.WeatherService;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class WeatherModel {

    public interface ArticleListener {
        void onArticleBefore();

        void onArticleSuccess(WeatherBean articleBean);

        void onArticleError(Throwable throwable);
    }

    public void getArticle(String path ,int  dev,@NonNull final WeatherModel.ArticleListener articleListener) {
        Retrofit retrofit = new WeatherRetrofit().getRetrofit();
        WeatherService articleService = retrofit.create(WeatherService.class);
        Observable<WeatherBean> article = articleService.getArticle(path,dev);
        article.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        articleListener.onArticleBefore();
                    }

                    @Override
                    public void onNext(WeatherBean articleBean) {
                        articleListener.onArticleSuccess(articleBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        articleListener.onArticleError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
//

    }
}
