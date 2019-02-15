package com.lukemi.android.tutorial.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Session2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session2);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.task)
    public void onViewClicked() {
        Intent intent = new Intent(this,NewTaskActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
