package com.lukemi.android.tutorial.popup;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.base.BaseActivity;
import com.lukemi.android.tutorial.util.WindowUtil;


import butterknife.BindView;
import butterknife.OnClick;

import static com.lukemi.android.tutorial.util.DeviceUtil.getStatusBarHeight;


public class PopupActivity extends BaseActivity {

    @BindView(R.id.tv_title_type1)
    TextView tvTitleType1;
    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.cl_title_type1)
    ConstraintLayout clTitleType1;
    @BindView(R.id.btn_bottom_pop)
    Button btnBottomPop;
    private PopupActivity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int bindLayout() {
        return R.layout.activity_popup;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        context = this;
    }

    @Override
    protected void initView() {
        tvTitleType1.setText("PopupWindow");
        imgMenu.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.img_back, R.id.img_menu, R.id.btn_bottom_pop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.img_menu:
                menu();
                break;
            case R.id.btn_bottom_pop:
                option();
                break;
        }
    }

    private void menu() {
        View view = getLayoutInflater().inflate(R.layout.popup_menu, null);
        int viewWidth = (int) getResources().getDimension(R.dimen.dp150);
        final PopupWindow popupWindow = new PopupWindow(view, viewWidth, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setOutsideTouchable(true);
        /**
         *防止点击触发按钮出现 关闭重新打开现象
         */
        popupWindow.setFocusable(true);
        int statusHeight = getStatusBarHeight(this);
        int titleBarHeight = clTitleType1.getHeight();
        int xOffSet = (int) getResources().getDimension(R.dimen.dp15);
        int yOffSet = statusHeight + titleBarHeight;
        popupWindow.showAtLocation(imgMenu, Gravity.END | Gravity.TOP, xOffSet, yOffSet);
    }

    private void option() {
        View view = getLayoutInflater().inflate(R.layout.popup_bottom, null);
        final PopupWindow bottomPOP = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomPOP.dismiss();
            }
        });
        bottomPOP.setBackgroundDrawable(new ColorDrawable());
        bottomPOP.setOutsideTouchable(true);
        bottomPOP.setAnimationStyle(R.style.Popup_bottom);
        bottomPOP.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowUtil.setWindowAlpha(context, 1.0f);
                btnBottomPop.setEnabled(true);
            }
        });

        /**
         *
         *防止点击触发按钮出现 关闭重新打开现象
         */
        bottomPOP.setFocusable(true);
        WindowUtil.setWindowAlpha(context, 0.5f);
        bottomPOP.showAtLocation(imgMenu, Gravity.BOTTOM, 0, 0);
    }
}
