package com.lukemi.android.tutorial.killprocess;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.bean.AppInfo;

import java.util.ArrayList;

/**
 * 适配器
 */
class AppinfoAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<AppInfo> appList;
    private LayoutInflater filter;

    public AppinfoAdapter(Context context, ArrayList<AppInfo> appList) {
        this.context = context;
        this.appList = appList;
        filter = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (appList != null) {
            return appList.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return appList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = filter.inflate(R.layout.item_process, null);
            vh = new ViewHolder();
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.title = (TextView) convertView.findViewById(R.id.title);
        vh.pName = (TextView) convertView.findViewById(R.id.pName);
        vh.iconIV = (ImageView) convertView.findViewById(R.id.icon);

        vh.title.setText(appList.get(position).getAppName());
        vh.pName.setText(appList.get(position).getPn());
        Drawable icon = null;//appList.get(position).getIcon();
        if (icon != null) {
//            vh.iconIV.setImageDrawable(appList.get(position).getIcon());
        } else {
            vh.iconIV.setImageResource(R.mipmap.ic_launcher);
        }
        return convertView;
    }

    static class ViewHolder {
        public TextView title;
        public TextView pName;
        public ImageView iconIV;
    }
}
