package com.lukemi.android.tutorial.evenbus;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forground);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.text_click)
    public void onViewClicked() {
        EventBus.getDefault().post("卧槽");
        finish();
    }
}
