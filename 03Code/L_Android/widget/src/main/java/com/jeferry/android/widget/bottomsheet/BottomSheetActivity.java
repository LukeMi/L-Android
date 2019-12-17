package com.jeferry.android.widget.bottomsheet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jeferry.android.widget.R;

public class BottomSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        findViewById(R.id.btn_bottom).setOnClickListener(view -> {
            MyBottomSheetDialogFragment fragment = new MyBottomSheetDialogFragment();
            fragment.show(getSupportFragmentManager(),"MyBottomSheetDialogFragment");
        });
    }
}
