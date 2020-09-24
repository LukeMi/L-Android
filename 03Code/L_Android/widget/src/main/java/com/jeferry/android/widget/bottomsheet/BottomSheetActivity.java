package com.jeferry.android.widget.bottomsheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jeferry.android.widget.R;
import com.jeferry.android.widget.bottomsheet.list.ListBottomSheetDialogFragment;

public class BottomSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        findViewById(R.id.btn_bottom).setOnClickListener(this::onClick);
        findViewById(R.id.btn_bottom_recycler).setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_bottom) {
            MyBottomSheetDialogFragment fragment = new MyBottomSheetDialogFragment();
            fragment.show(getSupportFragmentManager(), "MyBottomSheetDialogFragment");
        } else if (id == R.id.btn_bottom_recycler) {
            ListBottomSheetDialogFragment fragment = new ListBottomSheetDialogFragment();
            fragment.show(getSupportFragmentManager(), "ListBottomSheetDialogFragment");
        }
    }
}
