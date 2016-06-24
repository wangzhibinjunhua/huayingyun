package com.tentinet.healthy.bean;

import com.tentinet.healthy.util.DateUtil;
import com.tentinet.healthy.util.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 测量数据数据模型.
 *
 * @author paladin.
 */
public class DataBean extends BaseBean {

    /**
     * 数据id.
     */
    private String id;
    /**
     * 数据类型.
     */
    private int type;
    /**
     * 高压
     */
    private String sys;
    /**
     * 低压
     */
    private String dia;
    /**
     * 脉搏
     */
    private String pulse;
    /**
     * 血糖.
     */
    private String bloodSugar;
    /**
     * 血糖时段
     */
    private String period;
    /**
     * 温度
     */
    private String earTemperature;

    /**
     * 血氧浓度
     */
    private String bloodSpO2;
    /**
     * 测量时间.
     */
    private String date;
    /**
     * 心电波纹
     */
    private String blooding;

    private String ecgResult;

    public String getBloodPulse() {
        return bloodPulse;
    }

    public void setBloodPulse(String bloodPulse) {
        this.bloodPulse = bloodPulse;
    }

    /**
     * 心电心率
     */
    private String bloodPulse;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getBlooding() {
        return blooding;
    }

    public void setBlooding(String blooding) {
        this.blooding = blooding;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSys() {
        return sys;
    }

    public void setSys(String sys) {
        this.sys = sys;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getPulse() {
        return pulse;
    }

    public void setPulse(String pulse) {
        this.pulse = pulse;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public String getEarTemperature() {
        return earTemperature;
    }

    public void setEarTemperature(String earTemperature) {
        this.earTemperature = earTemperature;
    }

    @Override
    protected void setParams(JSONObject json) {
        LogUtil.logDebugMessage(json.toString());
        try {
            setId(json.getString("data_id"));
            setType(json.getInt("devicetype"));
            switch (getType()) {
                case TYPE_BLOOD_PRESSURE_MONITOR:
                    setSys(json.getString("highPressure"));
                    setDia(json.getString("lowPressure"));
                    setPulse(json.getString("heartRate"));
                    setDate(DateUtil.getDateStringByTimeMillis(json.getLong("unixTime")));
                    break;
                case TYPE_GLUCOSE_METER:
                    setBloodSugar(json.getString("bloodSugar"));
                    setPeriod(json.getString("period"));
                    setDate(DateUtil.getDateStringByTimeMillis(json.getLong("unixTime")));
                    break;
                case TYPE_BLE_EAR:
                    setEarTemperature(json.getString("earTemperature"));
                    setDate(DateUtil.getDateStringByTimeMillis(json.getLong("unixTime")));
                    break;
                case TYPE_BLE_ECG:
                    setBlooding(json.getString("blooding"));
                    setBloodPulse(json.getString("bloodPulse"));
                    setEcgResult(json.getString("ecgResult"));
                    setDate(DateUtil.getDateStringByTimeMillis(json.getLong("unixTime")));
                    break;
                case TYPE_BLE_OPD:
                    setBloodSpO2(json.getString("bloodSpO2"));
                    setBloodPulse(json.getString("bloodPulse"));
                    setDate(DateUtil.getDateStringByTimeMillis(json.getLong("unixTime")));
                    break;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtil.logDebugMessage(toString());
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", sys='" + sys + '\'' +
                ", dia='" + dia + '\'' +
                ", pulse='" + pulse + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    /**
     * 设备类型：血压计.
     */
    public static final int TYPE_BLOOD_PRESSURE_MONITOR = 1;
    /**
     * 设备类型：血糖仪.
     */
    public static final int TYPE_GLUCOSE_METER = 2;

    /**
     * 设备类型：血氧仪.
     */
    public static final int TYPE_BLE_OPD = 3;
    /**
     * 设备类型：心电仪
     */
    public static final int TYPE_BLE_ECG = 4;
    /**
     * 设备类型：耳温枪
     */
    public static final int TYPE_BLE_EAR = 5;
    /**
     * 设备类型：体温贴
     */
    public static final int TYPE_BLE_WATCH = 6;

    /**
     * 设备类型：体温贴
     */
    public static final int TYPE_BLE_BTEMP = 7;

    /**
     * 空腹
     */
    public static final int EAR_EMPTINESS = 1;
    /**
     * 早餐后
     */
    public static final int EAR_BREAKFAST_AFTER = 2;
    /**
     * 中餐前
     */
    public static final int EAR_LUNCH_FRONT = 3;
    /**
     * 中餐后
     */
    public static final int EAR_LUNCH_AFTER = 4;
    /**
     * 晚餐前
     */
    public static final int EAR_DINNER_FRONT = 5;
    /**
     * 晚餐后
     */
    public static final int EAR_DINNER_AFTER = 6;
    /**
     * 睡前
     */
    public static final int EAR_SLEPP_FRONT = 7;

    public String getBloodSpO2() {
        return bloodSpO2;
    }

    public void setBloodSpO2(String bloodSpO2) {
        this.bloodSpO2 = bloodSpO2;
    }

    public String getEcgResult() {
        return ecgResult;
    }

    public void setEcgResult(String ecgResult) {
        this.ecgResult = ecgResult;
    }
}
