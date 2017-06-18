package com.shantoo.develop.library.ui.widget.toolbar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shantoo.develop.library.utils.UIUtil;

/**
 * Created by shantoo on 2017/2/23.
 * Toolbar的基本使用
 */
public class ToolBarManager {

    private ToolBarManager(){}
    private static ToolBarManager instance;

    private static Context mContext;
    private Toolbar mToolbar;

    /*ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
        //显示导航按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        //设置导航按钮图标
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        //系统标题不居中，禁用系统标题
        actionBar.setDisplayShowTitleEnabled(false);
    }*/

    /**
     * @param context Context
     * */
    public static ToolBarManager with(Context context){
        mContext = context;
        if(instance==null){
            instance = new ToolBarManager();
        }
        return instance;
    }

    /**
     * @param toolBarView toolbar's com.ldwl.qlddsc.view or toolbar's parent com.ldwl.qlddsc.view
     * @param toolBarId toolbar id
     * */
    public ToolBarManager from(View toolBarView,int toolBarId){
        if(toolBarView == null){
            throw new IllegalArgumentException("toolbar should be has layout or parent layout,no toolbar layout or toolbar parent layout found");
        }
        //mToolbar = (Toolbar) toolBarView.findViewById(toolBarId);
        //mToolbar.setFitsSystemWindows(true);//ToolBar和状态栏融合
        //mToolbar.setPadding(32,32,32,32);
        return instance;
    }

    /**
     * @param paramToolbar toolbar
     * */
    public ToolBarManager from(Toolbar paramToolbar){
        mToolbar = paramToolbar;
        mToolbar.setFitsSystemWindows(true);
        LinearLayout.LayoutParams  params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int padding = UIUtil.px2dip(mContext,32);
        mToolbar.setPadding(padding,padding,padding,padding);
        mToolbar.setLayoutParams(params);
        return instance;
    }

    /**
     * @param bgColorResId toolbar background color
     * */
    public ToolBarManager initToolBar(int bgColorResId){
        // mToolbar.setBackgroundColor(bgColorResId); 方法无效
        // mToolbar.setBackground(getDrawable(bgColorResId));@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        mToolbar.setBackgroundResource(bgColorResId);
        return instance;
    }

    /**
     * Navigation 设置
     * init a toolbar has only navigation
     * @param navigationIcon navigation drawable
     * @param navigationOnClickListener navigation OnClickListener
     * */
    public ToolBarManager initNavigation(Drawable navigationIcon,
                                                    View.OnClickListener navigationOnClickListener){
        if(navigationIcon!=null){
            mToolbar.setNavigationIcon(navigationIcon);
        }
        if(navigationOnClickListener!=null){
            mToolbar.setNavigationOnClickListener(navigationOnClickListener);
        }
        return instance;
    }

    /**
     * Logo 设置
     * init a toolbar has only navigation
     * @param navigationResId navigation resources id
     * @param navigationOnClickListener navigation OnClickListener
     * */
    public ToolBarManager initNavigation(int navigationResId, View.OnClickListener navigationOnClickListener){
        return initNavigation(getDrawable(navigationResId),navigationOnClickListener);
    }

    /**
     * Title 设置
     * init a toolbar has only logo
     * @param logoDrawable logo drawable
     * */
    public ToolBarManager initLogo(Drawable logoDrawable){
        if(logoDrawable!=null){
            mToolbar.setLogo(logoDrawable);
        }
        return instance;
    }

    /**
     * Title 设置 也可以在Activity中通过android:label=""进行设置
     * init a toolbar has only logo
     * @param logoResId logo resources id
     * */
    public ToolBarManager initLogo(int logoResId){
        return initLogo(getDrawable(logoResId));
    }

    /**
     * init a toolbar has only toolBarTitle
     * @param toolBarTitle toolbar title text
     * */
    public ToolBarManager initTitle(String toolBarTitle){
        return initTitle(toolBarTitle,0xFFFFFFFF);
    }

