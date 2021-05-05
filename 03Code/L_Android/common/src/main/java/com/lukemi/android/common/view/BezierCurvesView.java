package com.lukemi.android.common.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 贝塞尔曲线
 */
public class BezierCurvesView extends View {


    private int lineWidth = 5;
    private int lineColor = Color.BLACK;

    public BezierCurvesView(Context context) {
        super(context);
    }

    public BezierCurvesView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BezierCurvesView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBezier(canvas);
    }

    private void drawBezier(Canvas canvas) {
        Paint paint = initLinePaint();
        Path path = new Path();
        path.moveTo(0, 600);
        path.cubicTo(0, 350, 40, 450, 100, 400);
        path.cubicTo(120, 250, 150, 300, 200, 800);
        path.cubicTo(300, 400, 400, 850, 500, 300);
        canvas.drawPath(path, paint);

        Paint pointPaint = new Paint();
        pointPaint.setColor(Color.BLACK);
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setStrokeWidth(5);
        canvas.drawCircle(0, 600, 15, pointPaint);
        canvas.drawCircle(100, 400, 15, pointPaint);
        canvas.drawCircle(200, 800, 15, pointPaint);
        canvas.drawCircle(300, 400, 15, pointPaint);
        canvas.drawCircle(400, 850, 15, pointPaint);
        canvas.drawCircle(500, 300, 15, pointPaint);

    }

    private Paint initLinePaint() {
        Paint paint = new Paint();
        paint.setStrokeWidth(lineWidth);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(lineColor);
        return paint;
    }
}
