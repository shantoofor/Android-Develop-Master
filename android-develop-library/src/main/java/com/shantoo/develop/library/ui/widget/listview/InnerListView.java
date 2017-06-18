package com.shantoo.develop.library.ui.widget.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class InnerListView extends ListView {

    public InnerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    // 当拦截触摸事件到达此位置的时候，返回true，
    // 说明将onTouch拦截在此控件，进而执行此控件的onTouchEvent
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}