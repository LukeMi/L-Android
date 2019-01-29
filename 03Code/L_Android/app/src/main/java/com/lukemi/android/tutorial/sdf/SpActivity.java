package com.lukemi.android.tutorial.sdf;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lukemi.android.common.util.Logcat;
import com.lukemi.android.tutorial.R;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lukemi
 * @date 2018/12/24 17:17
 * @des SharedPreference
 * @mail chenmingzhiji@163.com or mingzhichen1990@gmail.com
 */
public class SpActivity extends AppCompatActivity {

    private final String KEY = "key";

    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_show)
    TextView tvShow;

    private SharedPreferences mSP;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        mSP = this.getSharedPreferences("info", MODE_PRIVATE);
        mEditor = mSP.edit();
    }


    @OnClick({R.id.tv_edit, R.id.tv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                String input = etInput.getText().toString();
                if (!TextUtils.isEmpty(input)) {
                    mEditor.putString(input, input).commit();
                }
                break;
            case R.id.tv_clear:
                mEditor.clear().commit();
                break;
            default:
                break;
        }
        showResult();
    }

    private void showResult() {
        String show = "";
        Map<String, ?> all = mSP.getAll();
        Set<String> strings = all.keySet();
        Iterator<String> iterator = strings.iterator();
        Logcat.log("all.size() = " + all.size());
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = (String) all.get(key);
            show += value + "\n";
        }
        tvShow.setText(show);
    }
}
