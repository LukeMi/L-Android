package com.lukemi.android.tutorial.jsoup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.lukemi.android.tutorial.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.Charset;

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
