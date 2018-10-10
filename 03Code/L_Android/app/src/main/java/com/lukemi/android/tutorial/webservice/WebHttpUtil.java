package com.lukemi.android.tutorial.webservice;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mzchen on 2016/12/1.
 */

public class WebHttpUtil {
    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    /**
     * 获取手机号信息
     *
     * @param nameSpace       命名空间
     * @param methodName      方法名
     * @param endPoint        webservice url
     * @param soapAction      soapAction
     * @param phoneNumber     电话号码
     * @param onPhoneCallBack 回调接口
     */
    public static void getPhoneInfo(final String nameSpace, final String methodName, final String endPoint, final String soapAction, final String phoneNumber, final OnPhoneCallBack onPhoneCallBack) {
        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                // 指定WebService的命名空间和调用的方法名
                SoapObject rq = new SoapObject(nameSpace, methodName);
                // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
                rq.addProperty("mobileCode", phoneNumber);
//                soapObject.addProperty("userID", "");
                // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.bodyOut = rq;
                // 设置是否调用的是dotNet开发的WebService
                envelope.dotNet = true;
                // 等价于envelope.bodyOut = rpc;
                envelope.setOutputSoapObject(rq);
                HttpTransportSE ht = new HttpTransportSE(endPoint);
                ht.debug = true;
                try {
                    // 调用WebService
                    ht.call(soapAction, envelope);
                } catch (Exception e) {
                    Log.i("TAG", "----e.printStackTrace()----" + (e.getMessage()));
                    e.printStackTrace();
                }

                // 获取返回的数据
                SoapObject res = (SoapObject) envelope.bodyIn;
                // 获取返回的结果
                final String result = res.getProperty("getMobileCodeInfoResult").toString();
                int count = res.getAttributeCount();
                if (count != 0) {
                    for (int i = 0; i < count; i++) {
                        Log.i("TAG", "----result---->> " + res.getProperty(i).toString());
                    }
                }

                Log.i("TAG", "----result----" + res.getPropertyCount() + " : " + result);
                // 将WebService返回的结果显示在TextView中
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(onPhoneCallBack != null){
                            onPhoneCallBack.returnPhoneInfo(result);
                        }
                    }
                });

            }
        });
    }

    interface OnPhoneCallBack {
        void returnPhoneInfo(String result);
    }


}
