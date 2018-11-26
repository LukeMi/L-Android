package com.lukemi.android.tutorial.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.common.util.Logcat;

public class EmotionalFaceView extends View {

    private float borderWidth;
    private int faceColor = Color.YELLOW;
    private int borderColor = Color.BLACK;
    private int eyesColor = Color.BLACK;
    private int mouthColor = Color.BLACK;
    private int emotionStyle = HAPPY;

    public static final int HAPPY = 1;
    public static final int SAD = 0;

    private long startTime;

    private OnEmotionViewClickListener onEmotionViewClickListener;
    private RectF rectFLeft;
    private RectF rectFRight;
    private Region rectFmouth = new Region();

    public EmotionalFaceView(Context context) {
        super(context);
    }

    public EmotionalFaceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmotionalFaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.EmotionalFaceView, defStyleAttr, 0);
        borderWidth = array.getDimension(R.styleable.EmotionalFaceView_borderWidth, 0);
        borderColor = array.getColor(R.styleable.EmotionalFaceView_borderColor, Color.TRANSPARENT);
        faceColor = array.getColor(R.styleable.EmotionalFaceView_faceColor, Color.TRANSPARENT);
        eyesColor = array.getColor(R.styleable.EmotionalFaceView_eyesColor, Color.TRANSPARENT);
        mouthColor = array.getColor(R.styleable.EmotionalFaceView_mouthColor, Color.TRANSPARENT);
        emotionStyle = array.getInt(R.styleable.EmotionalFaceView_emotionStyle, HAPPY);
        array.recycle();
    }

    public void setOnEmotionViewClickListener(OnEmotionViewClickListener onEmotionViewClickListener) {
        this.onEmotionViewClickListener = onEmotionViewClickListener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawFace(canvas);
        drawBorder(canvas);
        drawEyes(canvas);
        drawMouth(canvas);
    }

    private void drawFace(Canvas canvas) {
        Paint facePaint = initFacePaint();


        int width = getWidth();
        int height = getHeight();
        System.out.println("width : " + width + " :height : " + height);

        canvas.drawCircle(width / 2, width / 2, width / 2, facePaint);
    }

    private void drawBorder(Canvas canvas) {
        Paint borderPaint = initBorderPaint();
        int width = getWidth();
        int height = getHeight();
        System.out.println("width : " + width + " :height : " + height);
        float cx = width / 2;
        float cy = height / 2;
        float radius = width / 2;
        canvas.drawCircle(cx, cy, radius - borderWidth, borderPaint);
    }

    private void drawEyes(Canvas canvas) {
        Paint eyesPaint = initEyesPaint();

        int diamter = getWidth();

        rectFLeft = new RectF(diamter * 0.30f, diamter * 0.20f, diamter * 0.45f, diamter * 0.50f);
        canvas.drawOval(rectFLeft, eyesPaint);

        rectFRight = new RectF(diamter * 0.55f, diamter * 0.20f, diamter * 0.70f, diamter * 0.50f);
        canvas.drawOval(rectFRight, eyesPaint);
    }

    private void drawMouth(Canvas canvas) {
        Paint mouthPaint = initMouthPaint();
        int diamter = getWidth();
        Path path = new Path();
        if (emotionStyle == HAPPY) {
            path.moveTo(diamter * 0.2f, diamter * 0.7f);
            path.quadTo(diamter * 0.5f, diamter * 0.8f, diamter * 0.8f, diamter * 0.7f);
            path.quadTo(diamter * 0.5f, diamter * 0.9f, diamter * 0.2f, diamter * 0.7f);
        } else {
            path.moveTo(diamter * 0.2f, diamter * 0.8f);
            path.quadTo(diamter * 0.5f, diamter * 0.7f, diamter * 0.8f, diamter * 0.8f);
            path.quadTo(diamter * 0.5f, diamter * 0.6f, diamter * 0.2f, diamter * 0.8f);
        }

        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        rectFmouth.setPath(path, new Region((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom));
        canvas.drawPath(path, mouthPaint);
    }

    private Paint initFacePaint() {
        Paint facePaint = new Paint();
        facePaint.setColor(faceColor);
        facePaint.setStyle(Paint.Style.FILL);
        return facePaint;
    }

    private Paint initBorderPaint() {
        Paint borderPaint = new Paint();
        borderPaint.setColor(borderColor);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidth*2);
        return borderPaint;
    }

    private Paint initEyesPaint() {
        Paint eyesPaint = new Paint();
        eyesPaint.setColor(eyesColor);
        eyesPaint.setStyle(Paint.Style.FILL);
        return eyesPaint;
    }

    private Paint initMouthPaint() {
        Paint mouthPaint = new Paint();
        mouthPaint.setColor(mouthColor);
        mouthPaint.setStyle(Paint.Style.FILL);
        return mouthPaint;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (onEmotionViewClickListener != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    checkAreaClick(event);
            }
            return true;
        } else {
            return super.onTouchEvent(event);
        }
    }

    public void setEmotionStyle(int emotionStyle) {
        this.emotionStyle = emotionStyle;
        invalidate();
    }

    private void checkAreaClick(MotionEvent event) {
        long gap = System.currentTimeMillis() - startTime;
        Logcat.log("gap : " + gap);
        if (gap <= 250) {
            if (onEmotionViewClickListener != null) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                boolean containsLeft = rectFLeft.contains(x, y);
                boolean containsRight = rectFRight.contains(x, y);
                boolean containsMouth = rectFmouth.contains(x, y);

                Logcat.log("gap : " + gap
                        + " ;containsLeft : " + containsLeft
                        + " ;containsRight : " + containsRight
                        + " ;containsMouth : " + containsMouth
                );
                if (containsLeft) {
                    onEmotionViewClickListener.onLeftEyeClick();
                } else if (containsRight) {
                    onEmotionViewClickListener.onRightEyeClick();
                } else if (containsMouth) {
                    onEmotionViewClickListener.onMouthClick();
                } else {
                }
            }
        }
    }

    public interface OnEmotionViewClickListener {
        void onLeftEyeClick();

        void onRightEyeClick();

        void onMouthClick();
    }
}
