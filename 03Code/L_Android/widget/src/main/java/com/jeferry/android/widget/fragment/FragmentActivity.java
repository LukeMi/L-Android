package com.jeferry.android.widget.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.jeferry.android.widget.R;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        init();
    }

    private void init() {
        for (int i = 0; i < 3; i++) {
            ParamsFragment fragment = ParamsFragment.newInstance(i + 1);
            addFragment(fragment);
        }
        findViewById(R.id.tv_fragment_back).setOnClickListener(this::onCLick);
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 添加到回退站
        transaction.addToBackStack("");
        transaction.add(R.id.fragment, fragment).commitAllowingStateLoss();
    }

    private void onCLick(View view) {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }
}
