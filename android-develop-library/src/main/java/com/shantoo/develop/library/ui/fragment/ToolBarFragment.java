package com.shantoo.develop.library.ui.fragment;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shantoo.develop.library.ui.widget.toolbar.ToolBarManager;
import com.shantoo.develop.library.utils.UIUtil;

/**
 * 作者: shantoo on 2017/5/26 16:10.
 */

public class ToolBarFragment extends Fragment {

    private Context context = getContext();
    protected final String TAG = this.getClass().getSimpleName();
    protected final String TAG_REFRESH = TAG + "_refresh";
    protected final String TAG_LOADMORE = TAG + "_loadmore";

    protected void initToolBar( Toolbar mToolbar,int customTitleId, String titleResId) {

        ToolBarManager.with(context).from(mToolbar)
                .initCustomTextView(customTitleId, titleResId);
        auto(mToolbar);
    }

    protected void initToolBar(Toolbar mToolbar,int customTitleId, int titleResId) {

        ToolBarManager.with(context).from(mToolbar)
                .initCustomTextView(customTitleId, titleResId);
        auto(mToolbar);
    }

    protected void initToolBar(Toolbar mToolbar, int customTitleId, int titleResId,
                           int menuResId, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {

        ToolBarManager.with(context).from(mToolbar)
                .initCustomTextView(customTitleId, titleResId)
                .initMenu(menuResId, onMenuItemClickListener);
        auto(mToolbar);
    }

    protected void initToolBar(Toolbar mToolbar,int customTitleId, String titleResId,
                           int menuResId, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {
        
        ToolBarManager.with(context).from(mToolbar)
                .initCustomTextView(customTitleId, titleResId)
                .initMenu(menuResId, onMenuItemClickListener);
        auto(mToolbar);
    }

    private void auto(Toolbar mToolbar){
        if (Build.VERSION.SDK_INT >= 21) {
            mToolbar.setPadding(0, UIUtil.getStatusHeight(getActivity()), 0, 0);
        }
    }

    public void hideToolBar(Toolbar mToolbar){
        mToolbar.setVisibility(View.GONE);
    }
}

