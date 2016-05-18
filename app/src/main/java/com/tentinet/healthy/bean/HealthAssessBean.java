package com.tentinet.healthy.bean;

import com.tentinet.healthy.util.DateUtil;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.widget.pulltorefresh.PullableRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 测量数据数据模型.
 *
 * @author paladin.
 */
public class HealthAssessBean extends BaseBean {


    /**
     * 正常血压结果
     */
    private int normalTimes;
    /**
     * 偏高血压结果
     */
    private int unnormalTimes;
    /**
     * 数据次数
     */
    private int dataNum;
    /**
     * 最高血压值
     */
    private String highPressureMax;
    /**
     * 低压最高值
     */
    private String lowPressureMax;
    /**
     * 高压平均值
     */
    private String highPressureAverage;
    /**
     * 低压平均值
     */
    private String lowPressureAverage;
    /**
     * 心率平均值
     */
    private String heartRateAverage;
    /**
     * 曲线图集合
     */
    private ArrayList<HealthAssessCharBean> data;

    /**
     * 健康指标结果
     *
     * @return
     */
    private String results;
    /**
     * 健康指标名称
     *
     * @return
     */
    private String name;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 健康指标名称后单位
     *
     * @return
     */
    private String msg;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    protected void setParams(JSONObject json) {
        try {
            setResults(json.getString("results"));
            setNormalTimes(json.getInt("normalTimes"));
            setUnnormalTimes(json.getInt("unnormalTimes"));
            setHighPressureMax(json.getString("highPressureMax"));
            setLowPressureMax(json.getString("lowPressureMax"));
            setHighPressureAverage(json.getString("highPressureAverage"));
            setLowPressureAverage(json.getString("lowPressureAverage"));
            setHeartRateAverage(json.getString("heartRateAverage"));
            setData((ArrayList<HealthAssessCharBean>) BaseBean.parsingJsonArray(new JSONArray(json.getString("data")), HealthAssessCharBean.class));
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public int getDataNum() {
        return dataNum;
    }

    public void setDataNum(int dataNum) {
        this.dataNum = dataNum;
    }

    public String getHighPressureMax() {
        return highPressureMax;
    }

    public void setHighPressureMax(String highPressureMax) {
        this.highPressureMax = highPressureMax;
    }

    public String getLowPressureMax() {
        return lowPressureMax;
    }

    public void setLowPressureMax(String lowPressureMax) {
        this.lowPressureMax = lowPressureMax;
    }

    public String getHighPressureAverage() {
        return highPressureAverage;
    }

    public void setHighPressureAverage(String highPressureAverage) {
        this.highPressureAverage = highPressureAverage;
    }

    public String getLowPressureAverage() {
        return lowPressureAverage;
    }

    public void setLowPressureAverage(String lowPressureAverage) {
        this.lowPressureAverage = lowPressureAverage;
    }

    public String getHeartRateAverage() {
        return heartRateAverage;
    }

    public void setHeartRateAverage(String heartRateAverage) {
        this.heartRateAverage = heartRateAverage;
    }

    public ArrayList<HealthAssessCharBean> getData() {
        return data;
    }

    public void setData(ArrayList<HealthAssessCharBean> data) {
        this.data = data;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
