package com.lukemi.myandroid.http.volley;

public interface IJasonListener<M> {

    void onSuccess(M m);

    void onError();

}
