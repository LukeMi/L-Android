package com.lukemi.android.tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lukemi.android.tutorial.baidu.BDActivity;
import com.lukemi.android.tutorial.handler.HandlerTestActivity;
import com.lukemi.android.tutorial.webservice.WebServiceActivity;

public class ThirdAPIActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_api);
        initView();
    }

    private void initView() {
        findViewById(R.id.bdActivity).setOnClickListener(this);
        findViewById(R.id.btn_webservice).setOnClickListener(this);
        findViewById(R.id.btn_handler).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class<?> targrtClass = null;
        switch (v.getId()) {
            case R.id.bdActivity:
                targrtClass = BDActivity.class;
                break;
            case R.id.btn_webservice:
                targrtClass = WebServiceActivity.class;
                break;
            case R.id.btn_handler:
                targrtClass = HandlerTestActivity.class;
                break;

            default:
                break;
        }
        if (targrtClass != null) {
            startActivity(new Intent(ThirdAPIActivity.this, targrtClass));
        }
    }
}
