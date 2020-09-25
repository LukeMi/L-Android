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
import com.socks.library.KLog;

public class BitmapActivity extends AppCompatActivity {

    private final String TAG = BitmapActivity.class.getSimpleName();

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
            options.inJustDecodeBounds = true;
//            options.inDensity = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                options.outConfig = Bitmap.Config.RGB_565;
            }
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.abc, options);
            int width = mIv.getWidth();
            int height = mIv.getHeight();
            int widthSize = options.outWidth / width;
//            int heightSize = options.outHeight /height;

            options.inJustDecodeBounds = false;
            options.inSampleSize = 2;
            Bitmap after = BitmapFactory.decodeResource(getResources(), R.drawable.abc, options);


            BitmapFactory.Options o = new BitmapFactory.Options();
            Bitmap oB = BitmapFactory.decodeResource(getResources(), R.drawable.abc, o);
            KLog.d(TAG, "bitmap==null  : " + (bitmap == null)
                            + " ;outWidth : " + options.outWidth
                            + " ;outHeight : " + options.outHeight
                            + " ;view width : " + width
                            + " ;view height : " + height
                            + " ; widthSize : " + widthSize
                            + " ; after RowBytes : " + after.getRowBytes()
                            + " ; oB ByteCount : " + oB.getByteCount()
                            + " ; ByteCount : " + after.getByteCount()
//                    + " ; heightSize : " + heightSize
            );

            mIv.setImageBitmap(after);
//            mIv.setImageBitmap(oB);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        mIv.setImageResource(R.drawable.abc);
//        mIv.setImageResource(R.drawable.stocksnap);

    }
}