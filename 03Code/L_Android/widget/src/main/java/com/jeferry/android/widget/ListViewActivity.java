package com.jeferry.android.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.lukemi.android.common.util.Logcat;
import com.jeferry.android.widget.view.CircleImageView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * ListView学习
 * 本例子资源来自于内涵段子-->>段子模块
 * <p>
 * created bt: tubg
 * created at: 2017/4/10 11:12
 * e_mail: tbug.chen@gmail.com / tbugchen@163.com
 */
public class ListViewActivity extends AppCompatActivity {


    private ListView listView;
    private List<String> lists = new ArrayList<>();
    private LVAdapter lvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initAdapter();
        initView();
    }

    private void initAdapter() {
        lists.add("list  item 1 ");
        lists.add("list  item 2 ");
        lists.add("list  item 3 ");
        lists.add("list  item 4 ");
        lists.add("list  item 5 ");
        lists.add("list  item 6 ");
        lists.add("list  item 7 ");
        lists.add("list  item 8 ");
        lists.add("list  item 9 ");
        lists.add("list  item 10 ");
        lists.add("list  item 11");
        lists.add("list  item 13 ");
        lvAdapter = new LVAdapter(this, lists);

    }

    private void initView() {
        listView = findViewById(R.id.listView);
        listView.setAdapter(lvAdapter);
    }

    class LVAdapter extends BaseAdapter {

        int selectItem = -1;
        private Context context;
        private LayoutInflater mInflater;
        private List<String> dataBeens;

        public LVAdapter(Context context, List<String> dataBeens) {
            this.context = context;
            mInflater = getLayoutInflater();
            this.dataBeens = dataBeens;

        }

        @Override
        public int getCount() {
            return dataBeens != null ? dataBeens.size() : 0;
        }

        @Override
        public Object getItem(int position) {
            return dataBeens.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_listview, parent, false);
                vh = new ViewHolder();
                vh.userIV = convertView.findViewById(R.id.user_icon);
                vh.userName = convertView.findViewById(R.id.userName);
                vh.deleteItemIV = convertView.findViewById(R.id.delte_item);
                vh.commentTV = convertView.findViewById(R.id.user_content);
                vh.support = convertView.findViewById(R.id.support);
                vh.unsupport = convertView.findViewById(R.id.unsupport);
                vh.hot = convertView.findViewById(R.id.hot);
                vh.share = convertView.findViewById(R.id.share);

                vh.support_text = convertView.findViewById(R.id.support_text);
                vh.unsupport_text = convertView.findViewById(R.id.unsupport_text);
                vh.hot_text = convertView.findViewById(R.id.hot_text);
                vh.share_text = convertView.findViewById(R.id.share_text);

                convertView.setTag(vh);
            } else {
                vh = (ViewHolder) convertView.getTag();
            }
            String s = dataBeens.get(position);
            vh.deleteItemIV.setOnClickListener(v -> showDeleteDialog(ListViewActivity.this, position));
            vh.commentTV.setText(s);
            return convertView;
        }

        /**
         * Glide加载图片
         * <p>
         * created by: tbug
         * created at: 2017/4/10 19:02
         */
        private void glideLoadPic(Context context, String url, ImageView imageView) {
            Glide
                    //传入上下文
                    .with(context)
                    //图片url
                    .load(url)
                    //传入要设置的ImageView
                    .into(imageView);
        }

        private void showDeleteDialog(Context context, int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle("choose the reason why not like")
                    .setPositiveButton("sure", (DialogInterface dialog, int which) -> {
                        //这里面的是Button的id
                        Logcat.log("which: setPositiveButton :" + which);
                        String flag = lists.get(position);
                        Iterator<String> iterator = lists.iterator();
                        while (iterator.hasNext()) {
                            if (iterator.next().endsWith(flag)) {
                                iterator.remove();
                            }
                        }
                        lvAdapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("cancel", (DialogInterface dialog, int which) -> Logcat.log("which: setNegativeButton :" + which))
                    .create()
                    .show();

        }

        public class ViewHolder {

            CircleImageView userIV;
            TextView userName;
            ImageView deleteItemIV;
            TextView commentTV;
            TextView support_text;
            TextView unsupport_text;
            TextView hot_text;
            TextView share_text;
            LinearLayout support;
            LinearLayout unsupport;
            LinearLayout hot;
            LinearLayout share;
        }

    }
}
