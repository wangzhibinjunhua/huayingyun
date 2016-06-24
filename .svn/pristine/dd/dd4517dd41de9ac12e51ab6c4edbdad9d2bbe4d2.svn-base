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
public class HealthAssessECGBean extends BaseBean {


    private int normalTimes;
    private int unnormalTimes;
    private String highest;

    public String getLowest() {
        return lowest;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest;
    }

    public int getNormalTimes() {
        return normalTimes;
    }

    public void setNormalTimes(int normalTimes) {
        this.normalTimes = normalTimes;
    }

    public int getUnnormalTimes() {
        return unnormalTimes;
    }

    public void setUnnormalTimes(int unnormalTimes) {
        this.unnormalTimes = unnormalTimes;
    }

    public String getHighest() {
        return highest;
    }

    public void setHighest(String highest) {
        this.highest = highest;
    }

    private String lowest;

    public int getBloodPulseAverage() {
        return bloodPulseAverage;
    }

    public void setBloodPulseAverage(int bloodPulseAverage) {
        this.bloodPulseAverage = bloodPulseAverage;
    }

    public EcgBean getResults() {
        return results;
    }

    public void setResults(EcgBean results) {
        this.results = results;
    }

    /**
     * 心率
     */
    private int bloodPulseAverage;

    /**
     * 曲线图集合
     */
    private EcgBean results;


    @Override
    protected void setParams(JSONObject json) {
        try {
            setResults((EcgBean) BaseBean.parsingJsonObject(new JSONObject(json.getString("results")), EcgBean.class));
            setBloodPulseAverage(json.getInt("bloodPulseAverage"));
            setNormalTimes(json.getInt("normalTimes"));
            setUnnormalTimes(json.getInt("unnormalTimes"));
            setHighest(json.getString("highest"));
            setLowest(json.getString("lowest"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
