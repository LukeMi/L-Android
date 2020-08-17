package com.jeferry.android.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

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
        Class<?> clazz = null;
        if (id == R.id.elecSign_CVA) {
            clazz = EleSignActivity.class;
        } else if (id == R.id.dialogUtil_CVA) {
            clazz = DialogActivity.class;
        } else if (id == R.id.zoomHeadListView_CVA) {
            clazz = ZoomHeadListViewActivity.class;
        } else {
            Toast.makeText(this, "这是什么鬼", Toast.LENGTH_SHORT).show();
        }
        if (clazz != null) {
            startActivity(new Intent(CViewActivity.this, clazz));
        }
    }
}
