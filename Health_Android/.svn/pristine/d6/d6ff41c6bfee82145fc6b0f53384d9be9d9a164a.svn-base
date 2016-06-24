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


    public int getNormalTimes() {
        return normalTimes;
    }

    public void setNormalTimes(int normalTimes) {
        this.normalTimes = normalTimes;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getLowest() {
        return lowest;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest;
    }

    public String getHighest() {
        return highest;
    }

    public void setHighest(String highest) {
        this.highest = highest;
    }

    public int getUnnormalTimes() {
        return unnormalTimes;
    }

    public void setUnnormalTimes(int unnormalTimes) {
        this.unnormalTimes = unnormalTimes;
    }

    private int normalTimes;
    private int unnormalTimes;
    private String highest;
    private String lowest;
    private String average;
    private String results;

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
            setNormalTimes(json.getInt("normalTimes"));
            setUnnormalTimes(json.getInt("unnormalTimes"));
            setHighest(json.getString("highest"));
            setLowest(json.getString("lowest"));
            setAverage(json.getString("average"));
            setResults(json.getString("results"));
            setData((ArrayList<HealthAssessCharPodBean>) BaseBean.parsingJsonArray(new JSONArray(json.getString("data")), HealthAssessCharPodBean.class));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
