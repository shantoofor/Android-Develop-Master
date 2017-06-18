package com.shantoo.develop.library.model.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;

import com.shantoo.develop.library.model.holder.BaseHolder;

import java.util.List;

public abstract class BaseGridViewAdapter<T> extends BaseViewAdapter<T> {
    public BaseGridViewAdapter(Context context, List<T> dataList) {
        super(context, dataList);
    }

    public BaseGridViewAdapter(Context context, List<T> dataList,
                               AbsListView listView) {
        super(context, dataList, listView);
    }

    @Override
    public void setHolderData(int position) {
        holder.setData(dataList.get(position));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initHolder(int position, View convertView) {
        if (convertView == null) {
            holder = getHolder();
        } else {
            if (convertView.getTag() != null) {
                holder = (BaseHolder<T>) convertView.getTag();
            }
        }
    }

    public abstract BaseHolder<T> getHolder();
}
