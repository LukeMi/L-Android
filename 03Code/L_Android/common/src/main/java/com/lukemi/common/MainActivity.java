package com.lukemi.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lukemi.common.test.DiDiViewActivity;
import com.lukemi.common.test.FloatViewActivity;
import com.lukemi.common.test.FontActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.FontActivity,R.id.DiDiViewActivity,R.id.FloatViewActivity})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.FontActivity:
                intent = new Intent(this, FontActivity.class);
                break;
            case R.id.DiDiViewActivity:
                intent = new Intent(this, DiDiViewActivity.class);
                break;
            case R.id.FloatViewActivity:
                intent = new Intent(this, FloatViewActivity.class);
                break;
            default:
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }

    }
}
