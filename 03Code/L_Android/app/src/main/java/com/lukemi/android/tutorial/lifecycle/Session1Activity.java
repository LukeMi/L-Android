package com.lukemi.android.tutorial.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lukemi.android.common.util.Logcat;


public class Session1Activity extends AppCompatActivity implements View.OnClickListener {

    private Button intentBTN_SA1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.tutorial.R.layout.activity_session1);
        intentBTN_SA1 = findViewById(com.lukemi.android.tutorial.R.id.intentBTN_SA1);
        intentBTN_SA1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.lukemi.android.tutorial.R.id.intentBTN_SA1:
                Intent intent = new Intent(this, Session2Activity.class);
                startActivity(intent);
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
