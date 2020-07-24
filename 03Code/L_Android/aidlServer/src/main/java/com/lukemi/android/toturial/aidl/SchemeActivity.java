package com.lukemi.android.toturial.aidl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.socks.library.KLog;

public class SchemeActivity extends AppCompatActivity {

    private String TAG = "TAG";
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);
        initData();
        initView();
    }

    private void initView() {
        TextView viewById = (TextView) findViewById(R.id.tv_result);
        viewById.setText(result);
    }

    private void initData() {
        Intent intent = getIntent();
        KLog.d(TAG, "scheme:" + intent.getScheme());
        String aidl = intent.getStringExtra("aidl");
        Uri uri = intent.getData();
        KLog.d(TAG, "scheme: " + uri.getScheme());
        KLog.d(TAG, "host: " + uri.getHost());
        KLog.d(TAG, "port: " + uri.getPort());
        KLog.d(TAG, "path: " + uri.getPath());
        KLog.d(TAG, "queryString: " + uri.getQuery());
        KLog.d(TAG, "id: " + uri.getQueryParameter("id"));
        KLog.d(TAG, "type: " + uri.getQueryParameter("type"));
        KLog.d(TAG, "bundle: " + aidl);

        result = "scheme: " + uri.getScheme() + "\n\n" +
                "host: " + uri.getHost() + "\n\n" +
                "port: " + uri.getPort() + "\n\n" +
                "path: " + uri.getPath() + "\n\n" +
                "queryString: " + uri.getQuery() + "\n\n" +
                "id: " + uri.getQueryParameter("id") + "\n\n" +
                "type: " + uri.getQueryParameter("type") + "\n\n";

    }


}
