package com.lukemi.android.tutorial.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.lukemi.android.tutorial.util.Logcat;

import java.util.ArrayList;
import java.util.List;


/**
 * 流式布局
 * Created by mzchen on 2017/1/18.
 */

public class FlowLayout extends ViewGroup implements View.OnClickListener {

    /**
     * 存储所有的View，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    /**
     * 记录每一行的最大高度
     */
    private List<Integer> mLineHeight = new ArrayList<Integer>();
    /**
     * 布局的宽高
     */
    private int width = 0; /*height = 0*/
    ;
    /**
     * 每一行的宽度，width不断取其中最大的宽度
     */
//    private int lineWidth = 0;
    /**
     * 每一行的高度，累加至height
     */
    private int lineHeight = 0;
    /**
     * 布局中的子控件
     */
    private View child;
    /**
     * 布局中子控件设置的LayoutParam
     */
    private MarginLayoutParams layoutParams;

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = 0;
        int lineWidth = 0;
        int count = 0;
        //根据所有子控件设置自己的宽和高
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            // 让系统去测量当前child的宽高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 获取当前child实际占据的宽高
            layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int childHeight = child.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            //如果加入当前child后超出最大允许宽度，则将目前最大宽度给width，累加height，然后开启新行
            if (lineWidth + childWidth > MeasureSpec.getSize(widthMeasureSpec)) {
                width = Math.max(lineWidth, childWidth);// 对比得到最大宽度
                // 开启新行，将当前行的宽高设为当前child的宽高
                lineWidth = childWidth;
                lineHeight = childHeight;
                // 累加行高
                height += lineHeight;
                Logcat.log("onMeasure: for " + (++count));
            } else {
                // 否则（不换行）累加行宽，lineHeight取最大高度
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较，并累加行高
            if (i == getChildCount() - 1 && lineWidth < MeasureSpec.getSize(widthMeasureSpec)) {
                Logcat.log("onMeasure: lastItem " + height);
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }
        //如果是布局中设置的是wrap_content，设置为我们计算的值；否则，直接设置为父容器测量的值。
        width = (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY) ? MeasureSpec.getSize(widthMeasureSpec) : width;
        height = (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) ? MeasureSpec.getSize(heightMeasureSpec) : height;
        setMeasuredDimension(width, height);
        requestLayout();

        Logcat.log("onMeasure: " + height);
        Logcat.log("onMeasure count:  " + getChildCount());
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();
        int lineWidth = 0;
        lineHeight = 0;
        // 存储每一行所有的childView
        List<View> lineViews = new ArrayList<View>();
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            // 如果需要换行
            if (childWidth + layoutParams.leftMargin + layoutParams.rightMargin + lineWidth > width) {
                // 记录这一行所有的View中的最大高度
                mLineHeight.add(lineHeight);
                // 将当前行的childView保存，然后开启新的ArrayList保存下一行的childView
                mAllViews.add(lineViews);
                lineWidth = 0;// 重置行宽
                lineViews = new ArrayList<View>();
            }
            //如果不需要换行，则累加
            lineWidth += childWidth + layoutParams.leftMargin + layoutParams.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + layoutParams.topMargin + layoutParams.bottomMargin);
            lineViews.add(child);
        }
        // 记录最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);
        //记录当前child相对前一个child的坐标位置
        int left = 0;
        int top = 0;
        // 一行一行的遍历
        for (int i = 0; i < mAllViews.size(); i++) {
            // 遍历每一行
            lineViews = mAllViews.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                child = lineViews.get(j);
                if (child.getVisibility() == View.GONE) continue;
                layoutParams = (MarginLayoutParams) child.getLayoutParams();
                //计算child的坐标
                int leftPosition = left + layoutParams.leftMargin;
                int topPosition = top + layoutParams.topMargin;
                int rightPosition = leftPosition + child.getMeasuredWidth();
                int bottomPosition = topPosition + child.getMeasuredHeight();
                //对child进行布局
                child.layout(leftPosition, topPosition, rightPosition, bottomPosition);
                //相对位置右移
                left = rightPosition + layoutParams.rightMargin;
            }
            //相对位置从左侧重头开始，并下移
            left = 0;
            top += mLineHeight.get(i);
        }
        requestLayout();
    }

    @Override
    public void onClick(View v) {

    }
}
