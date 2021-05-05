package com.lukemi.android.tutorial.utiltest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.common.util.DeviceUtil;
import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;

import java.util.LinkedHashMap;
import java.util.Map;

public class DeviceutilActivity extends AppCompatActivity implements View.OnClickListener {
    private static int REQUEST_CODE = 0x000001;
    private Button refreshBTN;
    private TextView showDeviceInfoTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deviceutil);
        initView();
        requestPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Logcat.log("requestCode " + requestCode);
        if (requestCode == REQUEST_CODE) {
            boolean granted = true;
            for (int p : grantResults) {
                if (p == PackageManager.PERMISSION_DENIED) {
                    granted = false;
                    break;
                }
            }
            if (granted) {
                setDeviceInfoToTV();
            } else {
                for (int i = 0; i < permissions.length; i++) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (!shouldShowRequestPermissionRationale(permissions[i])) {
                            Logcat.log("requestPermission reject");
                        } else {
                            requestPermission();
                        }
                    }
                }
            }
        }
    }

    private void initView() {
        refreshBTN = findViewById(R.id.refreshBTN_DUAct);
        refreshBTN.setOnClickListener(this);
        showDeviceInfoTV = findViewById(R.id.showDeviceInfo_DUAct);
    }

    /**
     * 请求权限
     */
    private void requestPermission() {
        if (!DeviceUtil.isGetParamPermission(this, Manifest.permission.READ_PHONE_STATE)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
            }
        } else {
            setDeviceInfoToTV();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.refreshBTN_DUAct:
                setDeviceInfoToTV();
                break;
            default:
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
        float density = DeviceUtil.getDensity(getApplicationContext());
//        density = displayMetrics.density + displayMetrics.densityDpi;

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
        String XX = "";
        if (checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            XX = DeviceUtil.getXX(this);
        }
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("phoneBand", phoneBand);
        map.put("phoneMode", phoneMode);
        map.put("language", language);
        map.put("osVersion", osVersion);
        map.put("widthPixels", widthPixels);
        map.put("heightPixels", heightPixels);
        map.put("density", density);

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
