package com.jeferry.android.widget;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.jeferry.android.widget.R;
import com.lukemi.android.common.util.ToastUtil;
import com.lukemi.android.tutorial.util.DialogUtil;

public class DialogUtilActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_dialog_util);
        initView();
    }

    private void initView() {
        findViewById( R.id.show_progressDialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.show_progressDialog) {
            showDlg();
        } else {
            ToastUtil.show_makeText(this, "这个位置有点尴尬", Toast.LENGTH_SHORT);
        }
    }

    private void showDlg() {
        progressDialog = DialogUtil.show_progressDialog(this, 0, null, "进度条对话框...", false, true);
        progressDialog.setOnKeyListener((dialog, keyCode, event) -> {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    progressDialog.setMessage("你点了返回键...");
                    break;
            }
            return false;
        });
    }
}
