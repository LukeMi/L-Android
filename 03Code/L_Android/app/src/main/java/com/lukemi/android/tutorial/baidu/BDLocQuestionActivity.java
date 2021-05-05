package com.lukemi.android.tutorial.baidu;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.tutorial.R;


public class BDLocQuestionActivity extends AppCompatActivity {

    String instrament = null;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bdloc_question);

        instrament = getResources().getString(R.string.error_code_detail)
                + getResources().getString(R.string.qa_callback)
                + getResources().getString(R.string.qa_callback_mail);

        tv = findViewById(R.id.instrament);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        tv.setText(instrament);
    }
}
