package com.lukemi.android.tutorial.lifecycle.flag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.lifecycle.NewTaskActivity;
import com.lukemi.android.tutorial.lifecycle.Session1Activity;
import com.lukemi.android.tutorial.lifecycle.SingleInstanceActivity;
import com.socks.library.KLog;

public class FlagActivity extends AppCompatActivity {

    private final String TAG = FlagActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flag);
        initView();
    }

    private void initView() {
        KLog.d(TAG, getTaskId());
        findViewById(R.id.btn_standard).setOnClickListener(this::onClick);
        findViewById(R.id.btn_single_top).setOnClickListener(this::onClick);
        findViewById(R.id.btn_single_task).setOnClickListener(this::onClick);
        findViewById(R.id.btn_single_instance).setOnClickListener(this::onClick);
        findViewById(R.id.btn_single_REORDER_TO_FRONT).setOnClickListener(this::onClick);
        findViewById(R.id.btn_clear_task_new_task).setOnClickListener(this::onClick);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(getApplicationContext(), "onNewIntent", Toast.LENGTH_SHORT).show();
        int flags = intent.getFlags();
        KLog.d(TAG, flags);
    }


    private void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_standard) {
            standard();
        } else if (id == R.id.btn_single_top) {
            singleTop();
        } else if (id == R.id.btn_single_task) {
            singleTask();
        } else if (id == R.id.btn_single_instance) {
            singleInstance();
        } else if (id == R.id.btn_single_REORDER_TO_FRONT) {
            reorderFront();
        } else if (id == R.id.btn_clear_task_new_task) {
            newTaskAndClear();
        }

    }

    private void standard() {
        Intent intent = new Intent(this, FlagActivity.class);
        startActivity(intent);
    }

    private void singleTop() {
        Intent intent = new Intent(this, FlagActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    private void singleTask() {
        Intent intent = new Intent(this, FlagActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void singleInstance() {
        Intent intent = new Intent(this, SingleInstanceActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void reorderFront() {
        Intent intent = new Intent(this, Session1Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    private void newTaskAndClear() {
        Intent intent = new Intent(this, NewTaskActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}