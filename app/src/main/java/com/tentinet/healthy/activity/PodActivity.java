package com.tentinet.healthy.activity;

import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bde.parentcyTransport.ACSUtility;
import com.bde.parentcyTransport.ACSUtility.blePort;
import com.creative.FingerOximeter.FingerOximeter;
import com.creative.FingerOximeter.IFingerOximeterCallBack;
import com.creative.base.BLEReader;
import com.creative.base.BLESender;
import com.creative.base.BaseDate.Wave;
import com.creative.bluetooth.ble.BLEOpertion;
import com.creative.bluetooth.ble.IBLECallBack;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.DateUtil;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.TitleView;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class PodActivity extends BaseActivity {

    // private WifiAdmin wifiadmin;
    private BLEOpertion ble;
    private FingerOximeter pod;
    private TextView tvMsg;
    private TextView tvWave;
    private TitleView title_View;
    private Button btn_pod_submit;

    private String address;

    private String bloodSpO2, bloodPulse;

    DataBean bean;
    /**
     * 蓝牙地址
     */
    public static final String EXTRAS_DEVICE_ADDRESS = "deviceAddress";

    PowerManager powerManager = null;
    PowerManager.WakeLock wakeLock = null;

    @Override
    protected void getData() {
        address = getIntent().getExtras().getString(EXTRAS_DEVICE_ADDRESS);
        //  deviceAddress = getIntent().getExtras().getString(EXTRAS_DEVICE_ADDRESS);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_pod;
    }

    @Override
    protected void init() {
        powerManager = (PowerManager) this.getSystemService(this.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
        this.title_View = (TitleView) findViewById(R.id.pod_title);
        title_View.setTitle(R.string.bluetooth_oxygen);
        title_View.setBackImageButton(R.mipmap.icon_back_white);
        btn_pod_submit = (Button) findViewById(R.id.btn_pod_submit);
        try {
            ble = new BLEOpertion(this, new BleCallBack());
            tvMsg = (TextView) findViewById(R.id.para);
            tvWave = (TextView) findViewById(R.id.wave);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (NegativeArraySizeException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myHandler.obtainMessage(0, getString(R.string.preparing_connection)).sendToTarget();
                    //等待service回调后开始连接
                    Thread.sleep(1000);
                    ble.connect(address);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myHandler.obtainMessage(0, getString(R.string.start_connection)).sendToTarget();

            }
        }).start();

        bean = new DataBean();
        userBiz = new UserBiz();
    }

    @Override
    protected void registerEvent() {
        tvMsg.setText(R.string.pod_result);
        btn_pod_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StringUtil.isEmpty(bloodSpO2) || StringUtil.isEmpty(bloodPulse)) {
                    ToastUtil.showLongToast(PodActivity.this, getString(R.string.pod_msg));
                    return;
                }
                asynProcessing.starAsyn();
            }
        });
    }

    private Handler myHandler = new Handler() {

        @SuppressWarnings("unchecked")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0: {
                    tvMsg.setText((String) msg.obj);
                }
                break;
                case 1: {
                    List<Wave> wave = (List<Wave>) msg.obj;
                    String showText = "wave=";
                    for (Iterator<Wave> iterator = wave.iterator(); iterator
                            .hasNext(); ) {
                        Wave w = iterator.next();
                        showText += w.data + "---";
                    }
                    tvWave.setText(showText);
                }
                break;
                case 2: {
                    tvMsg.setText((String) msg.obj);
                    btn_pod_submit.setVisibility(View.VISIBLE);
                }
                break;
            }
        }
    };

    class BleCallBack implements IBLECallBack {

        @Override
        public void onFindDevice(final blePort port) {
            //通过扫描所有蓝牙筛选
//            System.out.println("onFindDevice " + port._device.getAddress()
//                    + "  " + port._device.getName() + " " + port.devInfo);
//            if (port._device.getName().trim().equals("POD")) {// 将POD修改为对应的设备名即可
//                ble.stopDiscover();
//                address = port._device.getAddress();
//                new Thread() {
//                    @Override
//                    public void run() {
//                        super.run();
//                        ble.connect(port);
//                    }
//                }.start();
//            }
        }

        @Override
        public void onConnected(blePort port) {
            ble.stopDiscover();
            System.out.println("onConnected");

            pod = new FingerOximeter(new BLEReader(ble), new BLESender(ble),
                    new FingerOximeterCallBack());
            pod.Start();
            pod.SetWaveAction(true);
        }

        @Override
        public void onConnectFail() {
            if (ble != null) {
                myHandler.obtainMessage(0, getString(R.string.connect_connect_off)).sendToTarget();
                if (pod != null)
                    pod.Stop();
                pod = null;
                bloodPulse = "";
                bloodSpO2 = "";
            }
        }

        @Override
        public void onSended(boolean isSend) {

        }

        @Override
        public void onDiscoveryCompleted(List<blePort> device) {
        }

        @Override
        public void onDisConnect(blePort prot) {
            if (ble != null) {
                if (pod != null) {
                    pod.Stop();
                    pod = null;
                }
                bloodPulse = "";
                bloodSpO2 = "";
                myHandler.obtainMessage(0, getString(R.string.connect_connect_off)).sendToTarget();

            }
        }

        @Override
        public void onReadyForUse() {
            System.out.println("onReadyForUse");
        }

    }

    class FingerOximeterCallBack implements IFingerOximeterCallBack {

        @Override
        public void OnGetSpO2Param(int nSpO2, int nPR, float nPI,
                                   boolean nStatus, int nMode, float nPower) {
            myHandler.obtainMessage(2,
                    getString(R.string.pod_oxygen) + ":" + nSpO2 + "  " + getString(R.string.heart_rate2) + ":" + nPR).sendToTarget();
            bloodSpO2 = nSpO2 + "";
            bloodPulse = nPR + "";
        }

        @Override
        public void OnGetSpO2Wave(List<Wave> wave) {
            myHandler.obtainMessage(1, wave).sendToTarget();
        }

        @Override
        public void OnGetDeviceVer(int nHWMajor, int nHWMinor, int nSWMajor,
                                   int nSWMinor) {

        }

        @Override
        public void OnConnectLose() {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pod != null) {
            pod.Stop();
            pod = null;
        }
        ble.closeACSUtility();
        ble.disConnect();
    }

    private UserBiz userBiz;
    public AsynProcessing asynProcessing = new AsynProcessing() {
        @Override
        protected void before() {
            CustomDialog.showWaitDialog(PodActivity.this);
        }

        @Override
        protected Object AsynTask() {
            bean.setBloodSpO2(bloodSpO2);
            bean.setBloodPulse(bloodPulse);
            bean.setDate(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
            return userBiz.submitUserMeasurementData(TApplication.user.getPhone(), DataBean.TYPE_BLE_OPD + "", bean);
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            if (response.isSuccess()) {
                ToastUtil.showLongToast(PodActivity.this, getString(R.string.blood_measurement_success));
            }
            CustomDialog.dismissDialog();
            finish();
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        wakeLock.acquire();
    }

    @Override
    protected void onPause() {
        super.onPause();
        wakeLock.release();
    }
}
