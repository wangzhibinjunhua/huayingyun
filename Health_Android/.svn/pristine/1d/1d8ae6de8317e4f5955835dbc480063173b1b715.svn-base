package com.tentinet.healthy.widget;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.adapter.HomeDeviceAdapter;
import com.tentinet.healthy.adapter.HomeGridAdapter;
import com.tentinet.healthy.adapter.WindowDeviceAdapter;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.view.DataTab;

import java.util.ArrayList;

/**
 * <h3>Description</h3>
 * TODO
 * <h3>Author</h3> （你的姓名）
 * <h3>Date</h3> 2016/3/29 9:30
 * <h3>Copyright</h3> Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class DeviceWindow extends PopupWindow {

    private Context context;
    private GridView gridView;
    // private Button txt_ble_ear,txt_ble_opd,txt_ble_blood,txt_ble_glucometer,txt_ble_ecg;
    private TextView parent;
    private DataTab.OnWindowItemListeren onWindowItemListeren;
    private WindowDeviceAdapter gridAdapter;
    /**
     * 已绑定设备集合
     */
    private ArrayList<DeviceBean> bindedDevices;

    public DeviceWindow(Context context, View parent, DataTab.OnWindowItemListeren listeren) {
        super(context);
        init(context, parent, listeren);
    }

    /**
     * 初始化
     *
     * @param context 上下文
     * @param parent  父view
     */
    private void init(Context context, View parent, DataTab.OnWindowItemListeren listeren) {
        this.context = context;
        this.parent = (TextView) parent;
        this.onWindowItemListeren = listeren;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_device_window, null);
        gridView = (GridView) view.findViewById(R.id.device_window_giv);
        bindedDevices = new ArrayList<>();
//
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        // this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xbffffff);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        gridAdapter = new WindowDeviceAdapter(context, bindedDevices);
        gridView.setAdapter(gridAdapter);
        addBindDevice();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DeviceBean deviceBean = bindedDevices.get(position);
                if (deviceBean != null) {
                    changeItem(deviceBean.getDeviceName(), position + 1);
                }
            }
        });
    }


    /**
     * 改变选项
     *
     * @param view
     * @param btn
     * @param index
     */
    private void changeItem(String txt, int index) {
        parent.setText(txt);
        dismiss();
        //请求数据
        onWindowItemListeren.onClick(index);
    }

    private void addBindDevice() {
        DeviceBean bean = new DeviceBean();
        bean.setDeviceName("蓝牙血压计");
        bean.setUrl(R.mipmap.icon_bpm);
        bindedDevices.add(bean);
        bean = new DeviceBean();
        bean.setDeviceName("蓝牙血糖仪");
        bean.setUrl(R.mipmap.icon_bgm);
        bindedDevices.add(bean);

        bean = new DeviceBean();
        bean.setDeviceName("蓝牙血氧仪");
        bean.setUrl(R.mipmap.icon_box);
        bindedDevices.add(bean);

        bean = new DeviceBean();
        bean.setDeviceName("蓝牙心电仪");
        bean.setUrl(R.mipmap.icon_ecg);
        bindedDevices.add(bean);

        bean = new DeviceBean();
        bean.setDeviceName("蓝牙耳温枪");
        bean.setUrl(R.mipmap.icon_temp);
        bindedDevices.add(bean);

        gridAdapter.notifyDataSetChanged();
    }
}
