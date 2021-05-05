package com.jeferry.android.widget;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lukemi.android.common.view.ZoomHeadListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿照QQ空间头部可拉伸
 * 功能加入：<br/>
 * 标签：ListView，ArrayAdapter，ContextMenu，
 */
public class ZoomHeadListViewActivity extends AppCompatActivity {
    private ZoomHeadListView zoomHeadListView;
    private List<String> lists = new ArrayList<>();
    private String contextItemStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_head_list_view);
        initListData();
        initView();
    }


    /**
     * 初始化ListView数据
     *
     * @author tbug
     * created at 2017/3/28 17:09
     */
    private void initListData() {
        for (int i = 0; i < 7; i++) {
            lists.add("这是第 " + (i + 1) + " 条数据");
        }

    }

    private void initView() {
        View view = View.inflate(this, R.layout.zoom_head_listview, null);
        ImageView iv = view.findViewById(R.id.zoomHeadListView_ImageView);
        iv.setImageResource(R.drawable.bd_loc_bg);

        zoomHeadListView = findViewById(R.id.zoomHeadListView);
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
//                Toast.makeText(ZoomHeadListViewActivity.this, "setOnItemLongClickListener: \n" + itemStr, Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //给ListView注册上下文菜单
        registerForContextMenu(zoomHeadListView);

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Context Menu");

        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu_context_zoomlistview, menu);
        menu.add(0, 0, 0, "ContextMenu_0");
        menu.add(0, 1, 1, "ContextMenu_1");
        menu.add(0, 2, 2, "ContextMenu_2");
        menu.add(0, 3, 3, "ContextMenu_3");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        contextItemStr = (String) item.getTitle();
        switch (item.getItemId()) {
            case 0:
                contextItemStr += "-- itemID->" + 0;
                break;
            case 1:
                contextItemStr += "-- itemID->" + 1;
                break;
            case 2:
                contextItemStr += "-- itemID->" + 2;
                break;
            case 3:
                contextItemStr += "-- itemID->" + 3;
                break;
//            case R.id.w9:
//                contextItemStr += "-- itemID->" + 9;
//            case R.id.w10:
//                contextItemStr += "-- itemID->" + 10;
//            case R.id.w11:
//                contextItemStr += "-- itemID->" + 11;
//                break;
        }
        if (contextItemStr != null) {
            Toast.makeText(ZoomHeadListViewActivity.this, "onContextItemSelected:\n" + contextItemStr, Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}
