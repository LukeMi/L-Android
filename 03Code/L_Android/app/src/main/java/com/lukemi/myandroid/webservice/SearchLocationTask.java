package com.lukemi.myandroid.webservice;

import android.os.AsyncTask;
import android.os.Looper;

import com.lukemi.myandroid.util.Logcat;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by android on 2017/5/16.
 */

public class SearchLocationTask extends AsyncTask{
    private String methodName;
    private String soapAction;
    private String phoneNum;
    private String getPropertyName;
    public SearchLocationTask(String phoneNum){
        this.methodName = Constant.GET_LOCATION_METHOD_NAME;
        this.soapAction = Constant.GET_LOCATION_SOAP_ACTION;
        this.phoneNum= phoneNum;
        this.getPropertyName = Constant.GET_LOCATION_RESULT_NAME;
    }
    @Override
    protected Object doInBackground(Object... params) {
        // TODO Auto-generated method stub
        SoapObject detail = null;
        Object result = null;
        try{
            SoapObject rpc = new SoapObject(Constant.NAMESPACE, methodName);
            rpc.addProperty("mobileCode", phoneNum);
            Looper.prepare();
            HttpTransportSE ht = new HttpTransportSE(Constant.URL);
            ht.debug = true;
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = rpc;
            envelope.dotNet = true;
            envelope.setOutputSoapObject(rpc);
            ht.call(soapAction, envelope);
            detail = (SoapObject) envelope.bodyIn;
            result =  detail.getProperty(getPropertyName);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    @Override
    protected void onPostExecute(Object result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        /**
         * 解析数据
         */
        String resultStr = result.toString();
        String[] resultList = resultStr.split(" ");
        Logcat.log("resultStr: "+resultStr);
        if(resultList.length == 1){
            //获取字符串汇中的中文列表
//            Util.showMessageByToast(currentContext, resultList[0]);
        }else{
            //获取省份名
            String province = Constant.GetChineseWord(resultList[0]).get(0);
            //获取城市名
            String city = resultList[1];
            int length = province.length()+2;
            //获取运营商
            String service = resultList[2].substring(0,length);
            //获取卡类型
            String cardType = resultList[2].substring(length);
            //展示
            //......
        }
    }
}
