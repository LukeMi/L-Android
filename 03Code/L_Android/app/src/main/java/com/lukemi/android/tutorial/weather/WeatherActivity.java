package com.lukemi.android.tutorial.weather;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.common.util.HttpUtils;
import com.lukemi.android.tutorial.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherActivity extends AppCompatActivity implements View.OnClickListener {

    //http://wthrcdn.etouch.cn/WeatherApi?city=%E8%8B%8F%E5%B7%9E
    private final String BASE_URL = "http://wthrcdn.etouch.cn/WeatherApi?city=";
    private final String BASE_URL2 = "http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=101121301";
    private TextView cityName;
    private ExecutorService threadPool = Executors.newFixedThreadPool(2);
    private String weatherInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
        loadWeatherInfo("安庆");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        cityName = findViewById(R.id.cityName);
    }

    /**
     * 获取天气信息
     *
     * @param cityName 地名
     */
    private void loadWeatherInfo(final String cityName) {

        threadPool.submit(new Runnable() {
            @Override
            public void run() {
                weatherInfo = HttpUtils.getString(BASE_URL + cityName);
            }
        });
        inFlateLayout(weatherInfo);
    }

    /**
     * 填充布局
     *
     * @param weatherInfo 天气信息
     */
    private void inFlateLayout(String weatherInfo) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cityName:
                break;
            case R.id.wrap_content:
                break;
        }
    }


}
