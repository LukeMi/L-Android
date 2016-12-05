package com.luke.android.demo.util_qualify;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.luke.android.demo.R;
import com.luke.android.demo.util.DeviceInfoUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class DeviceutilActivity extends AppCompatActivity implements View.OnClickListener {
    private Button refreshBTN;
    private TextView showDeviceInfoTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviceutil);
        initView();
        setDeviceInfoToTV();
//        requestPermission();
    }

    /**
     * 请求权限
     */
    private void requestPermission() {
        if(!DeviceInfoUtil.isGetParamPermission(this, Manifest.permission.READ_PHONE_STATE)){
            DeviceInfoUtil.requestPermission(this,Manifest.permission.READ_PHONE_STATE);
        }
    }

    private void initView() {
        refreshBTN = ((Button) findViewById(R.id.refreshBTN_DUAct));
        refreshBTN.setOnClickListener(this);
        showDeviceInfoTV = ((TextView) findViewById(R.id.showDeviceInfo_DUAct));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.refreshBTN_DUAct:
                setDeviceInfoToTV();
                break;
        }
    }

    private void setDeviceInfoToTV() {
        String phoneBand = DeviceInfoUtil.getPhoneBrand();
        String phoneMode = DeviceInfoUtil.getPhoneMode();
        String language = DeviceInfoUtil.getLanguage(this);
        String osVersion = DeviceInfoUtil.getOSVersion();
        DisplayMetrics displayMetrics = DeviceInfoUtil.getDisplayMetrics(this);
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        float density = displayMetrics.density;
        String IMEI = DeviceInfoUtil.getIMEI(this);
        String IMSI = DeviceInfoUtil.getIMSI(this);
        String SIM = DeviceInfoUtil.getSIM(this);
        String phoneNumber = DeviceInfoUtil.getPhoneNumber(this);

        String netType = DeviceInfoUtil.getNetType(this);
        String netName = DeviceInfoUtil.getNetName(this);
        String ip = DeviceInfoUtil.getIp(this);
        String macID = DeviceInfoUtil.getMacID(this);

        String appName = DeviceInfoUtil.getAppName(this);
        String apppkg = DeviceInfoUtil.getApppakg(this);
        String appVersion = DeviceInfoUtil.getAppVersion(this);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("phoneBand", phoneBand);
        map.put("phoneMode", phoneMode);
        map.put("language", language);
        map.put("osVersion", osVersion);
        map.put("widthPixels", widthPixels);
        map.put("heightPixels", heightPixels);
        map.put("density", density);
        requestPermission();
        map.put("IMEI", IMEI);
        map.put("IMSI", IMSI);
        map.put("SIM", SIM);
        map.put("phoneNumber", phoneNumber);
        map.put("netType", netType);
        map.put("netName", netName);
        map.put("ip", ip);
        map.put("macID", macID);
        map.put("appName", appName);
        map.put("apppkg", apppkg);
        map.put("appVersion", appVersion);
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = String.valueOf(entry.getValue());
            builder.append(key + ":" + value + "\n");
        }
        String showStr = builder.toString();
        showDeviceInfoTV.setText(showStr);
    }
}
