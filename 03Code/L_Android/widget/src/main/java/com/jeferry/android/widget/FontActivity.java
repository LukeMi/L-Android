package com.jeferry.android.widget;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lukemi.android.common.base.RoutePath;
import com.lukemi.android.common.util.Logcat;

import java.util.ArrayList;
import java.util.List;

@Route(path = RoutePath.FONT_ACTIVITY)
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

    @Override
    public Resources getResources() {
        //禁止app字体大小跟随系统字体大小调节
        Resources resources = super.getResources();
        if (resources != null && resources.getConfiguration().fontScale != 1.0f) {
            Configuration configuration = resources.getConfiguration();
            configuration.fontScale = 1.0f;
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
        return resources;
    }

    private void initView() {
        welcomeTV = findViewById(R.id.welcomeTV);
        changeBTN = findViewById(R.id.changeBTN);
        showFontsLV = findViewById(R.id.showFontsLV);

        changeBTN.setOnClickListener(this);
        showFontsLV.setOnItemClickListener((parent, view, position, id) -> {
            String path = listStr.get(position);
            Typeface typeface = Typeface.createFromAsset(getAssets(), path);
            welcomeTV.setTypeface(typeface);
            changeBTN.setTypeface(typeface);
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.changeBTN) {
            try {
                AssetManager assetManager = getAssets();
                String[] fonts = assetManager.list("fonts");
                int fontsNO = fonts.length;
                for (int i = 0; i < fontsNO; i++) {
                    Logcat.log(fonts[i]);
                    listStr.add("fonts/" + fonts[i]);
                }
                showFontsLV.setAdapter(new ArrayAdapter<>(FontActivity.this, android.R.layout.simple_list_item_1,
                        fonts));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
