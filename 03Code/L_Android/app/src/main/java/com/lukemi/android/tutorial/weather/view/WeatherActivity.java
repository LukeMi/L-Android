package com.lukemi.android.tutorial.weather.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.BaseActivity;
import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.weather.model.WeatherBean;
import com.lukemi.android.tutorial.weather.presenter.WeatherPresenter;

import java.net.UnknownHostException;

import butterknife.BindView;

public class WeatherActivity extends BaseActivity implements WeatherView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.wb)
    WebView wb;
    private ProgressDialog progressDialog;
    private  WeatherPresenter articlePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        request();
    }

    @Override
    protected void onDestroy() {
        articlePresenter.onDetchPresenter();
        super.onDestroy();
    }

    private void request() {
        articlePresenter = new WeatherPresenter(this);
//        articlePresenter.getArticle("article", 1);
        articlePresenter.getArticle("app", 101240101);

    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_article;
    }

    @Override
    protected void initView() {
        WebSettings settings = wb.getSettings();
        settings.setJavaScriptEnabled(true);
        wb.setWebChromeClient(new WebChromeClient() {

        });
        wb.setWebViewClient(new WebViewClient() {

        });
    }

    @Override
    public void loading() {
        openLoginLoading();
    }

    @Override
    public void hindLoading() {
        closeLoginLoading();
    }

    @Override
    public void onArticleSuccess(WeatherBean articleBean) {
        Logcat.log("articleBean : " + articleBean.toString());
        String message = articleBean.getCode();
        tvTitle.setText(message);
    }

    @Override
    public void onArticleError(Throwable throwable) {
        if (throwable instanceof UnknownHostException) {
            Toast.makeText(this, "Pls check network", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 打开 Loading
     */
    private void openLoginLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Register...");
        progressDialog.show();
    }

    /**
     * 关闭 Loading
     */
    private void closeLoginLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }
}
