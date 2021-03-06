package com.tentinet.healthy.view;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.activity.BleDeviceInfoActivity;
import com.tentinet.healthy.activity.EcgActivity;
import com.tentinet.healthy.activity.PodActivity;
import com.tentinet.healthy.adapter.BleDeviceListAdapter;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.config.CommondConfig;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.service.BroadcastActions;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.widget.PageLoadIng;

import java.util.ArrayList;

/**
 * 测量视图.
 *
 * @author paladin.
 */
public class MeasurementView extends LinearLayout {

    /**
     * 上下文环境.
     */
    private Context context;

    /**
     * 蓝牙设备listview
     */
    private ListView bleDeviceListView;

    /**
     * 蓝牙设备列表适配器
     */
    private BleDeviceListAdapter deceiveAdapter;

    /**
     * 是否正在扫描
     */
    private boolean isScaning = false;


    private int tagetType;

    private ArrayList<DeviceBean> tmpDevices = new ArrayList<DeviceBean>();
    /**
     * 绑定过的设备
     */
    private ArrayList<DeviceBean> bindlist = new ArrayList<>();
    /**
     * 设备类别
     */
    private int measure_type;

    /**
     * 加载控件
     */
    private PageLoadIng pageLoadIng;
    /**
     * biz
     */
    UserBiz userBiz;
    /**
     * 设备
     */
    DeviceBean device;

    private String device_id;

    public MeasurementView(Context context, int measureType) {
        super(context);
        init(context, measureType);
    }

    public MeasurementView(Context context, AttributeSet attrs, int measureType) {
        super(context, attrs);
        init(context, measureType);
    }

    public MeasurementView(Context context, AttributeSet attrs, int defStyleAttr, int measureType) {
        super(context, attrs, defStyleAttr);
        init(context, measureType);
    }

    /**
     * 初始化.
     *
     * @param context 上下文环境.
     */
    private void init(final Context context, int measureType) {
        this.context = context;
        this.measure_type = measureType;
        tagetType = this.measure_type;
        this.addView(LayoutInflater.from(context).inflate(R.layout.item_measurement, null), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        this.bleDeviceListView = (ListView) findViewById(R.id.measure_list);
        deceiveAdapter = new BleDeviceListAdapter(context, tmpDevices);
        pageLoadIng = (PageLoadIng) findViewById(R.id.measure_load);
        pageLoadIng.setTvMsg(context.getString(R.string.scanning_for_bluetooth));
        pageLoadIng.setVisibility(View.GONE);
        bleDeviceListView.setAdapter(deceiveAdapter);
        userBiz = new UserBiz();
        //  注册广播监听器
        context.registerReceiver(mGattUpdateReceiver, updateDeviceIntentFilter());

        bleDeviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                device = deceiveAdapter.getDevice(position);
                if (device == null)
                    return;
                //停止扫描
                scanBleDevice(false);
                //查看设备详情
                if (!StringUtil.isEmpty(device.getDeviceName())) {
                    if (device.isBind()) {
                        if (device.getDeviceName().trim().contains("POD")) {
                            Bundle bundle = new Bundle();
                            bundle.putString(BleDeviceInfoActivity.EXTRAS_DEVICE_NAME, device.getDeviceName());
                            bundle.putString(BleDeviceInfoActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
                            IntentUtil.gotoActivity(context, PodActivity.class, bundle);
                        } else if (device.getDeviceName().trim().contains("PC80B")) {
                            IntentUtil.gotoActivity(context, EcgActivity.class);
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putString(BleDeviceInfoActivity.EXTRAS_DEVICE_NAME, device.getDeviceName());
                            bundle.putString(BleDeviceInfoActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
                            IntentUtil.gotoActivity(context, BleDeviceInfoActivity.class, bundle);
                        }
                    } else {
                        //没有绑定，调用绑定
                        asynProcessing.starAsyn();
                    }
                }
            }
        });
        pageLoadIng.messageAndProgressGone();
        getMineDeviceAsyn.starAsyn();
    }

