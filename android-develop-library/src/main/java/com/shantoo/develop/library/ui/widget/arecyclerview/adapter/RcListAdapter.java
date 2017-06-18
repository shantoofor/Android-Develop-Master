package com.shantoo.develop.library.ui.widget.arecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shantoo.develop.library.R;
import com.shantoo.develop.library.ui.widget.arecyclerview.widget.ARecyclerBaseAdapter;

import java.util.List;

/**
 * Created by Aislli on 2015/12/29.
 */
public class RcListAdapter extends ARecyclerBaseAdapter<RcListAdapter.MyViewHolder, String> {

    public RcListAdapter(Context mContext, List<String> mList) {
        super(mContext, mList);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_f3_recycler, parent,
                false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.idNum.setText(mList.get(position));
        if (null != onItemClickListener) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }
        if (null != onItemLongClickListener) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemLongClickListener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                    return true;
                }
            });
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView idNum;
        View itemView;

        public MyViewHolder(View view) {
            super(view);
            itemView = view;
            idNum = (TextView) view.findViewById(R.id.id_num);
        }
    }
}
