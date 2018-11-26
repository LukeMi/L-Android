package com.lukemi.android.tutorial;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 获取android系统内存情况
 */
public class SystemMemoryActivity extends AppCompatActivity {

    @BindView(R.id.tv_total_memory)
    TextView tvTotalMemory;
    @BindView(R.id.tv_free_memory)
    TextView tvFreeMemory;
    private ActivityManager mActivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_memory);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        mActivityManager = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
    }

    @OnClick(R.id.btn_obtain)
    public void onViewClicked() {
        obtainMemory();
    }

    private void obtainMemory() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcessInfoList = mActivityManager.getRunningAppProcesses();
        for (int i = 0; i < runningAppProcessInfoList.size(); i++) {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcessInfoList.get(i);
            int pid = runningAppProcessInfo.pid;
            String processName = runningAppProcessInfo.processName;
            int uid = runningAppProcessInfo.uid;
            int lastTrimLevel = runningAppProcessInfo.lastTrimLevel;

        }
        Observable.create(new ObservableOnSubscribe<ActivityManager.RunningAppProcessInfo>() {
            @Override
            public void subscribe(ObservableEmitter<ActivityManager.RunningAppProcessInfo> emitter) throws Exception {

            }
        }).subscribe(new Observer<ActivityManager.RunningAppProcessInfo>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
