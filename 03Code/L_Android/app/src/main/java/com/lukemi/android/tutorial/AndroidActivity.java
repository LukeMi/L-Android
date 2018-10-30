package com.lukemi.android.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lukemi.android.tutorial.base.BaseActivity;

import butterknife.OnClick;

public class AndroidActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_android;
    }


    @OnClick(R.id.btn_ipc)
    public void onViewClicked(View view) {
        Class<?> target = null;
        switch (view.getId()) {
            case R.id.btn_ipc:
                target = IPCActivity.class;
                break;
        }
        if(target != null){
            startActivity(new Intent(this,target));
        }
    }
}
