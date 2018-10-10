package com.lukemi.android.tutorial.webservice;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lukemi.android.tutorial.BaseActivity;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.util.PatternUtil;

public class WebServiceActivity extends BaseActivity implements View.OnClickListener {

    private EditText inputET;
    private Button queryBTN;
    private TextView showTV;
    private ProgressDialog progressDialog;

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
        inputET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    showTV.setText("");
                } else {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.query_WebSer:

//                new SearchLocationTask("13856976635").execute();

                String phoneNumber = inputET.getText().toString();
                if (PatternUtil.isPhoneLegal(phoneNumber)) {
                    queryNUM(phoneNumber);
                } else {
                    Toast.makeText(this, "号码非法", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void queryNUM(String phoneNumber) {
        String nameSpace = "http://WebXml.com.cn/";     //命名空间 一个斜号都不能错
        String methodName = "getMobileCodeInfo";    //方法名
        String endPoint = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx";    //webservice url
        String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";    //soapAction
        openLoading();
        WebHttpUtil.getPhoneInfo(nameSpace, methodName, endPoint, soapAction, phoneNumber, onPhoneCallBack);
    }


    private WebHttpUtil.OnPhoneCallBack onPhoneCallBack = new WebHttpUtil.OnPhoneCallBack() {
        @Override
        public void returnPhoneInfo(String result) {
            closeLoading();
            setDeviceInfoToTV(result);
        }
    };

    private void setDeviceInfoToTV(String area) {
        showTV.setText(area);
    }

    private void openLoading() {
        progressDialog = new ProgressDialog(this, R.style.AppTheme);
        progressDialog.setMessage("请求中...");
        progressDialog.show();
    }

    private void closeLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
