package com.shantoo.develop.library.ui.widget.LinkageView;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.shantoo.develop.library.R;
import com.shantoo.develop.library.ui.widget.wheelview.widget.OnWheelChangedListener;
import com.shantoo.develop.library.ui.widget.wheelview.widget.WheelView;

/**
 * 作者: shantoo on 2017/6/14 17:37.
 */

public class BaseLevelLinkageView extends DialogFragment implements OnWheelChangedListener,
        View.OnFocusChangeListener, View.OnClickListener {

    protected Context context;
    protected String TAG = this.getClass().getSimpleName();
    protected OnSubmitClickListener mOnSubmitClickListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {}

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {}

    @Override
    public void onClick(View v) {
        if(R.id.id_cancel == v.getId()) {
            this.dismiss();
        }
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

    public interface OnSubmitClickListener{
        void onSubmit(String address,String areaCode);
    }

    public BaseLevelLinkageView setOnSubmitClickListener(OnSubmitClickListener onSubmitClickListener){
        this.mOnSubmitClickListener = onSubmitClickListener;
        return this;
    }

    public void show(FragmentManager manager) {
        super.show(manager, TAG);
    }
}
