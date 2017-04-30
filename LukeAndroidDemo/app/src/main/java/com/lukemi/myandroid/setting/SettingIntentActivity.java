package com.lukemi.myandroid.setting;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;


public class SettingIntentActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.lukemi.myandroid.R.layout.activity_setting_intent);
        initView();
    }

    /**
     * 初始化控件
     * <p>
     * created by: tbug
     * created at: 2017/4/9 18:09
     */
    private void initView() {
        findViewById(com.lukemi.myandroid.R.id.ACTION_ACCESSIBILITY_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_ADD_ACCOUNT).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_AIRPLANE_MODE_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_WIRELESS_SETTING).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_APN_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_APPLICATION_DETAILS_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_APPLICATION_DEVELOPMENT_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_APPLICATION_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_MANAGE_APPLICATIONS_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_BLUETOOTH_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_DATA_ROAMING_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_DATE_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_DEVICE_INFO_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_DISPLAY_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_DREAM_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_INPUT_METHOD_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_INTERNAL_STORAGE_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_MEMORY_CARD_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_LOCALE_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_LOCATION_SOURCE_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_NETWORK_OPERATOR_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_NFCSHARING_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_NFC_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_PRIVACY_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_QUICK_LAUNCH_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_SEARCH_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_SECURITY_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_SOUND_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_SYNC_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_USER_DICTIONARY_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_WIFI_IP_SETTINGS).setOnClickListener(this);
        findViewById(com.lukemi.myandroid.R.id.ACTION_WIFI_SETTINGS).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {

            Intent intent = new Intent();
            switch (v.getId()) {
                case com.lukemi.myandroid.R.id.ACTION_ACCESSIBILITY_SETTINGS:
                    intent.setAction(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_ADD_ACCOUNT:
                    intent.setAction(Settings.ACTION_ADD_ACCOUNT);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_AIRPLANE_MODE_SETTINGS:
                    intent.setAction(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_WIRELESS_SETTING:
                    intent.setAction(Settings.ACTION_WIFI_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_APN_SETTINGS:
                    intent.setAction(Settings.ACTION_APN_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_APPLICATION_DETAILS_SETTINGS:
                    Uri packageURI = Uri.parse("package:" + "com.tencent.WBlog");
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(packageURI);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_APPLICATION_DEVELOPMENT_SETTINGS:
                    intent.setAction(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_APPLICATION_SETTINGS:
                    intent.setAction(Settings.ACTION_APPLICATION_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS:
                    intent.setAction(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_MANAGE_APPLICATIONS_SETTINGS:
                    intent.setAction(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_BLUETOOTH_SETTINGS:
                    intent.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_DATA_ROAMING_SETTINGS:
                    intent.setAction(Settings.ACTION_DATA_ROAMING_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_DATE_SETTINGS:
                    intent.setAction(Settings.ACTION_DATE_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_DEVICE_INFO_SETTINGS:
                    intent.setAction(Settings.ACTION_DEVICE_INFO_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_DISPLAY_SETTINGS:
                    intent.setAction(Settings.ACTION_DISPLAY_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_DREAM_SETTINGS:
                    intent.setAction(Settings.ACTION_DREAM_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_INPUT_METHOD_SETTINGS:
                    intent.setAction(Settings.ACTION_INPUT_METHOD_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS:
                    intent.setAction(Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_INTERNAL_STORAGE_SETTINGS:
                    intent.setAction(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_MEMORY_CARD_SETTINGS:
                    intent.setAction(Settings.ACTION_MEMORY_CARD_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_LOCALE_SETTINGS:
                    intent.setAction(Settings.ACTION_LOCALE_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_LOCATION_SOURCE_SETTINGS:
                    intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_NETWORK_OPERATOR_SETTINGS:
                    intent.setAction(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_NFCSHARING_SETTINGS:
                    intent.setAction(Settings.ACTION_NFCSHARING_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_NFC_SETTINGS:
                    intent.setAction(Settings.ACTION_NFC_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_PRIVACY_SETTINGS:
                    intent.setAction(Settings.ACTION_PRIVACY_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_QUICK_LAUNCH_SETTINGS:
                    intent.setAction(Settings.ACTION_QUICK_LAUNCH_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_SEARCH_SETTINGS:
                    intent.setAction(Settings.ACTION_SEARCH_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_SECURITY_SETTINGS:
                    intent.setAction(Settings.ACTION_SECURITY_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_SETTINGS:
                    intent.setAction(Settings.ACTION_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_SOUND_SETTINGS:
                    intent.setAction(Settings.ACTION_SOUND_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_SYNC_SETTINGS:
                    intent.setAction(Settings.ACTION_SYNC_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_USER_DICTIONARY_SETTINGS:
                    intent.setAction(Settings.ACTION_USER_DICTIONARY_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_WIFI_IP_SETTINGS:
                    intent.setAction(Settings.ACTION_WIFI_IP_SETTINGS);
                    break;
                case com.lukemi.myandroid.R.id.ACTION_WIFI_SETTINGS:
                    intent.setAction(Settings.ACTION_WIFI_SETTINGS);
                    break;
                default:
                    break;
            }
            if (!TextUtils.isEmpty(intent.getAction())) {
                
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
