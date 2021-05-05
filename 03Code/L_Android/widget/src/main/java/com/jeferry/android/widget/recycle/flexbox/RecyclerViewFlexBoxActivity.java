package com.jeferry.android.widget.recycle.flexbox;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.jeferry.android.widget.R;
import com.jeferry.android.widget.flexbox.FlexBoxEntity;

import java.util.ArrayList;

public class RecyclerViewFlexBoxActivity extends AppCompatActivity {
    private RecyclerView mRvFlex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_flex);
        initView();
    }

    private void initView() {
        mRvFlex = findViewById(R.id.rv_flex);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this, FlexDirection.ROW, FlexWrap.WRAP);
        mRvFlex.setLayoutManager(flexboxLayoutManager);
        ArrayList<FlexBoxEntity> list = new ArrayList<>();
        list.add(new FlexBoxEntity("java", false));
        list.add(new FlexBoxEntity("Python", false));
        list.add(new FlexBoxEntity("JavaScript", false));
        list.add(new FlexBoxEntity("PHP", false));
        list.add(new FlexBoxEntity("Swift", false));
        list.add(new FlexBoxEntity("Ruby", false));
        list.add(new FlexBoxEntity("Objective-C", false));
        list.add(new FlexBoxEntity("Kotlin", false));
        list.add(new FlexBoxEntity("ActionScript", false));
        list.add(new FlexBoxEntity("LiveCode", false));
        list.add(new FlexBoxEntity("Prolog", false));
        list.add(new FlexBoxEntity("PowerShell", false));
        list.add(new FlexBoxEntity("OpenEdge ABL", false));
        FlexboxAdapter flexboxAdapter = new FlexboxAdapter(R.layout.item_rv_flexbox, list);
        flexboxAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (view.getId() == R.id.tv_text) {
                if (adapter.getItem(position) instanceof FlexBoxEntity) {
                    FlexBoxEntity entity = (FlexBoxEntity) adapter.getItem(position);
                    entity.selected = !entity.selected;
                    adapter.notifyItemChanged(position);
                }
            }
        });
        mRvFlex.setAdapter(flexboxAdapter);

    }
}
