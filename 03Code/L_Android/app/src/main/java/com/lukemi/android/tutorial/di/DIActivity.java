package com.lukemi.android.tutorial.di;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.jeferry.android.annotation.ClassFinder;
import com.lukemi.android.tutorial.R;

public class DIActivity extends AppCompatActivity {

    GarageGlobalSetting garageGlobalSetting =
            ClassFinder.findClass(GarageGlobalSetting.class);

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_i);
        mTv = (TextView) findViewById(R.id.tv);
        mTv.setOnClickListener(this::onClick);


    }

    private void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv) {
            mTv.setText(JSON.toJSONString(garageGlobalSetting));
        }
    }
}