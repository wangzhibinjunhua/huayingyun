package com.tentinet.healthy.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 测量数据数据模型.
 *
 * @author paladin.
 */
public class HealthAssessPodBean extends BaseBean {


    /**
     * 血氧浓度
     */
    private String bloodSpO2Highest;

    public ArrayList<HealthAssessCharPodBean> getData() {
        return data;
    }

    public void setData(ArrayList<HealthAssessCharPodBean> data) {
        this.data = data;
    }

    private ArrayList<HealthAssessCharPodBean> data;

    @Override
    protected void setParams(JSONObject json) {
        try {
            setBloodSpO2Highest(json.getString("bloodSpO2Highest"));
            setData((ArrayList<HealthAssessCharPodBean>) BaseBean.parsingJsonArray(new JSONArray(json.getString("data")), HealthAssessCharPodBean.class));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public String getBloodSpO2Highest() {
        return bloodSpO2Highest;
    }

    public void setBloodSpO2Highest(String bloodSpO2Highest) {
        this.bloodSpO2Highest = bloodSpO2Highest;
    }
}
