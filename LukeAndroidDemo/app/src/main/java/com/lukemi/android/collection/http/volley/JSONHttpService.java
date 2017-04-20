package com.lukemi.android.collection.http.volley;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class JSONHttpService implements IHttpService {

    private HttpClient httpClient = new DefaultHttpClient();
    private HttpRequestBase httpRequestBase;
    private IHttpListener iHttpListener;
    private String url;
    private byte[] requestData;

    @Override
    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public void setHttpCallBack(IHttpListener iHttpListener) {
        this.iHttpListener = iHttpListener;
    }

    @Override
    public void setRequestData(byte[] requestData) {
        this.requestData = requestData;
    }

    @Override
    public void excute() {
        httpRequestBase = new HttpPost(url);
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(requestData);
        ((HttpPost) httpRequestBase).setEntity(byteArrayEntity);
        try {
            httpClient.execute(httpRequestBase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
