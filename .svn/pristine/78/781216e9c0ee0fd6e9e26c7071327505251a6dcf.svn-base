package com.tentinet.healthy.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.activity.HealthAssessActivity;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.interf.OpenBleListener;
import com.tentinet.healthy.service.BleManagerService;
import com.tentinet.healthy.util.BleUtil;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.widget.DeviceWindow;

/**
 * 首页标签页.
 *
 * @author paladin.
 */
public class DataTab extends RelativeLayout implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 切换视图控件.
     */
    private RadioGroup group_change;
    /**
     * 展示内容.
     */
    private RelativeLayout relative_content;
    /**
     * 测量视图.
     */
    private MeasurementView view_measurement;
    /**
     * 数据视图.
     */
    private DataView view_data;
    /**
     * 右边下拉按钮
     */
    private TextView txt_right_check;
    /**
     * 蓝牙打开监听
     */
    private OpenBleListener openBleListener;
    /**
     * 右边下拉按钮的popwindow
     */
    private DeviceWindow deviceWindow;

    private RadioButton rbtn_measurement, rbtn_data;

    private RelativeLayout tab_data_r1;
    /**
     * 设备类别
     */
    private int measure_type;

    private TitleView title_View;
    /**
     * 设备id
     */
    private static final String KEY_DEVICE_ID = "KEY_DEVICE_ID";

    /**
     * 默认选择的是测量
     */
    private int defaultIndex = 0;
    private static final String KEY_MEASURE_TYPE = "KEY_MEASURE_TYPE";

    public DataTab(Context context, int measureType) {
        super(context);
        init(context, measureType);

    }

    public DataTab(Context context, AttributeSet attrs, int measureType) {
        super(context, attrs);
        init(context, measureType);
    }

    public DataTab(Context context, AttributeSet attrs, int defStyleAttr, int measureType) {
        super(context, attrs, defStyleAttr);
        init(context, measureType);
    }

    /**
     * 初始化数据.
     *
     * @param context 上下文环境.
     */
    private void init(final Context context, final int measureType) {
        this.context = context;
        this.measure_type = measureType;

        this.addView(LayoutInflater.from(context).inflate(R.layout.tab_data, null), new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.txt_right_check = (TextView) findViewById(R.id.txt_right_check);
        this.title_View = (TitleView) findViewById(R.id.measure_title);
        setTitleText();
        title_View.setBackImageButton(R.mipmap.icon_back_white);
        group_change = (RadioGroup) findViewById(R.id.group_change);
        rbtn_measurement = (RadioButton) findViewById(R.id.radio_measurement);
        rbtn_data = (RadioButton) findViewById(R.id.radio_data);
        tab_data_r1 = (RelativeLayout) findViewById(R.id.tab_data_rl);
        relative_content = (RelativeLayout) findViewById(R.id.relative_content);
        view_measurement = new MeasurementView(context, measure_type);
        // view_data = new DataView(context);
        changeView(view_measurement);
        registerEvent();
        if (measure_type == DataBean.TYPE_BLOOD_PRESSURE_MONITOR || measure_type == DataBean.TYPE_GLUCOSE_METER || measure_type == DataBean.TYPE_BLE_EAR) {
//            title_View.setRightOneImageButton(R.mipmap.health_assess, new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Bundle bundle = new Bundle();
//                    bundle.putInt(KEY_MEASURE_TYPE, measure_type);
//                    bundle.putString(KEY_DEVICE_ID, view_measurement.getDevice_id());
//                    IntentUtil.gotoActivity(context, HealthAssessActivity.class, bundle);
//                }
//            });

            title_View.setRightButton(R.string.assess, new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt(KEY_MEASURE_TYPE, measure_type);
                    bundle.putString(KEY_DEVICE_ID, view_measurement.getDevice_id());
                    IntentUtil.gotoActivity(context, HealthAssessActivity.class, bundle);
                }
            });
        }

    }

    /**
     * 注册监听.
     */
    private void registerEvent() {
        group_change.setOnCheckedChangeListener(this);
        txt_right_check.setOnClickListener(this);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        final ViewGroup.LayoutParams lp;
        lp = txt_right_check.getLayoutParams();
        lp.width = w / 2;
        lp.height = 10;
        txt_right_check.setLayoutParams(lp);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_measurement:
                if (null == view_measurement) {
                    view_measurement = new MeasurementView(context, measure_type);
                }
                defaultIndex = 0;
                showOrGoneBtnRight(GONE);
                view_measurement.clearDevice();
                view_measurement.scanBleDevice(true);
                changeView(view_measurement);
                break;
            case R.id.radio_data:
                if (null == view_data) {
                    view_data = new DataView(context, measure_type);
                }
                defaultIndex = 1;
                // view_measurement.destroy();
                showOrGoneBtnRight(VISIBLE);
                view_measurement.scanBleDevice(false);
                changeView(view_data);
                break;
        }
    }

    /**
     * 切换视图.
     *
     * @param view 显示的视图.
     */
    private void changeView(View view) {
        relative_content.removeAllViews();
        relative_content.addView(view);
    }

    /**
     * tab切换时的状态操作
     *
     * @param state
     */
    private void showOrGoneBtnRight(int state) {
        RelativeLayout.LayoutParams params = (LayoutParams) txt_right_check.getLayoutParams();
        if (state == VISIBLE) {
            params.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            txt_right_check.setLayoutParams(params);
            rbtn_data.setTextColor(getResources().getColor(R.color.font_color_green_default));
            rbtn_measurement.setTextColor(getResources().getColor(R.color.font_color_default));
        } else {
            params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            txt_right_check.setLayoutParams(params);
            rbtn_data.setTextColor(getResources().getColor(R.color.font_color_default));
            rbtn_measurement.setTextColor(getResources().getColor(R.color.font_color_green_default));

        }
    }


    @Override
    public void onClick(View v) {
//        int id = v.getId();
//        switch (id) {
//            case R.id.txt_right_check:
//              //  showDevicePopWindow(v);
//                break;
//        }
    }

    private void showDevicePopWindow(View parent) {
        if (deviceWindow == null) {
            deviceWindow = new DeviceWindow(context, parent, onWindowItemListeren);
        }
        deviceWindow.showAsDropDown(parent, 0, (tab_data_r1.getHeight() - txt_right_check.getHeight()) / 2);
    }

    /**
     * 点击window item的回调
     */
    private OnWindowItemListeren onWindowItemListeren = new OnWindowItemListeren() {
        @Override
        public void onClick(int index) {
            if (defaultIndex == 1) {
                //数据
                view_data.reLoadData(index);
            } else if (defaultIndex == 0) {
                //测量
                view_measurement.changeDevice(index);
            }
        }
    };

    /**
     * 点击设备选项
     */
    public interface OnWindowItemListeren {
        void onClick(int index);
    }

    private void setTitleText() {
        switch (measure_type) {
            case DataBean.TYPE_BLOOD_PRESSURE_MONITOR:
                title_View.setTitle(R.string.bluetooth_blood_pressure);
                break;
            case DataBean.TYPE_GLUCOSE_METER:
                title_View.setTitle(R.string.bluetooth_blood_sugar);
                break;
            case DataBean.TYPE_BLE_OPD:
                title_View.setTitle(R.string.bluetooth_oxygen);
                break;
            case DataBean.TYPE_BLE_ECG:
                title_View.setTitle(R.string.bluetooth_ecg);
                break;
            case DataBean.TYPE_BLE_EAR:
                title_View.setTitle(R.string.bluetooth_ear_thermometer);
                break;
        }
    }

    /**
     * 提供 测量页面destroy时调用
     */
    public void destroy() {
        view_measurement.destroy();
    }

    /**
     * 提供 数据页面重新加载数据
     */
    public void reLoad() {
        view_data.reLoadData(measure_type);
    }

    public void showLoadSanBle() {
        view_measurement.showLoadSanBle();
    }

}
