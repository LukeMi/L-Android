package com.lukemi.myandroid.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lukemi.myandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ImageView 空间学习，加载gif图片
 * <p>
 * created bt: tubg
 * created at: 2017/4/22 19:06
 * e_mail: tbug.chen@gmail.com / tbugchen@163.com
 */
public class ImageViewActivity extends AppCompatActivity {

    @BindView(R.id.showIV)
    ImageView showIV;

    String url = "http://img5.imgtn.bdimg.com/it/u=2161251292,3731497031&fm=23&gp=0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ButterKnife.bind(this);

        Glide
                .with(this)
                .load(url)
                .asGif()
                .placeholder(R.drawable.qqproduce)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .dontAnimate()
                .into(showIV);
    }
}
