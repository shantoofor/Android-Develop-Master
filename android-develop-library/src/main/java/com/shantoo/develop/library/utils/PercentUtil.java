package com.shantoo.develop.library.utils;

import java.text.NumberFormat;

/**
 * 作者: shantoo on 2017/4/10 13:22.
 * 百分比工具类
 */

public class PercentUtil {

    /**
     *  根据当前数量和总数计算百分比，精确度默认为0
     *  @param current 当前数量
     *  @param total 总数
     *  @return 百分数
     * */
    public static int getPercent(long current,long total){
        NumberFormat nf = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nf.setMinimumFractionDigits(0);
        float percent = (float)current/total;
        return Integer.valueOf(nf.format(percent).replace("%",""));
    }

    /**
     *  根据当前数量和总数按指定精确度计算百分比
     *  @param current 当前数量
     *  @param total 总数
     *  @param accuracy 精确度
     *  @return 百分数
     * */
    public static int getPercent(long current,long total,int accuracy){
        NumberFormat nf = NumberFormat.getPercentInstance();
        //设置百分数精确度2即保留两位小数
        nf.setMinimumFractionDigits(accuracy);
        float percent = (float)current/total;
        return Integer.valueOf(nf.format(percent).replace("%",""));
    }
}
