package com.lukemi.android.collection.db;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.collection.R.layout.activity_green_dao);

        initView();
    }

    private void initView() {
        findViewById(com.lukemi.android.collection.R.id.addDB).setOnClickListener(this);
        findViewById(com.lukemi.android.collection.R.id.delDB).setOnClickListener(this);
        findViewById(com.lukemi.android.collection.R.id.updateDB).setOnClickListener(this);
        findViewById(com.lukemi.android.collection.R.id.queryDB).setOnClickListener(this);
        findViewById(com.lukemi.android.collection.R.id.chgDBStructure).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.lukemi.android.collection.R.id.addDB:
                //增加
                break;
            case com.lukemi.android.collection.R.id.delDB:
                //删除
                break;
            case com.lukemi.android.collection.R.id.updateDB:
                //更新
                break;
            case com.lukemi.android.collection.R.id.queryDB:
                //查询
                break;
            case com.lukemi.android.collection.R.id.chgDBStructure:
                //升级
                break;
            default:
                break;
        }
    }
}
