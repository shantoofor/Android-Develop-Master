package com.shantoo.develop.library.model.adapter;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;

import com.shantoo.develop.library.model.holder.BaseHolder;
import com.shantoo.develop.library.model.holder.MoreHolder;

import java.util.List;

public abstract class BaseListViewAdapter<T> extends BaseViewAdapter<T> {
    private MoreHolder moreHolder;
    private static final int LIST_VIEW_ITEM = 0;// 加载普通ListView的数据
    private static final int MORE_VIEW_ITEM = 1;// 上拉加载
    //private static final int REFRESH_VIEW_ITEM = 2;// 下拉刷新

    public BaseListViewAdapter(Context context, List<T> dataList, AbsListView listView) {
        super(context, dataList, listView);
    }

    public BaseListViewAdapter(Context context, List<T> dataList) {
        super(context, dataList);
    }

    public BaseListViewAdapter(List<T> dataList) {
        super(dataList);
    }

    @Override
    public int getCount() {
        int count = 1;
        if (dataList != null)
            count = hasMore() ? dataList.size() + 1 : dataList.size();
        return count;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initHolder(int position, View convertView) {
        if (convertView == null) {
            // 每一个界面的holder都是不同的，而且未知
            if (getItemViewType(position) == MORE_VIEW_ITEM) {
                holder = getMoreHolder();
            } else {
                holder = getHolder();
            }
        } else {
            if (convertView.getTag() != null) {
                holder = (BaseHolder<T>) convertView.getTag();
            }
        }
    }

    // 根据不同的子界面需求，生成相应的holder
    @Override
    public abstract BaseHolder<T> getHolder();

    public abstract boolean hasMore();

    public void loadMore() {
    }

    @Override
    public void setHolderData(int position) {
        if (!hasMore() && getItemViewType(position) == LIST_VIEW_ITEM) {
            if (dataList != null)
                holder.setData(dataList.get(position));
        }
    }

    private BaseHolder<T> getMoreHolder() {
        // 做更多条目的具体实现
        if (moreHolder == null) {
            moreHolder = new MoreHolder(context, hasMore(), this);
        }
        return moreHolder;
    }

    // 获取当前索引对应的条目类型（1，一般条目，2，加载更多条目）
    @Override
    public int getItemViewType(int position) {
        if (hasMore() && position == (getCount() - 1)) {
            // 加载更多类型的条目
            return MORE_VIEW_ITEM;
        } else {
            // 一般类型条目
            return getInnerType(position);
        }
    }

    // 当前方法在后期需要去重新，以保证可以扩展更多种的条目类型
    public int getInnerType(int position) {
        return LIST_VIEW_ITEM;
    }
}
