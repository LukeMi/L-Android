package com.tbug.android.collection.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tbug.android.collection.R;
import com.tbug.android.collection.util.Logcat;
import com.tbug.android.collection.widget.wechat.activity.WeChatLoginActivity;

import static com.tbug.android.collection.R.id.ic_password_delete;

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
        textPasswordET = ((EditText) findViewById(R.id.textPassword));
        weChatActivity = (Button) findViewById(R.id.weChatActivity);
        ic_password_deleteBTN = (Button) findViewById(ic_password_delete);
        ic_password_showBTN = (Button) findViewById(R.id.ic_password_show);

        weChatActivity.setOnClickListener(this);
        ic_password_deleteBTN.setOnClickListener(this);
        ic_password_showBTN.setOnClickListener(this);
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
        textPasswordET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //获得焦点并长度大于0
                if (hasFocus && ((EditText) v).getText().length() > 0) {
                    ic_password_deleteBTN.setVisibility(View.VISIBLE);
                } else {
                    ic_password_deleteBTN.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ic_password_delete:
                Logcat.log("EditTextActivity----> click---->> ic_password_delete");
                textPasswordET.setText("");
                break;
            case R.id.ic_password_show://设置密码是否可见
                Logcat.log("EditTextActivity----> click---->> ic_password_show");
                if (!showFlag) {
                    showFlag = true;
                    showPasswordETcontent(textPasswordET, showFlag);
                } else {
                    showFlag = false;
                    showPasswordETcontent(textPasswordET, showFlag);
                }
                break;
            case R.id.textPassword:
                //设置光标位置---防止点击之后游标回到0位置
                int location = textPasswordET.length();
                textPasswordET.setSelection(location);
                //设置光标可见
                textPasswordET.setCursorVisible(true);
                break;
            case R.id.weChatActivity:
                startActivity(new Intent(this,WeChatLoginActivity.class));
                break;
        }
    }


    /**
     * 密码EditText内容是否可见
     *
     * @param passwordET 密码输入框
     * @param showFlag   true显示，false不显示
     */
    public void showPasswordETcontent(EditText passwordET, boolean showFlag) {
        if (showFlag) {
            //注释掉的是第一种方法(numberPassword有效)
//                    textPasswordET.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordET.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);

        } else {
//                    textPasswordET.setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordET.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        passwordET.requestFocus();
        //设置光标可见
        passwordET.setCursorVisible(true);
        //设置光标位置---防止点击之后游标回到0位置
        int location = passwordET.length();
        passwordET.setSelection(location);
    }

}
