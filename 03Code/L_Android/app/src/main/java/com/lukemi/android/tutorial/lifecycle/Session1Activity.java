package com.lukemi.android.tutorial.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;


public class Session1Activity extends AppCompatActivity implements View.OnClickListener {

    private Button intentBTN_SA1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.tutorial.R.layout.activity_session1);
        intentBTN_SA1 = findViewById(com.lukemi.android.tutorial.R.id.intentBTN_SA1);
        intentBTN_SA1.setOnClickListener(this);
        findViewById(R.id.tv_activity_option).setOnClickListener(this::onClick);
        Intent intent = new Intent(Session1Activity.this, Session3Activity.class);
        new Handler().postDelayed(() -> startActivity(intent), 3_000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.intentBTN_SA1:
                Intent intent = new Intent(this, Session2Activity.class);
                startActivity(intent);
                break;
            case R.id.tv_activity_option:
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                        .makeCustomAnimation(this, R.anim.anim_bottom_in, R.anim.anim_bottom_out);
                ActivityOptionActivity.start(this, optionsCompat);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        Logcat.log("Session1Activity onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Logcat.log("Session1Activity onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Logcat.log("Session1Activity onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Logcat.log("Session1Activity onDestroy");
        super.onDestroy();
    }
}