    /**
     * 开始或停止扫描设备
     *
     * @version 1.0
     * @createTime 2015/12/28  15:56
     * @updateTime 2015/12/28  15:56
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public void scanBleDevice(final boolean enable) {
        if (enable) {
            isScaning = true;
            Intent intent = new Intent(BroadcastActions.ACTION_START_LE_SCAN);
            context.sendBroadcast(intent);
        } else {
            isScaning = false;
            Intent intent = new Intent(BroadcastActions.ACTION_STOP_LE_SCAN);
            context.sendBroadcast(intent);
        }
    }

    /**
     * 广播过滤器
     *
     * @version 1.0
     * @createTime 2015/12/28  15:57
     * @updateTime 2015/12/28  15:57
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    private static IntentFilter updateDeviceIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BroadcastActions.ACTION_UPDATE_DEVICES);
        intentFilter.addAction(BroadcastActions.ACTION_BLEMANAGER_INIT);
        return intentFilter;
    }

    /**
     * 只接收设备更新的广播
     *
     * @version 1.0
     * @createTime 2015/12/28  15:55
     * @updateTime 2015/12/28  15:55
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BroadcastActions.ACTION_UPDATE_DEVICES.equals(action)) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getExtras().get(BroadcastActions.UPDATE_DEVICES);
                int rssi = intent.getExtras().getInt(BroadcastActions.VALUE_RSSI);
                DeviceBean bean = new DeviceBean();
                bean.setAddress(bluetoothDevice.getAddress());
                bean.setDeviceName(bluetoothDevice.getName());
                deceiveAdapter.addDevice(bean, rssi);
                if (!StringUtil.isEmpty(bluetoothDevice.getName())) {
                    changeDevice(tagetType);
                }
            } else if (BroadcastActions.ACTION_BLEMANAGER_INIT.equals(action)) {
                scanBleDevice(true);
            }
        }
    };

    /**
     * 退出页面时候
     */
    public void destroy() {
        if (null != mGattUpdateReceiver) {
            //停止扫描
            scanBleDevice(false);
            Intent intent = new Intent(BroadcastActions.ACTION_CLOSE_GATT);
            context.sendBroadcast(intent);
            context.unregisterReceiver(mGattUpdateReceiver);
        }
    }

    /**
     * 扫描到设备以后，进行筛选
     *
     * @param index
     */
    public void changeDevice(int index) {
        if (!isScaning) {
            scanBleDevice(true);
        }
        tagetType = index;
        switch (index) {
            case DataBean.TYPE_BLOOD_PRESSURE_MONITOR:
                deceiveAdapter.tmpDevice = CommondConfig.BLOOD_NAME;
                break;
            case DataBean.TYPE_GLUCOSE_METER:
                deceiveAdapter.tmpDevice =CommondConfig.GLUCOSE_NAME;
                break;
            case DataBean.TYPE_BLE_OPD:
                deceiveAdapter.tmpDevice = CommondConfig.POD_NAME;
                break;
            case DataBean.TYPE_BLE_ECG:
                deceiveAdapter.tmpDevice = CommondConfig.ECG_NAME;
                break;
            case DataBean.TYPE_BLE_EAR:
                deceiveAdapter.tmpDevice = CommondConfig.EAR_THERMOMETER_NAME;
                break;
            case DataBean.TYPE_BLE_BTEMP:
                deceiveAdapter.tmpDevice = CommondConfig.TEMP_NAME;
                break;
        }
        tmpDevices.clear();
        for (DeviceBean deviceBean : bindlist) {
            if (deviceBean.getType() == tagetType) {
                device_id = deviceBean.getDevice_id();
                break;
            }
        }
        for (DeviceBean device : deceiveAdapter.getList_Device()) {
            if (!StringUtil.isEmpty(device.getDeviceName())) {
                if (device.getDeviceName().trim().contains(deceiveAdapter.tmpDevice)) {
                    //在绑定的设备列表中进行对比，如果mac地址相同为绑定
                    for (DeviceBean deviceBean : bindlist) {
                        if (deviceBean.getAddress().equals(device.getAddress())) {
                            device.setBind(true);
                            device.setDevice_id(deviceBean.getDevice_id());
                            break;
                        }
                    }
                    tmpDevices.add(device);

                }
            }
        }
        if (tmpDevices.size() > 0) {
            pageLoadIng.messageAndProgressGone();
        } else {
            pageLoadIng.showPageLoad(context.getString(R.string.scanning_for_bluetooth));
        }
        bleDeviceListView.setAdapter(deceiveAdapter);
    }