    /**
     * init a toolbar has only toolBarTitle
     * @param toolBarTitle toolbar title text
     * @param color toolbar title text color
     * */
    public ToolBarManager initTitle(String toolBarTitle, int color){
        if(toolBarTitle!=null){
            mToolbar.setTitle(toolBarTitle);
            mToolbar.setTitleTextColor(color);
        }
        return instance;
    }

    /**
     * init a toolbar has only toolBarTitle
     * @param toolBarTitle toolbar title text
     * @param start toolbar title start margin
     * @param top toolbar title top margin
     * @param end toolbar title end margin
     * @param bottom toolbar title bottom margin
     * */
    public ToolBarManager initTitle(String toolBarTitle,int start,int top,int end,int bottom){
        if(toolBarTitle!=null){
            mToolbar.setTitle(toolBarTitle);
            mToolbar.setTitleMargin(start,top,end,bottom);
        }
        return instance;
    }

    /**
     * init a toolbar has only toolBarTitle
     * @param toolBarTitle toolbar title text
     * @param color toolbar title text color
     * @param start toolbar title start margin
     * @param top toolbar title top margin
     * @param end toolbar title end margin
     * @param bottom toolbar title bottom margin
     * */
    public ToolBarManager initTitle(String toolBarTitle, int color,int start,int top,int end,int bottom){
        if(toolBarTitle!=null){
            mToolbar.setTitle(toolBarTitle);
            mToolbar.setTitleTextColor(color);
            mToolbar.setTitleMargin(start,top,end,bottom);
        }
        return instance;
    }

    /**
     * init a toolbar has only toolBarTitle
     * @param toolBarTitleResId toolbar title string resources id
     * @param color toolbar title text color
     * @param start toolbar title start margin
     * @param top toolbar title top margin
     * @param end toolbar title end margin
     * @param bottom toolbar title bottom margin
     * */
    public ToolBarManager initTitle(int toolBarTitleResId, int color,int start,int top,int end,int bottom){
        return initTitle(mContext.getResources().getString(toolBarTitleResId),color,start,top,end,bottom);
    }

    /**
     * init a toolbar has only toolBarTitle
     * @param toolBarTitleResId toolbar title string resources id
     * @param start toolbar title start margin
     * @param top toolbar title top margin
     * @param end toolbar title end margin
     * @param bottom toolbar title bottom margin
     * */
    public ToolBarManager initTitle(int toolBarTitleResId,int start,int top,int end,int bottom){
        return initTitle(mContext.getResources().getString(toolBarTitleResId),start,top,end,bottom);
    }

    /**
     * Title 设置
     * init a toolbar has only toolBarTitle
     * @param toolBarTitleResId toolbar title string resources id
     * @param color title text color
     * */
    public ToolBarManager initTitle(int toolBarTitleResId, int color){
        return initTitle(mContext.getResources().getString(toolBarTitleResId),color);
    }

    /**
     * Title 设置
     * init a toolbar has only toolBarTitle
     * @param toolBarTitleResId toolbar title string resources id
     * */
    public ToolBarManager initTitle(int toolBarTitleResId){
        return initTitle(toolBarTitleResId,0xFFFFFFFF);
    }

    /**
     * SubTitle 设置
     * init a toolbar has only toolBarSubTitle
     * @param toolBarSubTitle subtitle text
     * */
    public ToolBarManager initSubTitle(String toolBarSubTitle){
        if(toolBarSubTitle!=null){
            mToolbar.setSubtitle(toolBarSubTitle);
        }
        return instance;
    }

    /**
     * init a toolbar has only toolBarSubTitle
     * @param toolBarSubTitleResId toolbar sub title string resources id
     * */
    public ToolBarManager initSubTitle(int toolBarSubTitleResId){
        mToolbar.setTitle(mContext.getResources().getString(toolBarSubTitleResId));
        return instance;
    }

