package com.lukemi.android.collection.viewtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lukemi.android.collection.R;

public class CViewActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cview);
        initView();
    }

    private void initView() {
        findViewById(R.id.elecSign_CVA).setOnClickListener(this);
        findViewById(R.id.dialogUtil_CVA).setOnClickListener(this);
        findViewById(R.id.zoomHeadListView_CVA).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Class<?> targetClass = null;
        switch (id) {
            case R.id.elecSign_CVA:
                targetClass = EleSignActivity.class;
                break;
            case R.id.dialogUtil_CVA:
                targetClass = DialogUtilActivity.class;
                break;
            case R.id.zoomHeadListView_CVA:
                targetClass = ZoomHeadListViewActivity.class;
                break;
            default:
                Toast.makeText(this, "这是什么鬼", Toast.LENGTH_SHORT).show();
                break;
        }
        if (targetClass !=null){
            startActivity(new Intent(CViewActivity.this,targetClass));
        }
    }
}
