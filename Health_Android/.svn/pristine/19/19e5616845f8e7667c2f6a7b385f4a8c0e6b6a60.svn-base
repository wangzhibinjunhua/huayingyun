package com.tentinet.healthy.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.config.CommondConfig;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.service.BleManagerService;
import com.tentinet.healthy.service.BroadcastActions;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CommandBytesUtil;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.DataConverter;
import com.tentinet.healthy.util.DateUtil;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.util.MeasureResultUtil;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.TitleView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 类描述:蓝牙设备操作
 *
 * @author 王治粮
 * @date 2015/12/28,16:04
 * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class BleDeviceInfoActivity extends BaseActivity {
    /**
     * 蓝牙名字
     */
    private TextView text_deviceName;
    /**
     * 蓝牙地址
     */
    private TextView text_deviceAddress;
    /**
     * 连接状态
     */
    private TextView text_status;
    /**
     * 蓝牙名字
     */
    private String deviceName;
    /**
     * 蓝牙地址
     */
    private String deviceAddress;


    /**
     * 蓝牙所有服务适配器
     */

    private boolean mConnected = false;

    private ArrayList<String> resultList;


    private Button btn_write, btn_connet;

    private TextView txt_result;
    private boolean isresut = false;
    private TitleView title_View;
    private DataBean bean;
    UserBiz biz;
    StringBuilder stringBuilder = new StringBuilder();
    private LinearLayout inclue_blood_result, inclue_sugar_result;
    private TextView txt_date, txt_date1, txt_highPressure, txt_lowPressure, txt_heartRate,txt_measure_result,txt_blood_sugar_result,txt_blood_sugar_date;
    private TextView txt_bloodSugar;

    /**
     * 正在测量中，防止正在测量蓝牙突然断掉
     */
    private boolean isMeasureImg = false;


    private Handler mHandler;

    PowerManager powerManager = null;
    PowerManager.WakeLock wakeLock = null;

    @Override
    protected void getData() {
        deviceName = getIntent().getExtras().getString(EXTRAS_DEVICE_NAME);
        deviceAddress = getIntent().getExtras().getString(EXTRAS_DEVICE_ADDRESS);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_ble_info;
    }

    @Override
    protected void init() {
        powerManager = (PowerManager) this.getSystemService(this.POWER_SERVICE);
        wakeLock = this.powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Lock");
        biz = new UserBiz();
        text_deviceName = (TextView) findViewById(R.id.text_device_name);
        text_deviceAddress = (TextView) findViewById(R.id.text_device_address);
        text_status = (TextView) findViewById(R.id.text_connection_state);
        btn_write = (Button) findViewById(R.id.btn_write);
        btn_connet = (Button) findViewById(R.id.btn_connect);
        txt_result = (TextView) findViewById(R.id.txt_result);
        inclue_blood_result = (LinearLayout) findViewById(R.id.include_blood_result);
        inclue_sugar_result = (LinearLayout) findViewById(R.id.include_sugar_result);
        text_deviceName.setText(deviceName);
        text_deviceAddress.setText(deviceAddress);
        txt_date = (TextView) findViewById(R.id.txt_date);
        txt_highPressure = (TextView) findViewById(R.id.txt_high_pressure);
        txt_lowPressure = (TextView) findViewById(R.id.txt_low_pressure);
        txt_heartRate = (TextView) findViewById(R.id.txt_heart_rate);
        txt_measure_result=(TextView)findViewById(R.id.txt_measure_result);
        txt_blood_sugar_result=(TextView)findViewById(R.id.txt_blood_sugar_result) ;
        txt_blood_sugar_date=(TextView)findViewById(R.id.txt_blood_sugar_date) ;
        txt_date1 = (TextView) findViewById(R.id.txt_date1);
        txt_bloodSugar = (TextView) findViewById(R.id.txt_blood_sugar);
        this.title_View = (TitleView) findViewById(R.id.measure_title);
        title_View.setTitle(R.string.btn_measure);
        title_View.setBackImageButton(R.mipmap.icon_back_white);
        btn_write.setEnabled(false);
        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        mHandler = new Handler();
    }

    @Override
    protected void onDestroy() {
        Intent cintent = new Intent();
        cintent.setAction(BroadcastActions.ACTION_DISCONNECT);
        sendBroadcast(cintent);

        if (null != mGattUpdateReceiver) {
            unregisterReceiver(mGattUpdateReceiver);
        }
        super.onDestroy();
    }

    // @Override
    protected void registerEvent() {
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BleManagerService.characteristic != null && BleManagerService.service != null) {
                    if (!StringUtil.isEmpty(deviceName)) {
                        if (deviceName.contains(CommondConfig.EAR_THERMOMETER_NAME)) {
                            sendEarData(CommandBytesUtil.EAR_START);
                        } else {
                            sendData();
                        }
                    }
                } else {
                    ToastUtil.showLongToast(BleDeviceInfoActivity.this, getString(R.string.ble_error_msg));
                }
            }

        });
        btn_connet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConnected == false) {
                    connect();
                }
            }
        });
        connect();
    }

    //连接蓝牙
    private void connect() {
        Intent cintent = new Intent();
        cintent.setAction(BroadcastActions.ACTION_CONNECT);
        cintent.putExtra(BroadcastActions.EXTRA_NAME_DATA, deviceAddress);
        cintent.putExtra(BroadcastActions.EXTRA_DEVICENAME_DATA, deviceName);
        sendBroadcast(cintent);
    }

    /**
     * 广播接收-
     *
     * @version 1.0
     * @createTime 2015/12/29  9:42
     * @updateTime 2015/12/29  9:42
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BroadcastActions.ACTION_GATT_CONNECTED.equals(action)) {
                text_status.setText(R.string.connected);
                btn_write.setText(R.string.prepare_measure);
                mConnected = true;

            } else if (BroadcastActions.ACTION_GATT_DISCONNECTED.equals(action)) {
                text_status.setText(R.string.disconnected);
                btn_write.setEnabled(false);
                mConnected = false;
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        connect();
                    }
                };
                mHandler.postDelayed(runnable, 2000);

            } else if (BroadcastActions.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                displayService();
            } else if (BroadcastActions.ACTION_DATA_AVAILABLE.equals(action)) {
                if (deviceName.contains(CommondConfig.EAR_THERMOMETER_NAME)) {
                    earTmpData(intent);
                } else {
                    receiveData(intent);
                }
            }
        }
    };


    /**
     * 广播过滤
     *
     * @version 1.0
     * @createTime 2015/12/29  9:39
     * @updateTime 2015/12/29  9:39
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BroadcastActions.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BroadcastActions.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BroadcastActions.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BroadcastActions.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }

    /**
     * 将所有Service以及Characteristic添加列表，初始化适配器，更新列表
     *
     * @version 1.0
     * @createTime 2014-3-24,下午4:36:39
     * @updateTime 2014-3-24,下午4:36:39
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不�?.)
     */
    private void displayService() {
        if (!isMeasureImg) {
            btn_write.setEnabled(true);
            btn_write.setText(R.string.btn_measure);
        }

    }

    /**
     * 接收ble返回的数据(此处输入方法执行任务.)
     * <h3>Version</h3> 1.0
     * <h3>CreateTime</h3> ,
     * <h3>UpdateTime</h3> ,
     * <h3>CreateAuthor</h3> （你的姓名）
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3>
     */
    private void receiveData(Intent intent) {
        if (!isMeasureImg) {
            isMeasureImg = true;
            btn_write.setText(R.string.it_is_measured);
            btn_write.setEnabled(false);
        }
        String data = intent.getStringExtra(BroadcastActions.EXTRA_DATA);
        int[] ints = DataConverter.hexStr2Ints(data);
        if (ints != null) {
            //血压计结果包(暂定 结果包为3类别，长度为15，false区分有时候结果包会有多个，经过测试第一个结果包为本次测量)
            if (ints.length == 15 && isresut == false && ints[2] == 3&&deviceName.contains(CommondConfig.BLOOD_NAME)) {
                //  stringBuilder.append("时间：").append(ints[3] + "年").append(ints[4] + "月").append(ints[5] + "日").append(ints[6] + "时").append(ints[7] + "分");
                int total = 0;
                //当不为0时，9为高位，要补到低位前面（补位为hex）
                if (ints[9] != 0) {
                    total = Integer.parseInt(String.format("%02X", ints[9]) + String.format("%02X", ints[8]), 16);
                } else {
                    total = ints[8];
                }
                stringBuilder.append(getString(R.string.measure_blood_msg));
                isresut = true;
                bean = new DataBean();
                bean.setPulse(String.valueOf(ints[11]));
                bean.setSys(String.valueOf(total));
                bean.setDia(String.valueOf(ints[10]));
                bean.setType(DataBean.TYPE_BLOOD_PRESSURE_MONITOR);
                bean.setDate(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
              //  bean.setDate("20" + ints[3] + "-" + ints[4] + "-" + ints[5] + " " + ints[6] + ":" + ints[7] + ":30");
                submitProcessing.starAsyn();
                // 血糖仪(暂定 结果包为3类别，长度为14，false区分有时候结果包会有多个，经过测试第一个结果包为本次测量)
            } else if (ints.length == 14 && isresut == false && ints[2] == 3&&deviceName.contains(CommondConfig.GLUCOSE_NAME)) {
                //当不为0时，10为高位，要补到低位前面（补位为hex）
                int total = 0;
                if (ints[10] != 0) {
                    total = Integer.parseInt(String.format("%02X", ints[10]) + String.format("%02X", ints[9]), 16);
                } else {
                    total = ints[9];
                }
                float f = (float) (total / 18.0);//1mmol/L=18mg /DL 单位不同
                String mmol = String.format("%.1f", f);//保留一位小数
                //    stringBuilder.append("  血糖：" + mmol + "mmol/L");
                isresut = true;
                bean = new DataBean();
                bean.setBloodSugar(mmol);
                bean.setType(DataBean.TYPE_GLUCOSE_METER);
               // bean.setDate("20" + ints[3] + "-" + ints[4] + "-" + ints[5] + " " + ints[6] + ":" + ints[7] + ":30");
                bean.setDate(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
                sumbitSuger();
                // submitProcessing.starAsyn();
            }
            txt_result.setText(data);
            if (isresut) {
                txt_result.setText("");
                btn_write.setText(R.string.measure_complete);
                btn_write.setEnabled(false);
                if (bean != null) {
                    if (StringUtil.isEmpty(bean.getBloodSugar())) {
                        inclue_blood_result.setVisibility(View.VISIBLE);
                        txt_date.setText(bean.getDate());
                        txt_highPressure.setText(bean.getSys());
                        txt_lowPressure.setText(bean.getDia());
                        txt_heartRate.setText(bean.getPulse());
                        txt_measure_result.setText(MeasureResultUtil.getBloodResult(this,bean.getSys()));
                    } else {
                        inclue_sugar_result.setVisibility(View.VISIBLE);
                        txt_date1.setText(bean.getDate());
                        txt_bloodSugar.setText(bean.getBloodSugar());
                        txt_blood_sugar_result.setText(MeasureResultUtil.getSugarResult(this,bean.getBloodSugar()));
                        txt_blood_sugar_date.setText(bean.getBlooding());
                    }
                    txt_result.setText(stringBuilder.toString());
                }

            } else {
                //发送应答包
                if (data.contains("551000") || data.contains("550601")) {
                    sendData();
                }

            }


        }

    }

    /**
     * 发送耳温枪数据
     */
    private void sendEarData(String content) {
        byte commands[] = DataConverter.parseHexStringToBytes(content);
        Intent intent = new Intent();
        intent.setAction(BroadcastActions.ACTION_WRITE_TO_SERVICE);
        intent.putExtra(BroadcastActions.EXTRA_NAME_DATA, commands);
        sendBroadcast(intent);
        ToastUtil.showLongToast(BleDeviceInfoActivity.this, getString(R.string.please_start_temperature));
    }

    /**
     * 发送血压计和血糖仪数据
     */
    private void sendData() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                byte commands[] = DataConverter.parseHexStringToBytes(CommandBytesUtil.getCheckHexString());
                Intent intent = new Intent();
                intent.setAction(BroadcastActions.ACTION_WRITE_TO_SERVICE);
                intent.putExtra(BroadcastActions.EXTRA_NAME_DATA, commands);
                sendBroadcast(intent);

            }
        };
        mHandler.postDelayed(runnable, 100);

    }

    private AsynProcessing submitProcessing = new AsynProcessing() {
        @Override
        protected void before() {
            //CustomDialog.showWaitDialog(BleDeviceInfoActivity.this);
        }

        @Override
        protected Object AsynTask() {
            return biz.submitUserMeasurementData(TApplication.user.getPhone(), bean.getType() + "", bean);
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, BleDeviceInfoActivity.this, false);
            if (response.isSuccess()) {
                ToastUtil.showLongToast(BleDeviceInfoActivity.this, getString(R.string.blood_measurement_success));
            }
            LogUtil.logMessage("ykk", response.getInfo());
            CustomDialog.dismissDialog();

        }
    };

    /**
     * 蓝牙名字
     */
    public static final String EXTRAS_DEVICE_NAME = "deviceName";
    /**
     * 蓝牙地址
     */
    public static final String EXTRAS_DEVICE_ADDRESS = "deviceAddress";


    /**
     * 耳温枪展示数据
     *
     * @param intent
     */
    public void earTmpData(Intent intent) {
        String data = intent.getStringExtra(BroadcastActions.EXTRA_DATA);
        if (data.startsWith("FFFE08") && !isresut) {
            data = data.substring(12, 16);
            int[] ints = DataConverter.hexStr2Ints(data);
            if (ints != null && ints.length > 1) {
                float fl = (float) ((ints[0] + ints[1] * 256) / 10.0);
                LogUtil.logMessage("EAR", fl + "");
                txt_result.setText(getString(R.string.ear_temperature) + fl);
                btn_write.setText(R.string.measure_complete);
                btn_write.setEnabled(false);
                sendEarData(CommandBytesUtil.EAR_END);
                if (isresut == false) {
                    isresut = true;
                    DecimalFormat decimalFormat = new DecimalFormat(".00");
                    decimalFormat.format(fl);
                    bean = new DataBean();
                    bean.setDate(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
                    bean.setType(DataBean.TYPE_BLE_EAR);
                    bean.setEarTemperature(String.valueOf(fl));
                    submitProcessing.starAsyn();
                }

            }
        }

    }

    private TextView txt_dialog_emptiness, txt_dialog_breakfast_after, txt_dialog_lunch_front, txt_dialog_lunch_after, txt_dialog_dinner_front, txt_dialog_dinner_after, txt_dialog_slepp_front;
    private TextView tmpID;
    private Button btn_submit;

    /**
     * 提交血糖数据时候选择日期
     */
    private void sumbitSuger() {
        View sugerView = LayoutInflater.from(this).inflate(R.layout.dialog_check_suger, null);
        if (sugerView != null) {
            CustomDialog.showDefaultDialog(this, sugerView, false);
            txt_dialog_emptiness = (TextView) sugerView.findViewById(R.id.txt_dialog_emptiness);
            txt_dialog_breakfast_after = (TextView) sugerView.findViewById(R.id.txt_dialog_breakfast_after);
            txt_dialog_lunch_front = (TextView) sugerView.findViewById(R.id.txt_dialog_lunch_front);
            txt_dialog_lunch_after = (TextView) sugerView.findViewById(R.id.txt_dialog_lunch_after);
            txt_dialog_dinner_front = (TextView) sugerView.findViewById(R.id.txt_dialog_dinner_front);
            txt_dialog_dinner_after = (TextView) sugerView.findViewById(R.id.txt_dialog_dinner_after);
            txt_dialog_slepp_front = (TextView) sugerView.findViewById(R.id.txt_dialog_slepp_front);
            btn_submit = (Button) sugerView.findViewById(R.id.btn_ok);
            txt_dialog_emptiness.setOnClickListener(sugerListeren);
            txt_dialog_breakfast_after.setOnClickListener(sugerListeren);
            txt_dialog_lunch_front.setOnClickListener(sugerListeren);
            txt_dialog_lunch_after.setOnClickListener(sugerListeren);
            txt_dialog_dinner_front.setOnClickListener(sugerListeren);
            txt_dialog_dinner_after.setOnClickListener(sugerListeren);
            txt_dialog_slepp_front.setOnClickListener(sugerListeren);
            btn_submit.setOnClickListener(sugerListeren);
        }
    }

    private View.OnClickListener sugerListeren = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Drawable drawable = getResources().getDrawable(R.mipmap.icon_check);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            int id = v.getId();
            if (tmpID != null) {
                tmpID.setCompoundDrawables(null, null, null, null);
                tmpID.setTextColor(getResources().getColor(R.color.font_color_default));
            }
            tmpID = (TextView) v;
            switch (id) {
                case R.id.txt_dialog_emptiness:
                    txt_dialog_emptiness.setCompoundDrawables(null, null, drawable, null);
                    txt_dialog_emptiness.setTextColor(getResources().getColor(R.color.font_color_green_default));
                    bean.setPeriod(DataBean.EAR_EMPTINESS + "");
                    break;
                case R.id.txt_dialog_breakfast_after:
                    txt_dialog_breakfast_after.setCompoundDrawables(null, null, drawable, null);
                    bean.setPeriod(DataBean.EAR_BREAKFAST_AFTER + "");
                    break;
                case R.id.txt_dialog_lunch_front:
                    txt_dialog_lunch_front.setCompoundDrawables(null, null, drawable, null);
                    bean.setPeriod(DataBean.EAR_LUNCH_FRONT + "");
                    break;
                case R.id.txt_dialog_lunch_after:
                    txt_dialog_lunch_after.setCompoundDrawables(null, null, drawable, null);
                    bean.setPeriod(DataBean.EAR_LUNCH_AFTER + "");
                    break;
                case R.id.txt_dialog_dinner_front:
                    txt_dialog_dinner_front.setCompoundDrawables(null, null, drawable, null);
                    bean.setPeriod(DataBean.EAR_DINNER_FRONT + "");
                    break;
                case R.id.txt_dialog_dinner_after:
                    txt_dialog_dinner_after.setCompoundDrawables(null, null, drawable, null);
                    bean.setPeriod(DataBean.EAR_DINNER_AFTER + "");
                    break;
                case R.id.txt_dialog_slepp_front:
                    txt_dialog_slepp_front.setCompoundDrawables(null, null, drawable, null);
                    bean.setPeriod(DataBean.EAR_SLEPP_FRONT + "");
                    break;
                case R.id.btn_ok:
                    checkSuger();
                    break;

            }

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

    private void checkSuger() {
        if (StringUtil.isEmpty(bean.getPeriod())) {
            ToastUtil.showLongToast(this, getString(R.string.check_suger_msg));
        } else {
            CustomDialog.dismissDialog();
            submitProcessing.starAsyn();
        }
    }

}



