package com.lukemi.android.tutorial.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;

/**
 * RatingBar use
 */
public class RatingBarActivity extends AppCompatActivity {

    private RatingBar mRatingBar;

    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        mRatingBar = findViewById(R.id.rt);
        mTvContent = findViewById(R.id.tv_content);
        mRatingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            String content = "ratingBar : " + ratingBar.getId() + "\n\n" +
                    "rating : " + rating + "\n\n" +
                    "fromUser : " + fromUser + "\n\n";
            mTvContent.setText(content);
        });
        mTvContent.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_content:
                mRatingBar.setRating(4.9f);
                break;
            default:
                break;
        }
    }
}
