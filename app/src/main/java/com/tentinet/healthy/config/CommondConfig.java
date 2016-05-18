package com.tentinet.healthy.config;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/4/11 9:23
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class CommondConfig {

    /**
     * 血压计和血糖仪
     */
    public static final String BLOOD_AND_GLUCOSE_METER_SERVICE = "00001000-0000-1000-8000-00805f9b34fb";

    /**
     * 血压计和血糖仪可写BluetoothGattCharacteristic
     */
    public static final String BLOOD_AND_GLUCOSE_METER_WRITE_CHARA = "00001001-0000-1000-8000-00805f9b34fb";
    /**
     * 血压计和血糖仪可读BluetoothGattCharacteristic
     */
    public static final String BLOOD_AND_GLUCOSE_METER_READ_CHARA = "00001002-0000-1000-8000-00805f9b34fb";


    /**
     * 耳温计
     */
    public static final String EAR_THERMOMETER_SERVICE = "0000ffb0-0000-1000-8000-00805f9b34fb";

    /**
     * 耳温计可写BluetoothGattCharacteristic
     */
    public static final String EAR_THERMOMETER_SERVICE_WRITE_CHARA = "0000ffb1-0000-1000-8000-00805f9b34fb";
    /**
     * 耳温计可读BluetoothGattCharacteristic
     */
    public static final String EAR_THERMOMETER_SERVICE_READ_CHARA = "0000ffb2-0000-1000-8000-00805f9b34fb";

    /**
     * 血压计名称
     */
    public static final String BLOOD_NAME="BPM";
    /**
     * 血糖仪名称
     */
    public static final String GLUCOSE_NAME="BGM";

    /**
     * 耳温枪名称
     */
    public static final String EAR_THERMOMETER_NAME="MEDXING";

}
