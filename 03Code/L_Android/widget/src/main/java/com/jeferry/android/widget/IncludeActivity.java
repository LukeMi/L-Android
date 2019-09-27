package com.jeferry.android.widget;

import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class IncludeActivity extends AppCompatActivity {

    ConstraintLayout ccInclude;

    ConstraintLayout cRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include);
        ccInclude = findViewById(R.id.cc_include);
        cRoot = findViewById(R.id.c_root);
        new Handler().postDelayed(() -> cRoot.setVisibility(View.VISIBLE), 3000);
    }
}
