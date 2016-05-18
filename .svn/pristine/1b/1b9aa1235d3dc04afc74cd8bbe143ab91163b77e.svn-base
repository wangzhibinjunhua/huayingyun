package com.tentinet.healthy.bean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * <h3>Description</h3>
 * todo
 * <h3>Author</h3> Rick Chan
 * <h3>Date</h3> 2016/3/29 17:00
 * <h3>Copyright</h3>Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class ParentBean extends BaseBean implements Serializable {
    /**
     * 亲戚姓名
     */
    private String parentName;
    /**
     * 亲戚电话
     */
    private String parentPhone;
    /**
     * 亲戚头像
     */
    private String parentPortrait;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public String getParentPortrait() {
        return parentPortrait;
    }

    public void setParentPortrait(String parentPortrait) {
        this.parentPortrait = parentPortrait;
    }


    @Override
    protected void setParams(JSONObject json) {
        try {
            setParentName(json.getString("name"));
            setParentPhone(json.getString("friendsMobile"));
            //   setParentPortrait(json.optString("", ""));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
