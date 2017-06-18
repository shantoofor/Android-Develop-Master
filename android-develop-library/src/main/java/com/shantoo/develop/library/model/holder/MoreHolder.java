package com.shantoo.develop.library.model.holder;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shantoo.develop.library.model.adapter.BaseListViewAdapter;

public class MoreHolder extends BaseHolder {

    private Context context;
    private boolean has_more;
    public static int HAS_MORE = 1;         //有加载更多
    public static int NO_MORE = 2;          //没有更多
    public static int LOAD_MORE_ERROR = 3;  //加载更多出现错误
    private BaseListViewAdapter<?> myBaseAdapter;
    private LinearLayout child;
    private TextView loadMoreError;
    //private LinearLayout load_more_ll;
    //private TextView load_more_error;

    public MoreHolder(Context context) {
        super(context);
        this.context = context;
    }

    public MoreHolder(Context context, boolean has_more, BaseListViewAdapter<?> myBaseAdapter) {
        super(context);
        this.context = context;
        this.has_more = has_more;
        this.myBaseAdapter = myBaseAdapter;
        //只要创建了对应的MoreHolder对象，就需要去尝试加载更多的操作,状态坐标志
        //has_more变量就是告知当前的holder是否有更多数据
        setData(this.has_more ? HAS_MORE : NO_MORE);
    }

    @Override
    public View initView() {
        LinearLayout root = new LinearLayout(context);
        root.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        root.setGravity(Gravity.CENTER);
        root.setOrientation(LinearLayout.VERTICAL);

        child = new LinearLayout(context);
        child.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        child.setGravity(Gravity.CENTER);
        child.setOrientation(LinearLayout.HORIZONTAL);

        ProgressBar bar = new ProgressBar(context);
        bar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        TextView loading = new TextView(context);
        loading.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        loading.setText("正在加载...");
        loading.setTextSize(18);

        child.addView(bar);
        child.addView(loading);

        loadMoreError = new TextView(context);
        loadMoreError.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        loadMoreError.setText("加载更多失败，请点击重试");
        loadMoreError.setTextSize(18);
        loadMoreError.setVisibility(View.INVISIBLE);
        root.addView(child);
        root.addView(loadMoreError);
        //com.ldwl.qlddsc.view = UIUtil.inflate(R.layout.layout_load_more);
        //load_more_ll = (LinearLayout) com.ldwl.qlddsc.view.findViewById(R.id.load_more_ll);
        //load_more_error = (TextView) com.ldwl.qlddsc.view.findViewById(R.id.load_more_error);
        return root;
    }

    @Override
    public void refreshView() {
        int date = (Integer) getData();
        //根据当前的值做，显示UI的操作
        if (date == HAS_MORE) {
            child.setVisibility(View.VISIBLE);
            loadMoreError.setVisibility(View.GONE);
        }
        if (date == NO_MORE) {
            child.setVisibility(View.GONE);
            loadMoreError.setVisibility(View.GONE);
        }
        if (date == LOAD_MORE_ERROR) {
            child.setVisibility(View.GONE);
            loadMoreError.setVisibility(View.VISIBLE);
        }
    }

    //看见对应更多条目的时候，就需要去尝试加载更多数据的业务逻辑
    @Override
    public View getRootView() {
        //加载更多操作
        if (myBaseAdapter != null && HAS_MORE == (Integer) getData()) {
            myBaseAdapter.loadMore();
        }
        return super.getRootView();
    }
}
