package com.tentinet.healthy.bean;

import org.json.JSONObject;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/6/21 9:47
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class DoctorBean extends  BaseBean {

    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    private String name;
    private String type;
    private String sex;
    private String introduce;
    @Override
    protected void setParams(JSONObject json) {
       // json.getString("")
    }
}
