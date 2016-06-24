package com.tentinet.healthy.bean;

import android.annotation.TargetApi;
import android.os.Build;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.PhantomReference;
import java.util.Comparator;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/5/10 17:50
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class HealthAssessCharPodBean extends BaseBean implements Comparator<HealthAssessCharPodBean> {

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    private String createtime;




    private String bloodSpO2;

    public String getBloodPulse() {
        return bloodPulse;
    }

    public void setBloodPulse(String bloodPulse) {
        this.bloodPulse = bloodPulse;
    }

    private String bloodPulse;


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
            setData_id(json.getString("data_id"));
            setBloodSpO2(json.getString("bloodSpO2"));
            setBloodPulse(json.getString("bloodPulse"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int compare(HealthAssessCharPodBean lhs, HealthAssessCharPodBean rhs) {
        return Integer.compare(Integer.parseInt(lhs.getData_id()),Integer.parseInt(rhs.getData_id())) ;
    }

    public String getBloodSpO2() {
        return bloodSpO2;
    }

    public void setBloodSpO2(String bloodSpO2) {
        this.bloodSpO2 = bloodSpO2;
    }
}
