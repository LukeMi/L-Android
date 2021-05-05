package com.lukemi.android.tutorial.dagger2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;

import javax.inject.Inject;

public class StudentActivity extends AppCompatActivity {
    @Inject
    StudentBean studentBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget_student);
        com.lukemi.android.tutorial.dagger2.DaggerStudentComponent.builder().build().inject(this);
        studentBean.eat();
    }
}
