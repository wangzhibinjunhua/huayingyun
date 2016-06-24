package com.tentinet.healthy.util;

import android.content.Context;

import com.tentinet.healthy.R;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/5/17 16:30
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class MeasureResultUtil {

    /**
     * 正常血压
     */
    private static final int NORMAL_BLOOD = 139;
    /**
     *1级高血压
     */
    private static final int ONE_HIGH_BLOOD = 140;
    /**
     * 2级高血压
     */
    private static final int TWO_NORMAL_BLOOD = 160;
    /**
     * 3级高血压
     */
    private static final int THR_NORMAL_BLOOD = 180;
    /**
     * 血糖临界值
     */
    private static final int THR_NORMAL_SUGAR = 7;
    /**
     * 体温临界值
     */
    private static final int THR_NORMAL_EAR = 38;

    /**
     * 根据高压值判断结果
     *
     * @param sys 高压值
     * @return
     */
    public static String getBloodResult(Context context, String sys) {
        if (StringUtil.isEmpty(sys)) {
            return "";
        }
        int sysV = Integer.parseInt(sys);
        if (sysV <= NORMAL_BLOOD) {
            return context.getString(R.string.blood_normal);
        } else if (sysV >= THR_NORMAL_BLOOD) {
            return context.getString(R.string.blood_thr_high);
        } else if (sysV >= TWO_NORMAL_BLOOD) {
            return context.getString(R.string.blood_two_high);
        } else if (sysV >= ONE_HIGH_BLOOD) {
            return context.getString(R.string.blood_one_high);
        }
        return "";
    }

    /**
     * 根据血糖值判断结果
     *
     * @param sys 高压值
     * @return
     */
    public static String getSugarResult(Context context, String sys) {
        if (StringUtil.isEmpty(sys)) {
            return "";
        }
        Float sysV = Float.parseFloat(sys);
        if (sysV <= THR_NORMAL_SUGAR) {
            return context.getString(R.string.blood_normal);
        } else if (sysV > THR_NORMAL_SUGAR) {
            return context.getString(R.string.sugar_high);
        }
        return "";
    }

    /**
     * 根据体温值判断结果
     * @param sys 高压值
     * @return
     */
    public static String getEarResult(Context context, String sys) {
        if (StringUtil.isEmpty(sys)) {
            return "";
        }
        Float sysV = Float.parseFloat(sys);
        if (sysV <= THR_NORMAL_EAR) {
            return context.getString(R.string.blood_normal);
        } else if (sysV > THR_NORMAL_EAR) {
            return context.getString(R.string.ear_high);
        }
        return "";
    }

}
