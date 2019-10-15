package com.lukemi.android.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lukemi.android.common.test.DiDiViewActivity;
import com.lukemi.android.common.test.FloatViewActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


//    @OnClick({R2.id.FontActivity,R2.id.DiDiViewActivity,R2.id.FloatViewActivity})
    public void onViewClicked(View view) {
        Intent intent = null;
        int i = view.getId();
         if (i == R.id.DiDiViewActivity) {
            intent = new Intent(this, DiDiViewActivity.class);

        } else if (i == R.id.FloatViewActivity) {
            intent = new Intent(this, FloatViewActivity.class);

        } else {
        }
        if (intent != null) {
            startActivity(intent);
        }

    }
}
