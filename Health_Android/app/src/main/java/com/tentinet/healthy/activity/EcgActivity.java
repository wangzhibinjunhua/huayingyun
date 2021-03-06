package com.tentinet.healthy.activity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.creative.ecg.ecg.StatusMsg;
import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.ecg.BackGround;
import com.tentinet.healthy.ecg.ConnectActivity;
import com.tentinet.healthy.ecg.DrawThreadPC80B;
import com.tentinet.healthy.ecg.MyBluetoooth;
import com.tentinet.healthy.ecg.ReceiveService;
import com.tentinet.healthy.ecg.StaticReceive;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.DateUtil;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
/**
 * 心电仪测量页面
 * TODO
 * Author YKK
 * Date 2016/5/11 13:51
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class EcgActivity extends BaseActivity {

    /**
     * PC80B绘图的View
     */
    private DrawThreadPC80B drawRunable;

    private BackGround drawBG;

    /**
     * 绘图线程
     */
    private Thread drawThread;

    private TextView tv_Gain, tv_HR, tv_MSG;

    private ImageView img_Battery, img_Smooth, img_Pulse;

    /**
     * 心电测量结果
     */
    private String[] measureResult;

    /**
     * 电量等级
     */
    private int batteryRes[] = {R.mipmap.battery_0, R.mipmap.battery_1,
            R.mipmap.battery_2, R.mipmap.battery_3};


    private DataBean bean;


    private String ecgResult;
    PowerManager powerManager = null;
    PowerManager.WakeLock wakeLock = null;
    @Override
    protected void getData() {

    }

    @Override
    protected int setContent() {
        return R.layout.activity_ecg;
    }

    @Override
    protected void init() {
        powerManager = (PowerManager)this.getSystemService(this.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
        startService(new Intent(this, ReceiveService.class));
        measureResult = getResources().getStringArray(R.array.ecg_measureres);
        tv_MSG = (TextView) findViewById(R.id.main_pc80B_MSG);
        tv_Gain = (TextView) findViewById(R.id.main_pc80B_title_gain);
        tv_HR = (TextView) findViewById(R.id.main_pc80B_title_hr);

        img_Battery = (ImageView) findViewById(R.id.main_pc80B_title_battery);
        img_Smooth = (ImageView) findViewById(R.id.main_pc80B_title_smooth);
        img_Pulse = (ImageView) findViewById(R.id.main_pc80B_title_pulse);

        drawRunable = (DrawThreadPC80B) findViewById(R.id.main_pc80B_view_draw);
        drawBG = (BackGround) findViewById(R.id.main_pc80B_view_bg);
        drawRunable.setmHandler(mHandler);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ReceiveService.ACTION_BLU_DISCONNECT);
        registerReceiver(receiver, filter);

        Intent i = new Intent(this, ConnectActivity.class);
        i.putExtra("device", 3);
        startActivityForResult(i, 0x100);
        userBiz = new UserBiz();
        bean = new DataBean();
    }


    @Override
    protected void registerEvent() {

    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ReceiveService.ACTION_BLU_DISCONNECT)) {
                Toast.makeText(EcgActivity.this, R.string.connect_connect_off,
                        Toast.LENGTH_SHORT).show();
            }
        }
    };

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case StaticReceive.MSG_DATA_BATTERY: {
                    setBattery(msg.arg1);
                }
                break;
                case BATTERY_ZERO: {// 电池电量为0时的消息
                    if (img_Battery.isShown()) {
                        img_Battery.setVisibility(View.INVISIBLE);
                    } else {
                        img_Battery.setVisibility(View.VISIBLE);
                    }
                    mHandler.sendEmptyMessageDelayed(BATTERY_ZERO, 500);
                }
                break;
                case StaticReceive.MSG_DATA_ECG_STATUS_CH: {
                    switch (msg.arg1) {
                        case StatusMsg.FILE_TRANSMIT_START: {// 接收文件
//                            Vector<Integer> data =( Vector<Integer>) msg.obj;
//                            for (Integer integer :data){
//                                LogUtil.logMessage("FILE",integer+"");
//                            }
                            setMSG(getResources().getString(
                                    R.string.measure_ecg_file_ing));
                        }
                        break;
                        case StatusMsg.FILE_TRANSMIT_SUCCESS: {
//                            Vector<Integer> data =( Vector<Integer>) msg.obj;
//                            for (Integer integer :data){
//                                LogUtil.logMessage("FILE",integer+"");
//                            }
                            setMSG(getResources().getString(
                                    R.string.measure_ecg_file_end));
                        }
                        break;
                        case StatusMsg.FILE_TRANSMIT_ERROR: {
                            setMSG(getResources().getString(
                                    R.string.measure_ecg_time_err));
                        }
                        break;
                        case StaticReceive.MSG_DATA_TIMEOUT: {
                            setMSG(getResources().getString(
                                    R.string.measure_ecg_time_out));
                        }
                        break;
                        case 4: {// 准备阶段波形
                            if (drawRunable.isPause()) {
                                drawRunable.Continue();
                            }
                            Bundle data = msg.getData();
                            if (data.getBoolean("bLeadoff")) {
                                setMSG(getResources().getString(
                                        R.string.measure_lead_off));
                            } else {
                                setMSG(" ");
                            }
                            setGain(data.getInt("nGain"));
                        }
                        break;
                        case 5: {// 实时测量波形
                            if (drawRunable.isPause()) {
                                drawRunable.Continue();
                            }
                            Bundle data = msg.getData();
                            if (data.getBoolean("bLeadoff")) {
                                setMSG(getResources().getString(
                                        R.string.measure_lead_off));
                            } else {
                                setMSG(" ");
                            }
                            data.getInt("nTransMode");
                            setHR(data.getInt("nHR"));
                            setGain(data.getInt("nGain"));
                        }
                        break;
                        case 6: {// 测量结果
                            Bundle data = msg.getData();
                            nTransMode = data.getInt("nTransMode");
                            String time = data.getString("time");
                            if (nTransMode == StatusMsg.TRANSMIT_MODE_QUICK
                                    && time != null) {
                                setMSG(measureResult[data.getInt("nResult")]);
                                ecgResult=data.getInt("nResult")+"";
                            } else {
                                setMSG("");
                            }
                            TApplication.sp.set("ECG", drawRunable.getStringBuilder().toString());
                            drawRunable.cleanWaveData();
                            drawRunable.Pause();
                            setGain(0);
                            setHR(data.getInt("nHR"));
                            setSmooth(false);
                            bean.setBloodPulse(data.getInt("nHR") + "");
                            if (StringUtil.isEmpty(ecgResult)||ecgResult.equals("11")||ecgResult.equals("16")||ecgResult.equals("17")){
                                return ;
                            }
                            asynProcessing.starAsyn();
                        }
                        break;
                        case 7: {// 传输设置
                            int nSmoothingMode = msg.arg2;// 滤波模式
                            nTransMode = (Integer) msg.obj;// 传输模式
                            if (nTransMode == StatusMsg.TRANSMIT_MODE_FILE) {
                                setMSG(getResources().getString(
                                        R.string.measure_ecg_file_ing));
                            } else if (nTransMode == StatusMsg.TRANSMIT_MODE_CONTINUOUS) {
                                setMSG("");
                                setSmooth(nSmoothingMode == StatusMsg.SMOOTHMODE_ENHANCE);
                            }
                        }
                        break;
                    }
                }
                break;
                case StaticReceive.MSG_DATA_PULSE: {
                    showPulse(true);
                }
                break;
                case RECEIVEMSG_PULSE_OFF: {
                    showPulse(false);
                }
                break;
            }
        }

    };

    /**
     * 取消搏动标记
     */
    public static final int RECEIVEMSG_PULSE_OFF = 0x115;

    /**
     * 设置搏动标记
     */
    private void showPulse(boolean isShow) {
        if (isShow) {
            img_Pulse.setVisibility(View.VISIBLE);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHandler.sendEmptyMessage(RECEIVEMSG_PULSE_OFF);
                }
            }.start();
        } else {
            img_Pulse.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 当前数据传输模式
     */
    private int nTransMode = 0;

    @Override
    protected void onResume() {
        super.onResume();
        if (drawThread == null) {
            drawThread = new Thread(drawRunable, "DrawPC80BThread");
            drawThread.start();
        }
        if (drawRunable.isPause()) {
            drawRunable.Continue();
        }
        wakeLock.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (drawThread != null && !drawRunable.isPause()) {
            drawRunable.Pause();
        }
        wakeLock.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!drawRunable.isStop()) {
            drawRunable.Stop();
        }
        drawThread = null;
        unregisterReceiver(receiver);
        stopService(new Intent(this, ReceiveService.class));


        //  sendBroadcast(new Intent(ReceiveService.BLU_ACTION_DISCONNECT));


    }

    /**
     * 消息 电池电量为0
     */
    private static final int BATTERY_ZERO = 0x302;

    private void setBattery(int battery) {
        setImgResource(img_Battery, batteryRes[battery]);
        if (battery == 0) {
            if (!mHandler.hasMessages(BATTERY_ZERO)) {
                mHandler.sendEmptyMessage(BATTERY_ZERO);
            }
        } else {
            img_Battery.setVisibility(View.VISIBLE);
            mHandler.removeMessages(BATTERY_ZERO);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (MyBluetoooth.isConnected) {
            menu.add("断开连接");
        } else {
            menu.add("连接设备");
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (MyBluetoooth.isConnected) {
            sendBroadcast(new Intent(ReceiveService.BLU_ACTION_DISCONNECT));
        } else {
            Intent i = new Intent(this, ConnectActivity.class);
            i.putExtra("device", 3);
            startActivityForResult(i, 0x100);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x100) {
            StaticReceive.setmHandler(mHandler);
            //	StaticReceive.startReceive(MainPC80B.this, "PC80B", new BLEReader(), new BLESender(ble), _handler)
        }
    }

    /**
     * 设置滤波模式
     *
     * @param isVisible
     */
    private void setSmooth(boolean isVisible) {
        setImgVisible(img_Smooth, isVisible);
    }

    /**
     * 设置图片
     *
     * @param img
     * @param res
     */
    private void setImgResource(ImageView img, int res) {
        if (!img.isShown()) {
            img.setVisibility(View.VISIBLE);
        }
        img.setImageResource(res);
    }

    private void setImgVisible(ImageView img, boolean isVisible) {
        if (isVisible) {
            img.setVisibility(View.VISIBLE);
        } else {
            img.setVisibility(View.INVISIBLE);
        }
    }

    private void setGain(int gain) {
        System.out.println("setGain=" + gain);
        gain = gain <= 0 ? 2 : gain;
        setTVtext(tv_Gain, "x" + gain);
        drawRunable.setGain(gain);
        drawBG.setGain(gain);
    }

    private void setHR(int hr) {
        if (hr != 0)
            setTVtext(tv_HR, "HR=" + hr);
        else
            setTVtext(tv_HR, "HR=--");
    }

    private void setMSG(String msg) {
        setTVtext(tv_MSG, msg);
    }

    /**
     * 设置TextView显示的内容
     */
    private void setTVtext(TextView tv, String msg) {
        if (tv != null) {
            if (!tv.isShown()) {
                tv.setVisibility(View.VISIBLE);
            }
            if (msg != null && !msg.equals("")) {
                if (msg.equals("0")) {
                    tv.setText(getResources().getString(
                            R.string.const_data_nodata));
                } else {
                    tv.setText(msg);
                }
            }
        }
    }

    private UserBiz userBiz;
    public AsynProcessing asynProcessing = new AsynProcessing() {
        @Override
        protected void before() {

            CustomDialog.showWaitDialog(EcgActivity.this);
        }

        @Override
        protected Object AsynTask() {


            bean.setBlooding(drawRunable.getStringBuilder().toString());
            bean.setDate(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
            bean.setEcgResult(ecgResult);
            return userBiz.submitUserMeasurementData(TApplication.user.getPhone(), DataBean.TYPE_BLE_ECG + "", bean);
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            if (response.isSuccess()) {
                ToastUtil.showLongToast(EcgActivity.this, getString(R.string.blood_measurement_success));
            }
            LogUtil.logMessage("ykk", response.getInfo());
            CustomDialog.dismissDialog();
        }
    };

}
