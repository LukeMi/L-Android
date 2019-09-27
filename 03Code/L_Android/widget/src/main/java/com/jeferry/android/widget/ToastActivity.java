package com.jeferry.android.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lukemi.android.common.util.ToastUtil;

/**
 * @author mzchen
 * @date 2019/7/17 19:59
 * @des Toast
 * @mail
 */
public class ToastActivity extends AppCompatActivity {

    private Button mBtnToast;
    private String[] strings = new String[]{"第一条吐司", "第二条吐司", "第三条吐司",
            "第四条吐司", "第五条吐司"};
    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        initView();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    private void initView() {
        mBtnToast = findViewById(R.id.btn_toast);
        findViewById(R.id.btn_c_toast).setOnClickListener(this::onClick);
        mBtnToast.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_toast) {
            ToastUtil.show_makeText(getApplicationContext(), "吐司框", Toast.LENGTH_SHORT);
        } else if (id == R.id.btn_c_toast) {
            cToast();
        }

    }

    private void cToast() {
        ToastUtil.toast(strings[count]);
    }
}
