package com.lukemi.myandroid.widget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lukemi.myandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JSAndroidActivity extends AppCompatActivity {

    @BindView(R.id.wv)
    WebView wv;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsandroid);
        ButterKnife.bind(this);
        initView();
    }

    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    private void initView() {
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
