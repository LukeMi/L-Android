package com.lukemi.android.tutorial.recommend.model;

import com.lukemi.android.tutorial.util.Logcat;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RecommendModel {

    public interface RecommendListener<T> {
        void onBefore();

        void onSuccess(T result);

        void onError(Throwable e);

        void onComplete();
    }


    public void request(final RecommendListener recommendListener) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Logcat.log(" ObservableOnSubscribe subscribe " + Thread.currentThread().getName());
                Thread.sleep(3000);
                emitter.onNext("-------------------耗时操作-------------------");
//                emitter.onComplete();
//                emitter.onError(new Throwable("test error"));
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logcat.log("Observer onSubscribe " + Thread.currentThread().getName());
                        recommendListener.onBefore();
                    }

                    @Override
                    public void onNext(String s) {
                        Logcat.log("Observer onNext " + s + " ----   " + Thread.currentThread().getName());
                        recommendListener.onSuccess(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logcat.log("Observer onError " + Thread.currentThread().getName());
                        recommendListener.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        Logcat.log("Observer onComplete " + Thread.currentThread().getName());
                        recommendListener.onComplete();
                    }
                });
    }

}
