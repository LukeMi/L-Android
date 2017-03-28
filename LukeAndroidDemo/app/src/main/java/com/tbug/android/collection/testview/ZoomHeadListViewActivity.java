package com.tbug.android.collection.testview;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.tbug.android.collection.R;
import com.tbug.android.collection.view.ZoomHeadListView;

import java.util.ArrayList;
import java.util.List;

public class ZoomHeadListViewActivity extends AppCompatActivity {

    private ZoomHeadListView zoomHeadListView;
    private List<String> lists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_head_list_view);
        initListData();
        initView();
    }

    private void initListData() {
        for (int i = 0; i < 7; i++) {
            lists.add("这是第 " + (i + 1) + " 条数据");
        }

    }


    private void initView() {
        View view = View.inflate(this, R.layout.zoom_head_listview, null);
        ImageView iv = (ImageView) view.findViewById(R.id.zoomHeadListView_ImageView);
        iv.setImageResource(R.drawable.bd_loc_bg);

        zoomHeadListView = ((ZoomHeadListView) findViewById(R.id.zoomHeadListView));
        zoomHeadListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, lists));
        zoomHeadListView.addHeaderView(view);
        zoomHeadListView.setZoomImageView(iv);
        zoomHeadListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = (String) parent.getAdapter().getItem(position);
                Toast.makeText(ZoomHeadListViewActivity.this, "setOnItemClickListener: \n" + itemStr, Toast.LENGTH_SHORT).show();
            }
        });
        zoomHeadListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String itemStr = (String) parent.getAdapter().getItem(position);
                Toast.makeText(ZoomHeadListViewActivity.this, "setOnItemLongClickListener: \n" + itemStr, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 0, 0, "ContextMenu_0");
        menu.add(0, 1, 1, "ContextMenu_1");
        menu.add(0, 2, 2, "ContextMenu_2");
        menu.add(0, 3, 3, "ContextMenu_3");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        String contextItemStr = (String) item.getTitle();
        switch (item.getItemId()) {
            case 0:
                contextItemStr += 0;
                break;
            case 1:
                contextItemStr += 1;
                break;
            case 2:
                contextItemStr += 2;
                break;
            case 3:
                contextItemStr += 3;
                break;
        }
        if (contextItemStr != null) {
            Toast.makeText(ZoomHeadListViewActivity.this, "onContextItemSelected: \n" + contextItemStr, Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}
