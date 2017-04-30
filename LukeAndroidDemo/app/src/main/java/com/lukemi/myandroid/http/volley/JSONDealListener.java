package com.lukemi.myandroid.http.volley;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class JSONDealListener<M> implements IHttpListener {

    private Class<M> responseClass;
    private IJasonListener<M> iJasonListener;
    private Handler handler = new Handler(Looper.getMainLooper());

    public JSONDealListener(Class<M> responseClass, IJasonListener<M> iJasonListener) {
        this.responseClass = responseClass;
        this.iJasonListener = iJasonListener;
    }

    @Override
    public void onSuccess(InputStream inputStream) {
        String str = getContent(inputStream);
        Gson gson = new Gson();
        final M response = gson.fromJson(str,responseClass);
        handler.post(new Runnable() {
            @Override
            public void run() {
                iJasonListener.onSuccess(response);
            }
        });
    }

    @Override
    public void onError() {

    }

    private String getContent(InputStream inputStream) {
        try {
            if (inputStream != null) {
                byte[] bytes = new byte[1024];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int len = -1;
                while ((len = inputStream.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                }
                return baos.toString();
            } else {
                throw new RuntimeException("inputStream is null Exception");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
