package com.lukemi.android.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lukemi.android.common.R;


/**
 * 刮刮卡空间
 */
public class ScratchCardView extends View {

    private final float DEF_GESTURE_WIDTH = 10;

    private Paint mPaint;
    private Bitmap mBKGBitmap;
    private Bitmap maskBitmap;
    private Path mGesturePath;
    private Canvas maskCanvas;

    private float gestureWidth = DEF_GESTURE_WIDTH;

    public ScratchCardView(Context context) {
        super(context);
    }

    public ScratchCardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScratchCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseParams(context, attrs, defStyleAttr);
    }

    private void parseParams(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ScratchCardView, defStyleAttr, 0);
        gestureWidth = array.getDimension(R.styleable.ScratchCardView_gestureWidth, DEF_GESTURE_WIDTH);
        array.recycle();
    }

    private void init() {
        initGesturePaint();

        mGesturePath = new Path();

        mBKGBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic_bkg);

        int sizeWidth = getMeasuredWidth();
        int sizeHeight = getMeasuredHeight();
        maskBitmap = Bitmap.createBitmap(sizeWidth, sizeHeight, Bitmap.Config.ARGB_8888);

        maskCanvas = new Canvas(maskBitmap);
        maskCanvas.drawColor(Color.GRAY);
    }

    private void initGesturePaint() {
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setStrokeWidth(gestureWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));//设置混合模式为DST_IN
        mPaint.setStrokeJoin(Paint.Join.ROUND);//Paint.Join.ROUND和Paint.Cap.ROUND设置Paint笔触和连接处更加圆滑一点
        mPaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("onDraw");
        canvas.drawBitmap(mBKGBitmap, 0, 0, null);
        canvas.drawBitmap(maskBitmap, 0, 0, null);
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mGesturePath.reset();
                mGesturePath.moveTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mGesturePath.lineTo(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        maskCanvas.drawPath(mGesturePath, mPaint);
        invalidate();
        return true;
    }
}
