package com.tentinet.healthy.bean;

import java.io.Serializable;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/4/13 17:34
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class HomeMenuBean implements Serializable {

    /**
     * 设备名称
     */
    private String name;
    /**
     * 设备图片地址
     */
    private int url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }
}
