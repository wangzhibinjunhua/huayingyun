package com.tentinet.healthy.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 闪烁提示.
 *
 * @author paladin
 */
public class ToastUtil {

    /**
     * 提示板
     */
    private static Toast toast;

    /**
     * 显示提示信息.
     *
     * @param context  上下文环境.
     * @param txt      提示文本信息.
     * @param duration 显示时间.
     */
    public static void showToast(Context context, String txt, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context, txt, duration);
        } else {
            toast.setText(txt);
        }
        toast.show();
    }

    /**
     * 显示提示信息(自定义位置).
     *
     * @param context  上下文环境.
     * @param txt      提示文本信息.
     * @param duration 显示时间.
     * @param gravity  相对布局.
     * @param xSet     x坐标偏移量.
     * @param ySet     y坐标偏移量.
     */
    public static void showToastAtLocation(Context context, String txt, int duration, int gravity, int xSet, int ySet) {
        if (toast == null) {
            toast = Toast.makeText(context, txt, duration);
        } else {
            toast.setText(txt);
        }
        toast.setGravity(gravity, xSet, ySet);
        toast.show();
    }

    /**
     * (短时间)显示提示信息.
     *
     * @param context 上下文环境.
     * @param txt     提示文本信息.
     */
    public static void showShortToast(Context context, String txt) {
        showToast(context, txt, Toast.LENGTH_SHORT);
    }

    /**
     * (长时间)显示提示信息.
     *
     * @param context 上下文环境.
     * @param txt     提示文本信息.
     */
    public static void showLongToast(Context context, String txt) {
        showToast(context, txt, Toast.LENGTH_LONG);
    }

    /**
     * (短时间)显示提示信息(自定义位置).
     *
     * @param context 上下文环境.
     * @param txt     提示文本信息.
     * @param gravity 相对布局.
     * @param xSet    x坐标偏移量.
     * @param ySet    y坐标偏移量.
     */
    public static void showShortToast(Context context, String txt, int gravity, int xSet, int ySet) {
        showToastAtLocation(context, txt, Toast.LENGTH_SHORT, gravity, xSet, ySet);
    }

    /**
     * (长时间)显示提示信息(自定义位置).
     *
     * @param context 上下文环境.
     * @param txt     提示文本信息.
     * @param gravity 相对布局.
     * @param xSet    x坐标偏移量.
     * @param ySet    y坐标偏移量.
     */
    public static void showLongToast(Context context, String txt, int gravity, int xSet, int ySet) {
        showToastAtLocation(context, txt, Toast.LENGTH_LONG, gravity, xSet, ySet);
    }

}
