package com.lukemi.android.tutorial.manager;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lukemi
 * @date 2018/12/27 9:46
 * @des ActivtyManager
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class ActivityManagerActivity extends AppCompatActivity {

    @BindView(R.id.tv_statistics)
    TextView mTvStatistics;
    @BindView(R.id.lv)
    LinearLayout lv;

    private ActivityManager mActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_manager);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mActivityManager = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));

        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        mActivityManager.getMemoryInfo(memoryInfo);
        //字节 Byte
        long totalMem = memoryInfo.totalMem;
        long availMem = memoryInfo.availMem;
        boolean lowMemory = memoryInfo.lowMemory;
        int describeContents = memoryInfo.describeContents();

        String result = "totalMem = " + getSize(totalMem) + "\n" +
                "availMem = " + getSize(availMem) + "\n" +
                "lowMemory = " + lowMemory + "\n" +
                "describeContents = " + describeContents + "\n";
        mTvStatistics.setText(result);
        mActivityManager.getMemoryInfo(memoryInfo);
        List<String> pkgs = new ArrayList<>();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = mActivityManager.getRunningAppProcesses();
        for (int i = 0; i < runningAppProcesses.size(); i++) {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i);
            String processName = runningAppProcessInfo.processName;
            pkgs.add(processName);
            TextView textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER);
            lv.addView(textView);
        }

    }

    private String getSize(float bytes) {
        System.out.println("bytes = " + bytes);
        int flag = 1024;
        String[] unit = new String[]{"Byte", "KB", "MB", "GB"};
        for (int i = 0; i < 4; i++) {
            if (bytes < flag) {
                if (i == 0) {
                    return String.format("%.02f",bytes) + unit[i];
                } else {
                    System.out.println("bytes2 = " + bytes);
                    return String.format("%.02f",bytes) + unit[i];
                }
            } else {
                bytes = bytes / flag;
            }
        }
        return bytes + unit[3];

    }

}
