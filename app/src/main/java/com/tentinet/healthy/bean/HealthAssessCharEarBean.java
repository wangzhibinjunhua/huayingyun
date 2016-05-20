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
public class HealthAssessCharEarBean extends BaseBean implements Comparator<HealthAssessCharEarBean> {

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }



    private String createtime;


    public String getEarTemperature() {
        return earTemperature;
    }

    public void setEarTemperature(String earTemperature) {
        this.earTemperature = earTemperature;
    }

    private String earTemperature;


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
            setEarTemperature(json.getString("earTemperature"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public int compare(HealthAssessCharEarBean lhs, HealthAssessCharEarBean rhs) {
        return Integer.compare(Integer.parseInt(lhs.getData_id()),Integer.parseInt(rhs.getData_id())) ;
    }
}
