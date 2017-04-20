package com.lukemi.android.collection.http.volley;

public interface IJasonListener<M> {

    void onSuccess(M m);

    void onError();

}
