package com.lukemi.myandroid.http.httpprocessor;

import java.net.URLEncoder;
import java.util.Map;

public class HttpHelper {
    private HttpHelper mHttpHelper;
    private static IHttpProcessor mIHttpProcessor;
    private String mUrl;
    private Map<String, Object> mParams;
    private final int REQUEST_TYPE_GET = 0x01;
    private final int REQUEST_TYPE_POST = 0x02;
    private int mRequestType;
    private CallBack defaultCallBack = new CallBack() {
        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onSuccess(String result) {

        }
    };

    public static void init(IHttpProcessor iHttpProcessor) {
        mIHttpProcessor = iHttpProcessor;
    }

    public static HttpHelper obtain() {

        return new HttpHelper();
    }

    public HttpHelper url(String url) {
        mUrl = url;
        return this;
    }


    public HttpHelper appendParams(Map<String, Object> params) {
        mParams = params;
        return this;
    }

    public void get(CallBack callBack) {
        mRequestType = REQUEST_TYPE_GET;
        request(mRequestType, callBack);
    }

    private void request(int mRequestType, CallBack callBack) {
        if (callBack == null) {
            callBack = defaultCallBack;
        }
        switch (mRequestType) {
            case REQUEST_TYPE_GET:
                mIHttpProcessor.get(mUrl, mParams, callBack);
                break;
            case REQUEST_TYPE_POST:
                mIHttpProcessor.post(mUrl, mParams, callBack);
                break;
        }
    }

    //url不允许有空格字符，如果参数有空格，则需要此方法转换
    private static String encode(String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (Exception e) {
            //针对不支持的编码时报错，“utf-8”应该是支持的
            throw new RuntimeException(e);
        }
    }

    public static <T> Class<T> analysisClassInfo(HttpCallBack tHttpCallBack) {
        return null;
    }
}
