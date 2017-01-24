package com.luke.android.demo.viewlearn;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.luke.android.demo.R;

import static android.view.Window.FEATURE_NO_TITLE;

public class WebviewActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog dialog;
    private LinearLayout llayout;
    private EditText webSit_ET;
    private Button run_BTN;
    private Button refresh_BTN;
    private WebView web;
    private String url = "http://www.baidu.com/";

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
        web = ((WebView) findViewById(R.id.webview));
        llayout = ((LinearLayout) findViewById(R.id.llayout));


    }

    private void setListener() {
        run_BTN.setOnClickListener(this);
        refresh_BTN.setOnClickListener(this);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient() {

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog.show();
            }

            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                if (llayout.isShown()){
                    llayout.setVisibility(View.GONE);
                }
            }
        });
        web.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.run:
                String url = webSit_ET.getText().toString();
                web.loadUrl(url);
                break;
            case R.id.refresh:
                web.reload();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (web.canGoBack()) {
                web.goBack();
            }else{
                finish();
            }
        }
        return true;
    }
}
