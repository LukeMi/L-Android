package com.lukemi.android.tutorial.widget;

import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lukemi.android.tutorial.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IncludeActivity extends AppCompatActivity {

    @BindView(R.id.cc_include)
    ConstraintLayout ccInclude;
    @BindView(R.id.c_root)
    ConstraintLayout cRoot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_include);
        ButterKnife.bind(this);
        new Handler().postDelayed(() -> cRoot.setVisibility(View.VISIBLE), 3000);
    }
}
