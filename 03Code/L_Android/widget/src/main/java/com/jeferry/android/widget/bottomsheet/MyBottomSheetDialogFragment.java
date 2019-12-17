package com.jeferry.android.widget.bottomsheet;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeferry.android.widget.R;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private String[] items = new String[]{"第一条", "第二条", "第三条"};

    private ImageView mIvClose;

    private LinearLayout mLlContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.getDialog().setCanceledOnTouchOutside(true);
        View view = inflater.inflate(R.layout.dialog_gragment_my_bottom_sheet, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mIvClose = view.findViewById(R.id.iv_close);
        mIvClose.setOnClickListener(this::onClick);
        mLlContainer = view.findViewById(R.id.ll_container);
        for (int i = 0; i < items.length; i++) {
            TextView tv = new TextView(getContext());
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, metrics)));
            tv.setGravity(Gravity.CENTER);
            tv.setText(items[i]);
            tv.setTextSize(14);
            tv.setOnClickListener(this::onClick);
            mLlContainer.addView(tv);
            if (i != items.length - 1) {
                View line = new View(getContext());
                LinearLayout.LayoutParams lineLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, metrics));
                int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, metrics);
                lineLp.setMargins(margin, 0, margin, 0);
                line.setLayoutParams(lineLp);
                line.setBackgroundColor(Color.parseColor("#88666666"));
                mLlContainer.addView(line);
            }
        }
    }

    private void onClick(View v) {
        dismiss();
    }
}
