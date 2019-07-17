package com.lukemi.android.tutorial.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.util.ToastUtil;

/**
 * @author mzchen
 * @date 2019/7/17 19:59
 * @des Toast
 * @mail
 */
public class ToastActivity extends AppCompatActivity {

    private Button mBtnToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        initView();
    }

    private void initView() {
        mBtnToast = findViewById(R.id.btn_toast);
        mBtnToast.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_toast:
                ToastUtil.show_makeText(getApplicationContext(), "吐司框", Toast.LENGTH_SHORT);
                break;
            default:
                break;
        }

    }
}
