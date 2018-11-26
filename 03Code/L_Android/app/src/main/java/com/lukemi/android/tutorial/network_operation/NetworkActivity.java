package com.lukemi.android.tutorial.network_operation;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.common.util.Logcat;


public class NetworkActivity extends AppCompatActivity implements DownloadCallback, View.OnClickListener {

    // Keep a reference to the NetworkFragment, which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment mNetworkFragment;

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private boolean mDownloading = false;
    private Button loadBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initViews() {
        loadBTN = ((Button) findViewById(R.id.loadFM));
        loadBTN.setOnClickListener(this);

        mNetworkFragment = NetworkFragment.getInstance(getSupportFragmentManager(), "https://www.baidu.com");
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, mNetworkFragment);
    }

    private void startDownload() {
        if (!mDownloading && mNetworkFragment != null) {
            // Execute the async download.
            mNetworkFragment.startDownload();
            mDownloading = true;
        }
    }


    //------------------------------实现的接口方法开始-------------------------------------
    @Override
    public void updateFromDownload(Object result) {

    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch (progressCode) {
            // You can add UI behavior for progress updates here.
            case Progress.ERROR:
                Logcat.log(NetworkActivity.this.getClass().getSimpleName() + "progress ---->> " + "错误");
                break;
            case Progress.CONNECT_SUCCESS:
                Logcat.log(NetworkActivity.this.getClass().getSimpleName() + "progress ---->> " + "连接成功");
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
                Logcat.log(NetworkActivity.this.getClass().getSimpleName() + "progress ---->> " + "读取流成功");
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
                Logcat.log(NetworkActivity.this.getClass().getSimpleName() + "progress ---->> " + "进度：" + percentComplete);
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
                Logcat.log(NetworkActivity.this.getClass().getSimpleName() + "progress ---->> " + "结束");
                break;
        }
    }

    @Override
    public void finishDownloading() {
        mDownloading = false;
        if (mNetworkFragment != null) {
            mNetworkFragment.cancelDownload();
        }
    }

    //------------------------------实现的接口方法结束-------------------------------------
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loadFM:
                startDownload();
                break;
        }
    }
}
