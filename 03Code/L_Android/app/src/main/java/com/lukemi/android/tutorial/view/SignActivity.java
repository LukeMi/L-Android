package com.lukemi.android.tutorial.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignActivity extends AppCompatActivity {

    @BindView(R.id.sv_)
    SignView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.sv_, R.id.tv_sign_color, R.id.tv_sign_size, R.id.tv_save, R.id.tv_reset})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.sv_:
                Toast.makeText(this.getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_sign_color:
                sv.setmTextColorRes(R.color.color_black);
                break;
            case R.id.tv_sign_size:
                sv.setmTextSize(R.dimen.sp39);
                break;
            case R.id.tv_save:
                save();
                break;
            case R.id.tv_reset:
                sv.reset();
                break;
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
        imageView.setImageBitmap(bmp);
        AlertDialog.Builder b = new AlertDialog.Builder(this);
        b.setView(imageView)
                .create().show();
    }


}
