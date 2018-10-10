package com.lukemi.android.tutorial.http.httpprocessor;

import java.util.Map;

/**
 * 网络处理器接口
 * <p>
 * created bt: tubg
 * created at: 2017/4/18 23:26
 * e_mail: tbug.chen@gmail.com / tbugchen@163.com
 */
public interface IHttpProcessor {
    public void get(String url, Map<String, Object> mparams, CallBack callBack);

    public void post(String url, Map<String, Object> mparams, CallBack callBack);
}
