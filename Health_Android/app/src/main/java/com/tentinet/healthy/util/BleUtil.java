package com.tentinet.healthy.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.tentinet.healthy.interf.OpenBleListener;
import com.tentinet.healthy.service.BleManagerService;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/4/20 10:23
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class BleUtil {

    /**
     * 不支持蓝牙4.0
     */
    public static  final  int BLE_NO_SUPPORT=0;
    /**
     *蓝牙没有开启
     */
    public static  final  int BLE_NO_OPEN=1;
    /**
     *蓝牙开启
     */
    public static  final  int BLE_OPEN=2;

    /**
     * 检查蓝牙基本服务
     * @param context
     * @param openBleListener
     */
    public static void checkBleBaseService(Context context, OpenBleListener openBleListener) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            //不支持4.0蓝牙
            openBleListener.onNoSupport();
            return;
        }
        final BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        BluetoothAdapter mBluetoothAdapter = bluetoothManager.getAdapter();
        // 为了确保设备上蓝牙能使用, 如果当前蓝牙设备没启用,弹出对话框向用户要求授予权限来启用
        if (!mBluetoothAdapter.isEnabled()) {
            openBleListener.onNoOpen();

        } else {
            openBleListener.onOpen();
        }
    }
}
