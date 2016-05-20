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

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
