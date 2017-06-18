package com.shantoo.develop.library.ui.widget.wheelview.adapter;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;

/**
 * WheelView 适配器接口
 *
 * @author 王艳
 */
public interface WheelViewAdapter {
    /**
     * 获取条目的个数
     *
     * @return WheelView 的条目个数
     */
    int getItemsCount();

    /**
     * Get a View that displays the data at the specified position in the data set
     *
     * @param index       the item index
     * @param convertView the old view to reuse if possible
     * @param parent      the parent that this view will eventually be attached to
     * @return the wheel item View
     */
    View getItem(int index, View convertView, ViewGroup parent);

    /**
     * Get a View that displays an empty wheel item placed before the first or after
     * the last wheel item.
     *
     * @param convertView the old view to reuse if possible
     * @param parent      the parent that this view will eventually be attached to
     * @return the empty item View
     */
    View getEmptyItem(View convertView, ViewGroup parent);

    /**
     * Register an observer that is called when changes happen to the data used by this adapter.
     *
     * @param observer the observer to be registered
     */
    void registerDataSetObserver(DataSetObserver observer);

    /**
     * Unregister an observer that has previously been registered
     *
     * @param observer the observer to be unregistered
     */
    void unregisterDataSetObserver(DataSetObserver observer);
}
