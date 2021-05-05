package com.jeferry.android.widget;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.jeferry.android.widget.wechat.activity.WeChatLoginActivity;
import com.lukemi.android.common.util.Logcat;

public class EditTextActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText textPasswordET;
    private Button weChatActivity;
    private Button ic_password_deleteBTN;
    private Button ic_password_showBTN;
    private boolean showFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        initVIew();
    }

    private void initVIew() {
        textPasswordET = findViewById(R.id.textPassword);
        weChatActivity = findViewById(R.id.weChatActivity);
        ic_password_deleteBTN = findViewById(R.id.ic_password_delete);
        ic_password_showBTN = findViewById(R.id.ic_password_show);

        weChatActivity.setOnClickListener(this);
        ic_password_deleteBTN.setOnClickListener(this);
        ic_password_showBTN.setOnClickListener(this);
        findViewById(R.id.btn_soft_input).setOnClickListener(this);
        textPasswordET.setOnClickListener(this);
        textPasswordET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //设置光标位置---防止点击之后游标回到0位置
                int location = s.length();
                textPasswordET.setSelection(location);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s)) {
                    ic_password_deleteBTN.setVisibility(View.VISIBLE);
                } else {
                    ic_password_deleteBTN.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textPasswordET.setOnFocusChangeListener((v, hasFocus) -> {
            //获得焦点并长度大于0
            if (hasFocus && ((EditText) v).getText().length() > 0) {
                ic_password_deleteBTN.setVisibility(View.VISIBLE);
            } else {
                ic_password_deleteBTN.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ic_password_delete) {
            Logcat.log("EditTextActivity----> click---->> ic_password_delete");
            textPasswordET.setText("");
            //设置密码是否可见
        } else if (id == R.id.ic_password_show) {
            Logcat.log("EditTextActivity----> click---->> ic_password_show");
            if (!showFlag) {
                showFlag = true;
                showPasswordETcontent(textPasswordET, showFlag);
            } else {
                showFlag = false;
                showPasswordETcontent(textPasswordET, showFlag);
            }
        } else if (id == R.id.textPassword) {//设置光标位置---防止点击之后游标回到0位置
            int location = textPasswordET.length();
            textPasswordET.setSelection(location);
            //设置光标可见
            textPasswordET.setCursorVisible(true);
        } else if (id == R.id.weChatActivity) {
            startActivity(new Intent(this, WeChatLoginActivity.class));
        } else if (id == R.id.btn_soft_input) {
            startActivity(new Intent(this, SoftInputModeActivity.class));
        }
    }


    /**
     * 密码EditText内容是否可见
     *
     * @param editText 密码输入框
     * @param showFlag true显示，false不显示
     */
    public void showPasswordETcontent(EditText editText, boolean showFlag) {
        if (showFlag) {
            //注释掉的是第一种方法(numberPassword有效)
            textPasswordET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//            passwordET.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);

        } else {
            textPasswordET.setTransformationMethod(PasswordTransformationMethod.getInstance());
//            passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        editText.requestFocus();
        //设置光标可见
        editText.setCursorVisible(true);
        //设置光标位置---防止点击之后游标回到0位置
        int location = editText.length();
        editText.setSelection(location);
    }

}
