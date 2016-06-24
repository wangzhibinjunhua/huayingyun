package com.tentinet.healthy.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.NotificationCompat;

import com.btsdk.LocalBluetoothListener;
import com.btsdk.RemoteBluetooth;
import com.btsdk.StateCode;
import com.tentinet.healthy.R;
import com.tentinet.healthy.activity.TempActivity;
import com.tentinet.healthy.btsdk.LocalBluetooth;
import com.tentinet.healthy.btsdk.RemoteBluetoothRequestor;
import com.tentinet.healthy.btsdk.RemoteBluetoothResponder;
import com.tentinet.healthy.config.CommondConfig;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.LogUtil;

import java.util.ArrayList;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/5/23 17:46
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class TempService extends Service {

    /**
     * 是否已经成功连接和读取数据
     */
    public static boolean isSucceed = false;

    /**
     * 自身服务
     */
    public static TempService tempService;

    /**
     * 默认值
     */
    public static final String BLE_DEFAULT = "TEMP.DEFAULT";

    /**
     * 找到目标蓝牙
     */
    public static final String BLE_FIND = "BLE.FIND";

    /**
     * 连接成功
     */
    public static final String BLE_CONNET_SUCCEDD = "BLE.CONNET.SUCCEDD";
    /**
     * 没有发现目标蓝牙
     */
    public static final String BLE_NOT_FIND = "BLE.NOT.FIND";

    /**
     * 温度数据
     */
    public static final String BLE_DATA = "BLE.DATA";
    /**
     * 提醒
     */
    public static final String BLE_MSG = "BLE.MSG";

    public static final int TIMEOUT = 2000;

    /**
     * 默认警报值
     */
    public static final float DEFAULT_TEMP = 37.0f;
    /**
     * 警报sp key
     */
    private String SP_kEY = "HEATTEIP";
    /**
     * 扫描handler
     */
    private Handler scanHandler;
    /**
     * 具体蓝牙
     */
    public static RemoteBluetooth remoteBleDevice;

    private static ArrayList<RemoteBluetooth> remoteBluetooths;


    private IBinder iBinder = new TempBind();


    /**
     * 最大
     */
    private int max;
    /**
     * 最小
     */
    private int min;

    /**
     * 通知栏管理
     */
    NotificationManager mNotificationManager;
    /**
     * 扫描次数
     */
    private int scanCount = 0;
    /**
     * 声音池
     */
    private SoundPool soundpool;
    /**
     * 是否正在播放
     */
    private boolean isPlaySound = false;
    private boolean isInterrupt = false;
    /**
     * 声音返回的id
     */
    int sourceid;
    /**
     * 声音handler
     */
    private Handler soundHanler;

    public TempService() {
        super();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onCreate() {
        init();
        super.onCreate();
    }


    @Override
    public void onDestroy() {
        mNotificationManager.cancel(15869);
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /**
     * 初始化
     */
    private void init() {
        LocalBluetooth.initialize(this, bluetoothListener, getString(R.string.btsdk));
        soundpool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        remoteBluetooths = new ArrayList<>();
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    /**
     * 蓝牙基础监听
     */
    private LocalBluetoothListener bluetoothListener = new LocalBluetoothListener() {


        @Override
        public void onBluetoothEnable(boolean b) {

        }

        @Override
        public void onBluetoothDisable(boolean b) {

        }

        @Override
        public void onScanStart() {
            Intent intent = new Intent();
            intent.setAction(BLE_DEFAULT);
            intent.putExtra(BLE_MSG, getString(R.string.connect_startseach));
            sendBroadcast(intent);
        }

        @Override
        public void onScanning(RemoteBluetooth remoteBluetooth, int i, byte[] bytes) {
            if (remoteBluetooth != null) {
                LogUtil.logErrorMessage(remoteBluetooth.getName());
                if (remoteBluetooth.getName().contains(CommondConfig.TEMP_NAME)) {
                    LocalBluetooth.connect(remoteBluetooth);
                    remoteBleDevice = remoteBluetooth;
                    //   remoteBluetooths.add(remoteBluetooth);
                    // LocalBluetooth.stopScan();
                    Intent intent = new Intent();
                    intent.setAction(BLE_FIND);
                    intent.putExtra(BLE_MSG, getString(R.string.preparing_connection));
                    sendBroadcast(intent);
                }
            }
        }

        @Override
        public void onScanStop() {
        }

        @Override
        public void onConnected(RemoteBluetooth remoteBluetooth, boolean b) {
            LogUtil.logErrorMessage(b + "");
            if (b) {
                Intent intent = new Intent();
                intent.setAction(BLE_CONNET_SUCCEDD);
                intent.putExtra(BLE_MSG, getString(R.string.connect_read_data));
                sendBroadcast(intent);
                if (!isSucceed) {
                    isSucceed = true;
                }
            } else {
                Intent intent = new Intent();
                intent.setAction(BLE_DEFAULT);
                intent.putExtra(BLE_MSG, getString(R.string.connect_temp_fail));
                sendBroadcast(intent);
                isSucceed = false;
            }
        }

        @Override
        public void onDisconnected(RemoteBluetooth remoteBluetooth, boolean b) {
            if (b) {
                Intent intent = new Intent();
                intent.setAction(BLE_DEFAULT);
                intent.putExtra(BLE_MSG, getString(R.string.connect_temp_fail));
                sendBroadcast(intent);
                isSucceed = false;
                LocalBluetooth.connect(remoteBluetooth);
            }
        }
    };

    /**
     * 蓝牙业务实现
     */
    private RemoteBluetoothResponder remoteBluetoothResponder = new RemoteBluetoothResponder() {
        @Override
        public void onReceiveDegree(StateCode stateCode, RemoteBluetooth remoteBluetooth, int value) {
            if (stateCode == StateCode.SUCCESS) {

                if (value > max) {
                    max = value;
                }
                if (min == 0) {
                    min = value;
                } else {
                    if (value < min) {
                        min = value;
                    }
                }

                float heatVal = TApplication.sp.get(SP_kEY, DEFAULT_TEMP);
                if (heatVal * 100 <= (float) value) {
                    playSound();
                    //  String tempStr = value + "";
                    //  notifyTemp("体温贴", "当前体温", tempStr.length() > 2 ? tempStr.substring(0, 2) + "." + tempStr.substring(2, tempStr.length()) : tempStr);
                }

                LogUtil.logErrorMessage(value + "____________");
                Intent intent = new Intent();
                intent.setAction(BLE_DATA);
                intent.putExtra(BLE_MSG, value + "," + max + "," + min);
                sendBroadcast(intent);

            }
        }

        @Override
        public void onReadBattery(StateCode stateCode, RemoteBluetooth remoteBluetooth, int level) {

        }

        @Override
        public void onWriteStartOffline(StateCode stateCode, RemoteBluetooth remoteBluetooth, boolean isCompleted) {

        }

        @Override
        public void onWriteOfflineData(StateCode stateCode, RemoteBluetooth remoteBluetooth, boolean isCompleted) {
            RemoteBluetoothRequestor.writeOfflineData(remoteBluetooth,
                    TIMEOUT);
        }

        @Override
        public void onReceiveData(StateCode stateCode, RemoteBluetooth remoteBluetooth, byte[] data) {
            //  DataConverter.bytesToHexString(data);
        }

        @Override
        public void onSetIndicateOfDegree(StateCode stateCode, RemoteBluetooth remoteBluetooth, boolean enable, boolean isCompleted) {

        }

        @Override
        public void onSetNotification(StateCode stateCode, RemoteBluetooth remoteBluetooth, boolean enable, boolean isCompleted) {

        }
    };


    /**
     * 开始扫描并且连接
     */
    public void runRecevice() {
        scanCount = 0;
        LocalBluetooth.startScan();
        LocalBluetooth.registerBluetoothResponder(remoteBluetoothResponder);
        /**
         * sdk扫描默认为6秒，这里30秒没有扫描到设备，重新扫描
         */
        scanHandler = new Handler(Looper.myLooper());
        scanHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isSucceed) {
                    if (scanCount > 3) {
                        Intent intent = new Intent();
                        intent.setAction(BLE_NOT_FIND);
                        intent.putExtra(BLE_MSG, getString(R.string.no_search_ble));
                        sendBroadcast(intent);
                    } else {
                        LocalBluetooth.stopScan();
                        LocalBluetooth.startScan();
                        scanCount++;
                        scanHandler.postDelayed(this, 15 * 1000);
                    }
                }

            }
        }, 15 * 1000);
        startForeground(0,new Notification());
    }


    /**
     * 通过界面回调服务
     */
    public class TempBind extends Binder {
        public TempBind() {
        }

        public TempService getService() {
            return TempService.this;
        }
    }


    public boolean isInterrupt() {
        return isInterrupt;
    }

    public void setInterrupt(boolean interrupt) {
        isPlaySound = interrupt;
        isInterrupt = interrupt;
    }


    /**
     * 播放提醒声音
     */
    public void playSound() {
        soundHanler = new Handler();
        addHanler(soundRunable);
    }

    private Thread soundTheread;

    /**
     * 线程执行播放
     *
     * @param runnable
     */
    public void addHanler(Runnable runnable) {
        soundTheread = new Thread(runnable);
        soundTheread.start();
    }

    public void stopSound() {
        setInterrupt(true);
    }

    /**
     * 播放声音runable
     */
    private Runnable soundRunable = new Runnable() {
        @Override
        public void run() {
            if (!isPlaySound) {
                soundpool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
                sourceid = soundpool.load(TempService.this, R.raw.beep, 0);
                int streamVolume = ((AudioManager) getSystemService(AUDIO_SERVICE)).getStreamVolume(3);
                try {
                    Thread.sleep(500);
                    isPlaySound = true;
                    //循环
                    for (int i = 0; i < 30; i++) {
                        if (!isInterrupt) {
                            soundpool.play(sourceid, (float) streamVolume, (float) streamVolume, 1, 0, 2.0f);
                            Thread.sleep(500);
                        }
                    }

                } catch (Exception e) {
                }
            }
        }
    };

    /**
     * 报警时通知栏显示
     *
     * @param ticker
     * @param title
     * @param content
     */
    private void notifyTemp(String ticker, String title, String content) {
        // mNotificationManager.cancel(0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                this).setSmallIcon(R.mipmap.icon_logo)
                .setContentTitle(title)
                .setContentText(content);
        mBuilder.setTicker(ticker);//第一次提示消息的时候显示在通知栏上
        //   mBuilder.setNumber(12);
        mBuilder.setAutoCancel(true);//自己维护通知的消失
        mBuilder.setDefaults(Notification.DEFAULT_ALL);
        //  mBuilder.setContent()
        //构建一个Intent
        Intent resultIntent = new Intent(this,
                TempActivity.class);
        //封装一个Intent
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                this, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        // 设置通知主题的意图
        mBuilder.setContentIntent(resultPendingIntent);
        //获取通知管理器对象
        mNotificationManager.notify(15869, mBuilder.build());

    }
}
