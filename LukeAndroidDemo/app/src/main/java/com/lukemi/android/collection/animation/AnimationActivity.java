package com.lukemi.android.collection.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {


    private Button tween_AniBTN;
    private Button fram_AniBTN;
    private Button property_AniBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.collection.R.layout.activity_animation);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        tween_AniBTN = ((Button) findViewById(com.lukemi.android.collection.R.id.tween_Ani));
        fram_AniBTN = ((Button) findViewById(com.lukemi.android.collection.R.id.fram_Ani));
        property_AniBTN = ((Button) findViewById(com.lukemi.android.collection.R.id.property_Ani));
        tween_AniBTN.setOnClickListener(this);
        fram_AniBTN.setOnClickListener(this);
        property_AniBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch (id){
            case com.lukemi.android.collection.R.id.tween_Ani:
                intent.setClass(this,TweenActivity.class);
                break;
            case com.lukemi.android.collection.R.id.fram_Ani:
                intent.setClass(this,FrameActivity.class);
                break;
            case com.lukemi.android.collection.R.id.property_Ani:
                intent.setClass(this,PropertyActivity.class);
                break;
        }
        startActivity(intent);
    }
}
