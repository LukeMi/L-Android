package com.lukemi.myandroid.webservice;

public interface ResultListener<R> {

    public void onSuccess(R m);

    public void onError();
}
