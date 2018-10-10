package com.lukemi.android.tutorial.http.volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * ButterNife使用
 */
public class VollyActivity extends AppCompatActivity {
    @BindView(R.id.btn)
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volly);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn, R.id.btn1})//绑定多个点击事件
//    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                Toast.makeText(this, "btn", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
