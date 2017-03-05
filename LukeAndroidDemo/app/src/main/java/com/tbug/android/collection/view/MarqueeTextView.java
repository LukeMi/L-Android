package com.tbug.android.collection.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tbug.android.collection.util.Logcat;

/* xml文件自定义控件使用样例：
<com.tbug.android.collection.view.MarqueeTextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:maxLines="1"
        android:ellipsize="marquee"
        android:marqueeRepeatLimit="marquee_forever"
        android:text="我在你的前生或来世 碧落或黄泉 只是不在此刻 不在眼前" />*/

/**
 * 自定义跑马灯TextView<br>
 * 功能：解决-->>控件xml文件-->>singLine属性被废弃；instead maxLine="1" ，与ellipsize="marquee" 跑马灯无效的issue；<br/>
 * <p>
 * Created by mzchen on 2017/3/1.
 */

public class MarqueeTextView extends TextView {
    public MarqueeTextView(Context context) {
        super(context);
        Logcat.log("MarqueeTextView----默认构造方法>> " + "MarqueeTextView(Context context)");
        judgeMaxLine();
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Logcat.log("MarqueeTextView----默认构造方法>> " + "MarqueeTextView(Context context, AttributeSet attrs)");
        judgeMaxLine();
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Logcat.log("MarqueeTextView----默认构造方法>> " + "MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr)");
        judgeMaxLine();
    }

    @TargetApi(21)
    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Logcat.log("MarqueeTextView----默认构造方法>> " + "MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)");
        judgeMaxLine();
    }

    /**
     * 判断最大行数是否为1，实现singLine的效果
     */
    public void judgeMaxLine() {
        int maxLines = this.getMaxLines();
        if (maxLines == 1) {
            //投机取巧，这个方法没有被废弃；
            this.setSingleLine();
        }
    }

    /**
     * 功能：TextView 一直能够获得焦点
     *
     * @return true 一直能够获得焦点
     */
    @Override
    public boolean isFocused() {
        return true;
    }
}
