package com.shantoo.develop.library.ui.widget.dialogfragment;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shantoo.develop.library.utils.UIUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者: shantoo on 2017/5/12 16:51.
 *
 */

public class PaymentDialog extends DialogFragment implements RadioGroup.OnCheckedChangeListener{

    private Context context;
    private LinearLayout root;
    private RadioGroup radioGroup;
    private final static String key = "pay_title_list";
    private static ArrayList<String> mList;
    private static Map<String, Integer> mMap;
    private OnItemCheckedChangeListener mOnItemCheckedChangeListener;
    private final static String TAG = PaymentDialog.class.getSimpleName();

    public static PaymentDialog newInstance(ArrayList<String> payList) {
        mList = payList;
        PaymentDialog newFragment = new PaymentDialog();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(key, payList);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        return init();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        RadioButton first = (RadioButton) radioGroup.getChildAt(0);
        if(first!=null){
            first.setChecked(false);
        }
        RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
        if(radioButton!=null){
            String title = radioButton.getText().toString();
            int position = mMap.get(title);
            mOnItemCheckedChangeListener.onItemCheckedChanged(radioButton,position,title);
        }
    }

    private View init() {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        createRoot(context);
        createGroup(context);
        createChild(context);
        return root;
    }

    private void createRoot(Context context){
        root = new LinearLayout(context);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;

        root.setLayoutParams(params);
        root.setBackgroundColor(Color.WHITE);
        root.setOrientation(LinearLayout.VERTICAL);
    }

    private void createGroup(Context context){
        radioGroup = new RadioGroup(context);

        RadioGroup.LayoutParams radioGroupParams = new RadioGroup.LayoutParams(
                UIUtil.dip2px(context,250),RadioGroup.LayoutParams.WRAP_CONTENT);
        int margin = UIUtil.dip2px(context,16);
        radioGroupParams.setMargins(margin,margin,margin,margin);

        radioGroup.setLayoutParams(radioGroupParams);
        radioGroup.setOrientation(RadioGroup.VERTICAL);
        radioGroup.setOnCheckedChangeListener(this);
        root.addView(radioGroup);
    }

    private void createChild(Context context){
        RadioButton radioButton;

        RadioGroup.LayoutParams radioGroupParams = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT,RadioGroup.LayoutParams.WRAP_CONTENT);
        radioGroupParams.gravity = Gravity.CENTER_VERTICAL;
        radioGroupParams.setMargins(0,UIUtil.dip2px(context,10),0,0);

        mMap = new HashMap<>();

        for (int i = 0; i< mList.size(); i++){
            String title = mList.get(i);
            radioButton = new RadioButton(context);
            mMap.put(title,i);
            radioButton.setLayoutParams(radioGroupParams);
            radioButton.setTextSize(16);
            radioButton.setPadding(UIUtil.dip2px(context,30),0,0,0);
            radioButton.setText(title);
            if(i == 0){
                radioButton.setChecked(true);
            }
            if(radioButton.isChecked()){
                radioButton.setBackgroundColor(Color.parseColor("#559A9A9A"));
            }else{
                radioButton.setBackgroundColor(Color.WHITE);
            }
            radioGroup.addView(radioButton);
        }
    }

    public PaymentDialog setOnItemCheckedChangeListener(OnItemCheckedChangeListener onItemCheckedChangeListener){
       this.mOnItemCheckedChangeListener = onItemCheckedChangeListener;
       return this;
    }

    public interface OnItemCheckedChangeListener{
        void onItemCheckedChanged(RadioButton radioButton, int position, String title);
    }

    public void show(FragmentManager manager) {
        super.show(manager, TAG);
    }
}
