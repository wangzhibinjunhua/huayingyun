package com.tentinet.healthy.util;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.tentinet.healthy.R;

/**
 * 自定义对话框.
 *
 * @author paladin
 * @Description
 * @date 2014年9月15日
 * @Copyright: Copyright (c) 2014 Shenzhen Tentinet Technology Co., Ltd. All
 * rights reserved.
 */
public class CustomDialog {

    private static Dialog dialog;
    private static View view;
    /**
     * 显示默认对话框.
     *
     * @param context  上下文环境.
     * @param view     视图.
     * @param isCancel 是否可取消.
     */
    public static void showDefaultDialog(Context context, View view, boolean isCancel) {

        dialog = new Dialog(context, R.style.DefalutDialog);
        dialog.setCancelable(isCancel);
        dialog.setCanceledOnTouchOutside(isCancel);
        dialog.setContentView(view);

/*
         * 将对话框的大小按屏幕大小的百分比设置
         */
        Window dialogWindow = dialog.getWindow();
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.7); // 宽度设置为屏幕的0.7
        dialogWindow.setAttributes(p);
        dialog.show();
    }

    /**
     * 显示默认对话框.
     *
     * @param context  上下文环境.
     * @param view     视图.
     * @param isCancel 是否可取消.
     */
    public static void showNoSizeDefaultDialog(Context context, View view, boolean isCancel) {
        dialog = new Dialog(context, R.style.WaitDialog);
        dialog.setCancelable(isCancel);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
        dialog.show();
    }

    /**
     * 显示等待对话框.
     *
     * @param context 上下文环境.
     */
    public static void showWaitDialog(Context context) {

        showNoSizeDefaultDialog(context, LayoutInflater.from(context).inflate(R.layout.dialog_wait, null), true);
    }

    /**
     * 普通对话框
     *
     * @param context 上下文环境.
     */
    public static void showOkAndCalcelDialog(Context context, String title, String msg, View.OnClickListener okListenter, View.OnClickListener cancleListener) {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_defalut, null);
        TextView tv_title,tv_msg;
        Button bt_ok,bt_cancel;
        tv_title=(TextView)view.findViewById(R.id.txt_dialog_title) ;
        tv_msg=(TextView)view.findViewById(R.id.txt_dialog_msg) ;
        bt_ok=(Button)view.findViewById(R.id.btn_ok);
        bt_cancel=(Button)view.findViewById(R.id.btn_cancle);
        tv_title.setText(title);
        tv_msg.setText(msg);
        bt_ok.setOnClickListener(okListenter);
        bt_cancel.setOnClickListener(cancleListener);
        showDefaultDialog(context, view, false);
    }
    /**
     * 关闭对话框.
     */
    public static void dismissDialog() {
        if (null != dialog) {
            dialog.dismiss();
        }
    }

}
