package com.lukemi.android.collection.webservice;

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
                SoapObject soapObject = new SoapObject(nameSpace, methodName);
                // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
                soapObject.addProperty("mobileCode", phoneNumber);
                soapObject.addProperty("userID", "");

                // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);

                envelope.bodyOut = soapObject;
                // 设置是否调用的是dotNet开发的WebService
                envelope.dotNet = false;
                // 等价于envelope.bodyOut = rpc;
                envelope.setOutputSoapObject(soapObject);

                HttpTransportSE transport = new HttpTransportSE(endPoint);
                try {
                    // 调用WebService
                    transport.call(soapAction, envelope);
                } catch (Exception e) {
                    Log.i("TAG", "----e.printStackTrace()----" + (e.getMessage()));
                    e.printStackTrace();
                }

                // 获取返回的数据
                SoapObject object = (SoapObject) envelope.bodyIn;
                // 获取返回的结果
                String result = object.getProperty(0).toString();

                Log.i("TAG", "----result----" + object.getPropertyCount()+" : "+result);
                // 将WebService返回的结果显示在TextView中
                onPhoneCallBack.returnPhoneInfo(result);
            }
        });
    }

    interface OnPhoneCallBack {
        void returnPhoneInfo(String result);
    }


}
