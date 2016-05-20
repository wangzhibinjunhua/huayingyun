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
            setData((ArrayList<HealthAssessCharEarBean>) BaseBean.parsingJsonArray(new JSONArray(json.getString("data")), HealthAssessCharEarBean.class));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
