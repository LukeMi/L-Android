package com.lukemi.android.common.test;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lukemi.android.common.R;
import com.lukemi.android.common.util.Logcat;

import java.util.ArrayList;
import java.util.List;


public class FontActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView welcomeTV;
    private Button changeBTN;
    private ListView showFontsLV;
    private List<String> listStr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        initView();
    }

    private void initView() {
        welcomeTV = ((TextView) findViewById(R.id.welcomeTV));
        changeBTN = ((Button) findViewById(R.id.changeBTN));
        showFontsLV = ((ListView) findViewById(R.id.showFontsLV));

        changeBTN.setOnClickListener(this);
        showFontsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String path = listStr.get(position);
                Typeface typeface = Typeface.createFromAsset(getAssets(), path);
                welcomeTV.setTypeface(typeface);
                changeBTN.setTypeface(typeface);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i1 = v.getId();
        if (i1 == R.id.changeBTN) {
            try {
                AssetManager assetManager = getAssets();
                String[] fontses = assetManager.list("fonts");
                int fontsNO = fontses.length;
                for (int i = 0; i < fontsNO; i++) {
                    Logcat.log(fontses[i]);
                    listStr.add("fonts/" + fontses[i]);
                }
                showFontsLV.setAdapter(new ArrayAdapter<String>(FontActivity.this, android.R.layout.simple_list_item_1,
                                                                       fontses));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
