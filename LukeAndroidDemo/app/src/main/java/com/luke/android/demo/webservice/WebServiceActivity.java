package com.luke.android.demo.webservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.luke.android.demo.R;
import com.luke.android.demo.util.PatternUtil;

public class WebServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputET;
    private Button queryBTN;
    private TextView showTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        inputET = ((EditText) findViewById(R.id.inputPhone_WebSer));
        queryBTN = ((Button) findViewById(R.id.query_WebSer));
        showTV = ((TextView) findViewById(R.id.showResult_Webser));
        queryBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.query_WebSer:
                String phoneNumber = inputET.getText().toString();
                if (PatternUtil.isPhoneLegal(phoneNumber)) {
                    queryArea(phoneNumber);
                } else {
                    Toast.makeText(this, "号码非法", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private void queryArea(String phoneNumber) {
        String nameSpace = "http://WebXml.com.cn";     //命名空间
        String methodName = "getMobileCodeInfoResponse";    //方法名
        String endPoint = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx";    //webservice url
        String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";    //soapAction
        String onPhoneCallBack = ""; //回调接口
        WebHttpUtil.getPhoneInfo(nameSpace, methodName, endPoint, soapAction, phoneNumber, new WebHttpUtil.OnPhoneCallBack() {
            @Override
            public void returnPhoneInfo(String result) {
                setDeviceInfoToTV(result);
            }
        });
    }

    private void setDeviceInfoToTV(String area) {
        showTV.setText(area);
    }
}
