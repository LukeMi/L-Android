package com.jeferry.android.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jeferry.android.widget.view.RoundAngleImageView;

/**
 * ImageView 空间学习，加载gif图片
 * <p>
 * created bt: tubg
 * created at: 2017/4/22 19:06
 * e_mail: tbug.chen@gmail.com / tbugchen@163.com
 */
public class ImageViewActivity extends AppCompatActivity {

    ImageView showIV;
    RoundAngleImageView roundAngleImageView;
    String url = "http://img5.imgtn.bdimg.com/it/u=2161251292,3731497031&fm=23&gp=0.jpg";
    String linux_url = "https:\\/\\/section.91360.com\\/Thumbnail\\/2016\\/5\\/20160518163147518_2016_5_18_164115_120.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        showIV = findViewById(R.id.showIV);
        roundAngleImageView = findViewById(R.id.roundAngleImageView);

        Glide
                .with(this)
                .load(url)
                .into(showIV);

        Glide
                .with(this)
                .load(linux_url)
                .into(roundAngleImageView);

    }
}
