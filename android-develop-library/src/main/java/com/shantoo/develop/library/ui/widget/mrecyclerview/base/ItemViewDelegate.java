package com.shantoo.develop.library.ui.widget.mrecyclerview.base;

/**
 * Created by Administrator on 2017/2/24.
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

}