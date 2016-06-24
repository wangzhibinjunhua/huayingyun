package com.tentinet.healthy.util;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.activity.TempActivity;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.service.TempService;
import com.tentinet.healthy.widget.FlyDialog;

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

    private static FlyDialog dialog;
    private static View view;

    /**
     * 显示默认对话框.
     *
     * @param context  上下文环境.
     * @param view     视图.
     * @param isCancel 是否可取消.
     */
    public static void showDefaultDialog(Context context, View view, boolean isCancel) {
        if (context == null) {
            return;
        }
        dialog = new FlyDialog(context, R.style.DefalutDialog);
        dialog.setCancelable(isCancel);
        dialog.setCanceledOnTouchOutside(isCancel);
        dialog.setContentView(view);
        dialog.showBounceTopEenter(view);
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
        if (context == null) {
            return;
        }
        dialog = new FlyDialog(context, R.style.WaitDialog);
        dialog.setCancelable(isCancel);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
       // dialog.showBounceEenter(view);
        dialog.show();
    }

    /**
     * 显示等待对话框.
     *
     * @param context 上下文环境.
     */
    public static void showWaitDialog(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_wait, null);
        showNoSizeDefaultDialog(context, view, true);
    }

    /**
     * 显示等待对话框.
     *
     * @param context 上下文环境.
     */
    public static void showWaitDialog(Context context, String msg) {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_wait, null);
        TextView txt_dialog_msg = (TextView) view.findViewById(R.id.txt_dialog_msg);
        txt_dialog_msg.setText(msg);
        showNoSizeDefaultDialog(context, view, true);
    }

    /**
     * 显示等待对话框.
     *
     * @param context 上下文环境.
     */
    public static void showWaitDialog(Context context, int msg) {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_wait, null);
        TextView txt_dialog_msg = (TextView) view.findViewById(R.id.txt_dialog_msg);
        txt_dialog_msg.setText(context.getString(msg));
        showNoSizeDefaultDialog(context, view, true);
    }

    /**
     * 普通对话框
     *
     * @param context 上下文环境.
     */
    public static void showOkAndCalcelDialog(Context context, String title, String msg, boolean isShowCancel, View.OnClickListener okListenter, View.OnClickListener cancleListener) {
        if (context == null) {
            return;
        }
        view = LayoutInflater.from(context).inflate(R.layout.dialog_defalut, null);
        TextView tv_title, tv_msg;
        Button bt_ok, bt_cancel;
        tv_title = (TextView) view.findViewById(R.id.txt_dialog_title);
        tv_msg = (TextView) view.findViewById(R.id.txt_dialog_msg);
        bt_ok = (Button) view.findViewById(R.id.btn_ok);
        bt_cancel = (Button) view.findViewById(R.id.btn_cancle);
        if (isShowCancel){
            bt_cancel.setVisibility(View.GONE);
        }
        tv_title.setText(title);
        tv_msg.setText(msg);
        bt_ok.setOnClickListener(okListenter);
        bt_cancel.setOnClickListener(cancleListener);
        showDefaultDialog(context, view, false);
    }

    /**
     * 普通对话框
     *
     * @param context 上下文环境.
     */
    public static void showOkAndCalcelDialog(Context context, String title, String msg, View.OnClickListener okListenter, View.OnClickListener cancleListener) {
        showOkAndCalcelDialog(context, title, msg, false, okListenter, cancleListener);
    }

    /**
     * 关闭对话框.
     */
    public static void dismissDialog() {
        if (null != dialog) {
            dialog.dismiss();
        }
    }


    /**
     * 显示体温贴预警
     *
     * @param context
     * @param key
     * @param listener
     */

    public static void showHeatTemp(Context context, final String key, final TempActivity.IheatTemp listener) {
        if (context == null) {
            return;
        }
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_heat_window, null);
        final NumberPicker np_data;
        np_data = (NumberPicker) view.findViewById(R.id.np_data);
        np_data.setMaxValue(450);
        np_data.setMinValue(340);
        np_data.setValue((int) (TApplication.sp.get(key, TempService.tempService.DEFAULT_TEMP) * 10));
        Button bt_ok;
        bt_ok = (Button) view.findViewById(R.id.btn_ok);
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TApplication.sp.set(key, ((float) np_data.getValue()) / 10.0f);
                dismissDialog();
                listener.onClick(np_data.getValue());

            }
        });
        np_data.setFormatter(new NumberPicker.Formatter() {
            public String format(int value) {
                return String.valueOf(((float) value) / 10.0f);
            }
        });
        showDefaultDialog(context, view, true);
    }

}
