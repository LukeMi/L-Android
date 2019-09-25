package com.lukemi.android.tutorial.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lukemi.android.tutorial.R;

public class EditText1Activity extends AppCompatActivity {

    private final String TAG = EditText1Activity.class.getSimpleName();

    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text1);
        initView();
    }

    private void initView() {
        mEditText = findViewById(R.id.editText);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d(TAG, "beforeTextChanged : " + s);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d(TAG, "onTextChanged : " + s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged : " + s);
            }
        });
        mEditText.setOnEditorActionListener((v, actionId, event) -> {
            Log.d(TAG, "v.getId() : " + v.getId() + " ; actionId : " + actionId);
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Log.d(TAG, v.getText().toString());
            }
            return false;
        });
    }

}
