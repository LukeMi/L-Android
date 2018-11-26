package com.lukemi.android.tutorial.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.SystemMemoryActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.bet_set_wall_paper, R.id.btn_system_intent, R.id.btn_memory_analyse})
    public void onViewClicked(View view) {
        Class<?> targetClass = null;
        switch (view.getId()) {
            case R.id.bet_set_wall_paper:
                targetClass = WallPaperActivity.class;
                break;
            case R.id.btn_system_intent:
                targetClass = SystemIntentActivity.class;
                break;
            case R.id.btn_memory_analyse:
                targetClass = SystemMemoryActivity.class;
                break;
        }
        if (targetClass != null) {
            startActivity(new Intent(SettingActivity.this, targetClass));
        }
    }
}
