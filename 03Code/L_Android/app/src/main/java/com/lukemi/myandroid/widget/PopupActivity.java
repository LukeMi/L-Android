package com.lukemi.myandroid.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lukemi.myandroid.R;
import com.lukemi.myandroid.util.Logcat;
import com.lukemi.myandroid.view.MyEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class PopupActivity extends AppCompatActivity {

    @BindView(R.id.btn_pop)
    Button btnPop;
    private AlertDialog dialog;
    private MyEditText editComment;
    private PopupWindow popupWindow;
    private InputMethodManager inputManager;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        setContentView(R.layout.activity_popup);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_pop, R.id.btn_dlg})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btn_pop:
                showPop();
                popupInputMethodWindow();
                break;
            case R.id.btn_dlg:
                showDlg1();
                break;
        }

    }

    private void showDlg1() {
//        TextView view = new TextView(this);
//        view.setText("heheheh");
        View view = LayoutInflater.from(this).inflate(R.layout.view_comment, null);
        PopupWindow pop = new PopupWindow(view, MATCH_PARENT, 200, false);
        pop.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        pop.setTouchable(true);
        pop.setOutsideTouchable(true);
        pop.showAtLocation(btnPop, Gravity.BOTTOM, 0, 0);

    }

    private void popupInputMethodWindow() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
          /*      if (inputManager.isActive()) {
                    inputManager.showSoftInput(btnPop, InputMethodManager.SHOW_FORCED);
                }*/
                inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                Logcat.log("inputManager.isActive(): " + inputManager.isActive());
            }
        }, 0);

    }

    public boolean onTouchEvent(MotionEvent event) {

// TODO Auto-generated method stub

        if (popupWindow != null && popupWindow.isShowing()) {

            popupWindow.dismiss();

            popupWindow = null;

        }

        return super.onTouchEvent(event);

    }


    private void showPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.view_comment, null);
        view.findViewById(R.id.text_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        view.findViewById(R.id.text_sent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        editComment = ((MyEditText) view.findViewById(R.id.edit_comment));
        editComment.setFocusable(true);
        editComment.requestFocus();
        editComment.setOnCancelDialogImp(new MyEditText.OnCancelDialogImp() {
            @Override
            public void onCancelDialog() {
                //判断弹框是否为空
                Logcat.log("onCancelDialog");
                if (popupWindow != null) {
                    popupWindow.dismiss();  //弹框消失
                    popupWindow = null;  //赋空值
                }
            }
        });

        popupWindow = new PopupWindow(view, MATCH_PARENT, 300, true);
        //监听菜单的关闭事件
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Logcat.log("onDismiss inputManager.isActive(): " + inputManager.isActive());
//                if (inputManager.isActive()) {
//                    inputManager.hideSoftInputFromWindow(btnPop.getWindowToken(), 0); //强制隐藏键盘
//                }
                inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
        //监听触屏事件
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        //软键盘不会挡着popupwindow
        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //设置菜单显示的位置
        popupWindow.showAtLocation(btnPop, Gravity.BOTTOM, 0, 0);//相对于父控件的位置，同时可以设置偏移量。
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void oshoshowDlg() {
    /*    AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.view_comment, null);
        view.findViewById(R.id.text_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.text_sent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        editComment = ((EditText) view.findViewById(R.id.edit_comment));
        editComment.setFocusable(true);
        editComment.requestFocus();
        builder.setView(R.layout.view_comment);
        dialog = builder.create();
        dialog.show();*/

        dialog = new AlertDialog.Builder(PopupActivity.this).create();
        dialog.show();//show方法放在此处，如果先SetContentView之后在show会报错
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = (int) (display.getWidth()); //设置宽度
        dialog.getWindow().setAttributes(lp);
        View view = LayoutInflater.from(this).inflate(R.layout.view_comment, null);
        view.findViewById(R.id.text_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.text_sent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        editComment = ((MyEditText) view.findViewById(R.id.edit_comment));
        editComment.setFocusable(true);
        editComment.requestFocus();
        dialog.setView(view, 0, 0, 0, 0);

    }
}
