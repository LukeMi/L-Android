package com.lukemi.android.tutorial.user.userInfo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lukemi.android.tutorial.R;
import com.lukemi.android.tutorial.user.userInfo.bean.UserParamsBean;

import java.util.List;

/**
 * 用户信息 适配器
 */
public class UserInfoAdapter extends RecyclerView.Adapter<UserInfoAdapter.UserAdapterViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private List<UserParamsBean> list;

    public UserInfoAdapter(List<UserParamsBean> list, Context context) {
        this.list = list;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public UserAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.user_userinfo_item_params, parent, false);
        UserAdapterViewHolder userAdapterViewHolder = null;
        switch (viewType) {
            case UserParamsBean.ITEM_TYPE_1:
                itemView = mLayoutInflater.inflate(R.layout.user_userinfo_item_params, parent, false);
                userAdapterViewHolder = new UserAdapterViewHolder1(itemView);
                break;
            case UserParamsBean.ITEM_TYPE_2:
                itemView = mLayoutInflater.inflate(R.layout.user_userinfo_item_params, parent, false);
                userAdapterViewHolder = new UserAdapterViewHolder2(itemView);
                break;
            case UserParamsBean.ITEM_TYPE_3:

                userAdapterViewHolder = new UserAdapterViewHolder3(itemView);
                break;
        }
        return userAdapterViewHolder;
    }


    public List<UserParamsBean> getData() {
        return list;
    }

    public void setList(List<UserParamsBean> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(UserAdapterViewHolder holder, int position) {
        UserParamsBean userParamsBean = list.get(position);
        String key = userParamsBean.getKey();
        String value = userParamsBean.getValue();
        switch (userParamsBean.getItemType()) {
            case UserParamsBean.ITEM_TYPE_1:
                UserAdapterViewHolder1 holder1 = (UserAdapterViewHolder1) holder;
                holder1.setTvKey(key);
                holder1.setTvValue(value);
                break;
            case UserParamsBean.ITEM_TYPE_2:
                UserAdapterViewHolder2 holder2 = (UserAdapterViewHolder2) holder;
                holder2.setTvKey(key);
                holder2.setTvValue(value);
                break;
            case UserParamsBean.ITEM_TYPE_3:
                UserAdapterViewHolder3 holder3 = (UserAdapterViewHolder3) holder;
                holder3.setTvKey(key);
                holder3.setTvValue(value);
                break;
        }


    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class UserAdapterViewHolder extends RecyclerView.ViewHolder {

        public UserAdapterViewHolder(View itemView) {
            super(itemView);
        }
    }


    public static class UserAdapterViewHolder1 extends UserAdapterViewHolder {

        private TextView tvKey;
        private TextView tvValue;

        public UserAdapterViewHolder1(View itemView) {
            super(itemView);
            tvKey = itemView.findViewById(R.id.tv_hint);
            tvValue = itemView.findViewById(R.id.tv_value);
        }

        public void setTvKey(String key) {
            tvKey.setText(key);
        }

        public void setTvValue(String value) {
            tvValue.setText(value);
        }

    }


    public static class UserAdapterViewHolder2 extends UserAdapterViewHolder {

        private TextView tvKey;
        private TextView tvValue;

        public UserAdapterViewHolder2(View itemView) {
            super(itemView);
            tvKey = itemView.findViewById(R.id.tv_hint);
            tvValue = itemView.findViewById(R.id.tv_value);
        }

        public void setTvKey(String key) {
            tvKey.setText(key);
        }

        public void setTvValue(String value) {
            tvValue.setText(value);
            tvValue.setTextColor(Color.parseColor("#223344"));
            tvValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        }

    }

    public static class UserAdapterViewHolder3 extends UserAdapterViewHolder {

        private TextView tvKey;
        private TextView tvValue;

        public UserAdapterViewHolder3(View itemView) {
            super(itemView);
            tvKey = itemView.findViewById(R.id.tv_hint);
            tvValue = itemView.findViewById(R.id.tv_value);
        }

        public void setTvKey(String key) {
            tvKey.setText(key);
        }

        public void setTvValue(String value) {
            tvValue.setText(value);
            tvValue.setTextColor(Color.parseColor("#0f3ff4"));
            tvValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }

    }

}
