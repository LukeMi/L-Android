package com.lukemi.myandroid.widget;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lukemi.myandroid.R;
import com.lukemi.myandroid.util.DeviceUtil;
import com.lukemi.myandroid.util.Logcat;

import static android.view.Window.FEATURE_NO_TITLE;

public class WebviewActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog dialog;
    private LinearLayout llayout;
    private EditText webSit_ET;
    private Button run_BTN;
    private Button refresh_BTN;
    private Button clear_BTN;
    private WebView web;
    //    private String defaultURL = "http://www.baidu.com/";
    //web 缓存目录
    private static final String APP_CACHE_DIRNAME = "/webcache";
    private String defaultURL = "http://meeting.91360.com/Meeting/ShowDetails_436.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(FEATURE_NO_TITLE);
        setContentView(R.layout.activity_webview);
        initView();
        setListener();
    }

    private void initView() {
        dialog = new ProgressDialog(WebviewActivity.this);
        dialog.setMessage("加载中请稍候。。。");
        webSit_ET = ((EditText) findViewById(R.id.inputsite));
        run_BTN = ((Button) findViewById(R.id.run));
        refresh_BTN = ((Button) findViewById(R.id.refresh));
        clear_BTN = ((Button) findViewById(R.id.clear));
        web = ((WebView) findViewById(R.id.webview));
        llayout = ((LinearLayout) findViewById(R.id.llayout));
    }

    private void setListener() {
        WebSettings webSettings = web.getSettings();
        run_BTN.setOnClickListener(this);
        refresh_BTN.setOnClickListener(this);
        clear_BTN.setOnClickListener(this);
        webSettings.setJavaScriptEnabled(true);
        //设置缓存模式
        if (DeviceUtil.getNetType(this).equalsIgnoreCase(DeviceUtil.NETWORK_TYPE_NAME_UNKNOWN)
                    || DeviceUtil.getNetType(this).equalsIgnoreCase(DeviceUtil.NETWORK_TYPE_NAME_NONENETWORK)
                ) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        }
        // 开启 DOM storage API功能
        webSettings.setDomStorageEnabled(true);
        // 开启 database storage API 功能
        webSettings.setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath() + APP_CACHE_DIRNAME;
        Log.i("cachePath", cacheDirPath);
        // 设置数据库缓存路径
        webSettings.setAppCachePath(cacheDirPath);
        webSettings.setAppCacheEnabled(true);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Logcat.log("shouldOverrideUrlLoading url: " + url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Logcat.log("onPageStarted url: " + url);
                dialog.show();
            }

            public void onPageFinished(WebView view, String url) {
                Logcat.log("onPageFinished url: " + url);
                dialog.dismiss();
                if (llayout.isShown()) {
//                    llayout.setVisibility(View.GONE);
                }
            }
        });

        web.loadUrl(defaultURL);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.run:
                String inputUrl = webSit_ET.getText().toString();
                if (!inputUrl.startsWith("http://")) {
                    inputUrl = "http://" + inputUrl;
                }
//                web.loadUrl(inputUrl);
                web.loadUrl("homepathology91360://91360");
                break;
            case R.id.refresh:
                web.reload();
                break;
            case R.id.clear:
                web.clearCache(true);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (web.canGoBack()) {
                web.goBack();
            } else {
                finish();
            }
        }
        return true;
    }
}