    public void clearDevice() {
        deceiveAdapter.clear();
        tmpDevices.clear();
    }

    public void showLoadSanBle() {
        pageLoadIng.setVisibility(View.VISIBLE);
    }

    /**
     * 没有扫描到对应的蓝牙设备（60秒以后）
     */
    private void scanNoBle() {
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (tmpDevices.size() == 0) {
                    pageLoadIng.noData(context.getString(R.string.no_search_ble));
                    scanBleDevice(false);
                }
            }
        }.sendEmptyMessageDelayed(0, 60 * 1000);
        pageLoadIng.setMsgListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                scanBleDevice(true);
                pageLoadIng.showPageLoad(context.getString(R.string.scanning_for_bluetooth));
                scanNoBle();
            }
        });
    }

    /**
     * 绑定设备
     */
    private AsynProcessing asynProcessing = new AsynProcessing() {
        @Override
        protected void before() {
            if (StringUtil.isEmpty(device.getAddress())) {
                ToastUtil.showLongToast(context, context.getString(R.string.bound_error_msg));
                return;
            }

            CustomDialog.showWaitDialog(context);
        }

        @Override
        protected Object AsynTask() {
            return userBiz.bindDevice(TApplication.user.getPhone(), measure_type + "", device.getAddress());
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, context, true);
            if (response.isSuccess()) {
                device.setBind(true);
                tmpDevices.clear();
                tmpDevices.add(device);
                bleDeviceListView.setAdapter(deceiveAdapter);
                if (device.getDeviceName().trim().contains(CommondConfig.POD_NAME)) {
                    Bundle bundle = new Bundle();
                    bundle.putString(BleDeviceInfoActivity.EXTRAS_DEVICE_NAME, device.getDeviceName());
                    bundle.putString(BleDeviceInfoActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
                    IntentUtil.gotoActivity(context, PodActivity.class, bundle);
                } else if (device.getDeviceName().trim().contains(CommondConfig.ECG_NAME)) {
                    IntentUtil.gotoActivity(context, EcgActivity.class);
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString(BleDeviceInfoActivity.EXTRAS_DEVICE_NAME, device.getDeviceName());
                    bundle.putString(BleDeviceInfoActivity.EXTRAS_DEVICE_ADDRESS, device.getAddress());
                    IntentUtil.gotoActivity(context, BleDeviceInfoActivity.class, bundle);
                }

            }
            CustomDialog.dismissDialog();
        }
    };

    /**
     * 请求我的设备异步.
     */
    private AsynProcessing getMineDeviceAsyn = new AsynProcessing() {

        @Override
        protected void before() {
            CustomDialog.showWaitDialog(context);
        }

        @Override
        protected Object AsynTask() {
            return userBiz.getUserDeviceList(TApplication.user.getPhone());
        }

        @Override
        protected void after(Object obj) {
            CustomDialog.dismissDialog();
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, context, false);
            if (response.isSuccess()) {
                bindlist.addAll((ArrayList<DeviceBean>) response.getData());
            }
            // 开始扫描设备
            scanBleDevice(true);
            scanNoBle();
            pageLoadIng.showPageLoad(context.getString(R.string.scanning_for_bluetooth));
        }

    };

    public String getDevice_id() {
        return device_id;
    }
}
