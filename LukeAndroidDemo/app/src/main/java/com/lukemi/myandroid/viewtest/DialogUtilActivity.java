package com.lukemi.myandroid.viewtest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.lukemi.myandroid.util.ToastUtil;
import com.lukemi.myandroid.util.DialogUtil;

public class DialogUtilActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.myandroid.R.layout.activity_dialog_util);
        initView();
    }

    private void initView() {
        findViewById(com.lukemi.myandroid.R.id.show_progressDialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.lukemi.myandroid.R.id.show_progressDialog:
                progressDialog = DialogUtil.show_progressDialog(this, 0, null, "进度条对话框...", false, true);
                progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        switch (keyCode) {
                            case KeyEvent.KEYCODE_BACK:
                                progressDialog.setMessage("你点了返回键...");
                                break;
                        }
                        return false;
                    }
                });
                break;
            default:
                ToastUtil.show_makeText(this, "这个位置有点尴尬", Toast.LENGTH_SHORT);
                break;
        }
    }
}
