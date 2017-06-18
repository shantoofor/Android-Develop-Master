package com.shantoo.develop.library.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shantoo.develop.library.ui.widget.toolbar.ToolBarManager;
import com.shantoo.develop.library.utils.UIUtil;

public class ToolBarActivity extends AppCompatActivity{

    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){actionBar.hide();}
    }

    private View.OnClickListener onBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    protected void initToolBar(Toolbar mToolbar,int returnResId ,int customTitleId, String titleResId) {

        ToolBarManager.with(this).from(mToolbar)
                .initCustomTextView(customTitleId, titleResId)
                .initNavigation(returnResId, onBackClickListener);
        auto(mToolbar);
    }

    protected void initToolBar(Toolbar mToolbar, int returnResId,int customTitleId, int titleResId) {

        ToolBarManager.with(this).from(mToolbar)
                .initCustomTextView(customTitleId, titleResId)
                .initNavigation(returnResId, onBackClickListener);
        auto(mToolbar);
    }

    protected void initToolBar(Toolbar mToolbar,int returnResId, int customTitleId, int titleResId,
                           int menuResId, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {

        ToolBarManager.with(this).from(mToolbar)
                .initCustomTextView(customTitleId, titleResId)
                .initNavigation(returnResId, onBackClickListener)
                .initMenu(menuResId, onMenuItemClickListener);
        auto(mToolbar);
    }

    protected void initToolBar(Toolbar mToolbar,int returnResId, int customTitleId, String titleResId,
                           int menuResId, Toolbar.OnMenuItemClickListener onMenuItemClickListener) {

        ToolBarManager.with(this).from(mToolbar)
                .initCustomTextView(customTitleId, titleResId)
                .initNavigation(returnResId, onBackClickListener)
                .initMenu(menuResId, onMenuItemClickListener);
        auto(mToolbar);
    }

    private void auto(Toolbar mToolbar) {
       /* if (Build.VERSION.SDK_INT >= 21) {
            mToolbar.setPadding(0, UIUtil.getStatusHeight(this), 0, 0);
        }*/
    }
}
