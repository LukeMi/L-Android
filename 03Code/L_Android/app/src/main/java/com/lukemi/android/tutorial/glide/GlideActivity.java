package com.lukemi.android.tutorial.glide;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.BaseActivity;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.OnClick;

public class GlideActivity extends BaseActivity {

    @BindView(R.id.img_thumb)
    ImageView imgThumb;

    private String url = "http://photocdn.sohu.com/20151126/mp44425938_1448498418499_7.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_glide;
    }

    @Override
    protected void getIntentData() {
        Intent intent = getIntent();
        System.out.println("intent == null :  " + (intent == null));
    }

    @Override
    protected void initView() {
        int x = 0;
    }

    @OnClick(R.id.btn_load)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_load:
                GlideUtil.loadImgRoot(this, imgThumb, url,true,true);
                break;
        }
    }
}
