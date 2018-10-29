package com.lukemi.android.tutorial.category;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lukemi.android.tutorial.MainActivity;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.BaseActivity;
import com.lukemi.android.tutorial.sessionlifecycle.SessionActivity1;

import butterknife.OnClick;


public class ComponentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_component;
    }


    @OnClick({R.id.btn_activity, R.id.btn_service, R.id.btn_receiver, R.id.btn_provider})
    public void onViewClicked(View view) {
        Class<?> targrtClass = null;
        switch (view.getId()) {
            case R.id.btn_activity:
                targrtClass = SessionActivity1.class;
                break;
            case R.id.btn_service:
                break;
            case R.id.btn_receiver:
                break;
            case R.id.btn_provider:
                break;
        }
        if (targrtClass != null) {
            startActivity(new Intent(this, targrtClass));
        }
    }
}
