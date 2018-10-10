package com.lukemi.android.webservice;

import android.text.TextUtils;

public class WebServiceTask<R> {
    private String url;//请求URL
    private String method;//请求方法名
    private R r;//返回类型

    public WebServiceTask() {
        super();
    }

    public WebServiceTask setUrl(String url) {
        this.url = url;
        return this;
    }

    public WebServiceTask setMethod(String method) {
        this.method = method;
        return this;
    }

    public WebServiceTask setParams(R m) {
        this.r = m;
        return this;
    }


    public void excute() throws Exception {

        if (TextUtils.isEmpty(url)) {
            throw new Exception("url can not be null or empty");
        }else if (TextUtils.isEmpty(method)){
            throw new Exception("method can not be null or empty");
        }else if ( r == null){
            throw new Exception("r can not be null or empty");
        }

    }
}
