package com.tentinet.healthy.bean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * 设备数据模型.
 *
 * @author paladin.
 */
public class DeviceBean extends BaseBean implements Serializable {

    /**
     * 设备id.
     */
    private String device_id;
    /**
     * 设备类型.
     */
    private int type;
    /**
     * 设备名称.
     */
    private String deviceName;

    private int url;

    private String address;

    private boolean isBind=false;




    public int getType() {
        return type;
    }



    public void setType(int type) {
        this.type = type;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    @Override
    protected void setParams(JSONObject json) {
        try {
            setDevice_id(json.getString("device_id"));
            setType(json.getInt("devicetype"));
            setDeviceName(json.getString("devicetypeName"));
            setAddress(json.getString("address"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "DeviceBean{" +
                "id='" + device_id + '\'' +
                ", type=" + type +
                ", deviceName='" + deviceName + '\'' +
                '}';
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isBind() {
        return isBind;
    }

    public void setBind(boolean bind) {
        isBind = bind;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
