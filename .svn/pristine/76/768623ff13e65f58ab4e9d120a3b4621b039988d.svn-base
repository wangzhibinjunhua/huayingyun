package com.tentinet.healthy.bean;

import org.json.JSONObject;

/**
 * 呼叫中心
 * TODO
 * Author YKK
 * Date 2016/5/13 10:16
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class CallCenterBean extends BaseBean {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String name;
    private String phone;
    private String url;

    @Override
    protected void setParams(JSONObject json) {

    }
    public CallCenterBean(String name,String phone,String url){
        setName(name);
        setPhone(phone);
        setUrl(url);
    }
    public CallCenterBean(){}
}
