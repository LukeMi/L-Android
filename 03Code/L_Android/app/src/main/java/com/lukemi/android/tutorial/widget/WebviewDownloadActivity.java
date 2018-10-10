package com.lukemi.android.tutorial.widget;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.BaseActivity;
import com.lukemi.android.tutorial.util.Logcat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * webView 下载功能
 * Created by chenmz
 * on 2017/12/26 .
 */
public class WebviewDownloadActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView webview;
    private String TAG = this.getClass().getSimpleName();
    private String url = "https://comc.91360.com/app/appList.aspx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_webview_download;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                if (webview.canGoBack()) {
                    webview.goBack();
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initView() {
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webview.setWebChromeClient(mWebChromeClient);
        webview.setWebViewClient(mWebViewClient);
        webview.setDownloadListener(new MDownloader());
        webview.loadUrl(url);

    }

    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Logcat.log(TAG + " : " + "onProgressChanged");
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            Logcat.log(TAG + " : " + "onReceivedTitle");
        }

        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
            Logcat.log(TAG + " : " + "onReceivedIcon");
        }

        @Override
        public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
            super.onReceivedTouchIconUrl(view, url, precomposed);
            Logcat.log(TAG + " : " + "onReceivedTouchIconUrl");
        }
    };

    private WebViewClient mWebViewClient = new WebViewClient() {
        private ProgressDialog progressDialog;

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Logcat.log(TAG + " : " + "shouldOverrideUrlLoading(WebView view, String url)");
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Logcat.log(TAG + " : " + "shouldOverrideUrlLoading(WebView view, WebResourceRequest request)");
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            Logcat.log(TAG + " : " + "shouldInterceptRequest(WebView view, WebResourceRequest request)");
            return super.shouldInterceptRequest(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Logcat.log(TAG + " : " + "onPageStarted");
            progressDialog = new ProgressDialog(WebviewDownloadActivity.this, android.R.style.Widget_DeviceDefault_Light_ProgressBar_Horizontal);
            progressDialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Logcat.log(TAG + " : " + "onPageFinished");
            progressDialog.dismiss();
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
            Logcat.log(TAG + " : " + "onPageCommitVisible");
        }
    };

    private class MDownloader implements DownloadListener {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Logcat.log(TAG + " ：" + "onDownloadStart: " + url);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    }

}
