package com.shantoo.develop.library.model.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shantoo.develop.library.R;

import java.util.Arrays;
import java.util.List;

/**
 * 作者: shantoo on 2017/5/26 14:53.
 */

public class RecyclerViewTreeAdapter extends RecyclerViewBaseAdapter{
    private int type;
    public final static int IMAGE_TEXT_NULL_ARROW = 0;
    public final static int TEXT_NULL_TEXT_ARROW = 1;
    public final static int TEXT_NULL_IMAGE_ARROW = 2;
    public final static int IMAGE_TEXT_TEXT_ARROW = 3;
    public final static int TEXT_TEXT_TEXT = 4;

    private List<Object> mList1;
    private List<String> mList2;
    private List<String> mList3;
    private List<Class> mIntentList;

    /*public OnItemClickListener mOnItemClickListener;*/

    /*public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }*/

    public RecyclerViewTreeAdapter(
                int type, Object[] array1, String[] array2, String[] array3, Class[] intentArray) {
        this.type = type;
        if (array1 != null) {
            mList1 = Arrays.asList(array1);
        }
        if (array2 != null) {
            mList2 = Arrays.asList(array2);
        }
        if(array3!=null){
            mList3 = Arrays.asList(array3);
        }
        if (intentArray != null) {
            mIntentList = Arrays.asList(intentArray);
        }
    }

    public RecyclerViewTreeAdapter(int type, Object[] array1, String[] array2, String[] array3){
        this(type,array1,array2,array3,null);
    }

    public RecyclerViewTreeAdapter(
            int type, List<Object> list1, List<String> list2, List<String> list3, List<Class> intentList) {
        this.type = type;
        this.mList1 = list1;
        this.mList2 = list2;
        this.mList3 = list3;
        this.mIntentList = intentList;
    }

    public RecyclerViewTreeAdapter(
                            int type, List<Object> list1, List<String> list2, List<String> list3) {
        this(type,list1,list2,list3,null);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (type) {
            case IMAGE_TEXT_NULL_ARROW:
                return onCreateImageTextHolder(parent);
            case TEXT_NULL_TEXT_ARROW:
                return onCreateTextTextHolder(parent);
            case TEXT_NULL_IMAGE_ARROW:
                return onCreateTextImageHolder(parent);
            case IMAGE_TEXT_TEXT_ARROW:
                return onCreateImageTextTextHolder(parent);
            case TEXT_TEXT_TEXT:
                return onCreateTextTextTextHolder(parent);
        }
        return onCreateImageTextTextHolder(parent);
    }

    private RecyclerView.ViewHolder onCreateTextTextTextHolder(ViewGroup parent) {
        return new TextNullImageHolder(inflate(parent, R.layout.recyclierview_item_text_text_text));
    }

    private RecyclerView.ViewHolder onCreateTextImageHolder(ViewGroup parent) {
        return new TextNullImageHolder(inflate(parent, R.layout.recyclierview_item_text_image));
    }

    private RecyclerView.ViewHolder onCreateTextTextHolder(ViewGroup parent) {
        return new TextNullTextHolder(inflate(parent, R.layout.recyclierview_item_text_text));
    }

    private RecyclerView.ViewHolder onCreateImageTextHolder(ViewGroup parent) {
        return new ImageTextNullHolder(inflate(parent, R.layout.recyclierview_item_image_text));
    }

    private RecyclerView.ViewHolder onCreateImageTextTextHolder(ViewGroup parent) {
        return new ImageTextTextHolder(inflate(parent, R.layout.recyclierview_item_image_text_text));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        switch (type) {
            case IMAGE_TEXT_NULL_ARROW:
                onBindImageNullTextHolder((ImageTextNullHolder)holder,position);
                break;
            case TEXT_NULL_TEXT_ARROW:
                onBindTextNullTextHolder((TextNullTextHolder)holder,position);
                break;
            case TEXT_NULL_IMAGE_ARROW:
                onBindTextNullImageHolder((TextNullImageHolder)holder,position);
                break;
            case IMAGE_TEXT_TEXT_ARROW:
                onBindImageTextTextHolder((ImageTextTextHolder)holder,position);
                break;
            case TEXT_TEXT_TEXT:
                onBindTextTextTextHolder((TextTextTextHolder)holder,position);
                break;
        }
    }

