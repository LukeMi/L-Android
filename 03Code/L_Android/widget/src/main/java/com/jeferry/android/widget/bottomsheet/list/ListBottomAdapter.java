package com.jeferry.android.widget.bottomsheet.list;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeferry.android.widget.R;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class ListBottomAdapter extends RecyclerView.Adapter<ListBottomAdapter.BaseAdapterViewHolder> {

    private final String TAG = ListBottomAdapter.class.getSimpleName();

    private List<Person> mList = new ArrayList();

    private OnChildClickListener mOnChildClickListener;

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @NonNull
    @Override
    public BaseAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        KLog.d(TAG, viewGroup.getClass().getCanonicalName());
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bottom_sheet, viewGroup, false);
        BaseAdapterViewHolder baseViewHolder = new BaseAdapterViewHolder(view);
        baseViewHolder.setAdapter(this);
        return baseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseAdapterViewHolder baseAdapterViewHolder, int i) {
        convert(baseAdapterViewHolder, mList.get(i), i);
    }

    public void replaceData(List<Person> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnChildClickListener(OnChildClickListener onChildClickListener) {
        mOnChildClickListener = onChildClickListener;
    }

    public OnChildClickListener getOnChildClickListener() {
        return mOnChildClickListener;
    }

    public void convert(@NonNull BaseAdapterViewHolder baseAdapterViewHolder, Person person, int position) {
        mList.indexOf(person);
        baseAdapterViewHolder
                .setText(R.id.tv_name, person.name)
                .setText(R.id.tv_info, person.info)
                .setOnChildClickListener(R.id.tv_name, position);
    }

    public class BaseAdapterViewHolder extends RecyclerView.ViewHolder {

        public ListBottomAdapter adapter;

        public BaseAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public BaseAdapterViewHolder setText(@IdRes int id, String text) {
            TextView textView = itemView.findViewById(id);
            textView.setText(text);
            return this;
        }

        public View getView(@IdRes int id) {
            return itemView.findViewById(id);
        }

        public BaseAdapterViewHolder setOnChildClickListener(@IdRes int id, int position) {
            itemView.findViewById(id).setOnClickListener(v -> {
                if (ListBottomAdapter.this.getOnChildClickListener() != null) {
                    ListBottomAdapter.this.getOnChildClickListener().onChildClick(v, position);
                }
            });
            return this;
        }

        public void setAdapter(ListBottomAdapter adapter) {
            this.adapter = adapter;
        }
    }


    public interface OnChildClickListener {
        void onChildClick(View view, int position);
    }

}


