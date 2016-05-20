package com.tentinet.healthy.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/5/20 16:39
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class EcgBean extends BaseBean {

    @Override
    protected void setParams(JSONObject json) {
        try {
            setNormal(json.getInt("心跳正常"));
            setFast(json.getInt("心跳过快"));
            setSlow(json.getInt("心跳过缓"));
            setAnomaly(json.getInt("心跳不规则"));
            setExcursion(json.getInt("心跳漂移"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getFast() {
        return fast;
    }

    public void setFast(int fast) {
        this.fast = fast;
    }

    public int getSlow() {
        return slow;
    }

    public void setSlow(int slow) {
        this.slow = slow;
    }

    public int getAnomaly() {
        return anomaly;
    }

    public void setAnomaly(int anomaly) {
        this.anomaly = anomaly;
    }

    public int getExcursion() {
        return excursion;
    }

    public void setExcursion(int excursion) {
        this.excursion = excursion;
    }

    /**
     * 正常
     */
    private int normal;
    /**
     * 过快
     */
    private int fast;
    /**
     * 正慢
     */
    private int slow;
    /**
     * 不规则
     */
    private int anomaly;
    /**
     * 漂移
     */
    private int excursion;


}
