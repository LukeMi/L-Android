package com.lukemi.android.tutorial.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.common.util.Logcat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmotionalFaceActivity extends AppCompatActivity {

    @BindView(R.id.ef)
    EmotionalFaceView ef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emotional_face);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
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
    }


    @OnClick({R.id.left, R.id.right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.left:
                Logcat.log("left");
                ef.setEmotionStyle(EmotionalFaceView.HAPPY);
                break;
            case R.id.right:
                ef.setEmotionStyle(EmotionalFaceView.SAD);
                break;
        }
    }
}
