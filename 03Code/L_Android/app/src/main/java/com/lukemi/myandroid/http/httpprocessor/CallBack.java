package com.lukemi.myandroid.http.httpprocessor;

public interface CallBack {

    public void onError(Exception e);

    public void onSuccess(String result);
}
