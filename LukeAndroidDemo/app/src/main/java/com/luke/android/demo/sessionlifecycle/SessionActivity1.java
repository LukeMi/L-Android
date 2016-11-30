package com.luke.android.demo.sessionlifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.luke.android.demo.R;


public class SessionActivity1 extends AppCompatActivity implements View.OnClickListener {

    private Button intentBTN_SA1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session1);
        intentBTN_SA1 = ((Button) findViewById(R.id.intentBTN_SA1));
        intentBTN_SA1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.intentBTN_SA1:
                Intent intent = new Intent(this, SessionActivity2.class);
                startActivity(intent);
                break;
        }

    }
}
