package com.tentinet.healthy.activity;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.btsdk.LocalBluetooth;
import com.tentinet.healthy.btsdk.RemoteBluetoothRequestor;
import com.tentinet.healthy.interf.OpenBleListener;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.service.BleManagerService;
import com.tentinet.healthy.service.BroadcastActions;
import com.tentinet.healthy.service.TempService;
import com.tentinet.healthy.util.BleUtil;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.PageLoadIng;

/**
 * 体温贴测量页面
 * TODO
 * Author YKK
 * Date 2016/5/11 13:51
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class TempActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_connect;

    private TitleView view_title;

    private PageLoadIng pageLoadIng;

    private TextView txt_temp_max, txt_temp_min, txt_temp_current, txt_heat_alter;


    private LinearLayout lin_parent;

    private boolean isConneted = false;

    //选择高温的回调
    private IheatTemp listener = new IheatTemp() {
        @Override
        public void onClick(int val) {
            txt_heat_alter.setText(((float) val) / 10.0f + "");
        }
    };

    @Override
    protected void getData() {

    }

    @Override
    protected int setContent() {
        return R.layout.activity_temp;
    }

    /**
     * 广播过滤
     *
     * @return
     */
    private IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TempService.BLE_DEFAULT);
        intentFilter.addAction(TempService.BLE_FIND);
        intentFilter.addAction(TempService.BLE_CONNET_SUCCEDD);
        intentFilter.addAction(TempService.BLE_DATA);
        intentFilter.addAction(TempService.BLE_NOT_FIND);
        return intentFilter;
    }

    @Override
    protected void init() {
        btn_connect = (Button) findViewById(R.id.btn_connect);
        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setBackImageButton();
        view_title.setTitle(R.string.txt_window_ble_temp);
        pageLoadIng = (PageLoadIng) findViewById(R.id.page_load);
        pageLoadIng.setTvMsg(R.string.connect_startseach);
        pageLoadIng.setVisibility(View.GONE);
        txt_temp_max = (TextView) findViewById(R.id.txt_temp_max);
        txt_temp_min = (TextView) findViewById(R.id.txt_temp_min);
        txt_temp_current = (TextView) findViewById(R.id.txt_temp_current);
        txt_heat_alter = (TextView) findViewById(R.id.txt_heat_alter);
        txt_heat_alter.setText(TApplication.sp.get(SP_kEY, TempService.tempService.DEFAULT_TEMP) + "");
        lin_parent = (LinearLayout) findViewById(R.id.lin_parent);
        registerReceiver(tempReceiver, makeGattUpdateIntentFilter());
        doBaseBleRun();
    }


    @Override
    protected void registerEvent() {
        btn_connect.setOnClickListener(this);
        txt_heat_alter.setOnClickListener(this);
    }

    //shared保存高温值的 key
    private String SP_kEY = "HEATTEIP";

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_connect:
                TempService.tempService.runRecevice();
                break;
            case R.id.txt_heat_alter:
                CustomDialog.showHeatTemp(this, SP_kEY, listener);
                break;
        }
    }

    private BroadcastReceiver tempReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, final Intent intent) {
            final String action = intent.getAction();
            if (TempService.BLE_DEFAULT.equals(action)) {
                pageLoadIng.setTvMsg(intent.getExtras().get(TempService.BLE_MSG).toString());
            } else if (TempService.BLE_FIND.equals(action)) {
                pageLoadIng.setTvMsg(intent.getExtras().get(TempService.BLE_MSG).toString());
            } else if (TempService.BLE_CONNET_SUCCEDD.equals(action)) {
                if (TempService.remoteBleDevice != null) {
                    pageLoadIng.setTvMsg(intent.getExtras().get(TempService.BLE_MSG).toString());
                    RemoteBluetoothRequestor.setIndicateOfDegree(TempService.remoteBleDevice, true, TempService.TIMEOUT);
                }
            } else if (TempService.BLE_DATA.equals(action)) {
                CustomDialog.dismissDialog();
                showTempVal(intent.getExtras().get(TempService.BLE_MSG).toString());
            } else if (TempService.BLE_NOT_FIND.equals(action)) {
                pageLoadIng.noData(intent.getExtras().get(TempService.BLE_MSG).toString());
                pageLoadIng.setMsgListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pageLoadIng.showPageLoad(intent.getExtras().get(TempService.BLE_MSG).toString());
                        TempService.tempService.runRecevice();
                    }
                });
            }
        }
    };

    //开始连接设备
    private void start() {
        pageLoadIng.setVisibility(View.VISIBLE);
        if (!TempService.isSucceed) {
            if (TempService.tempService == null)
                finish();
            else {
                TempService.tempService.runRecevice();
            }
        } else {
            pageLoadIng.setTvMsg(R.string.connect_read_data);

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isConneted) {
            unregisterReceiver(tempReceiver);
        }
    }

    //高温值
    private float heatVal;

    /**
     * 展示获取到的体温
     *
     * @param tempVal
     */
    private void showTempVal(String tempVal) {
        if (StringUtil.isEmpty(tempVal)) {
            return;
        }
        if (tempVal.contains(",")) {
            String[] arr = tempVal.split(",");
            String max = "", min = "", current = "";
            current = arr[0];
            max = arr[1];
            min = arr[2];
            if (current.length() > 2) {
                current = current.substring(0, 2) + "." + current.substring(2, current.length());
                txt_temp_current.setText(getString(R.string.ear_temperature) + current);
                heatVal = TApplication.sp.get(SP_kEY, TempService.tempService.DEFAULT_TEMP);
                //当高于警报值，提醒
                if (heatVal <= Float.parseFloat(current)) {
                    // playSound();
                    txt_temp_current.setText(getString(R.string.ear_temperature) + current + " (" + getString(R.string.more_hightemp) + ")");
                }
            }
            if (max.length() > 2) {
                max = max.substring(0, 2) + "." + max.substring(2, max.length());
                txt_temp_max.setText(getString(R.string.ear_maxtemperature) + max);
            }
            if (min.length() > 2) {
                min = min.substring(0, 2) + "." + min.substring(2, min.length());
                txt_temp_min.setText(getString(R.string.ear_mintemperature) + min);
            }
        }
        pageLoadIng.noData(getString(R.string.bluetooth_con_succ));
        lin_parent.setVisibility(View.VISIBLE);
        isConneted = true;
        view_title.setRightButton(R.string.bluetooth_close, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConneted) {
                    //断开时候
                    pageLoadIng.noData(getString(R.string.bluetooth_close_msg));
                    view_title.getRightButton().setText(getString(R.string.bluetooth_con_msg));
                    unregisterReceiver(tempReceiver);
                    //停止播放
                    TempService.tempService.stopSound();
                    isConneted = false;
                } else {
                    //连接时候
                    CustomDialog.showWaitDialog(TempActivity.this, R.string.connect_read_data);
                    view_title.getRightButton().setText(getString(R.string.bluetooth_close));
                    registerReceiver(tempReceiver, makeGattUpdateIntentFilter());
                    //初始化中断值
                    TempService.tempService.setInterrupt(false);
                    isConneted = true;
                }

            }
        });
    }

    /**
     * 高温警报dialog回调接口
     */
    public interface IheatTemp {
        public void onClick(int val);
    }

    /**
     * 判断支持蓝牙设备
     */
    private void doBaseBleRun() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 检查当前手机是否支持ble 蓝牙,如果不支持退出程序
                Looper.prepare();
                BleUtil.checkBleBaseService(TempActivity.this, openBleListener);
            }
        }).start();
    }

    /**
     * 蓝牙操作的回调(DataTab页面)
     */
    private OpenBleListener openBleListener = new OpenBleListener() {
        @Override
        public void onNoSupport() {
            ToastUtil.showLongToast(TempActivity.this, "unsuported ble");
            finish();
        }

        @Override
        public void onNoOpen() {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, DeviceMeasureAcvitiy.REQUEST_ENABLE_BT);
        }

        @Override
        public void onOpen() {
            start();
        }


    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (DeviceMeasureAcvitiy.REQUEST_ENABLE_BT == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                start();
            } else {
                /** 用户未同意开启蓝牙，或蓝牙打开错误 */
                ToastUtil.showLongToast(this, "The bluetooth is not enable");
                finish();
            }
        }
    }


}
