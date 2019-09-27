package com.lukemi.android.common.util;

import android.widget.EditText;

import com.lukemi.android.common.util.Logcat;

public class EditTextUtil {

    /**
     * 判定EditText 是否可以滚动
     *
     * @param editText target EditText
     * @return true EditText可以滚动，false 不可以
     */
    public static boolean canScrollY(EditText editText) {
        int scrollY = editText.getScrollY();
        //内容的总高度
        int scrollRange = editText.getLayout().getHeight();
        //实际显示内容的高度
        int realHeight = editText.getHeight() - editText.getPaddingTop() - editText.getPaddingBottom();
        //差值
        int gap = scrollRange - realHeight;
        Logcat.log("scrollY : " + scrollY + " ;scrollRange : " + scrollRange + " ;realHeight : " + realHeight + " ;gap : " + gap);
        if (gap == 0) {
            return false;
        }
        return scrollY > 0 || scrollY < gap - 1;
    }
}
