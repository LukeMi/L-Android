package com.jeferry.android.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.common.util.WindowUtil;
import com.lukemi.android.common.view.MyEditText;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.lukemi.android.common.util.DeviceUtil.getStatusBarHeight;


public class PopupActivity extends AppCompatActivity {


    TextView tvTitleType1;
    ImageView imgMenu;
    ConstraintLayout clTitleType1;
    Button btnBottomPop;
    private PopupActivity context;
    private PopupWindow popupWindow;

    private InputMethodManager inputManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        initData(savedInstanceState);
        initView();
    }

    protected void initData(Bundle savedInstanceState) {
        context = this;
        inputManager = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
    }

    protected void initView() {
        tvTitleType1 = findViewById(R.id.tv_title_type1);
        imgMenu = findViewById(R.id.img_menu);
        clTitleType1 = findViewById(R.id.cl_title_type1);
        btnBottomPop = findViewById(R.id.btn_bottom_pop);

        tvTitleType1.setText("PopupWindow");
        imgMenu.setVisibility(View.VISIBLE);
        findViewById(R.id.img_back).setOnClickListener(this::onViewClicked);
        findViewById(R.id.img_menu).setOnClickListener(this::onViewClicked);
        findViewById(R.id.btn_comment).setOnClickListener(this::onViewClicked);
        findViewById(R.id.btn_bottom_pop).setOnClickListener(this::onViewClicked);
        findViewById(R.id.btn_normal).setOnClickListener(this::onViewClicked);
    }

    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.img_back) {
            finish();
        } else if (id == R.id.img_menu) {
            menu();
        } else if (id == R.id.btn_comment) {
            showPop();
        } else if (id == R.id.btn_bottom_pop) {
            option();
        } else if (id == R.id.btn_normal) {
            normalPop();
        }
    }

    private void normalPop() {
        View view = getLayoutInflater().inflate(R.layout.pop_normal, null);
        PopupWindow popupWindow = new PopupWindow(view, MATCH_PARENT, WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(btnBottomPop, Gravity.BOTTOM, 0, 0);
    }

    private void showPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_comment, null);
        TextView tvCancel = view.findViewById(R.id.text_cancel);
        TextView tvSent = view.findViewById(R.id.text_sent);
        tvCancel.setOnClickListener(v -> popupWindow.dismiss());
        tvSent.setOnClickListener(v -> popupWindow.dismiss());
        MyEditText editComment = view.findViewById(R.id.edit_comment);
        editComment.setFocusable(true);
        editComment.requestFocus();
        editComment.setOnCancelDialogImp(() -> {
            //判断弹框是否为空
            Logcat.log("onCancelDialog");
            if (popupWindow != null) {
                //弹框消失
                popupWindow.dismiss();
                //赋空值
                popupWindow = null;
            }
        });
        popupWindow = new PopupWindow(view, MATCH_PARENT, WRAP_CONTENT, true);
        //监听菜单的关闭事件
        popupWindow.setOnDismissListener(() -> {
            Logcat.log("onDismiss inputManager.isActive(): " + inputManager.isActive());
            inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        });
        //监听触屏事件 - 拦截了屏幕外事件
        popupWindow.setTouchInterceptor((view1, event) -> {
            if (popupWindow != null && popupWindow.isShowing()) {
                return false;
            } else {
                return super.dispatchTouchEvent(event);
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
//        popupWindow.update();
        //软键盘不会挡着popupwindow
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //设置菜单显示的位置
        //相对于父控件的位置，同时可以设置偏移量。
        popupWindow.showAtLocation(btnBottomPop, Gravity.BOTTOM, 0, 0);
        inputManager.toggleSoftInput(0, InputMethodManager.SHOW_IMPLICIT);
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
        popupWindow.setAnimationStyle(R.style.MenuStyle);
        int statusHeight = getStatusBarHeight(this);
        int titleBarHeight = clTitleType1.getHeight();
        int xOffSet = (int) getResources().getDimension(R.dimen.dp15);
        int yOffSet = statusHeight + titleBarHeight;
        popupWindow.showAtLocation(imgMenu, Gravity.END | Gravity.TOP, xOffSet, yOffSet);
    }

    private void option() {
        View view = getLayoutInflater().inflate(R.layout.popup_bottom, null);
        final PopupWindow bottomPOP = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        view.findViewById(R.id.tv_cancel).setOnClickListener(view1 -> bottomPOP.dismiss());
        bottomPOP.setBackgroundDrawable(new ColorDrawable());
        bottomPOP.setOutsideTouchable(true);
        bottomPOP.setAnimationStyle(R.style.Popup_bottom);
        bottomPOP.setOnDismissListener(() -> {
            WindowUtil.setWindowAlpha(context, 1.0f);
            btnBottomPop.setEnabled(true);
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
