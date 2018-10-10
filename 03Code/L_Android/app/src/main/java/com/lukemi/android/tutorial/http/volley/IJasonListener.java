package com.lukemi.android.tutorial.http.volley;

public interface IJasonListener<M> {

    void onSuccess(M m);

    void onError();

}
