package com.lukemi.myandroid.viewtest;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukemi.myandroid.R;
import com.lukemi.myandroid.util.BitmapUtils;
import com.lukemi.myandroid.view.ResChangeImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResChangeImageViewActivity extends AppCompatActivity {

    @BindView(R.id.img)
    ResChangeImageView img;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    private List<Bitmap> res = new ArrayList<>();
    private List<Drawable> drawables = new ArrayList<>();

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_change_image_view);
        ButterKnife.bind(this);
        drawables.add(getResources().getDrawable(R.drawable.frame1));
        drawables.add(getResources().getDrawable(R.drawable.ic_launcher));
        drawables.add(getResources().getDrawable(R.drawable.frame2));
        drawables.add(getResources().getDrawable(R.drawable.frame3));

        for (int i = 0; i < drawables.size(); i++) {
            Drawable drawable = drawables.get(i);
            Bitmap bitmap = BitmapUtils.drawable2Bitmap(drawable);
            res.add(bitmap);
        }

        img.setOnImgeChangeListener(new ResChangeImageView.OnImgeChangeListener() {
            @Override
            public void onImgeChangeListener(boolean changed) {
                tvHint.setText(count + "");
            }
        });

    }

    @OnClick(R.id.btn_change)
    public void onViewClicked() {
        int index = count % drawables.size();
        Bitmap bitmap = res.get(index);
        img.setImageBitmap(bitmap);
        count++;
    }
}
