package com.lukemi.android.tutorial.manager;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;

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
        long totalMem = memoryInfo.totalMem;
        long availMem = memoryInfo.availMem;
        boolean lowMemory = memoryInfo.lowMemory;
        int describeContents = memoryInfo.describeContents();

        String result = "totalMem = " + totalMem + "\n" +
                "availMem = " + availMem + "\n" +
                "lowMemory = " + lowMemory + "\n" +
                "describeContents = " + describeContents + "\n";
        mTvStatistics.setText(result);
        mActivityManager.getMemoryInfo(memoryInfo);
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = mActivityManager.getRunningAppProcesses();
        for (int i = 0; i < runningAppProcesses.size(); i++) {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i);
//            runningAppProcessInfo.
        }
    }


}
