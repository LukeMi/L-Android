package com.lukemi.android.tutorial.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.lukemi.android.tutorial.util.Logcat;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局
 */
public class FlowLayout extends ViewGroup {

    private int mWidth = 0;
    private int mHeight = 0;

    private List<List<View>> mAllViews = new ArrayList<>();
    private List<Integer> lineHeights = new ArrayList<>();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mAllViews.clear();
        lineHeights.clear();

        //wrap_content
        int width = 0;
        int height = 0;

        int lineHeight = 0;
        int lineWidth = 0;

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHieght = MeasureSpec.getSize(heightMeasureSpec);

        Logcat.log("modeWidth : " + modeWidth);
        Logcat.log("modeHeight : " + modeHeight);

        int childCount = getChildCount();
        int lines = 0;
        for (int i = 0; i < childCount; i++) {
            //get View params
            View childAt = this.getChildAt(i);
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) childAt.getLayoutParams();
            int leftMargin = lp.leftMargin;
            int topMargin = lp.topMargin;
            int rightMargin = lp.rightMargin;
            int bottomMargin = lp.bottomMargin;
            int widthChild = childAt.getMeasuredWidth();
            int heightChild = childAt.getMeasuredHeight();
            int realWidth = leftMargin + widthChild + rightMargin;
            int realHeight = topMargin + heightChild + bottomMargin;
            if (lines == 0 && mAllViews.size() == 0) {
                mAllViews.add(new ArrayList<View>());
                lineHeights.add(0);
            }
            if (lineWidth + realWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
                width = Math.max(lineWidth, width);
                lineWidth = realWidth;
                height += lineHeight;
                lineHeight = realHeight;
                lines++;

                mAllViews.add(new ArrayList<View>());
                System.out.println("lineWidth + realWidth > ----" + "lines ++ = " + lines);
                lineHeights.add(lineHeight);

            } else {
                lineWidth += realWidth;
                lineHeight = Math.max(lineHeight, realHeight);
                lineHeights.set(mAllViews.size() - 1, lineHeight);
            }

            List<View> views = mAllViews.get(mAllViews.size() - 1);
            views.add(childAt);

            if (i == childCount - 1) {
                lines++;
                width = Math.max(lineWidth, width);
                height += lineHeight;
            }
        }

        int realWidth = MeasureSpec.EXACTLY == modeWidth ? sizeWidth : width + getPaddingLeft() + getPaddingRight();
        int realHeight = MeasureSpec.EXACTLY == modeHeight ? sizeHieght : height + getPaddingTop() + getPaddingTop();

        Logcat.log("realWidth : " + realWidth);
        Logcat.log("realHeight : " + realHeight);
        Logcat.log("lines : " + lines);

        setMeasuredDimension(realWidth, realHeight);
    }

    @Override
    protected void onLayout(boolean b, int e, int i1, int i2, int i3) {
        System.out.println("mAllViews.size() = " + mAllViews.size() + " --- " + lineHeights.size());
        for (int i = 0; i < mAllViews.size(); i++) {
            List<View> lineViews = mAllViews.get(i);
            for (int j = 0; j < lineViews.size(); j++) {
                View view = lineViews.get(j);
                MarginLayoutParams lpView = (MarginLayoutParams) view.getLayoutParams();
                int leftMargin = lpView.leftMargin;
                int topMargin = lpView.topMargin;
                int rightMargin = lpView.rightMargin;
                int bottomMargin = lpView.bottomMargin;

                int top = 0;
                int left = 0;
                int right = 0;
                int bootom = 0;
                for (int k = 0; k < i; k++) {
                    int height = lineHeights.get(k);
                    top += height;
                }
                top += topMargin;

                for (int k = 0; k < j; k++) {
                    View view1 = lineViews.get(k);
                    MarginLayoutParams lp = (MarginLayoutParams) view1.getLayoutParams();
                    int width = view1.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                    left += width;
                }

                left += leftMargin;
                right = view.getMeasuredWidth() + left;
                bootom = view.getMeasuredHeight() + top;

                System.out.println("left : " + left + "\n"
                        + "top : " + top + "\n"
                        + "right : " + right + "\n"
                        + "bootom : " + bootom);
                view.layout(left, top, right, bootom);
            }
        }
    }

}
