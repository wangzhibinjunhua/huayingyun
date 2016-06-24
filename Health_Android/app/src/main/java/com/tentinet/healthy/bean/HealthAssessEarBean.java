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
public class HealthAssessEarBean extends BaseBean {


    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    private String results;

    public int getNormalTimes() {
        return normalTimes;
    }

    public void setNormalTimes(int normalTimes) {
        this.normalTimes = normalTimes;
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

    /**
     * 耳温枪 平均值
     *
     * @return
     */
    public String getTempAverage() {
        return tempAverage;
    }

    public void setTempAverage(String tempAverage) {
        this.tempAverage = tempAverage;
    }

    private String tempAverage;

    public ArrayList<HealthAssessCharEarBean> getData() {
        return data;
    }

    public void setData(ArrayList<HealthAssessCharEarBean> data) {
        this.data = data;
    }

    private ArrayList<HealthAssessCharEarBean> data;

    @Override
    protected void setParams(JSONObject json) {
        try {
            setTempAverage(json.getString("tempAverage"));
            setNormalTimes(json.getInt("normalTimes"));
            setUnnormalTimes(json.getInt("unnormalTimes"));
            setHighest(json.getString("highest"));
            setLowest(json.getString("lowest"));
            setResults(json.getString("results"));
            setData((ArrayList<HealthAssessCharEarBean>) BaseBean.parsingJsonArray(new JSONArray(json.getString("data")), HealthAssessCharEarBean.class));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
