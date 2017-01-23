package com.luke.android.demo.testutil;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.luke.android.demo.R;
import com.luke.android.demo.util.DeviceUtil;

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
    }

    /**
     * 请求权限
     */
    private void requestPermission() {
        if (!DeviceUtil.isGetParamPermission(this, Manifest.permission.READ_PHONE_STATE)) {
            DeviceUtil.requestPermission(this, Manifest.permission.READ_PHONE_STATE);
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
        String phoneBand = DeviceUtil.getPhoneBrand();
        String phoneMode = DeviceUtil.getPhoneMode();
        String language = DeviceUtil.getLanguage(this);
        String osVersion = DeviceUtil.getOSVersion();
        DisplayMetrics displayMetrics = DeviceUtil.getDisplayMetrics(this);
        int widthPixels = displayMetrics.widthPixels;
        int heightPixels = displayMetrics.heightPixels;
        float density = displayMetrics.density;
        String IMEI = DeviceUtil.getIMEI(this);
        String IMSI = DeviceUtil.getIMSI(this);
        String SIM = DeviceUtil.getSIM(this);
        String phoneNumber = DeviceUtil.getPhoneNumber(this);

        String netType = DeviceUtil.getNetType(this);
        String netName = DeviceUtil.getNetName(this);
        String ip = DeviceUtil.getIp(this);
        String macID = DeviceUtil.getMacID(this);

        String appName = DeviceUtil.getAppName(this);
        String apppkg = DeviceUtil.getApppakg(this);
        String appVersion = DeviceUtil.getAppVersion(this);
        String androidId = DeviceUtil.getANDROID_ID(this);
        String udid = DeviceUtil.getUdid(this);
        String TotalMemory = DeviceUtil.getTotalMemory(this);
        String getAvailMemory = DeviceUtil.getAvailMemory(this);
        String CpuName = DeviceUtil.getCpuName();
        String MaxCpuFreq = DeviceUtil.getMaxCpuFreq();
        String MinCpuFreq = DeviceUtil.getMinCpuFreq();
        String CurCpuFreq = DeviceUtil.getCurCpuFreq();
        String cpuSN = DeviceUtil.getCPUSerial();
        int CPUNumCores = DeviceUtil.getCPUNumCores();
        String XX = DeviceUtil.getXX(this);

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("phoneBand", phoneBand);
        map.put("phoneMode", phoneMode);
        map.put("language", language);
        map.put("osVersion", osVersion);
        map.put("widthPixels", widthPixels);
        map.put("heightPixels", heightPixels);
        map.put("density", density);
        requestPermission();
        map.put("macID", macID);
        map.put("IMEI", IMEI);
        map.put("androidId", androidId);
        map.put("udid", udid);
        map.put("IMSI", IMSI);
        map.put("SIM", SIM);
        map.put("phoneNumber", phoneNumber);
        map.put("netType", netType);
        map.put("netName", netName);
        map.put("ip", ip);
        map.put("appName", appName);
        map.put("apppkg", apppkg);
        map.put("appVersion", appVersion);

        map.put("TotalMemory", TotalMemory);
        map.put("getAvailMemory", getAvailMemory);
        map.put("CpuName", CpuName);
        map.put("MaxCpuFreq", MaxCpuFreq);
        map.put("MinCpuFreq", MinCpuFreq);
        map.put("CurCpuFreq", CurCpuFreq);
        map.put("CPUNumCores", CPUNumCores);
        map.put("CPU_SN", cpuSN);
        map.put("XX", XX);
        try {
//            Logcat.log("----DeDeviceutilActivity----getBuildInfo----" + DeviceUtil.getBuildInfo());
        } catch (Exception e) {
            e.printStackTrace();
        }

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
