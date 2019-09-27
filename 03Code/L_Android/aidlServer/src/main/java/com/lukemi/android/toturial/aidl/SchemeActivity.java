package com.lukemi.android.toturial.aidl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

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
        Log.e(TAG, "scheme:" + intent.getScheme());
        Uri uri = intent.getData();
        Log.e(TAG, "scheme: " + uri.getScheme());
        Log.e(TAG, "host: " + uri.getHost());
        Log.e(TAG, "port: " + uri.getPort());
        Log.e(TAG, "path: " + uri.getPath());
        Log.e(TAG, "queryString: " + uri.getQuery());
        Log.e(TAG, "id: " + uri.getQueryParameter("id"));
        Log.e(TAG, "type: " + uri.getQueryParameter("type"));

        result = "scheme: " + uri.getScheme() + "\n\n" +
                "host: " + uri.getHost() + "\n\n" +
                "port: " + uri.getPort() + "\n\n" +
                "path: " + uri.getPath() + "\n\n" +
                "queryString: " + uri.getQuery() + "\n\n" +
                "id: " + uri.getQueryParameter("id") + "\n\n" +
                "type: " + uri.getQueryParameter("type") + "\n\n";

    }


}
