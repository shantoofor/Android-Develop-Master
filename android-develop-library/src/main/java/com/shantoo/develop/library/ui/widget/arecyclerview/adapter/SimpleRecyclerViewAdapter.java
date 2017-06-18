package com.shantoo.develop.library.ui.widget.arecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shantoo.develop.library.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Aislli on 2015/12/25.
 */
public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mList;

    public SimpleRecyclerViewAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_f3_recycler, parent,
                false));
        return holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.idNum.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void add(String string) {
        insert(string, mList.size());
    }

    public void insert(String string, int position) {
        mList.add(position, string);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        int size = mList.size();
        mList.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void addAll(String[] strings) {
        int startIndex = mList.size();
        mList.addAll(startIndex, Arrays.asList(strings));
        notifyItemRangeInserted(startIndex, strings.length);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idNum;

        public MyViewHolder(View view) {
            super(view);
            idNum = (TextView) view.findViewById(R.id.id_num);
        }
    }
}
