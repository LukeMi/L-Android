package com.lukemi.android.tutorial.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.lukemi.android.common.util.Logcat;

/**
 * Created by mzchen on 2017/2/24.
 */

public class NoPadingTextView extends android.support.v7.widget.AppCompatTextView {

    private static final String TEXT = "ap爱哥ξτβбпшㄎㄊěǔぬも┰┠№＠↓";
    private Paint mPaint;// 画笔
    private Paint.FontMetrics mFontMetrics;// 文本测量对象

    public NoPadingTextView(Context context) {
        super(context);
    }

    public NoPadingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public NoPadingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        // 实例化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLACK);
        mFontMetrics = mPaint.getFontMetrics();

        Logcat.log_view("Aige" + " ascent：" + mFontMetrics.ascent);
        Logcat.log_view("Aige" + " top：" + mFontMetrics.top);
        Logcat.log_view("Aige" + " leading：" + mFontMetrics.leading);
        Logcat.log_view("Aige" + " descent：" + mFontMetrics.descent);
        Logcat.log_view("Aige" + " bottom：" + mFontMetrics.bottom);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("", 0, Math.abs(mFontMetrics.top), mPaint);
    }
}
