package com.lukemi.android.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;


public class WaterWaveProgressBar extends View {
    private Paint roundPaint;
    private Paint fontPaint;
   public WaterWaveProgressBar(Context context) {
        super(context);
//        initPaint(context);
    }

/*

    public WaterWaveProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint(context);
    }

    public WaterWaveProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context);
    }

    public WaterWaveProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint(context);
    }
    private void initPaint(Context context) {
        roundPaint = new Paint();
        roundPaint.setColor(ballColor);
        roundPaint.setAntiAlias(true);//抗锯齿
        fontPaint = new Paint();
        fontPaint.setTextSize(centerTextSize);
        fontPaint.setColor(centerTextColor);
        fontPaint.setAntiAlias(true);
        fontPaint.setFakeBoldText(true);//粗体
*//**
 * 获取自定义属性
 *//*
        TypedArray typedArray=context.obtainStyledAttributes(R.styleable.waterWaveProgressbar);

        typedArray.recycle();
    }*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
