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
public class HealthAssessSugerBean extends BaseBean {


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

    public int getKfHighest() {
        return kfHighest;
    }

    public void setKfHighest(int kfHighest) {
        this.kfHighest = kfHighest;
    }

    public int getKfLowest() {
        return kfLowest;
    }

    public void setKfLowest(int kfLowest) {
        this.kfLowest = kfLowest;
    }

    public String getChHighest() {
        return chHighest;
    }

    public void setChHighest(String chHighest) {
        this.chHighest = chHighest;
    }

    public String getChLowest() {
        return chLowest;
    }

    public void setChLowest(String chLowest) {
        this.chLowest = chLowest;
    }

    public int getKfAverage() {
        return kfAverage;
    }

    public void setKfAverage(int kfAverage) {
        this.kfAverage = kfAverage;
    }

    public String getChAverage() {
        return chAverage;
    }

    public void setChAverage(String chAverage) {
        this.chAverage = chAverage;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    private int normalTimes;
    private int unnormalTimes;
    private int kfHighest;
    private int kfLowest;
    private String chHighest;
    private String chLowest;
    private int kfAverage;
    private String chAverage;
    private String results;
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

    public ArrayList<HealthAssessCharSugerBean> getData() {
        return data;
    }

    public void setData(ArrayList<HealthAssessCharSugerBean> data) {
        this.data = data;
    }

    private ArrayList<HealthAssessCharSugerBean> data;

    @Override
    protected void setParams(JSONObject json) {
        try {
            setNormalTimes(json.getInt("normalTimes"));
            setUnnormalTimes(json.getInt("unnormalTimes"));
            setKfHighest(json.getInt("kfHighest"));
            setKfLowest(json.getInt("kfLowest"));
            setChHighest(json.getString("chHighest"));
            setChLowest(json.getString("chLowest"));
            setKfAverage(json.getInt("kfAverage"));
            setChAverage(json.getString("chAverage"));
            setResults(json.getString("results"));
            setData((ArrayList<HealthAssessCharSugerBean>) BaseBean.parsingJsonArray(new JSONArray(json.getString("data")), HealthAssessCharSugerBean.class));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
