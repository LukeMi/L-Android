package com.lukemi.android.tutorial.recommend.presenter;


import com.lukemi.android.tutorial.recommend.model.RecommendModel;
import com.lukemi.android.tutorial.recommend.view.RecommendView;

import java.lang.ref.WeakReference;

public class RecommendPresenter implements RecommendModel.RecommendListener<String> {

    private final RecommendModel recommendModel;
    private RecommendView recommendView;
    private WeakReference<RecommendView> weakReference;

    public RecommendPresenter(RecommendView recommendView) {
        this.recommendView = recommendView;
        weakReference = new WeakReference<>(recommendView);
        recommendModel = new RecommendModel();
    }

    public void refresh() {
        recommendModel.request(this);
    }

    public void loadMore() {
    }

    @Override
    public void onBefore() {
        recommendView.loading();
    }

    @Override
    public void onSuccess(String result) {
        recommendView.hindLoading();
        recommendView.onSuccess(result);
    }


    @Override
    public void onError(Throwable e) {
        recommendView.hindLoading();
        recommendView.onError(e);
    }

    public void onDestroy() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    @Override
    public void onComplete() {
        recommendView.hindLoading();
    }
}
