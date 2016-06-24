package com.tentinet.healthy.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/5/6 17:21
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class VersionBean extends BaseBean {
    private String file;
    private String description;
    private String code;
    private String version;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    protected void setParams(JSONObject json) {
        try {
            setFile(json.getString("file"));
            setDescription(json.getString("description"));
            setCode(json.getString("code"));
            setVersion(json.getString("version"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
