package com.lukemi.android.webservice;

public interface ResultListener<R> {

    public void onSuccess(R m);

    public void onError();
}
