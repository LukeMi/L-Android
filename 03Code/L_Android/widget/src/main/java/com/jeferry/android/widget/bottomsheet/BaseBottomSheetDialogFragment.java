package com.jeferry.android.widget.bottomsheet;

import android.graphics.Color;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;

import com.socks.library.KLog;

public class BaseBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private final String TAG = BaseBottomSheetDialogFragment.class.getSimpleName();
    /**
     * 是否全屏
     */
    protected boolean fullScreen;

    /**
     * 固定高度
     */
    protected int fixHeight;

    @Override
    public void onStart() {
        super.onStart();
        // 设置透明度
        if (getDialog() != null) {
            getDialog().getWindow().setDimAmount(0.3f);
        }

        // 设置高度
        if (getView() != null) {
            ViewGroup.LayoutParams layoutParams = ((View) getView().getParent()).getLayoutParams();
            layoutParams.height = fullScreen ? ViewGroup.LayoutParams.MATCH_PARENT
                    : (fixHeight <= 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : fixHeight);
        }

        // 设置
        if (getView() != null) {
            getView().post(() -> {
                ViewGroup parent = (ViewGroup) getView().getParent();
                ViewGroup.LayoutParams layoutParams = parent.getLayoutParams();
                if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                    CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
                    if (behavior instanceof BottomSheetBehavior) {
                        ((BottomSheetBehavior) behavior).setPeekHeight(getView().getHeight());
                    }
                    parent.setBackgroundColor(Color.TRANSPARENT);
                    KLog.d(TAG, parent.getClass().getName());
                }
            });
        }

    }
}
