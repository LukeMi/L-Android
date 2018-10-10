package com.lukemi.android.tutorial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lukemi.android.tutorial.baidu.BDActivity;

public class ThirdAPIActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_api);
        initView();
    }

    private void initView() {
        findViewById(R.id.bdActivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Class<?> targrtClass = null;
        switch (v.getId()) {
            case R.id.bdActivity:
                targrtClass = BDActivity.class;
                break;
            default:
                break;
        }
        if (targrtClass != null) {
            startActivity(new Intent(ThirdAPIActivity.this, targrtClass));
        }
    }
}
