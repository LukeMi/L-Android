package com.lukemi.android.tutorial.http.httpprocessor;

public interface CallBack {

    public void onError(Exception e);

    public void onSuccess(String result);
}
