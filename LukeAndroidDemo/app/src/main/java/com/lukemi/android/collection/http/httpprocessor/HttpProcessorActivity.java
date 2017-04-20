package com.lukemi.android.collection.http.httpprocessor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HttpProcessorActivity extends AppCompatActivity implements View.OnClickListener {

    String url = "http://toutiao.com/api/article/recent/?source=2&category=__all__&max_behot_time=0&_=1438843572950";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.android.collection.R.layout.activity_http_processor);
        textView = ((TextView) findViewById(com.lukemi.android.collection.R.id.shoeTV));
        findViewById(com.lukemi.android.collection.R.id.btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case com.lukemi.android.collection.R.id.btn:
                excuteHttp();
                break;
            default:
                break;
        }
    }

    private void excuteHttp() {
        HttpHelper
                .obtain()
                .url(url)
                .appendParams(null)
                .get(new CallBack() {
                    @Override
                    public void onError(Exception e) {

                    }

                    @Override
                    public void onSuccess(String result) {
                        textView.setText(result);
                    }
                });
    }
}
