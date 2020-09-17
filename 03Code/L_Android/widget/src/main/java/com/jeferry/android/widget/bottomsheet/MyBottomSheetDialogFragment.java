package com.jeferry.android.widget.bottomsheet;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jeferry.android.widget.R;
import com.socks.library.KLog;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private static final String TAG = MyBottomSheetDialogFragment.class.getSimpleName();

    private String[] items = new String[]{"第一条", "第二条", "第三条"};

    private ImageView mIvClose;

    private LinearLayout mLlContainer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置dialog背景透明 - 方法一
//        this.setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
        // 设置dialog背景透明 - 方法二
//        this.setStyle(STYLE_NORMAL, R.style.SheetDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setDimAmount(0.3f);
        View view = inflater.inflate(R.layout.dialog_gragment_my_bottom_sheet, null);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        KLog.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        if (getDialog() != null) {
            getDialog().setCanceledOnTouchOutside(true);
            getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        // 设置dialog背景透明 - 方法三
        if (getView() != null && getView().getParent() != null) {
            ((View) getView().getParent()).setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();

        // 设置全屏高度
        if (dialog != null) {
            View bottomSheet = dialog.findViewById(R.id.design_bottom_sheet);
            bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        final View view = getView();
        view.post(() -> {
            View parent = (View) view.getParent();
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) (parent).getLayoutParams();
            CoordinatorLayout.Behavior behavior = params.getBehavior();
            BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setPeekHeight(view.getMeasuredHeight());
            parent.setBackgroundColor(Color.TRANSPARENT);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 设置dialog背景透明
        if (view.getParent() != null) {
            ((View) view.getParent()).setBackgroundColor(Color.TRANSPARENT);
        } else {
            KLog.d(TAG, view.getParent() == null);
        }
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
