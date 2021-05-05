package com.jeferry.android.widget;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.common.view.SignView;


public class SignActivity extends AppCompatActivity {

    private SignView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        sv = findViewById(R.id.sv_);
        findViewById(R.id.sv_).setOnClickListener(this::onViewClicked);
        findViewById(R.id.tv_sign_color).setOnClickListener(this::onViewClicked);
        findViewById(R.id.tv_sign_size).setOnClickListener(this::onViewClicked);
        findViewById(R.id.tv_save).setOnClickListener(this::onViewClicked);
        findViewById(R.id.tv_reset).setOnClickListener(this::onViewClicked);
    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.sv_) {
            Toast.makeText(this.getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.tv_sign_color) {
            sv.setmTextColorRes(R.color.color_black);
        } else if (id == R.id.tv_sign_size) {
            sv.setmTextSize(R.dimen.sp39);
        } else if (id == R.id.tv_save) {
            save();
        } else if (id == R.id.tv_reset) {
            sv.reset();
        }
    }

    private void save() {
        int w = sv.getWidth();
        int h = sv.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmp);
        c.drawColor(Color.WHITE);
        /** 如果不设置canvas画布为白色，则生成透明 */
        sv.layout(0, 0, w, h);
        sv.draw(c);
        ImageView imageView = new ImageView(this);
        if (bmp == null) {
            return;
        }
        imageView.setImageBitmap(bmp);
        new AlertDialog.Builder(this)
                .setView(imageView)
                .create()
                .show();
    }


}
