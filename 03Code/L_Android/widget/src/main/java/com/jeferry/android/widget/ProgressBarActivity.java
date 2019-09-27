package com.jeferry.android.widget;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn).setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        if (view.getId() == R.id.btn) {
            dlg();
        }
    }

    private void dlg() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("加载中请稍候。。。");
        dialog.setCancelable(true);
        dialog.show();
    }
}
