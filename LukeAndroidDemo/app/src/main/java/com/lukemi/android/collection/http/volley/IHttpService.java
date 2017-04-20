package com.lukemi.android.collection.http.volley;

public interface IHttpService {

    void setUrl(String url);

    void setHttpCallBack(IHttpListener iHttpListener);

    void setRequestData(byte[] requestData);

    void excute();
}
