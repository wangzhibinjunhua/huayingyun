package com.tentinet.healthy.activity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.interf.OpenBleListener;
import com.tentinet.healthy.service.BleManagerService;
import com.tentinet.healthy.service.BroadcastActions;
import com.tentinet.healthy.util.BleUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.DataTab;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/4/25 9:26
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class DeviceMeasureAcvitiy extends BaseActivity {
    /**
     * parent布局
     */
    private LinearLayout device_measure_lin;
    /**
     * 测量，数据Tab
     */
    private DataTab data_tab;
    /**
     * 设备测量类型key
     */
    private static final String KEY_MEASURE_TYPE = "KEY_MEASURE_TYPE";
    /**
     * 设备测量类型value
     */
    private int MEASURE_TYPE = DataBean.TYPE_BLOOD_PRESSURE_MONITOR;

    private String DEVICE_ID;

    /**
     * 设备id
     */
    private static final String KEY_DEVICE_ID = "KEY_DEVICE_ID";

    @Override
    protected void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            MEASURE_TYPE = bundle.getInt(KEY_MEASURE_TYPE, MEASURE_TYPE);
            DEVICE_ID = bundle.getString(KEY_DEVICE_ID);
        }


    }

    @Override
    protected int setContent() {
        return R.layout.acivity_device_measure;
    }

    @Override
    protected void init() {

        device_measure_lin = (LinearLayout) findViewById(R.id.device_measure_lin);
        if (data_tab == null) {
            data_tab = new DataTab(this, MEASURE_TYPE);
        }
        device_measure_lin.removeAllViews();
        device_measure_lin.addView(data_tab);
        doBaseBleRun();
    }

    @Override
    protected void registerEvent() {

    }

    /**
     * 请求码：打开蓝牙
     */
    public static final int REQUEST_ENABLE_BT = 5;
    /**
     * 蓝牙操作的回调(DataTab页面)
     */
    private OpenBleListener openBleListener = new OpenBleListener() {
        @Override
        public void onNoSupport() {
            ToastUtil.showLongToast(DeviceMeasureAcvitiy.this, "unsuported ble");
            finish();
        }

        @Override
        public void onNoOpen() {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

        @Override
        public void onOpen() {
            data_tab.showLoadSanBle();
            Intent intent = new Intent(DeviceMeasureAcvitiy.this, BleManagerService.class);
            startService(intent);
        }


    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_ENABLE_BT == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                data_tab.showLoadSanBle();
                Intent intent = new Intent(BroadcastActions.ACTION_START_LE_SCAN);
                sendBroadcast(intent);
            } else {
                /** 用户未同意开启蓝牙，或蓝牙打开错误 */
                ToastUtil.showLongToast(this, "The bluetooth is not enable");
                finish();
            }
        } else if (MainActivity.REQUEST_LOGIN == requestCode && resultCode == Activity.RESULT_OK) {
            data_tab.reLoad();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        data_tab.destroy();
    }

    /**
     * 判断支持蓝牙设备
     */
    private void doBaseBleRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 检查当前手机是否支持ble 蓝牙,如果不支持退出程序
                BleUtil.checkBleBaseService(DeviceMeasureAcvitiy.this, openBleListener);
            }
        }).start();


    }
}
