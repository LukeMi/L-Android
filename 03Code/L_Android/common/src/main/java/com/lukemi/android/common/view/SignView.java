package com.lukemi.android.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Nullable;

import com.lukemi.android.common.R;


public class SignView extends View {

    private float mTextSize;
    private int mTextColor;

    private Paint mSignPaint;
    private int sizeWidth;
    private int sizeHeight;
    private Path mPath;

    private Paint mHintPaint;
    private float mHintTextSize;
    private int mHintTextColor;
    private String mHintText;


    public SignView(Context context) {
        super(context);
        initPath();
    }

    public SignView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SignView, defStyleAttr, 0);
        mTextColor = array.getColor(R.styleable.SignView_textColor, Color.BLACK);
        mTextSize = array.getDimension(R.styleable.SignView_textSize, 16);
        mHintTextColor = array.getColor(R.styleable.SignView_textHintColor, Color.BLACK);
        mHintTextSize = array.getDimension(R.styleable.SignView_textHintSize, 16);
        mHintText = array.getString(R.styleable.SignView_textHint);
        mHintText = TextUtils.isEmpty(mHintText) ? "" : mHintText;
        array.recycle();
        initPath();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    private void initSignPaint() {
        mSignPaint = new Paint();
        mSignPaint.setColor(mTextColor);
        mSignPaint.setStrokeWidth(mTextSize);
        mSignPaint.setAntiAlias(true);
        mSignPaint.setStyle(Paint.Style.STROKE);
        mSignPaint.setStrokeJoin(Paint.Join.ROUND);//Paint.Join.ROUND和Paint.Cap.ROUND设置Paint笔触和连接处更加圆滑一点
        mSignPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    private void initHintPaint() {
        mHintPaint = new Paint();
    }

    private void initPath() {
        mPath = new Path();
        System.out.println("mPath.toString() : " + mPath.toString());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        drawHint(canvas);
        drawSign(canvas);
    }

    private void drawSign(Canvas canvas) {
        initSignPaint();
        canvas.drawPath(mPath, mSignPaint);
    }

    private void drawHint(Canvas canvas) {
        initHintPaint();

        mHintPaint.setTextSize(mHintTextSize);
        mHintPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
//        mHintPaint.setTextAlign(Paint.Align.CENTER);
        mHintPaint.setAntiAlias(true);
        mHintPaint.setColor(mHintTextColor);
        mHintPaint.setLinearText(true);

        Rect rect = new Rect();
        mHintPaint.getTextBounds(mHintText, 0, mHintText.length(), rect);
        int w = rect.width();
        int h = rect.height();
        float x = sizeWidth / 2 - w / 2;
        float y = (sizeHeight - h) / 2;
        System.out.println("x : " + x + " ;y : " + y + " ;mHintText : " + mHintText);
        canvas.drawText(mHintText, x, y, mHintPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    /**
     * 清除画板
     */
    public void reset() {
        mPath.reset();
        invalidate();
    }


    public float getmTextSize() {
        return mTextSize;
    }

    public void setmTextSize(float mTextSize) {
        if (this.mTextSize == mTextSize) return;
        this.mTextSize = mTextSize;
        invalidate();
    }

    public void setmTextSize(@DimenRes int mTextSize) {
        setmTextSize(getResources().getDimension(mTextSize));
    }

    public int getmTextColor() {
        return mTextColor;
    }

    public void setmTextColor(int mTextColor) {
        if (this.mTextColor == mTextColor) return;
        this.mTextColor = mTextColor;
        invalidate();
    }

    public void setmTextColorRes(@ColorRes int mTextColor) {
        int color = getResources().getColor(mTextColor);
        setmTextColor(color);
    }


    public float getmHintTextSize() {
        return mHintTextSize;
    }

    public void setmHintTextSize(float mHintTextSize) {
        if (this.mHintTextSize == mHintTextSize) return;
        this.mHintTextSize = mHintTextSize;
        invalidate();
    }


    public int getmHintTextColor() {
        return mHintTextColor;
    }

    public void setmHintTextColor(int mHintTextColor) {
        if (this.mHintTextColor == mHintTextColor) return;
        this.mHintTextColor = mHintTextColor;
        invalidate();

    }

    public String getmHintText() {
        return mHintText;
    }

    public void setmHintText(String mHintText) {
        if (this.mHintText == mHintText) return;
        this.mHintText = mHintText;
        invalidate();
    }
}
