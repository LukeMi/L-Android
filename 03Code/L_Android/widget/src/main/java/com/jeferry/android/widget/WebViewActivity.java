package com.jeferry.android.widget;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.common.util.DeviceUtil;
import com.lukemi.android.common.util.Logcat;

import static android.view.Window.FEATURE_NO_TITLE;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog dialog;
    private LinearLayout llayout;
    private EditText webSit_ET;
    private Button run_BTN;
    private Button refresh_BTN;
    private Button clear_BTN;
    private WebView web;
    //    private String defURL = "http://www.baidu.com/";
    //web 缓存目录
    private static final String APP_CACHE_DIRNAME = "/webcache";
    //    private String defURL = "http://meeting.91360.com/Meeting/ShowDetails_436.html";
    private String defURL = "https://www.funcity.one/?ref=wxwalktomars";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(FEATURE_NO_TITLE);
        setContentView(R.layout.activity_webview);
        initView();
        setListener();
    }

    private void initView() {

        webSit_ET = findViewById(R.id.inputsite);
        run_BTN = findViewById(R.id.run);
        refresh_BTN = findViewById(R.id.refresh);
        clear_BTN = findViewById(R.id.clear);
        web = findViewById(R.id.webview);
        llayout = findViewById(R.id.llayout);
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

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Logcat.log("onPageStarted url: " + url);
                showDlg();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Logcat.log("onPageFinished url: " + url);
                dialog.dismiss();
                if (llayout.isShown()) {
//                    llayout.setVisibility(View.GONE);
                }
            }
        });

        web.loadUrl(defURL);
    }

    private void showDlg() {
        dialog = new ProgressDialog(WebViewActivity.this);
        dialog.setMessage("加载中请稍候。。。");
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.run) {
            String inputUrl = webSit_ET.getText().toString();
            if (!inputUrl.startsWith("http://")) {
                inputUrl = "http://" + inputUrl;
            }
            web.loadUrl(inputUrl);
//                web.loadUrl("homepathology91360://91360");
        } else if (id == R.id.refresh) {
            web.reload();
        } else if (id == R.id.clear) {
            web.clearCache(true);
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
