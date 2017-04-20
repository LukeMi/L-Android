package com.lukemi.android.collection.utiltest;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.lukemi.android.collection.BaseActivity;
import com.lukemi.android.collection.util.Logcat;
import com.lukemi.android.collection.view.ShowBigPicClass;
import com.lukemi.android.collection.util.BitmapUtils;

/**
 * BitmapUtil 帮助类<btr/>
 * 验证
 * 使用ImageView 设置 OnTouchListener,实现放大功能
 * <ImageView 弹出对话框消失，imageVIew会重画></ImageView>
 */
public class BitmapUtilActivity extends BaseActivity implements View.OnClickListener {

    private ImageView showIV;
    private Bitmap bitmap;
    private final int MSG_EVENT = 0x0001;
    private long eventTime = 0;
    private Handler mhandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_EVENT:
                    eventTime += 300;
                    mhandler.sendEmptyMessageDelayed(MSG_EVENT, 300);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.collection.R.layout.activity_bitmap_util);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        showIV = ((ImageView) findViewById(com.lukemi.android.collection.R.id.showIV));
        bitmap = BitmapUtils.view2Bitmap(showIV);
//        showIV.setOnClickListener(this);
        showIV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                float x_DOWN = 0;
                float y_DOWN = 0;
                float x_MOVE;
                float y_MOVE;
                Bitmap b = bitmap;

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Logcat.log("----MotionEvent.ACTION_DOWN----");
                        if (!mhandler.hasMessages(MSG_EVENT)) {
                            mhandler.sendEmptyMessageDelayed(MSG_EVENT, 300);//开始统计时间
                        }

                        x_DOWN = event.getX();
                        y_DOWN = event.getY();
                        Logcat.log("x_DOWN: " + x_DOWN + " ;y_DOWN: " + y_DOWN);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Logcat.log("----MotionEvent.ACTION_MOVE----");
                        x_MOVE = event.getX();
                        y_MOVE = event.getY();
                        float x_delt = x_MOVE - x_DOWN;
                        float y_delt = y_MOVE - y_DOWN;
                        Logcat.log("x_MOVE: " + x_MOVE + " ;y_MOVE: " + y_MOVE + " ;x_MOVE - x_DOWN: " + String.valueOf(x_delt) + " ;y_MOVE - y_DOWN " + String.valueOf(y_delt));
                        if ((Math.abs(x_MOVE - x_DOWN) > 10 || Math.abs(y_MOVE - y_DOWN) > 10) && eventTime > 300) {
                            b = BitmapUtils.zoomBitmap(b, (int) ((x_MOVE - x_DOWN + 1)), (int) ((y_MOVE - y_DOWN + 1)));
                            showIV.setImageBitmap(b);
                            Logcat.log("0----------------dddddddddddd-----------------------");
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        Logcat.log("----MotionEvent.ACTION_UP----");
                        showIV.setImageBitmap(bitmap);
                        if (eventTime < 300) {
                            ShowBigPicClass sbpc = new ShowBigPicClass(BitmapUtilActivity.this, bitmap);
                            sbpc.showDetailPhoto();
                        }
                        if (mhandler.hasMessages(MSG_EVENT)) {
                            eventTime = 0;
                            mhandler.removeMessages(MSG_EVENT);//移除消息
                        }
                        break;
                }
                return true;
            }
        });
        findViewById(com.lukemi.android.collection.R.id.compressBitmap).setOnClickListener(this);
        findViewById(com.lukemi.android.collection.R.id.zoomBitmap).setOnClickListener(this);
        findViewById(com.lukemi.android.collection.R.id.getRoundedCornerBitmap).setOnClickListener(this);
        findViewById(com.lukemi.android.collection.R.id.createReflectionImageWithOrigin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.lukemi.android.collection.R.id.showIV:
                ShowBigPicClass sbpc = new ShowBigPicClass(BitmapUtilActivity.this, bitmap);
                sbpc.showDetailPhoto();
                break;

            case com.lukemi.android.collection.R.id.compressBitmap:
                byte[] bytes = BitmapUtils.bitmap2ByteArray(bitmap);
                Logcat.log("----BitmapUtilActivity----compressBitmap---->> " + "bytes.length: " +
                        bytes.length + " ;bitmap.getByteCount(): " + bitmap.getByteCount());
                showIV.setImageBitmap(BitmapUtils.compressBitmap(bytes, 100, 150));
                break;

            case com.lukemi.android.collection.R.id.zoomBitmap:
                showIV.setImageBitmap(BitmapUtils.zoomBitmap(bitmap, 100, 150));
                break;

            case com.lukemi.android.collection.R.id.getRoundedCornerBitmap:
                showIV.setImageBitmap(BitmapUtils.getRoundedCornerBitmap(bitmap, 50));
                break;

            case com.lukemi.android.collection.R.id.createReflectionImageWithOrigin:
                showIV.setImageBitmap(BitmapUtils.createReflectionImageWithOrigin(bitmap));
                break;
        }
    }
}
