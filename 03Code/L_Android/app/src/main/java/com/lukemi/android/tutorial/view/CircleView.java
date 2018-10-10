package com.lukemi.android.tutorial.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lukemi.android.tutorial.R;

public class CircleView extends View {


    private final int DEF_CIRCLE_COLOR = Color.TRANSPARENT;
    private final int DEF_CIRCLE_RADIUS = 0;
    private int circleColor = DEF_CIRCLE_COLOR;
    private int circleRadius = DEF_CIRCLE_RADIUS;

    private Paint circlePaint = null;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleView, defStyleAttr, 0);
        circleColor = array.getColor(R.styleable.CircleView_circle_color, DEF_CIRCLE_COLOR);
        circleRadius = (int) array.getDimension(R.styleable.CircleView_circle_radius, DEF_CIRCLE_RADIUS);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initOutCirclePaint();
        drawOutCircle(canvas);
    }

    private void initOutCirclePaint() {
        circlePaint = new Paint();
        circlePaint.setColor(circleColor);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);
    }

    private void drawOutCircle(Canvas canvas) {
        int width = this.getWidth();
        int height = getHeight();
        float x = 0 /*getX()*/;
        float y = 0 /*getY()*/;
        int radius = Math.min((width - getPaddingLeft() - getPaddingRight()) / 2, (height - getPaddingTop() - getPaddingBottom()) / 2);
        radius = Math.min(radius, circleRadius);
        float cx = x + width / 2;
        float cy = y + height / 2;
        canvas.drawCircle(cx, cy, radius, circlePaint);
//        System.out.println("radius : " + radius + " ;cx : " + cx + " ;cy : " + cy);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}

