package com.lukemi.android.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.lukemi.android.common.R;


public class RingView extends View {


    private final int DEF_CIRCLE_COLOR = Color.BLACK;
    private final int DEF_CIRCLE_RADIUS = 0;
    private int circleColor = DEF_CIRCLE_COLOR;
    private int circleRadius = DEF_CIRCLE_RADIUS;

    private Paint circleOutPaint = null;

    public RingView(Context context) {
        super(context);
    }

    public RingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RingView, defStyleAttr, 0);
        circleColor = array.getColor(R.styleable.RingView_border_color, DEF_CIRCLE_COLOR);
        circleRadius = (int) array.getDimension(R.styleable.RingView_border_width, DEF_CIRCLE_RADIUS);
        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initOutCirclePaint();
        drawOutCircle(canvas);
    }

    private void initOutCirclePaint() {
        circleOutPaint = new Paint();
        circleOutPaint.setColor(circleColor);
        circleOutPaint.setAntiAlias(true);
    }

    private void drawOutCircle(Canvas canvas) {
        int width = this.getWidth();
        int height = getHeight();

        int radius = Math.min((width - getPaddingLeft() - getPaddingRight()) / 2, (height - getPaddingTop() - getPaddingBottom()) / 2);
        radius = Math.min(radius, circleRadius);

        float cx = width / 2;
        float cy = height / 2;

        System.out.println("radius : " + radius + " ;cx : " + cx + " ;cy : " + cy);

//        canvas.drawCircle(cx, cy, radius, circleOutPaint);
//        canvas.drawARGB(255,0,0,255);
        canvas.drawLine(cx,cy,getWidth(),getHeight(),circleOutPaint);



    }
}

