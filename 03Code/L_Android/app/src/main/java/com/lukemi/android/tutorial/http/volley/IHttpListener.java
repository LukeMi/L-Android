package com.lukemi.android.tutorial.http.volley;

import java.io.InputStream;

public interface IHttpListener {

    void onSuccess(InputStream inputStream);

    void onError();
}