    private void onBindTextTextTextHolder(TextTextTextHolder holder, int position) {
        holder.view1.setText(mList1.get(position).toString());
        holder.view2.setText(mList2.get(position));
        holder.view3.setText(mList3.get(position));
        setOnItemClickListener(holder.itemView, position);
    }

    private void onBindImageTextTextHolder(ImageTextTextHolder holder, int position){
        Context context = holder.itemView.getContext();

        setImage(context,mList1.get(position),holder.view1);
        holder.view2.setText(mList2.get(position));
        holder.view3.setText(mList3.get(position));
        setOnItemClickListener(holder.itemView, position);
    }

    private void onBindImageNullTextHolder(ImageTextNullHolder holder, int position) {
        Context context = holder.itemView.getContext();

        setImage(context,mList1.get(position),holder.view1);
        holder.view2.setText(mList2.get(position));
        setOnItemClickListener(holder.itemView, position);
    }

    private void onBindTextNullImageHolder(TextNullImageHolder holder, int position) {
        Context context = holder.itemView.getContext();

        holder.view1.setText(mList1.get(position).toString());
        setImage(context,mList3.get(position),holder.view3);
        setOnItemClickListener(holder.itemView, position);
    }

    private void onBindTextNullTextHolder(TextNullTextHolder holder, int position) {
        holder.view1.setText(mList1.get(position).toString());
        holder.view3.setText(mList3.get(position));
        setOnItemClickListener(holder.itemView, position);
    }

    private void setImage(Context context,Object data,ImageView view){
        if(data instanceof String){
            Glide.with(context).load(data.toString()).into(view);
        }else if(data instanceof Integer ){
            view.setScaleType(ImageView.ScaleType.CENTER);
            Glide.with(context).load((int)data).into(view);
        }
    }

    private void setOnItemClickListener(final View view, final int position) {
        if(mIntentList!=null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class clazz = mIntentList.get(position);
                    if(clazz!=null){
                        v.getContext().startActivity(new Intent(v.getContext(), clazz));
                    }
                }
            });
        }
        /*if(mOnItemClickListener!=null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(view,position);
                }
            });
        }else if(mIntentList!=null){
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Class clazz = mIntentList.get(position);
                    if(clazz!=null){
                        v.getContext().startActivity(new Intent(v.getContext(), clazz));
                    }
                }
            });
        }*/
    }

    private class ImageTextNullHolder extends RecyclerView.ViewHolder {
        ImageView view1;
        TextView view2;

        private ImageTextNullHolder(View itemView) {
            super(itemView);
            view1 = (ImageView) itemView.findViewById(R.id.view1);
            view2 = (TextView) itemView.findViewById(R.id.view2);
        }
    }

    private class TextNullTextHolder extends RecyclerView.ViewHolder {
        TextView view1;
        TextView view3;
        private TextNullTextHolder(View itemView) {
            super(itemView);
            view1 = (TextView) itemView.findViewById(R.id.view1);
            view3 = (TextView) itemView.findViewById(R.id.view2);
        }
    }

    private class TextNullImageHolder extends RecyclerView.ViewHolder {
        TextView view1;
        ImageView view3;

        private TextNullImageHolder(View itemView) {
            super(itemView);
            view1 = (TextView) itemView.findViewById(R.id.view1);
            view3 = (ImageView) itemView.findViewById(R.id.view2);
        }
    }

    private class ImageTextTextHolder extends RecyclerView.ViewHolder {
        ImageView view1;
        TextView view2;
        TextView view3;
        private ImageTextTextHolder(View itemView) {
            super(itemView);
            view1 = (ImageView) itemView.findViewById(R.id.view1);
            view2 = (TextView) itemView.findViewById(R.id.view2);
            view3 = (TextView) itemView.findViewById(R.id.view3);
        }
    }

    private class TextTextTextHolder extends RecyclerView.ViewHolder {
        TextView view1;
        TextView view2;
        TextView view3;
        private TextTextTextHolder(View itemView) {
            super(itemView);
            view1 = (TextView) itemView.findViewById(R.id.view1);
            view2 = (TextView) itemView.findViewById(R.id.view2);
            view3 = (TextView) itemView.findViewById(R.id.view3);
        }
    }

    @Override
    public int getItemCount() {
        return mList1.size();
    }
}
