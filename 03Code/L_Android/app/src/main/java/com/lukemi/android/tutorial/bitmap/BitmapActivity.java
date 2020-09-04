package com.lukemi.android.tutorial.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lukemi.android.tutorial.R;

public class BitmapActivity extends AppCompatActivity {
    private ImageView mIv;
    private Button mBtnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        mIv = (ImageView) findViewById(R.id.iv);
        mBtnLoad = (Button) findViewById(R.id.btn_load);
        mBtnLoad.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_load) {
            compress();
        }
    }

    private void compress() {
        try {


            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDensity = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                options.outConfig = Bitmap.Config.RGB_565;
            }
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.abc, options);
//            mIv.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        mIv.setImageResource(R.drawable.abc);
        mIv.setImageResource(R.drawable.stocksnap);

    }
}