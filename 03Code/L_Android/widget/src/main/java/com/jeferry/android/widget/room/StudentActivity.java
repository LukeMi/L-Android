package com.jeferry.android.widget.room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.jeferry.android.widget.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StudentActivity extends AppCompatActivity {

    private Button mTvAdd;
    private TextView mTvContent;
    private LocalStudentSource source;
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_student);
        initRoom();
        initView();
        run();
    }

    private void initRoom() {
        StudentDatabase instance = StudentDatabase.getInstance(getApplicationContext());
        source = new LocalStudentSource(instance.studentDao());
    }

    private void initView() {
        mTvAdd = findViewById(R.id.tv_add1);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        findViewById(R.id.tv_add1).setOnClickListener(this::onClick);
    }

    @Override
    protected void onDestroy() {
        dispose();
        super.onDestroy();
    }

    private void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_add1) {
            Student student = new Student();
            student.name = "name : " + Math.random();
            source.insert(student, this::run);
        }
    }

    private void run() {
        dispose();
        disposable = source.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    String s = JSON.toJSONString(list);
                    mTvContent.setText(s);
                }, throwable -> {

                });
    }

    private void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}