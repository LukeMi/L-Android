package com.lukemi.android.tutorial.jsoup;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;

import org.jsoup.nodes.Document;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JsoupActivity extends AppCompatActivity {

    @BindView(R.id.wv)
    WebView wv;
    String url = "http://www.91360.com//201706//60//56039.html";
    private Document doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);
        ButterKnife.bind(this);
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

    }
}
