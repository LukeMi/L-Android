package com.lukemi.android.collection.http.volley;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class HttpTask<T> implements Runnable {
    private IHttpService iHttpService;
    private T requestInfo;
    private String url;
    private IHttpListener iHttpListener;

    public <T extends Serializable> HttpTask(T requestInfo, String url, IHttpListener iHttpListener) {
        this.iHttpService = new JSONHttpService();
        iHttpService.setUrl(url);
        iHttpService.setHttpCallBack(iHttpListener);
        String requestContent = null;//
        try {
            this.iHttpService.setRequestData(requestContent.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.iHttpService.excute();
    }
}
