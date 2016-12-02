package com.luke.android.demo.webservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.luke.android.demo.R;

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

    }

    private void setDeviceInfoToTV() {
    }
}
