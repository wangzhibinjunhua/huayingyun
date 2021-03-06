package com.tentinet.healthy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.util.MeasureResultUtil;
import com.tentinet.healthy.util.StringUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * 数据适配器.
 *
 * @author paladin
 */
public class DataAdapter extends LoadImageBaseAdapter {

    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 数据.
     */
    private ArrayList<DataBean> list;

    public DataAdapter(Context context, ArrayList<DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        DataBean data = list.get(position);
        switch (data.getType()) {
            case DataBean.TYPE_BLOOD_PRESSURE_MONITOR:
                return getBloodPressureMonitorView(view, data);
            case DataBean.TYPE_GLUCOSE_METER:
                return getGlucoseMeter(view, data);
            case DataBean.TYPE_BLE_ECG:
                return getEcg(view, data);
            case DataBean.TYPE_BLE_EAR:
                return getEar(view, data);
            case DataBean.TYPE_BLE_OPD:
                return getPod(view, data);
            default:
                return getGlucoseMeter(view, data);
        }
    }

    /**
     * 获取血压计item.
     *
     * @return 血压计item.
     */
    private View getBloodPressureMonitorView(View view, DataBean data) {
        ItemHolder item;
        if (null == view) {
            item = new ItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_blood_pressure, null);
            item.txt_date = (TextView) view.findViewById(R.id.txt_date);
            item.txt_highPressure = (TextView) view.findViewById(R.id.txt_high_pressure);
            item.txt_lowPressure = (TextView) view.findViewById(R.id.txt_low_pressure);
            item.txt_heartRate = (TextView) view.findViewById(R.id.txt_heart_rate);
            item.txt_measure_result = (TextView) view.findViewById(R.id.txt_measure_result);
            view.setTag(item);
        } else {
            item = (ItemHolder) view.getTag();
        }
        item.txt_date.setText(data.getDate());
        item.txt_highPressure.setText(data.getSys());
        item.txt_lowPressure.setText(data.getDia());
        item.txt_heartRate.setText(data.getPulse());
        item.txt_measure_result.setText(MeasureResultUtil.getBloodResult(context, data.getSys()));
        return view;
    }

    /**
     * 获取血糖仪item.
     *
     * @return 血糖仪item.
     */
    private View getGlucoseMeter(View view, DataBean data) {

        ItemHolder item;
        if (null == view) {
            item = new ItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_blood_sugar, null);
            item.txt_date = (TextView) view.findViewById(R.id.txt_date1);
            item.txt_bloodSugar = (TextView) view.findViewById(R.id.txt_blood_sugar);
            item.txt_heartRate = (TextView) view.findViewById(R.id.txt_blood_sugar_date);
            item.txt_measure_result = (TextView) view.findViewById(R.id.txt_blood_sugar_result);
            view.setTag(item);
        } else {
            item = (ItemHolder) view.getTag();
        }
        item.txt_date.setText(data.getDate());
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        if (!StringUtil.isEmpty(data.getBloodSugar())) {
            String distanceString = decimalFormat.format(Float.parseFloat(data.getBloodSugar()));
            item.txt_bloodSugar.setText(distanceString);
        }else {
            item.txt_bloodSugar.setText("");
        }

        item.txt_heartRate.setText(getGlucoseMeterDate(Integer.parseInt(data.getPeriod())));
        item.txt_measure_result.setText(MeasureResultUtil.getSugarResult(context, data.getBloodSugar()));
        return view;
    }

    /**
     * 获取心电仪item.
     *
     * @return 血糖仪item.
     */
    private View getEcg(View view, DataBean data) {

        ItemHolder item;
        if (null == view) {
            item = new ItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_blood_ecg, null);
            item.txt_date = (TextView) view.findViewById(R.id.txt_date1);
            item.txt_bloodSugar = (TextView) view.findViewById(R.id.txt_blood_sugar);
            view.setTag(item);
        } else {
            item = (ItemHolder) view.getTag();
        }
        item.txt_date.setText(data.getDate());
        item.txt_bloodSugar.setText(data.getBloodPulse());
        return view;
    }

    /**
     * 获取耳温枪item.
     *
     * @return 血糖仪item.
     */
    private View getEar(View view, DataBean data) {

        ItemHolder item;
        if (null == view) {
            item = new ItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_blood_ear, null);
            item.txt_date = (TextView) view.findViewById(R.id.txt_date1);
            item.txt_bloodSugar = (TextView) view.findViewById(R.id.txt_blood_sugar);
            item.txt_measure_result = (TextView) view.findViewById(R.id.txt_blood_sugar_result);
            view.setTag(item);
        } else {
            item = (ItemHolder) view.getTag();
        }
        item.txt_date.setText(data.getDate());
        item.txt_bloodSugar.setText(data.getEarTemperature());
        item.txt_measure_result.setText(MeasureResultUtil.getEarResult(context, data.getEarTemperature()));
        return view;
    }

    /**
     * 获取血氧item.
     *
     * @return 血糖仪item.
     */
    private View getPod(View view, DataBean data) {

        ItemHolder item;
        if (null == view) {
            item = new ItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_blood_pod, null);
            item.txt_date = (TextView) view.findViewById(R.id.txt_date1);
            item.txt_bloodSugar = (TextView) view.findViewById(R.id.txt_blood_sugar);
            item.txt_heartRate = (TextView) view.findViewById(R.id.txt_blood_plues);
            view.setTag(item);
        } else {
            item = (ItemHolder) view.getTag();
        }
        item.txt_date.setText(data.getDate());
        item.txt_heartRate.setText(data.getBloodPulse());
        item.txt_bloodSugar.setText(data.getBloodSpO2());
        return view;
    }

    private class ItemHolder {
        private TextView txt_date;
        private TextView txt_highPressure;
        private TextView txt_lowPressure;
        private TextView txt_heartRate;
        private TextView txt_bloodSugar;
        private TextView txt_measure_result;

    }

    private String getGlucoseMeterDate(int type) {
        String reslut = "";
        switch (type) {
            case DataBean.EAR_EMPTINESS:
                reslut = context.getString(R.string.EAR_EMPTINESS);
                break;
            case DataBean.EAR_BREAKFAST_AFTER:
                reslut = context.getString(R.string.EAR_BREAKFAST_AFTER);
                break;
            case DataBean.EAR_LUNCH_FRONT:
                reslut = context.getString(R.string.EAR_LUNCH_FRONT);
                break;
            case DataBean.EAR_LUNCH_AFTER:
                reslut = context.getString(R.string.EAR_LUNCH_AFTER);
                break;
            case DataBean.EAR_DINNER_FRONT:
                reslut = context.getString(R.string.EAR_DINNER_FRONT);
                break;
            case DataBean.EAR_DINNER_AFTER:
                reslut = context.getString(R.string.EAR_DINNER_AFTER);
                break;
            case DataBean.EAR_SLEPP_FRONT:
                reslut = context.getString(R.string.EAR_SLEPP_FRONT);
                break;
        }
        return reslut;
    }

    ;

}
