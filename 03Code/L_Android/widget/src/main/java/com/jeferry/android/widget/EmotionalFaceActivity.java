package com.jeferry.android.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jeferry.android.widget.view.EmotionalFaceView;
import com.lukemi.android.common.util.Logcat;

public class EmotionalFaceActivity extends AppCompatActivity {


    EmotionalFaceView ef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotional_face);
        initView();
    }

    private void initView() {

        ef = findViewById(R.id.ef);
        ef.setOnEmotionViewClickListener(new EmotionalFaceView.OnEmotionViewClickListener() {
            @Override
            public void onLeftEyeClick() {
                System.out.println("onLeftEyeClick");
                Toast.makeText(EmotionalFaceActivity.this, "onLeftEyeClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightEyeClick() {
                Toast.makeText(EmotionalFaceActivity.this, "onRightEyeClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMouthClick() {
                Toast.makeText(EmotionalFaceActivity.this, "onMouthClick", Toast.LENGTH_SHORT).show();
            }
        });
         findViewById(R.id.left).setOnClickListener(this::onViewClicked);
         findViewById(R.id.right).setOnClickListener(this::onViewClicked);
    }


    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.left) {
            Logcat.log("left");
            ef.setEmotionStyle(EmotionalFaceView.HAPPY);
        } else if (id == R.id.right) {
            ef.setEmotionStyle(EmotionalFaceView.SAD);
        }
    }
}
