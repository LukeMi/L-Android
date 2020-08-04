package com.jeferry.android.widget;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.util.DensityUtil;


public class TextViewActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView vertical_Scroll;
    private TextView horizontal_Scroll;
    private TextView mTvSpannableStringBuilder;

    /**
     * 跑马灯textView
     */
    private TextView marqueeTextView;

    private TextView tvDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        vertical_Scroll = findViewById(R.id.vitercal_Scroll);
        horizontal_Scroll = findViewById(R.id.horizontal_Scroll);
        marqueeTextView = findViewById(R.id.marqueeTextView);
        mTvSpannableStringBuilder = findViewById(R.id.tv_SpannableStringBuilder);
        tvDrawable = findViewById(R.id.tv_drawable);
        tvDrawable.setOnClickListener(this);
//        marqueeTextView.init((WindowManager)this.getSystemService(WINDOW_SERVICE));
        setView();
        spannableStringBuilder();
    }

    /**
     * 设置View
     */
    private void setView() {
        vertical_Scroll.setMovementMethod(ScrollingMovementMethod.getInstance());
        horizontal_Scroll.setMovementMethod(ScrollingMovementMethod.getInstance());
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_drawable) {
            tvDrawable.setSelected(!tvDrawable.isSelected());
        }
    }

    private void spannableStringBuilder() {
        String colorText = "文字颜色";
        String background = "背景颜色";
        String size = "大小";
        String bold = "粗体";
        String italic = "斜体";
        String boldItalic = "粗斜体";
        String strikeThrough = "删除线";
        String underline = "下划线";
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.RED);
        BackgroundColorSpan backgroundColorSpan = new BackgroundColorSpan(Color.BLUE);
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(DensityUtil.sp2px(getApplicationContext(), 12));
        StyleSpan BoldStyleSpan = new StyleSpan(Typeface.BOLD);
        StyleSpan italicStyleSpan = new StyleSpan(Typeface.ITALIC);
        StyleSpan boldItalicStyleSpan = new StyleSpan(Typeface.BOLD_ITALIC);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        UnderlineSpan underlineSpan = new UnderlineSpan();

        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(colorText)
                .setSpan(foregroundColorSpan, 0, builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        builder.append(background)
                .setSpan(backgroundColorSpan, builder.length() - background.length(), builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        builder.append(size)
                .setSpan(absoluteSizeSpan, builder.length() - size.length(), builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        builder.append(bold)
                .setSpan(BoldStyleSpan, builder.length() - bold.length(), builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        builder.append(italic)
                .setSpan(italicStyleSpan, builder.length() - italic.length(), builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        builder.append(boldItalic)
                .setSpan(boldItalicStyleSpan, builder.length() - boldItalic.length(), builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        builder.append(strikeThrough)
                .setSpan(strikethroughSpan, builder.length() - strikeThrough.length(), builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        builder.append(underline)
                .setSpan(underlineSpan, builder.length() - underline.length(), builder.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        mTvSpannableStringBuilder.setText(builder);
    }
}
