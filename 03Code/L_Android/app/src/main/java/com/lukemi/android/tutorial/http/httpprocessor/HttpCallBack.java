package com.lukemi.android.tutorial.http.httpprocessor;

import com.google.gson.Gson;

public abstract class HttpCallBack<T> implements CallBack {

    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<T> cls = HttpHelper.analysisClassInfo(this);
        T objResult = gson.fromJson(result,cls);
        onSuccess(objResult);
    }

    public abstract void onSuccess(T objResult);
}
