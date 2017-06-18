package com.shantoo.develop.library.ui.widget.mrecyclerview.listener;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/2/24.
 */

public abstract class OnItemTouchListener implements RecyclerView.OnItemTouchListener {

    private RecyclerView mRecyclerView;
    private GestureDetectorCompat mGestureDetectorCompat;

    public OnItemTouchListener(RecyclerView recyclerView) {
        mRecyclerView = recyclerView;
        mGestureDetectorCompat = new GestureDetectorCompat(mRecyclerView.getContext(), new MGestureListener());
    }

    public abstract void onItemClick(RecyclerView.ViewHolder vh);

    /**处理触摸事件*/
    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
    }

    /**拦截触摸事件*/
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetectorCompat.onTouchEvent(e);
        return false;
    }

    /**处理触摸冲突*/
    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    private class MGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            doClick(e);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            doClick(e);
        }
    }

    private void doClick(MotionEvent e){
        View view = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
        if (view != null) {
            RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(view);
            onItemClick(holder);
        }
    }
}
