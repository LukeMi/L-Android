package com.lukemi.android.webservice;

import java.io.Serializable;

/**
 * 自定义Webservice框架
 * created bt: tubg
 * created at: 2017/5/1 17:40
 * e_mail: tbug.chen@gmail.com / tbugchen@163.com
 */
public class WSLibrary {

    public static <T extends Serializable, R> void
    wsRequest(String url, String method, T requestInfo, Class<R> responseClass,ResultListener<R> resultListenner) {

    }
}
