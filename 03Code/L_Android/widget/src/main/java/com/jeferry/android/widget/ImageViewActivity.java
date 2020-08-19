package com.jeferry.android.widget;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
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

    private ImageView showIV;
    private RoundAngleImageView roundAngleImageView;
    private ImageView mIvDrawableWrapPre;
    private ImageView mIvDrawableWrapAfter;
    String url = "https://app-icon.pgyer.com/4/a/9/c/5/4a9c592c26da5c63b72e98df4ca3551e?x-oss-process=image/format,jpg";
    String linux_url = "https:\\/\\/section.91360.com\\/Thumbnail\\/2016\\/5\\/20160518163147518_2016_5_18_164115_120.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        initView();
        initData();
    }

    private void initView() {
        showIV = findViewById(R.id.showIV);
        roundAngleImageView = findViewById(R.id.roundAngleImageView);
        mIvDrawableWrapPre = (ImageView) findViewById(R.id.iv_drawable_wrap_pre);
        mIvDrawableWrapAfter = (ImageView) findViewById(R.id.iv_drawable_wrap_after);
    }

    private void initData() {
        Glide
                .with(this)
                .load(url)
                .into(showIV);

        Glide
                .with(this)
                .load(linux_url)
                .into(roundAngleImageView);

        drawableCompat();
    }

    private void drawableCompat() {
        Drawable drawable = getResources().getDrawable(R.drawable.ic_wifi_black_24dp);
        Drawable wrap = DrawableCompat.wrap(drawable).mutate();
        DrawableCompat.setTint(wrap, Color.parseColor("#ffff0000"));
        mIvDrawableWrapPre.setImageResource(R.drawable.ic_wifi_black_24dp);
        mIvDrawableWrapAfter.setImageDrawable(wrap);
    }
}
