package com.shantoo.develop.library.model.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;

import com.shantoo.develop.library.model.holder.BaseHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseViewAdapter<T> extends BaseAdapter {
    protected Context context;
    protected BaseHolder<T> holder;
    protected List<T> dataList;
    protected AbsListView listView;
    protected List<BaseHolder<T>> holderList = new ArrayList<>();

    public BaseViewAdapter(List<T> dataList) {
        this.dataList = dataList;
    }

    public BaseViewAdapter(Context context, List<T> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    public BaseViewAdapter(Context context, List<T> dataList, AbsListView listView) {
        this.context = context;
        this.dataList = dataList;
        this.listView = listView;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        initHolder(position, convertView);
        setHolderData(position);
        if (holder != null) {
            holderList.add(holder);
        }
        return holder.getRootView();
    }

    // 根据不同的子界面需求，生成相应的holder
    public abstract BaseHolder<T> getHolder();

    public abstract void setHolderData(int position);

    public abstract void initHolder(int position, View convertView);
}
