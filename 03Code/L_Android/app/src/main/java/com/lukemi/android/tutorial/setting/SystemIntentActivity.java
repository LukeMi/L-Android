package com.lukemi.android.tutorial.setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.lukemi.android.tutorial.AppInfoActivity;
import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.BaseActivity;


public class SystemIntentActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_system_intent;
    }

    /**
     * 初始化控件
     * <p>
     * created by: tbug
     * created at: 2017/4/9 18:09
     */
    @Override
    protected void initView() {
        findViewById(R.id.ACTION_ACCESSIBILITY_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_ADD_ACCOUNT).setOnClickListener(this);
        findViewById(R.id.ACTION_AIRPLANE_MODE_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_WIRELESS_SETTING).setOnClickListener(this);
        findViewById(R.id.ACTION_APN_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_APPLICATION_DETAILS_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_APPLICATION_DEVELOPMENT_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_APPLICATION_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_MANAGE_APPLICATIONS_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_BLUETOOTH_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_DATA_ROAMING_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_DATE_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_DEVICE_INFO_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_DISPLAY_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_DREAM_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_INPUT_METHOD_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_INTERNAL_STORAGE_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_MEMORY_CARD_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_LOCALE_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_LOCATION_SOURCE_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_NETWORK_OPERATOR_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_NFCSHARING_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_NFC_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_PRIVACY_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_QUICK_LAUNCH_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_SEARCH_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_SECURITY_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_SOUND_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_SYNC_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_USER_DICTIONARY_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_WIFI_IP_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_WIFI_SETTINGS).setOnClickListener(this);
        findViewById(R.id.ACTION_app_info).setOnClickListener(this);
        findViewById(R.id.AppInfoActivity).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {

            Intent intent = new Intent();
            switch (v.getId()) {
                case R.id.ACTION_ACCESSIBILITY_SETTINGS:
                    intent.setAction(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                    break;
                case R.id.ACTION_ADD_ACCOUNT:
                    intent.setAction(Settings.ACTION_ADD_ACCOUNT);
                    break;
                case R.id.ACTION_AIRPLANE_MODE_SETTINGS:
                    intent.setAction(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
                    break;
                case R.id.ACTION_WIRELESS_SETTING:
                    intent.setAction(Settings.ACTION_WIFI_SETTINGS);
                    break;
                case R.id.ACTION_APN_SETTINGS:
                    intent.setAction(Settings.ACTION_APN_SETTINGS);
                    break;
                case R.id.ACTION_APPLICATION_DETAILS_SETTINGS:
                    Uri packageURI = Uri.parse("package:" + "com.tencent.WBlog");
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(packageURI);
                    break;
                case R.id.ACTION_APPLICATION_DEVELOPMENT_SETTINGS:
                    intent.setAction(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS);
                    break;
                case R.id.ACTION_APPLICATION_SETTINGS:
                    intent.setAction(Settings.ACTION_APPLICATION_SETTINGS);
                    break;
                case R.id.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS:
                    intent.setAction(Settings.ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS);
                    break;
                case R.id.ACTION_MANAGE_APPLICATIONS_SETTINGS:
                    intent.setAction(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
                    break;
                case R.id.ACTION_BLUETOOTH_SETTINGS:
                    intent.setAction(Settings.ACTION_BLUETOOTH_SETTINGS);
                    break;
                case R.id.ACTION_DATA_ROAMING_SETTINGS:
                    intent.setAction(Settings.ACTION_DATA_ROAMING_SETTINGS);
                    break;
                case R.id.ACTION_DATE_SETTINGS:
                    intent.setAction(Settings.ACTION_DATE_SETTINGS);
                    break;
                case R.id.ACTION_DEVICE_INFO_SETTINGS:
                    intent.setAction(Settings.ACTION_DEVICE_INFO_SETTINGS);
                    break;
                case R.id.ACTION_DISPLAY_SETTINGS:
                    intent.setAction(Settings.ACTION_DISPLAY_SETTINGS);
                    break;
                case R.id.ACTION_DREAM_SETTINGS:
                    intent.setAction(Settings.ACTION_DREAM_SETTINGS);
                    break;
                case R.id.ACTION_INPUT_METHOD_SETTINGS:
                    intent.setAction(Settings.ACTION_INPUT_METHOD_SETTINGS);
                    break;
                case R.id.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS:
                    intent.setAction(Settings.ACTION_INPUT_METHOD_SUBTYPE_SETTINGS);
                    break;
                case R.id.ACTION_INTERNAL_STORAGE_SETTINGS:
                    intent.setAction(Settings.ACTION_INTERNAL_STORAGE_SETTINGS);
                    break;
                case R.id.ACTION_MEMORY_CARD_SETTINGS:
                    intent.setAction(Settings.ACTION_MEMORY_CARD_SETTINGS);
                    break;
                case R.id.ACTION_LOCALE_SETTINGS:
                    intent.setAction(Settings.ACTION_LOCALE_SETTINGS);
                    break;
                case R.id.ACTION_LOCATION_SOURCE_SETTINGS:
                    intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    break;
                case R.id.ACTION_NETWORK_OPERATOR_SETTINGS:
                    intent.setAction(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
                    break;
                case R.id.ACTION_NFCSHARING_SETTINGS:
                    intent.setAction(Settings.ACTION_NFCSHARING_SETTINGS);
                    break;
                case R.id.ACTION_NFC_SETTINGS:
                    intent.setAction(Settings.ACTION_NFC_SETTINGS);
                    break;
                case R.id.ACTION_PRIVACY_SETTINGS:
                    intent.setAction(Settings.ACTION_PRIVACY_SETTINGS);
                    break;
                case R.id.ACTION_QUICK_LAUNCH_SETTINGS:
                    intent.setAction(Settings.ACTION_QUICK_LAUNCH_SETTINGS);
                    break;
                case R.id.ACTION_SEARCH_SETTINGS:
                    intent.setAction(Settings.ACTION_SEARCH_SETTINGS);
                    break;
                case R.id.ACTION_SECURITY_SETTINGS:
                    intent.setAction(Settings.ACTION_SECURITY_SETTINGS);
                    break;
                case R.id.ACTION_SETTINGS:
                    intent.setAction(Settings.ACTION_SETTINGS);
                    break;
                case R.id.ACTION_SOUND_SETTINGS:
                    intent.setAction(Settings.ACTION_SOUND_SETTINGS);
                    break;
                case R.id.ACTION_SYNC_SETTINGS:
                    intent.setAction(Settings.ACTION_SYNC_SETTINGS);
                    break;
                case R.id.ACTION_USER_DICTIONARY_SETTINGS:
                    intent.setAction(Settings.ACTION_USER_DICTIONARY_SETTINGS);
                    break;
                case R.id.ACTION_WIFI_IP_SETTINGS:
                    intent.setAction(Settings.ACTION_WIFI_IP_SETTINGS);
                    break;
                case R.id.ACTION_WIFI_SETTINGS:
                    intent.setAction(Settings.ACTION_WIFI_SETTINGS);
                    break;
                case R.id.ACTION_app_info:
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.fromParts("package", this.getPackageName(), null));
                case R.id.AppInfoActivity:
                    intent = new Intent(this, AppInfoActivity.class);
                    break;
                default:
                    break;
            }
            if (intent != null) {
                startActivity(intent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
