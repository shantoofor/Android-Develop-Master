package com.shantoo.develop.library.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * 一个BaseFragment的Builder，可以解决Fragment嵌套Fragment时生命周期混乱的问题,Fragment必须继承BaseFragment
 */
public class FragmentBuilder<T extends Fragment> {
    private FragmentManager mFragmentManager;
    private List<T> mFragmentList;
    private int mContainerViewId;

    private T mCurrentFragment;

    public T getCurrentFragment() {
        return mCurrentFragment;
    }

    /**
     * @param manager      FragmentManager
     * @param fragmentList 存储BaseFragment的集合
     * @param containerViewId fragment需要替换的资源ID
     * */
    public FragmentBuilder with(FragmentManager manager,List<T> fragmentList,int containerViewId){
        this.mFragmentManager = manager;
        this.mFragmentList = fragmentList;
        this.mContainerViewId = containerViewId;
        return this;
    }

    public void setCurrentFragment(int currentFragmentTag) {
        this.mCurrentFragment = mFragmentList.get(currentFragmentTag);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.add(mContainerViewId, mCurrentFragment, mCurrentFragment.getClass().getName());
        transaction.commit();
        mFragmentManager.executePendingTransactions();
    }

    /**
     * 创建一个BaseFragment，并交给FragmentManager管理，该BaseFragment会替换指定mContainerViewId的布局界面
     *
     * @param fragmentTag mCurrentFragment所对应的fragmentList集合中的index
     */
    public void createFragment(int fragmentTag) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        // 判断当前的Fragment是否为空，不为空则隐藏
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        mCurrentFragment = getFragment(fragmentTag);
        // 判断此Fragment是否已经添加到FragmentTransaction事物中
        if (!mCurrentFragment.isAdded()) {
            transaction.add(mContainerViewId, mCurrentFragment, mCurrentFragment.getClass().getName());
        }
        transaction.show(mCurrentFragment);
        transaction.commitAllowingStateLoss();
        mFragmentManager.executePendingTransactions();
    }

    /**
     * 创建一个BaseFragment，并交给FragmentManager管理。
     *
     * @param fragmentTag mCurrentFragment所对应的fragmentList集合中的index
     */
    /*public void createFragment(int fragmentTag) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        // 判断当前的Fragment是否为空，不为空则隐藏
        if (mCurrentFragment != null) {
            transaction.hide(mCurrentFragment);
        }
        mCurrentFragment = getFragment(fragmentTag);
        // 判断此Fragment是否已经添加到FragmentTransaction事物中
        if (!mCurrentFragment.isAdded()) {
            transaction.add(mCurrentFragment, mCurrentFragment.getClass().getName());
        }
        transaction.show(mCurrentFragment);
        transaction.commit();
        mFragmentManager.executePendingTransactions();
    }*/

    public T getFragment(int fragmentTag) {
        // 先根据Tag从FragmentTransaction事物获取之前添加的Fragment
        String tagName = mFragmentList.get(fragmentTag).getClass().getName();
        T fragment = (T) mFragmentManager.findFragmentByTag(tagName);
        if (fragment == null) {
            // 如fragment为空，则之前未添加此Fragment。便从集合中取出
            fragment = mFragmentList.get(fragmentTag);
        }
        return fragment;
    }
}