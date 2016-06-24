package com.tentinet.healthy.interf;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;


import com.tentinet.healthy.exception.BLEException;
import com.tentinet.healthy.util.BLEGattAttributes;
import com.tentinet.healthy.util.BrandInfoUtils;
import com.tentinet.healthy.util.LogUtil;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class BleManager {

    /**
     * 日志开关
     */
    //   private static final boolean DBT = true;
    private Context mContext;
    /**
     * ble BluetoothAdapter的获取必须BluetoothManager来获得
     */
    private BluetoothManager mBluetoothManager;
    /**
     * 本地蓝牙适配器
     */
    private BluetoothAdapter mBluetoothAdapter;
    /**
     * 连接的蓝牙设备地址
     */
    private String mBluetoothDeviceAddress;
    /**
     * 连接的设备
     */
    BluetoothDevice mDevice;

    public BluetoothGatt getmBluetoothGatt() {
        return mBluetoothGatt;
    }

    /**
     * BluetoothGatt
     */
    private BluetoothGatt mBluetoothGatt;


    /**
     * 防丢是否打开
     */
    private boolean enableAntiLost = false;
    /**
     * 是否已连接
     */
    private boolean mConnected;
    /***/
    private boolean isConnecting;
    /**
     * RSSI Handler
     */
    private Handler rssiHandler = new Handler();
    /**
     * 读取RSSI间隔
     */
    private static final long RSSI_READ_INTERVAL = 1500;
    /**
     * 稳定连接handler
     */
    private Handler steadyConnectHandler = new Handler();
    /**
     * BleManagerListener
     */
    private BleManagerListener bleListener;
    /**
     * 默认BleManagerListener
     */
    private BleManagerListener defaultListener = new DefaultBleManagerListener();

    private int tries;

    private long time;

    public BleManager(Context context) {
        mContext = context;
    }

    public BleManager(Context context, BleManagerListener listener) {
        mContext = context;
        bleListener = listener;
        if (bleListener == null) {
            bleListener = defaultListener;

        }
    }

    /**
     * initialize() Service 初始化
     *
     * @return true if success，false otherwise；
     * @version 1.0
     * @createTime 2014-3-18,上午10:24:20
     * @updateTime 2014-3-18,上午10:24:20
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public boolean initialize() {
        if (mBluetoothManager == null) {
            mBluetoothManager = (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
            if (mBluetoothManager == null) {

                LogUtil.logMessage("wutl", "Unable to initialize BluetoothManager.");
                return false;
            }
        }

        // mBluetoothAdapter = mBluetoothManager.getAdapter();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {

            LogUtil.logMessage("wutl", "Unable to obtain a BluetoothAdapter.");
            return false;
        }
        return true;
    }

    public void setBluetoothGatt(BluetoothGatt gatt) {
        mBluetoothGatt = gatt;
    }

    /**
     * BLE扫描回调接口
     *
     * @version 1.0
     * @createTime 2014-3-18,上午10:27:34
     * @updateTime 2014-3-18,上午10:27:34
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            LogUtil.logMessage("wutl", "onLeScan");
            bleListener.onLeScaned(device, rssi, scanRecord);
        }
    };


    /**
     * Gatt回调，BluetoothGatt作为中央来使用和处理数据；BluetoothGattCallback返回中央的状态和周边提供的数据。
     *
     * @version 1.0
     * @createTime 2014-3-18,上午10:27:34
     * @updateTime 2014-3-18,上午10:27:34
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {

            LogUtil.logMessage("wutl", "status / new status" + status + "/" + newState);
            try {
                LogUtil.logMessage("wutl", "onConnectionStateChange");
                bleListener.onConnectionStateChange(gatt, status, newState);
            } catch (BLEException e) {
                System.out.println(e);
                reconnect();
                e.printStackTrace();
            }
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                mConnected = true;
                isConnecting = false;

                LogUtil.logMessage("wutl", "Connected to GATT server.");
                if (!(BrandInfoUtils.isNexus())) {
                    // 搜索连接设备所支持的service。
                    boolean discoverServices = mBluetoothGatt.discoverServices();

                    LogUtil.logMessage("wutl", "Attempting to start service discovery:" + discoverServices);
                }
                System.out.println(System.currentTimeMillis() - time);
                time = System.currentTimeMillis();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                mConnected = false;
                isConnecting = false;
                close();

            }

        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {

                LogUtil.logMessage("wutl", "onServicesDiscovered reveived is success");
                try {
                    bleListener.onServicesDiscoveredSuccess(gatt, status, getSupportedGattServices());
                } catch (BLEException e) {
                    System.out.println(e);
                    reconnect();
                    e.printStackTrace();
                }

            } else {

                LogUtil.logMessage("wutl", "onServicesDiscovered received: " + status);
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                try {
                    bleListener.onCharacteristicReadSuccess(gatt, status, characteristic);
                } catch (BLEException e) {
                    System.out.println(e);
                    reconnect();
                    e.printStackTrace();
                }
            } else {

                LogUtil.logMessage("wutl", "status is not success");
            }
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {

            LogUtil.logMessage("wutl", "CharacteristicChanged");
            bleListener.onCharacteristicChanged(gatt, characteristic);
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {

                LogUtil.logMessage("wutl", "current rssi is " + rssi);
                try {
                    bleListener.onReadRemoteRssiSuccess(gatt, rssi, status);
                } catch (BLEException e) {
                    System.out.println(e);
                    reconnect();
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {

            LogUtil.logMessage("wutl", "onCharacteristicWrite status:" + status);
            try {
                bleListener.onCharacteristicWrite(gatt, characteristic, status);
            } catch (BLEException e) {
                System.out.println(e);
                reconnect();
                e.printStackTrace();
            }
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {

            LogUtil.logMessage("wutl", "onDescriptorRead status:" + status);
            try {
                bleListener.onDescriptorRead(gatt, descriptor, status);
            } catch (BLEException e) {
                System.out.println(e);
                reconnect();
                e.printStackTrace();
            }
            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {

            LogUtil.logMessage("wutl", "onDescriptorWrite status:" + status);
            try {
                bleListener.onDescriptorWrite(gatt, descriptor, status);
            } catch (BLEException e) {
                System.out.println(e);
                reconnect();
                e.printStackTrace();
            }
            super.onDescriptorWrite(gatt, descriptor, status);
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {

            LogUtil.logMessage("wutl", "onReliableWriteCompleted status:" + status);
            try {
                bleListener.onReliableWriteCompleted(gatt, status);
            } catch (BLEException e) {
                System.out.println(e);
                reconnect();
                e.printStackTrace();
            }
            super.onReliableWriteCompleted(gatt, status);
        }
    };


    /**
     * 判断蓝牙是否可用
     *
     * @return
     * @version 1.0
     * @createTime 2014-4-12,下午6:01:54
     * @updateTime 2014-4-12,下午6:01:54
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public boolean checkBleAvailable() {
        final BluetoothManager manager = (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
        if (manager == null) {
            return false;
        }

        final BluetoothAdapter adapter = manager.getAdapter();
        if (adapter == null) {
            return false;
        }

        return mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    /**
     * 判断蓝牙是否已打开
     *
     * @return
     * @version 1.0
     * @createTime 2014-3-24,下午2:40:39
     * @updateTime 2014-3-24,下午2:40:39
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public boolean isBluetoothAdapterEnable() {
        final BluetoothManager manager = (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE);
        if (manager == null) {
            return false;
        }

        final BluetoothAdapter adapter = manager.getAdapter();
        if (adapter == null) {
            return false;
        }
        return adapter.isEnabled();
    }

    /**
     * 蓝牙未开启则开启蓝牙,enable()最长用于设置，有可能部分设备无效，如果
     * 返回false，可以设置android.bluetooth.adapter.action.REQUEST_ENABLE的intent拉起
     * 蓝牙打开权限对话框。
     *
     * @version 1.0
     * @createTime 2014-3-21,下午2:37:56
     * @updateTime 2014-3-21,下午2:37:56
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public boolean enableBluetooth() {
        if (!mBluetoothAdapter.isEnabled()) {
            LogUtil.logMessage("wutl", "enableBluetooth");
            return mBluetoothAdapter.enable();
        } else {
            return true;
        }
    }

    /**
     * 关闭蓝牙
     *
     * @return
     * @version 1.0
     * @createTime 2014-4-17,下午5:17:45
     * @updateTime 2014-4-17,下午5:17:45
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public boolean disableBluetooth() {
        if (mBluetoothAdapter.isEnabled()) {
            LogUtil.logMessage("wutl", "disableBluetooth");
            return mBluetoothAdapter.disable();
        } else {
            return true;
        }
    }

    /**
     * 读取rssi值，相隔指定时间读取一次rssi值，
     *
     * @param enable
     * @version 1.0
     * @createTime 2014-4-14,上午9:15:21
     * @updateTime 2014-4-14,上午9:15:21
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void readRssiValue(final boolean enable) {
        enableAntiLost = enable;
        if (mConnected == false || mBluetoothGatt == null || enableAntiLost == false) {
            enableAntiLost = false;
            return;
        }

        rssiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mBluetoothGatt == null || mBluetoothAdapter == null || mConnected == false) {
                    enableAntiLost = false;
                    return;
                }
                mBluetoothGatt.readRemoteRssi();
                readRssiValue(enableAntiLost);
            }
        }, RSSI_READ_INTERVAL);
    }

    /**
     * startLeScan() 开始扫描
     *
     * @version 1.0
     * @createTime 2014-3-18,上午10:27:34
     * @updateTime 2014-3-18,上午10:27:34
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void startLeScan() {
        if (mBluetoothAdapter == null) {

            LogUtil.logMessage("wutl", "Unable to obtain a BluetoothAdapter.");
            return;
        }
        LogUtil.logMessage("wutl", "startLeScan");
        mBluetoothAdapter.startLeScan(mLeScanCallback);
    }

    /**
     * stopLeScan() 停止扫描
     *
     * @version 1.0
     * @createTime 2014-3-18,上午10:27:01
     * @updateTime 2014-3-18,上午10:27:01
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void stopLeScan() {
        if (mBluetoothAdapter == null) {

            LogUtil.logMessage("wutl", "Unable to obtain a BluetoothAdapter.");
            return;
        }
        LogUtil.logMessage("wutl", "stopLeScan");
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }

    /**
     * startLeScanByUUID(String []uuids) 根据服务UUID扫描，确保与startLeScan()不同时调用
     *
     * @param uuids Services的UUID数组
     * @version 1.0
     * @createTime 2014-3-18,上午10:26:09
     * @updateTime 2014-3-18,上午10:26:09
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void startLeScanByUUID(String[] uuids) {
        if (mBluetoothAdapter == null) {

            LogUtil.logMessage("wutl", "Unable to obtain a BluetoothAdapter.");
            return;
        }
        int len = uuids.length;
        UUID[] serviceUuids = new UUID[len];
        for (int i = 0; i < len; i++) {
            serviceUuids[i] = UUID.fromString(uuids[i]);
        }
        mBluetoothAdapter.startLeScan(serviceUuids, mLeScanCallback);
    }

    /**
     * connect(final String address) 连接
     *
     * @param address ble设备mac地址
     * @return true if success，false otherwise
     * @version 1.0
     * @createTime 2014-3-18,上午10:23:28
     * @updateTime 2014-3-18,上午10:23:28
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public boolean connect(final String address) {
        if (!isConnecting) {
            tries = 2;
            isConnecting = true;
            if (mBluetoothAdapter == null || address == null) {

                LogUtil.logMessage("wutl", "BluetoothAdapter not initialized or unspecified address.");
                return false;
            }

            mDevice = mBluetoothAdapter.getRemoteDevice(address);
            if (mDevice == null) {

                LogUtil.logMessage("wutl", "Device not found.  Unable to connect.");
                return false;
            }
            // setting the autoConnect parameter to false.
            mBluetoothGatt = mDevice.connectGatt(mContext, false, mGattCallback);


            LogUtil.logMessage("wutl", "Trying to create a new connection.");
            mBluetoothDeviceAddress = address;

            return true;
        }
        return false;

    }

    /**
     * 重连
     *
     * @return
     * @version 1.0
     * @createTime 2014-4-16,上午10:33:17
     * @updateTime 2014-4-16,上午10:33:17
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public boolean reconnect() {
        if (!isConnecting) {
            isConnecting = true;
            if (mBluetoothAdapter == null) {

                LogUtil.logMessage("wutl", "BluetoothAdapter not initialized or unspecified address.");
                return false;
            }

            // 上次连接的设备，进行重新连接，保留原BluetoothGatt
            if (mBluetoothDeviceAddress != null && mBluetoothGatt != null) {

                LogUtil.logMessage("wutl", "Trying to use an existing mBluetoothGatt for connection.");
                // 连接远程设备。
                if (mBluetoothGatt.connect()) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
        return false;
    }

    /**
     * 稳定连接，断开后延迟2s再重连
     *
     * @param address
     * @version 1.0
     * @createTime 2014-4-17,下午6:21:47
     * @updateTime 2014-4-17,下午6:21:47
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void steadyConnect(final String address) {
        if (!isConnecting) {
            steadyConnectHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (!mConnected)
                        reconnect();
                }
            }, 2000);
        }
    }

    /**
     * disconnect() 断开连接
     *
     * @version 1.0
     * @createTime 2014-3-18,上午10:22:56
     * @updateTime 2014-3-18,上午10:22:56
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void disconnect() {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {

            LogUtil.logMessage("wutl", "BluetoothAdapter not initialized");
            return;
        }
        // 断开与远程设备的GATT连接。
        mBluetoothGatt.disconnect();
    }

    /**
     * 通过mac绑定设备，createBond()是异步调用，返回结果并不是指绑定是否成功，而是指是否
     * 成功拉起绑定进程，如果要知道绑定状态，许注册广播android
     * .bluetooth.device.action.BOND_STATE_CHANGED 该广播包括其它属性EXTRA_DEVICE,
     * EXTRA_BOND_STATE and EXTRA_PREVIOUS_BOND_STATE. createBond() 的API Levle
     * 19
     *
     * @param address
     * @return 是否成功拉起绑定进程
     * @version 1.0
     * @createTime 2014-3-22,上午11:19:55
     * @updateTime 2014-3-22,上午11:19:55
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    @SuppressLint("NewApi")
    public boolean createBondDevice(final String address) {
        if (mBluetoothAdapter == null || address == null) {

            LogUtil.logMessage("wutl", "BluetoothAdapter not initialized or unspecified address.");
            return false;
        }

        final BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        if (device == null) {

            LogUtil.logMessage("wutl", "Device not found.  Unable to connect.");
            return false;
        }

        return device.createBond();
    }

    /**
     * close() 退出时，必须回收gatt
     *
     * @version 1.0
     * @createTime 2014-3-18,上午10:21:57
     * @updateTime 2014-3-18,上午10:21:57
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void close() {
        if (mBluetoothGatt == null) {
            return;
        }
        isConnecting = false;
        // 关闭GATT Client端。
        mBluetoothGatt.close();
        mBluetoothGatt = null;
        LogUtil.logMessage("wutl", "gatt close");
    }

    /**
     * 读取特征值，读取特征的value值，在BluetoothGattCallback的
     * onCharacteristicRead(BluetoothGatt gatt,BluetoothGattCharacteristic
     * characteristic, int status) 中回调
     *
     * @param characteristic 可读取特征
     * @version 1.0
     * @createTime 2014-3-18,上午10:21:28
     * @updateTime 2014-3-18,上午10:21:28
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void readCharacteristic(BluetoothGattCharacteristic characteristic) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {

            LogUtil.logMessage("wutl", "BluetoothAdapter not initialized");
            return;
        }
        // 读取指定的characteristic。
        mBluetoothGatt.readCharacteristic(characteristic);
    }

    /**
     * 注册该Characteristic，当该Characteristic发生变化时，回调onCharacteristicChanged
     * characteristic的变化，包括value值的更新都会回调onCharacteristicChanged，
     * 在android中注册Notification必须写入正确的描述，否则将导致不能跟着Characteristic的value变化
     *
     * @param characteristic 可注册服务特征
     * @param enabled        注册/停止注册该通知
     * @version 1.0
     * @createTime 2014-3-18,上午10:16:58
     * @updateTime 2014-3-18,上午10:16:58
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void setCharacteristicNotification(BluetoothGattCharacteristic characteristic, boolean enabled) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {

            LogUtil.logMessage("wutl", "BluetoothAdapter not initialized");
            return;
        }
        // 设置当指定characteristic值变化时，发出通知。
        boolean setno = mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);

        LogUtil.logMessage("wutl", "notification set :" + setno);

        try {
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString(BLEGattAttributes.CLIENT_CHARACTERISTIC_CONFIGURATION));
            if (descriptor != null) {

                LogUtil.logMessage("wutl", "set descriptor" + descriptor + ":" + descriptor.getValue());
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                mBluetoothGatt.writeDescriptor(descriptor);
            } else {

                LogUtil.logMessage("wutl", "descriptor is null");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取指定descriptor的value值，在onDescriptorRead中回调
     *
     * @param characteristic descriptor属于哪个Characteristic
     * @param descriptorUUID descriptor的uuid
     * @version 1.0
     * @createTime 2014-3-25,下午2:35:37
     * @updateTime 2014-3-25,下午2:35:37
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void readDescriptor(BluetoothGattCharacteristic characteristic, String descriptorUUID) {
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.fromString(descriptorUUID));
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {

            LogUtil.logMessage("wutl", "BluetoothAdapter not initialized");

            LogUtil.logMessage("wutl", "mBluetoothGatt is " + mBluetoothGatt);
            return;
        }
        mBluetoothGatt.readDescriptor(descriptor);
    }

    /**
     * 注册该Characteristic，当该Characteristic发生变化时，回调onCharacteristicChanged
     * 与setCharacteristicNotification(BluetoothGattCharacteristic
     * characteristic,boolean enabled) 唯一区别是将descriptor是未确定的
     *
     * @param characteristic 可注册Characteristic
     * @param descriptor     该Characteristic 的descriptor
     * @param enabled        注册/停止注册该通知
     * @version 1.0
     * @createTime 2014-3-21,下午2:00:08
     * @updateTime 2014-3-21,下午2:00:08
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void setCharacteristicNotification(BluetoothGattCharacteristic characteristic, BluetoothGattDescriptor descriptor, boolean enabled) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {

            LogUtil.logMessage("wutl", "BluetoothAdapter not initialized");

            LogUtil.logMessage("wutl", "mBluetoothGatt is " + mBluetoothGatt);
            return;
        }

        boolean setno = mBluetoothGatt.setCharacteristicNotification(characteristic, enabled);

        LogUtil.logMessage("wutl", "notification set :" + setno);
        try {
            if (descriptor != null) {

                LogUtil.logMessage("wutl", "set descriptor" + descriptor + descriptor.getValue());
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                mBluetoothGatt.writeDescriptor(descriptor);
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写数据，将byte写入到特定的characteristic
     *
     * @param characteristic 可写的服务特征
     * @param bytes          写入二进制数组
     * @version 1.0
     * @createTime 2014-3-18,上午10:15:50
     * @updateTime 2014-3-18,上午10:15:50
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void writeValue(BluetoothGattCharacteristic characteristic, byte[] bytes) {
        if (mBluetoothAdapter == null || mBluetoothGatt == null) {

            LogUtil.logMessage("wutl", "BluetoothAdapter not initialized");
        }

        if (bytes != null && bytes.length > 0) {
            final StringBuilder stringBuilder = new StringBuilder(bytes.length);
            for (byte byteChar : bytes) {
                stringBuilder.append(String.format("%02X", byteChar)); // 16进制
            }

            LogUtil.logMessage("wutl", "TX:" + stringBuilder.toString());
        }
        //String u="0x5AH0BH05H0FH06H01H08H38HC0H00H00H";
        characteristic.setValue(bytes);

        mBluetoothGatt.writeCharacteristic(characteristic);
    }

    /**
     * 返回已绑定的所有设备集
     *
     * @return
     * @version 1.0
     * @createTime 2014-3-22,下午4:45:44
     * @updateTime 2014-3-22,下午4:45:44
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public Set<BluetoothDevice> getBondDevices() {
        return mBluetoothAdapter.getBondedDevices();
    }

    /**
     * 取得设备支持的所有服务，在mBluetoothGatt.discoverServices()（搜索连接设备所支持的service）之后调用
     *
     * @return 相应设备支持的所有Service
     * @version 1.0
     * @createTime 2014-3-18,上午10:14:06
     * @updateTime 2014-3-18,上午10:14:06
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public List<BluetoothGattService> getSupportedGattServices() {
        if (mBluetoothGatt == null)
            return null;
        // 获取远程设备所支持的services。
        return mBluetoothGatt.getServices();
    }

    /**
     * 取得服务的所有Characteristic
     *
     * @param bleService
     * @return
     * @version 1.0
     * @createTime 2014-3-20,下午4:49:36
     * @updateTime 2014-3-20,下午4:49:36
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public List<BluetoothGattCharacteristic> getCharacteristicByService(BluetoothGattService bleService) {
        if (bleService == null)
            return null;
        return bleService.getCharacteristics();
    }

    /**
     * getDescriptorByCharacteristic(BluetoothGattCharacteristic
     * bleCharacteristic) 取得Characteristic的所有描述
     *
     * @param bleCharacteristic
     * @return
     * @version 1.0
     * @createTime 2014-3-20,下午4:50:22
     * @updateTime 2014-3-20,下午4:50:22
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public List<BluetoothGattDescriptor> getDescriptorByCharacteristic(BluetoothGattCharacteristic bleCharacteristic) {
        if (bleCharacteristic == null)
            return null;
        return bleCharacteristic.getDescriptors();
    }

}
