package com.tentinet.healthy.bean;

import android.annotation.TargetApi;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Comparator;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/5/10 17:50
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class HealthAssessCharBean extends BaseBean implements Comparator<HealthAssessCharBean> {
    public String getHighPressure() {
        return highPressure;
    }

    public void setHighPressure(String highPressure) {
        this.highPressure = highPressure;
    }

    public String getLowPressure() {
        return lowPressure;
    }

    public void setLowPressure(String lowPressure) {
        this.lowPressure = lowPressure;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * 高压
     */
    private String highPressure;
    /**
     * 低压
     */
    private String lowPressure;

    /**
     * 心率
     */
    private String heartRate;
    /**
     * 时间
     */

    private String createtime;


    public String getData_id() {
        return data_id;
    }

    public void setData_id(String data_id) {
        this.data_id = data_id;
    }

    private String data_id;

    @Override
    protected void setParams(JSONObject json) {
        try {
            setCreatetime(json.getString("createtime"));
            setHeartRate(json.getString("heartRate"));
            setHighPressure(json.getString("highPressure"));
            setLowPressure(json.getString("lowPressure"));
            setData_id(json.getString("data_id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int compare(HealthAssessCharBean lhs, HealthAssessCharBean rhs) {
        return Integer.compare(Integer.parseInt(lhs.getData_id()),Integer.parseInt(rhs.getData_id())) ;
    }
}
