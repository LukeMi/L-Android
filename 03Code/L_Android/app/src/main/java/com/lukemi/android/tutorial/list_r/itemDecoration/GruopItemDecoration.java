package com.lukemi.android.tutorial.list_r.itemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;

import com.lukemi.android.tutorial.R;

/**
 * 加标题的风格线
 * Created by chenmz
 * on 2018/1/11 .
 */

public class GruopItemDecoration extends RecyclerView.ItemDecoration {

    private Context context;

    private int titleHeight = 0;
    private int titleBKGColor = 0;
    private int textSize = 0;
    private int textColor = 0;
    private String TAG = "GruopItemDecoration";
    private DecorationListener callback;

    private Paint paint;
    private TextPaint textPaint;

    public GruopItemDecoration() {

    }

    public GruopItemDecoration(Context context, DecorationListener callback) {
        this.context = context;
        this.callback = callback;
        titleHeight = 40;
        titleBKGColor = context.getResources().getColor(R.color.white);
        textSize = 40;
        textColor = context.getResources().getColor(R.color.red);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int pos = parent.getChildAdapterPosition(view);
//        Log.i(TAG, "getItemOffsets：" + pos);
        long groupId = callback.getGroupId(pos);
        if (groupId < 0) return;
        if (pos == 0 || isFirstInGroup(pos)) {//同组的第一个才添加padding
            outRect.top = titleHeight;
        } else {
            outRect.top = 0;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        initPaint();
        initTextPaint();

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            long groupId = callback.getGroupId(position);
            if (groupId < 0) return;
            String textLine = callback.getGroupTitle(position).toUpperCase();
//            Log.i(TAG, "textLine：" + textLine);
            if (position == 0 || isFirstInGroup(position)) {
                float top = view.getTop() ;
                float bottom = view.getTop();
                c.drawRect(left, top, right, bottom, paint);//绘制红色矩形
                c.drawText(textLine, left, bottom , textPaint);//绘制文本

                Log.i(TAG, "textLine：" + textLine
                        + "left：" + left
                        + "bottom：" + bottom
                        + "textLine：" + textLine
                        + "textLine：" + textLine
                );
            }
        }
    }

    private void initTextPaint() {
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(titleBKGColor);
    }


    private boolean isFirstInGroup(int pos) {
        if (pos == 0) {
            return true;
        } else {
            long prevGroupId = callback.getGroupId(pos - 1);
            long groupId = callback.getGroupId(pos);
            return prevGroupId != groupId;
        }

    }

    /**
     * 回调
     */
    public interface DecorationListener {

        int getGroupId(int position);//

        String getGroupTitle(int position);
    }

}
