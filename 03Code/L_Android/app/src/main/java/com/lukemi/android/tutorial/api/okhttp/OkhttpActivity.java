package com.lukemi.android.tutorial.api.okhttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.lukemi.android.common.util.HttpUtils;
import com.lukemi.android.tutorial.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class OkhttpActivity extends AppCompatActivity {
    public static final String TAG = OkhttpActivity.class.getSimpleName();
    private ProgressBar mProgress;
    private AppCompatButton mTvDownload;
    private AppCompatButton mTvDelete;
    private AppCompatImageView mIvImg;
    private List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initView();
    }

    private void initView() {
        mProgress = findViewById(R.id.progress);
        mTvDownload = findViewById(R.id.tv_download);
        mTvDelete = findViewById(R.id.tv_delete);
        mIvImg = findViewById(R.id.iv_img);
        mTvDownload.setOnClickListener(this::onClick);
        mTvDelete.setOnClickListener(this::onClick);
        mIvImg.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_download:
                downloadFile();
                break;
            case R.id.tv_delete:
                for (String s : list) {
                    File file = new File(s);
                    file.delete();
                }
                list.clear();
                break;
            case R.id.iv_img:
                downloadImg();
                break;
            default:
                break;
        }
    }

    private void downloadImg() {
        new Thread(() -> {
            String filePath = getExternalFilesDir(Environment.DIRECTORY_PICTURES) + File.separator + System.currentTimeMillis() + ".jpg";
            HttpUtils.downloadImg(HttpUtils.IMAGE_PATH, filePath, new HttpUtils.ProgressListener() {
                @Override
                public void onProgress(int progress) {

                }

                @Override
                public void onSuccess(String filePath) {
                    OkhttpActivity.this.runOnUiThread(() -> {
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(new File(filePath)));
                            OkhttpActivity.this.runOnUiThread(() -> {
                                mIvImg.setImageBitmap(bitmap);
                            });
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                public void onFailure(Throwable throwable) {

                }
            });
        }).start();
    }

    private void downloadFile() {
        new Thread(() -> {
            String filePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + System.currentTimeMillis() + ".apk";
            HttpUtils.download(HttpUtils.TOU_TIAO_URL, filePath, new HttpUtils.ProgressListener() {
                @Override
                public void onProgress(int progress) {
                    if (mProgress.getProgress() == progress) {
                        return;
                    }
                    OkhttpActivity.this.runOnUiThread(() -> {
                        Log.i(TAG, "onProgress: " + progress + ";" + mProgress.getMax() + " : " + mProgress.getProgress());
                        mProgress.setProgress(progress);
                        mProgress.setSecondaryProgress(progress);
                    });
                }

                @Override
                public void onSuccess(String filePath) {
                    Log.i(TAG, "onSuccess: " + filePath);
                    list.add(filePath);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    Log.i(TAG, "onFailure: " + throwable.getMessage());
                }
            });
        }).start();
    }
}