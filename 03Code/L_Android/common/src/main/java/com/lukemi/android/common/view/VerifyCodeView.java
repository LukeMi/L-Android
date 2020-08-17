package com.lukemi.android.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * 验证码View
 */
public class VerifyCodeView extends LinearLayout {

    public VerifyCodeView(Context context) {
        super(context, null, 0);
    }

    public VerifyCodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public VerifyCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        initParams(context, attrs, defStyleAttr);

        this.setOrientation(HORIZONTAL);
        EditText e1 = new EditText(context);
//        LayoutParams layoutParams = new LayoutParams();
        this.addView(e1);
    }

    private void initParams(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs,null);

        array.recycle();
    }


}
