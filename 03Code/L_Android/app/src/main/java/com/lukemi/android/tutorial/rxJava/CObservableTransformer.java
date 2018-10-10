package com.lukemi.android.tutorial.rxJava;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public class CObservableTransformer<T> implements ObservableTransformer<String, Class<T>> {

    @Override
    public ObservableSource<Class<T>> apply(Observable<String> upstream) {

        final Gson gson = new Gson();
        Observable<T> map = upstream.map(new Function<String, T>() {
            @Override
            public T apply(String s) throws Exception {
//                return gson.fromJson(s, T);
                return null;
            }
        });
        return null;
    }
}
