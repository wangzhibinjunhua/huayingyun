package com.tentinet.healthy.interf;

/**
 * <h3>支持，打开蓝牙时接口</h3>
 * TODO
 * <h3>Author</h3> （你的姓名）
 * <h3>Date</h3> 2016/3/28 10:47
 * <h3>Copyright</h3> Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public interface OpenBleListener {
    /**
     * 不支持4.0蓝牙
     * param type 类型
     */
    public void onNoSupport();

    /**
     * 4.0蓝牙没有开启
     * param type 类型
     */
    public void onNoOpen();

    /**
     * 打开蓝牙
     * param type 类型
     */
    public void onOpen();

}
