package com.shantoo.develop.library.ui.widget.LinkageView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.shantoo.develop.library.R;
import com.shantoo.develop.library.ui.widget.wheelview.adapter.ArrayWheelAdapter;
import com.shantoo.develop.library.ui.widget.wheelview.widget.WheelView;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者: shantoo on 2017/6/14 17:01.
 * 二级联动
 */

public class TwoLevelLinkageView extends BaseLevelLinkageView {

    private TextView cancel;
    private TextView submit;
    private WheelView mList;
    private WheelView mDetail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_two_level_address_linkage_view,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initListener();
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
        Window win = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable( new ColorDrawable(ContextCompat.getColor(context, R.color.Color_Light_White)));

        getActivity().getWindowManager().getDefaultDisplay().getMetrics( new DisplayMetrics() );

        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width =  ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        win.setAttributes(params);
    }

    private void initViews(View view) {
        cancel = (TextView) view.findViewById(R.id.id_cancel);
        submit = (TextView) view.findViewById(R.id.id_submit);
        mList = (WheelView) view.findViewById(R.id.id_list);
        mDetail = (WheelView) view.findViewById(R.id.id_detail);
    }

    private void initListener() {
        mList.addChangingListener(this);
        mDetail.addChangingListener(this);
        cancel.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mList) {
            updateDetail();
        }
    }

    private void initData(){
        mDetailDatasMap.put("item1",new String[]{"value1"});
        mDetailDatasMap.put("item2",new String[]{"value1","value2"});
        mDetailDatasMap.put("item3",new String[]{"value1","value2","value3"});
        mDetailDatasMap.put("item4",new String[]{"value1","value2","value3","value4"});
    }

    /**列表的数据数组*/
    protected String[] mListDatas = new String[]{"item1","item2","item3","item4"};

    /**key - values detail的键值对*/
    protected Map<String, String[]> mDetailDatasMap = new HashMap<>();

    private String mCurrentListName;

    private void updateDetail(){
        int pCurrent = mList.getCurrentItem();
        mCurrentListName = mListDatas[pCurrent];
        String[] cities = mDetailDatasMap.get(mCurrentListName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mDetail.setViewAdapter(new ArrayWheelAdapter<>(context, cities));
        mDetail.setCurrentItem(0);
    }
}
