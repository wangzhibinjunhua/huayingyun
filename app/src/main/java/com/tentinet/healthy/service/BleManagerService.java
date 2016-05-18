package com.tentinet.healthy.service;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.util.Log;


import com.tentinet.healthy.config.CommondConfig;
import com.tentinet.healthy.exception.BLEException;
import com.tentinet.healthy.interf.BleManager;
import com.tentinet.healthy.interf.BleManagerListener;
import com.tentinet.healthy.interf.LeRequest;
import com.tentinet.healthy.interf.LeResponse;
import com.tentinet.healthy.util.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.prefs.BackingStoreException;

/**
 * 类描述:蓝牙通信服务
 *
 * @author 王治粮
 * @date 2015/12/28,15:49
 * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class BleManagerService extends Service {

    /**
     * 当蓝牙建立连接成功之后,点击蓝牙的某个服务时，给Service初始化值
     */
    public static BluetoothGattService service;
    /**
     * 当蓝牙建立连接成功之后,点击蓝牙的某个服务时，给characteristic初始化值
     */
    public static BluetoothGattCharacteristic characteristic;

    public static BluetoothGatt bluetoothGatt;

    /**
     * BleControllerActivity(蓝牙连接之后，对蓝牙设备操作类),在这个类中试用
     */
    public static ArrayList<String> lastCharasUUID = new ArrayList<String>();


    private String device_name;


    /**
     * 蓝牙
     */
    private BleManager bleManager;
    private LeRequest leRequest;
    private LeResponse leResponse;
    /**
     * 搜索到的设备列表
     */
    private ArrayList<BluetoothDevice> mLeDevices = new ArrayList<BluetoothDevice>();
    /**
     * 当前连接的设备的服务列表
     */
    public static List<BluetoothGattService> listServices = new ArrayList<BluetoothGattService>();
    WifiManager wifiManager;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        init();
        //  wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        setFilterAction();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 过滤广播
     *
     * @version 1.0
     * @createTime 2015/12/29  11:53
     * @updateTime 2015/12/29  11:53
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    private void setFilterAction() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BroadcastActions.ACTION_CLOSE_GATT);
        filter.addAction(BroadcastActions.ACTION_START_LE_SCAN);
        filter.addAction(BroadcastActions.ACTION_STOP_LE_SCAN);
        filter.addAction(BroadcastActions.ACTION_CONNECT);
        filter.addAction(BroadcastActions.ACTION_DISCONNECT);
        filter.addAction(BroadcastActions.ACTION_WRITE_TO_SERVICE);
        filter.addAction(BroadcastActions.ACTION_READ_CHARACTERISTIC);
        filter.addAction(BroadcastActions.ACTION_SET_NOTIFICATION_CHARACTERISTIC);
        registerReceiver(bleServiceReceiver, filter);
    }


    /**
     * 接收广播
     *
     * @author 王治粮
     * @date 2015/12/29  14:03
     * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
     */
    private BroadcastReceiver bleServiceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BroadcastActions.ACTION_START_LE_SCAN.equals(action)) {//扫描
                mLeDevices.clear();
                leRequest.startLeScan();
            } else if (BroadcastActions.ACTION_STOP_LE_SCAN.equals(action)) {
                leRequest.stopLeScan();
            } else if (BroadcastActions.ACTION_CONNECT.equals(action)) {
                device_name = intent.getExtras().getString(
                        BroadcastActions.EXTRA_DEVICENAME_DATA);
                // closeWifi();
                leRequest.connect(intent.getExtras().getString(
                        BroadcastActions.EXTRA_NAME_DATA));

            } else if (BroadcastActions.ACTION_DISCONNECT.equals(action)) {
                leRequest.disconnect();
            }
            // 向ble写数据
            else if (BroadcastActions.ACTION_WRITE_TO_SERVICE.equals(action)) {
                Log.e("ykk", "write16Bytes chara uuid is "
                        + characteristic.getUuid().toString());
                leRequest.write16Bytes(characteristic, intent.getExtras()
                        .getByteArray(BroadcastActions.EXTRA_NAME_DATA));
            }
            // 读取指定Characteristic
            else if (BroadcastActions.ACTION_READ_CHARACTERISTIC.equals(action)) {

                leRequest.readCharactersitc(characteristic);
            } else if (BroadcastActions.ACTION_SET_NOTIFICATION_CHARACTERISTIC
                    .equals(action)) {
                Log.e("ykk", "setNotificatioin chara uuid is "
                        + characteristic.getUuid().toString());
                leRequest.setNotification(characteristic, intent.getExtras()
                        .getBoolean(BroadcastActions.EXTRA_NAME_DATA));
            } else if (BroadcastActions.ACTION_CLOSE_GATT.equals(action)) {
                leRequest.closeGatt();
            }
        }
    };

    @Override
    public void onDestroy() {
        if (null != bleServiceReceiver) {
            unregisterReceiver(bleServiceReceiver);
        }
        super.onDestroy();
    }

    /**
     * 初始化
     *
     * @version 1.0
     * @createTime 2015/12/29  9:49
     * @updateTime 2015/12/29  9:49
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public void init() {
        bleManager = new BleManager(this, bleListener);
        leRequest = new LeRequest(bleManager);
        leResponse = new LeResponse(this);
        if (bleManager.initialize()) {
            Intent intent = new Intent();
            intent.setAction(BroadcastActions.ACTION_BLEMANAGER_INIT);
            sendBroadcast(intent);
            // 返回已绑定的所有设备集
            bleManager.getBondDevices();
        }
    }


    /**
     * 蓝牙设备监听
     *
     * @author 王治粮
     * @date 2015/12/29  15:30
     * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
     */
    private BleManagerListener bleListener = new BleManagerListener() {
        @Override
        public void onLeScaned(BluetoothDevice device, int rssi, byte[] scanRecord) {
            if (!mLeDevices.contains(device)) {
                mLeDevices.add(device);
                Intent intent = new Intent();
                intent.setAction(BroadcastActions.ACTION_UPDATE_DEVICES);
                intent.putExtra(BroadcastActions.UPDATE_DEVICES, device);
                intent.putExtra(BroadcastActions.VALUE_RSSI, rssi);
                sendBroadcast(intent);
            }
        }

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) throws BLEException {
            if (status == 135) {
                throw new BLEException("ERR135");
            } else if (status == 129) {
                throw new BLEException("ERR129");
            } else if (status == 133) {
                throw new BLEException("ERR133");
            } else if (status == 257) {
                throw new BLEException("ERR257");
            } else if (status == 132) {
                throw new BLEException("ERR132");
            }

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                bluetoothGatt = bleManager.getmBluetoothGatt();
                String intentAction = BroadcastActions.ACTION_GATT_CONNECTED;
                leResponse.broadcastUpdate(intentAction);

            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                // service = null;
                //  characteristic = null;
                String intentAction = BroadcastActions.ACTION_GATT_DISCONNECTED;
                leResponse.broadcastUpdate(intentAction);
            }

            LogUtil.logMessage("UUU", newState + "");
        }

        @Override
        public void onServicesDiscoveredSuccess(BluetoothGatt gatt, int status, List<BluetoothGattService> gattServices) throws BLEException {
            listServices.clear();
            if (status == 135) {
                throw new BLEException("ERR135");
            } else if (status == 129) {
                throw new BLEException("ERR129");
            } else if (status == 133) {
                throw new BLEException("ERR133");
            } else if (status == 257) {
                throw new BLEException("ERR257");
            } else if (status == 132) {
                throw new BLEException("ERR132");
            }
            //    listServices = gattServices;
            if (device_name.contains(CommondConfig.BLOOD_NAME) || device_name.contains(CommondConfig.GLUCOSE_NAME)) {
                connonDiaService();
            } else if (device_name.contains(CommondConfig.EAR_THERMOMETER_NAME)) {
                connonEarService();
            }
            leResponse.broadcastUpdate(BroadcastActions.ACTION_GATT_SERVICES_DISCOVERED);
        }

        @Override
        public void onCharacteristicReadSuccess(BluetoothGatt gatt, int status, BluetoothGattCharacteristic characteristic) throws BLEException {
            if (status == 135) {
                throw new BLEException("ERR135");
            } else if (status == 129) {
                throw new BLEException("ERR129");
            } else if (status == 133) {
                throw new BLEException("ERR133");
            } else if (status == 257) {
                throw new BLEException("ERR257");
            } else if (status == 132) {
                throw new BLEException("ERR132");
            } else if (status == BluetoothGatt.GATT_SUCCESS) {

                //读取成功
            } else if (status == BluetoothGatt.GATT_FAILURE) {
                //读取失败
            }
            leResponse.broadcastUpdate(BroadcastActions.ACTION_DATA_AVAILABLE,
                    characteristic);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {

            leResponse.broadcastUpdate(BroadcastActions.ACTION_DATA_AVAILABLE,
                    characteristic);
            LogUtil.logMessage("ykk", "onCharacteristicChanged");
        }

        @Override
        public void onReadRemoteRssiSuccess(BluetoothGatt gatt, int rssi, int status) throws BLEException {
            if (status == 135) {
                throw new BLEException("ERR135");
            } else if (status == 129) {
                throw new BLEException("ERR129");
            } else if (status == 133) {
                throw new BLEException("ERR133");
            } else if (status == 257) {
                throw new BLEException("ERR257");
            } else if (status == 132) {
                throw new BLEException("ERR132");
            }
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) throws BLEException {
            if (status == 135) {
                throw new BLEException("ERR135");
            } else if (status == 129) {
                throw new BLEException("ERR129");
            } else if (status == 133) {
                throw new BLEException("ERR133");
            } else if (status == 257) {
                throw new BLEException("ERR257");
            } else if (status == 132) {
                throw new BLEException("ERR132");
            } else if (status == BluetoothGatt.GATT_SUCCESS) {
//                leResponse.broadcastUpdate(BroadcastActions.ACTION_DATA_AVAILABLE,
//                        characteristic);
                // leRequest.readCharactersitc(characteristic);
                //  LogUtil.logMessage("123456", "监听写Characteristic变化----》" + "写入成功");
            } else if (status == BluetoothGatt.GATT_FAILURE) {
                LogUtil.logMessage("123456", "监听写Characteristic变化----》" + "写入失败");
            }
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) throws BLEException {
            if (status == 135) {
                throw new BLEException("ERR135");
            } else if (status == 129) {
                throw new BLEException("ERR129");
            } else if (status == 133) {
                throw new BLEException("ERR133");
            } else if (status == 257) {
                throw new BLEException("ERR257");
            } else if (status == 132) {
                throw new BLEException("ERR132");
            } else if (status == BluetoothGatt.GATT_SUCCESS) {
//                 leRequest.readCharactersitc(characteristic);
                LogUtil.logMessage("ykk", "监听读取onDescriptorRead变化----》" + "读取成功");
            } else if (status == BluetoothGatt.GATT_FAILURE) {
                LogUtil.logMessage("ykk", "监听读取onDescriptorRead变化----》" + "读取失败");
            }
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) throws BLEException {
            if (status == 135) {
                throw new BLEException("ERR135");
            } else if (status == 129) {
                throw new BLEException("ERR129");
            } else if (status == 133) {
                throw new BLEException("ERR133");
            } else if (status == 257) {
                throw new BLEException("ERR257");
            } else if (status == 132) {
                throw new BLEException("ERR132");
            } else if (status == BluetoothGatt.GATT_SUCCESS) {
                // leRequest.readCharactersitc(characteristic);
                LogUtil.logMessage("ykk", "监听写Descriptor变化----" + "写入成功");
            } else if (status == BluetoothGatt.GATT_FAILURE) {
                LogUtil.logMessage("ykk", "监听写Descriptor变化----" + "写入失败");
            }
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) throws BLEException {
            if (status == 135) {
                throw new BLEException("ERR135");
            } else if (status == 129) {
                throw new BLEException("ERR129");
            } else if (status == 133) {
                throw new BLEException("ERR133");
            } else if (status == 257) {
                throw new BLEException("ERR257");
            } else if (status == 132) {
                throw new BLEException("ERR132");
            }
        }
    };

    /**
     * 连接血压和血糖服务
     */
    public void connonDiaService() {
        service = bluetoothGatt.getService(UUID.fromString(CommondConfig.BLOOD_AND_GLUCOSE_METER_SERVICE));
        if (service != null) {
            characteristic = service.getCharacteristic(UUID.fromString(CommondConfig.BLOOD_AND_GLUCOSE_METER_WRITE_CHARA));
            BluetoothGattCharacteristic read_bgc = service.getCharacteristic(UUID.fromString(CommondConfig.BLOOD_AND_GLUCOSE_METER_READ_CHARA));
            bleManager.setCharacteristicNotification(read_bgc, true);
        }

    }

    /**
     * 连接耳温枪
     */
    public void connonEarService() {
        service = bluetoothGatt.getService(UUID.fromString(CommondConfig.EAR_THERMOMETER_SERVICE));
        if (service != null) {
            characteristic = service.getCharacteristic(UUID.fromString(CommondConfig.EAR_THERMOMETER_SERVICE_WRITE_CHARA));
            BluetoothGattCharacteristic read_bgc = service.getCharacteristic(UUID.fromString(CommondConfig.EAR_THERMOMETER_SERVICE_READ_CHARA));
            bleManager.setCharacteristicNotification(read_bgc, true);
        }

    }

}
