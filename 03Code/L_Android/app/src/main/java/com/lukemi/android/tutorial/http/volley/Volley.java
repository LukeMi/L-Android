package com.lukemi.android.tutorial.http.volley;

import java.io.Serializable;
import java.util.concurrent.FutureTask;

public class Volley {

    public static <T extends Serializable, M> void
    sendRequest(T requestInfo, String url, Class<M> responseClass, IJasonListener<M> httpcallable) {
        IHttpListener iHttpListener = new JSONDealListener(responseClass, httpcallable);
        HttpTask<T> httpTask = new HttpTask<T>(responseClass, url, iHttpListener);
        ThreadPoolManager.getInstance().equals(new FutureTask<T>(httpTask, null));
    }


}
