package com.lukemi.android.tutorial.recommend.view;

public interface RecommendView<T> {

    void loading();

    void hindLoading();

    void onSuccess(T t);

    void onError(Throwable e);

}
