package com.jeferry.android.widget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class JSAndroidActivity extends AppCompatActivity {

    WebView wv;

    private static Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsandroid);
        initView();
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    private void initView() {
        wv = findViewById(R.id.wv);
        WebSettings wvSettings = wv.getSettings();
        wvSettings.setJavaScriptEnabled(true);
        wv.addJavascriptInterface(new DemoJavaScriptInterface(), "myAndroid");
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                wv.loadUrl("javascript:clickOnAndroid");
            }
        });
        wv.loadUrl("file:///android_asset/js.html");
//        wv.loadUrl("file:///android_asset/schemeTest.html");
    }

    class DemoJavaScriptInterface {
        DemoJavaScriptInterface() {
        }

        public void clickOnAndroid() {
            mHandler.post(new Runnable() {
                public void run() {
                    Log.e("Tag", "被js调用了！");
                }
            });
        }

    }

}
