package com.lukemi.android.collection.http.httpprocessor;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class VollyProcessor implements IHttpProcessor {
    private static RequestQueue requestQueue;

    public VollyProcessor(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    @Override
    public void get(String url, Map<String, Object> mparams, final CallBack callBack) {
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //主线程操作(可结合Handler使用)
                        callBack.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //主线程操作
                        callBack.onError(error);
                    }
                });
        requestQueue.add(stringRequest);
    }

    @Override
    public void post(String url, Map<String, Object> mparams, CallBack callBack) {

    }
}