    /**
     * 自定义控件设置
     * 如果custom ,title ,subtitle 同时存在，并且屏幕空间不够，toolbar将会优先显示custom
     * init a toolbar has only custom TextView
     * @param customViewResId TextView's com.ldwl.qlddsc.view resources id
     * @param customString TextView's string
     * */
    public ToolBarManager initCustomTextView(int customViewResId,String customString){
        View view = mToolbar.findViewById(customViewResId);
        if(view!=null && view instanceof TextView){
            TextView mToolBarTitle = (TextView) view;
            mToolBarTitle.setText(customString);
        }
        return instance;
    }

    /**
     * 自定义控件设置
     * init a toolbar has only custom TextView
     * @param customViewResId TextView's com.ldwl.qlddsc.view resources id
     * @param customStringResId TextView's string resources id
     * */
    public ToolBarManager initCustomTextView(int customViewResId,int customStringResId){
        View view = mToolbar.findViewById(customViewResId);
        if(view!=null && view instanceof TextView){
            TextView mCustomTextView = (TextView) view;
            mCustomTextView.setText(mContext.getResources().getString(customStringResId));
        }
        return instance;
    }

    /**
     * 自定义控件设置
     * init a toolbar has only custom ImageView
     * @param customViewResId ImageView's com.ldwl.qlddsc.view resources id
     * @param customImgResId TextView's img resources id
     * */
    public ToolBarManager initCustomImageView(int customViewResId,int customImgResId){
        View view = mToolbar.findViewById(customViewResId);
        if(view!=null && view instanceof ImageView){
            ImageView mCustomImageView = (ImageView) view;
            mCustomImageView.setBackgroundResource(customImgResId);
        }
        return instance;
    }

    /**
     * init a toolbar has only menu
     * @param menuResId menu resources id
     * @param popupThemeResId 用于给Menu单独指定主题样式,如果不指定将使用toolbar的主题样式
     * @param onMenuItemClickListener menu item OnClickListener
     * */
    public ToolBarManager initMenu(int menuResId,int popupThemeResId,Toolbar.OnMenuItemClickListener onMenuItemClickListener){
        mToolbar.setPopupTheme(popupThemeResId);
        return initMenu(menuResId,onMenuItemClickListener);
    }

    /**
     * init a toolbar has only menu
     * @param menuResId menu resources id
     * @param onMenuItemClickListener menu item OnClickListener
     * */
    public ToolBarManager initMenu(int menuResId,Toolbar.OnMenuItemClickListener onMenuItemClickListener){
        //Menu 设置 代替onCreateOptionsMenu方法
        mToolbar.inflateMenu(menuResId);
        //Menu 设置 代替onOptionsItemSelected方法
        if(onMenuItemClickListener!=null){
            mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);
        }
        return instance;
    }

    /**
     * init a toolbar has only menu
     * @param menuResId menu resources id
     * @param onMenuItemClickListener menu item OnClickListener
     * @param overFlowResId overflow resources id
     * */
    public ToolBarManager initMenu(int menuResId,Toolbar.OnMenuItemClickListener onMenuItemClickListener,int overFlowResId){
        //Menu 设置 代替onCreateOptionsMenu方法
        mToolbar.inflateMenu(menuResId);
        //Menu 设置 代替onOptionsItemSelected方法
        if(onMenuItemClickListener!=null){
            mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);
            mToolbar.setOverflowIcon(getDrawable(overFlowResId));
        }
        return instance;
    }

    /**
     * init a toolbar has only menu
     * @param menuResId menu resources id
     * @param onMenuItemClickListener menu item OnClickListener
     * @param overFlowDrawable overflow drawable
     * */
    public ToolBarManager initMenu(int menuResId,Toolbar.OnMenuItemClickListener onMenuItemClickListener,Drawable overFlowDrawable){
        //Menu 设置 代替onCreateOptionsMenu方法
        mToolbar.inflateMenu(menuResId);
        //Menu 设置 代替onOptionsItemSelected方法
        if(onMenuItemClickListener!=null){
            mToolbar.setOnMenuItemClickListener(onMenuItemClickListener);
            mToolbar.setOverflowIcon(overFlowDrawable);
        }
        return instance;
    }

    private Drawable getDrawable(int resId){
        return  AppCompatResources.getDrawable(mContext, resId);
    }
}




