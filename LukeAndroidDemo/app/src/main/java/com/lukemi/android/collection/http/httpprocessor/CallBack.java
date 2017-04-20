package com.lukemi.android.collection.http.httpprocessor;

public interface CallBack {

    public void onError(Exception e);

    public void onSuccess(String result);
}
