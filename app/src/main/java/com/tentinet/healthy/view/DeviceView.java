package com.tentinet.healthy.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.tentinet.healthy.R;
import com.tentinet.healthy.activity.DeviceMeasureAcvitiy;
import com.tentinet.healthy.activity.LoginActivity;
import com.tentinet.healthy.activity.MainActivity;
import com.tentinet.healthy.adapter.WindowDeviceAdapter;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.util.LogUtil;

import java.util.ArrayList;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/4/25 9:12
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class DeviceView extends LinearLayout {

    /**
     * 设备grid
     */
    private GridView gridView;
    private Context context;
    /**
     * 设备适配器
     */
    private WindowDeviceAdapter gridAdapter;
    /**
     * 标题
     */
    private TitleView view_title;
    /**
     * 设备测量类型key
     */
    private static final String KEY_MEASURE_TYPE = "KEY_MEASURE_TYPE";

    /**
     * 设备id
     */
    private static final String KEY_DEVICE_ID = "KEY_DEVICE_ID";

    /**
     * 设备测量类型value
     */
    private int MEASURE_TYPE = DataBean.TYPE_BLOOD_PRESSURE_MONITOR;
    /**
     * 设备集合
     */
    private ArrayList<DeviceBean> bindedDevices;

    public DeviceView(Context context) {
        super(context);
        init(context);
    }

    public DeviceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        this.context = context;
        this.addView(LayoutInflater.from(context).inflate(R.layout.item_device_window, null), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        gridView = (GridView) findViewById(R.id.device_window_giv);
        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setTitle(R.string.device);
        bindedDevices = new ArrayList<>();
        gridAdapter = new WindowDeviceAdapter(context, bindedDevices);
        gridView.setAdapter(gridAdapter);
        addBindDevice();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (TApplication.user.isLogin()) {

                    DeviceBean deviceBean = bindedDevices.get(position);
                    LogUtil.logDebugMessage("bindedDevices___________" + position);
                    if (deviceBean != null) {
                        Bundle bundle = new Bundle();
                        MEASURE_TYPE = position + 1;
                        bundle.putInt(KEY_MEASURE_TYPE, MEASURE_TYPE);
                        //bundle.putString(KEY_DEVICE_ID, deviceBean.getDevice_id());
                        IntentUtil.gotoActivity(context, DeviceMeasureAcvitiy.class, bundle);
                    }
                }else{
                    IntentUtil.gotoActivityForResult(context, LoginActivity.class, MainActivity.REQUEST_LOGIN);
                }
            }
        });
    }

    private void addBindDevice() {
        DeviceBean bean = new DeviceBean();
        bean.setDeviceName(context.getString(R.string.bluetooth_blood_pressure));
        bean.setUrl(R.mipmap.icon_bpm);
        bindedDevices.add(bean);
        bean = new DeviceBean();
        bean.setDeviceName(context.getString(R.string.bluetooth_blood_sugar));
        bean.setUrl(R.mipmap.icon_bgm);
        bindedDevices.add(bean);

        bean = new DeviceBean();
        bean.setDeviceName(context.getString(R.string.bluetooth_oxygen));
        bean.setUrl(R.mipmap.icon_box);
        bindedDevices.add(bean);

        bean = new DeviceBean();
        bean.setDeviceName(context.getString(R.string.bluetooth_ecg));
        bean.setUrl(R.mipmap.icon_ecg);
        bindedDevices.add(bean);

        bean = new DeviceBean();
        bean.setDeviceName(context.getString(R.string.bluetooth_ear_thermometer));
        bean.setUrl(R.mipmap.icon_temp);
        bindedDevices.add(bean);

        gridAdapter.notifyDataSetChanged();
    }

}
