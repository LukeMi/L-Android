package com.lukemi.myandroid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SchemeActivity extends AppCompatActivity {

    private final String TAG = "SchemeActivity";
    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme);
        ButterKnife.bind(this);

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

        String result = "scheme: " + uri.getScheme() + "\n\n" +
                                "host: " + uri.getHost() + "\n\n" +
                                "port: " + uri.getPort() + "\n\n" +
                                "path: " + uri.getPath() + "\n\n" +
                                "queryString: " + uri.getQuery() + "\n\n" +
                                "id: " + uri.getQueryParameter("id") + "\n\n" +
                                "type: " + uri.getQueryParameter("type") + "\n\n";

        tv.setText(result);
    }

}
