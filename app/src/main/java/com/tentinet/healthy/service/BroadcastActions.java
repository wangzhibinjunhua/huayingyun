package com.tentinet.healthy.service;


/**
 * @Description 所有广播action，具体见描述
 * @author wutianlin
 * @version 1.0
 * @date 2014-3-18
 * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 *
 */
public class BroadcastActions {
	
	/**更新扫描到设备列表*/
	public static final String ACTION_UPDATE_DEVICES = "com.tentinet.ble.common.ACTION_UPDATE_DEVICES";
	/**新扫描到设备 extra name*/
	public static final String UPDATE_DEVICES = "updateDevices"; 
	
	/**已连接*/
	public static final String ACTION_GATT_CONNECTED = "com.tentinet.ble.common.ACTION_GATT_CONNECTED";
	/**未连接的，（可描述状态可以是连接失败，或连接丢失等）*/
	public static final String ACTION_GATT_DISCONNECTED = "com.tentinet.ble.common.ACTION_GATT_DISCONNECTED";
	/**已搜索Gatt Service*/
	public static final String ACTION_GATT_SERVICES_DISCOVERED = "com.tentinet.ble.common.ACTION_GATT_SERVICES_DISCOVERED";
	/**有可用数据*/
	public static final String ACTION_DATA_AVAILABLE = "com.tentinet.ble.common.ACTION_DATA_AVAILABLE";
	/**具体可用数据，可能根据具体情况进行分化*/
	public static final String EXTRA_DATA = "com.tentinet.ble.common.EXTRA_DATA";

	/**读取RSSI广播*/
	public static final String ACTION_READ_RSSI = "com.tentinet.ble.common.ACTION_READ_RSSI";
	/**防丢广播*/
	public static final String ACTION_ANTILOST_ALRAM = "com.tentinet.ble.comom.ACTION_ANTILOST_ALRAM";
	/**rssi value extra name*/
	public static final String VALUE_RSSI = "rssiValue";
	/**rssi enable/disable alram extra name*/
	public static final String ENABLE_DISABLE_RSSI_ALARM = "com.tentinet.ble.common.ENABLE_DISABLE_RSSI_ALARM";
	
	/**时间同步回复*/
	public static final String ACTION_DATE_TIME_RESPOND = "com.tentinet.ble.common.ACTION_DATE_TIME_RESPOND";
	
	//***********************设置设备信息回复**************************
	/**设置名称回复*/
	public static final String ACTION_SETNAME_RESPOND = "com.tentinet.ble.comon.ACTION_SETNAME_RESPOND";
	/**设置用户信息回复*/
	public static final String ACTION_USERINFO_RESPOND = "com.tentinet.ble.com.ACTION_USERINFO_RESPOND";
	/**设置配对手环名称信息回复*/
	public static final String ACTION_SETPAIRDDEVICE_RESPOND = "com.tentinet.ble.com.ACTION_SETPAIRDDEVICE_RESPOND";
	/**设置闹钟回复*/
	public static final String ACTION_SETALARM_RESPOND = "com.tentinet.ble.com.ACTION_SETALARM_RESPOND";
	/**设置久坐提醒时长回复*/
	public static final String ACTION_SETLONGSEATTIME_RESPOND = "com.tentinet.ble.com.ACTION_SETALARM_RESPOND";
	
	//***************************** 实时监控模式申请以及停止回复**********************	
	/**申请实时步数信息监控回复 */
	public static final String ACTION_REGISTER_STEP_RESPOND = "com.tentinet.ble.ACTION_REGISTER_STEP_RESPOND";
	/**停止实时步数信息监控回复 */
	public static final String ACTION_UNREGISTER_STEP_RESPOND = "com.tentinet.ble.ACTION_UNREGISTER_STEP_RESPOND";
	/**申请实时温度信息监控回复 */
	public static final String ACTION_REGISTER_TEMP_RESPOND = "com.tentinet.ble.ACTION_REGISTER_TEMP_RESPOND";
	/**停止实时温度信息监控回复 */
	public static final String ACTION_UNREGISTER_TEMP_RESPOND = "com.tentinet.ble.ACTION_UNREGISTER_TEMP_RESPOND";
	/**申请实时血压信息监控回复 */
	public static final String ACTION_REGISTER_BP_RESPOND = "com.tentinet.ble.ACTION_REGISTER_BP_RESPOND";
	/**停止实时血压信息监控回复 */
	public static final String ACTION_UNREGISTER_BP_RESPOND = "com.tentinet.ble.ACTION_UNREGISTER_BP_RESPOND";
	/**申请实时血氧信息监控回复 */
	public static final String ACTION_REGISTER_PO_RESPOND = "com.tentinet.ble.ACTION_REGISTER_PO_RESPOND";
	/**停止实时血氧信息监控回复 */
	public static final String ACTION_UNREGISTER_PO_RESPOND = "com.tentinet.ble.ACTION_UNREGISTER_PO_RESPOND";
	/**申请实时心率信息监控回复 */
	public static final String ACTION_REGISTER_HR_RESPOND = "com.tentinet.ble.ACTION_REGISTER_HR_RESPOND";
	/**停止实时心率信息监控回复 */
	public static final String ACTION_UNREGISTER_HR_RESPOND = "com.tentinet.ble.ACTION_UNREGISTER_HR_RESPOND";
	/**申请实时脉搏信息监控回复 */
	public static final String ACTION_REGISTER_PULSE_RESPOND = "com.tentinet.ble.ACTION_REGISTER_PULSE_RESPOND";
	/**停止实时脉搏信息监控回复 */
	public static final String ACTION_UNREGISTER_PULSE_RESPOND = "com.tentinet.ble.ACTION_UNREGISTER_PULSE_RESPOND";
	/**申请实时体重信息监控回复 */
	public static final String ACTION_REGISTER_WEIGHT_RESPOND = "com.tentinet.ble.ACTION_REGISTER_WEIGHT_RESPOND";
	/**停止实时体重信息监控回复 */
	public static final String ACTION_UNREGISTER_WEIGHT_RESPOND = "com.tentinet.ble.ACTION_UNREGISTER_WEIGHT_RESPOND";
	/**申请实时车轮圈数信息监控回复 */
	public static final String ACTION_REGISTER_CN_RESPOND = "com.tentinet.ble.ACTION_REGISTER_CN_RESPOND";
	/**停止实时车轮圈数信息监控回复 */
	public static final String ACTION_UNREGISTER_CN_RESPOND = "com.tentinet.ble.ACTION_UNREGISTER_CN_RESPOND";

	
	//****************************实时监控模式数据传输**********************
	/**实时步数数据 */
	public static final String ACTION_STEP_DATA  = "com.tentinet.ble.ACTION_STEP_DATA";
	/**实时温度数据 */
	public static final String ACTION_TEMP_DATA = "com.tentinet.ble.ACTION_TEMP_DATA";
	/**实时血压数据 */
	public static final String ACTION_BP_DATA = "com.tentinet.ble.ACTION_BP_DATA";
	/**实时血氧数据 */
	public static final String ACTION_PO_DATA = "com.tentinet.ble.ACTION_PO_DATA";
	/**实时心率数据 */
	public static final String ACTION_HR_DATA = "com.tentinet.ble.ACTION_HR_DATA";
	/**实时脉搏数据 */
	public static final String ACTION_PULSE_DATA = "com.tentinet.ble.ACTION_PULSE_DATA";
	/**实时体重数据 */
	public static final String ACTION_WEIGHT_DATA = "com.tentinet.ble.ACTION_WEIGHT_DATA";
	/**实时车轮圈数数据 */
	public static final String ACTION_CN_DATA = "com.tentinet.ble.ACTION_CN_DATA";
	
	
	//******************************查询记录信息回复,开始读取数据包回复,本次记录读取结束回复**********************
	/**数据包为空*/
	public static final String ACTION_CHECK_MEMORY_EMPTY = "com.tentinet.ble.ACTION_CHECK_MEMORY_EMPTY";
	/**查询步数信息回复 */
	public static final String ACTION_CHECK_STEP_MEMORY_RESPOND = "com.tentinet.ble.ACTION_CHECK_STEP_MEMORY_RESPOND";
	/**开始步数数据上传信息回复 */
	public static final String ACTION_BEGIN_READ_STEP_RESPOND = "com.tentinet.ble.ACTION_BEGIN_READ_STEP_RESPOND";
	/**结束步数数据上传信息回复*/
	public static final String ACTION_END_READ_STEP_RESPOND = "com.tentinet.ble.ACTION_END_READ_STEP_RESPOND";
	/**本次步数信息读取结束回复*/
	public static final String ACTION_FINISH_ALL_STEP_RESPOND = "com.tentinet.ble.ACTION_FINISH_ALL_STEP_RESPOND";
	/**查询温度信息回复 */
	public static final String ACTION_CHECK_TEMP_MEMORY_RESPOND = "com.tentinet.ble.ACTION_CHECK_TEMP_MEMORY_RESPOND";
	/**开始温度数据上传信息回复 */
	public static final String ACTION_BEGIN_READ_TEMP_RESPOND = "com.tentinet.ble.ACTION_BEGIN_READ_TEMP_RESPOND";
	/**结束温度数据上传信息回复*/
	public static final String ACTION_END_READ_TEMP_RESPOND = "com.tentinet.ble.ACTION_END_READ_TEMP_RESPOND";
	/**本次温度信息读取结束回复*/
	public static final String ACTION_FINISH_ALL_TEMP_RESPOND = "com.tentinet.ble.ACTION_FINISH_ALL_TEMP_RESPOND";	
	/**查询血压信息回复 */
	public static final String ACTION_CHECK_BP_MEMORY_RESPOND = "com.tentinet.ble.ACTION_CHECK_BP_MEMORY_RESPOND";
	/**开始血压数据上传信息回复 */
	public static final String ACTION_BEGIN_READ_BP_RESPOND = "com.tentinet.ble.ACTION_BEGIN_READ_BP_RESPOND";
	/**结束血压数据上传信息回复*/
	public static final String ACTION_END_READ_BP_RESPOND = "com.tentinet.ble.ACTION_END_READ_BP_RESPOND";
	/**本次血压信息读取结束回复*/
	public static final String ACTION_FINISH_ALL_BP_RESPOND = "com.tentinet.ble.ACTION_FINISH_ALL_BP_RESPOND";	
	/**查询血氧信息回复 */
	public static final String ACTION_CHECK_PO_MEMORY_RESPOND = "com.tentinet.ble.ACTION_CHECK_PO_MEMORY_RESPOND";
	/**开始血氧数据上传信息回复 */
	public static final String ACTION_BEGIN_READ_PO_RESPOND = "com.tentinet.ble.ACTION_BEGIN_READ_PO_RESPOND";
	/**结束血氧数据上传信息回复*/
	public static final String ACTION_END_READ_PO_RESPOND = "com.tentinet.ble.ACTION_END_READ_PO_RESPOND";
	/**本次血氧信息读取结束回复*/
	public static final String ACTION_FINISH_ALL_PO_RESPOND = "com.tentinet.ble.ACTION_FINISH_ALL_PO_RESPOND";	
	/**查询心率信息回复 */
	public static final String ACTION_CHECK_HR_MEMORY_RESPOND = "com.tentinet.ble.ACTION_CHECK_HR_MEMORY_RESPOND";
	/**开始心率数据上传信息回复 */
	public static final String ACTION_BEGIN_READ_HR_RESPOND = "com.tentinet.ble.ACTION_BEGIN_READ_HR_RESPOND";
	/**结束心率数据上传信息回复*/
	public static final String ACTION_END_READ_HR_RESPOND = "com.tentinet.ble.ACTION_END_READ_HR_RESPOND";
	/**本次心率信息读取结束回复*/
	public static final String ACTION_FINISH_ALL_HR_RESPOND = "com.tentinet.ble.ACTION_FINISH_ALL_HR_RESPOND";	
	/**查询脉搏信息回复 */
	public static final String ACTION_CHECK_PULSE_MEMORY_RESPOND = "com.tentinet.ble.ACTION_CHECK_PULSE_MEMORY_RESPOND";
	/**开始脉搏数据上传信息回复 */
	public static final String ACTION_BEGIN_READ_PULSE_RESPOND = "com.tentinet.ble.ACTION_BEGIN_READ_PULSE_RESPOND";
	/**结束脉搏数据上传信息回复*/
	public static final String ACTION_END_READ_PULSE_RESPOND = "com.tentinet.ble.ACTION_END_READ_PULSE_RESPOND";
	/**本次脉搏信息读取结束回复*/
	public static final String ACTION_FINISH_ALL_PULSE_RESPOND = "com.tentinet.ble.ACTION_FINISH_ALL_PULSE_RESPOND";	
	/**查询体重信息回复 */
	public static final String ACTION_CHECK_WEIGHT_MEMORY_RESPOND = "com.tentinet.ble.ACTION_CHECK_WEIGHT_MEMORY_RESPOND";
	/**开始体重数据上传信息回复 */
	public static final String ACTION_BEGIN_READ_WEIGHT_RESPOND = "com.tentinet.ble.ACTION_BEGIN_READ_WEIGHT_RESPOND";
	/**结束体重数据上传信息回复*/
	public static final String ACTION_END_READ_WEIGHT_RESPOND = "com.tentinet.ble.ACTION_END_READ_WEIGHT_RESPOND";
	/**本次重量信息读取结束回复*/
	public static final String ACTION_FINISH_ALL_WEIGHT_RESPOND = "com.tentinet.ble.ACTION_FINISH_ALL_WEIGHT_RESPOND";	
	/**查询车轮圈数信息回复 */
	public static final String ACTION_CHECK_CN_MEMORY_RESPOND = "com.tentinet.ble.ACTION_CHECK_CN_MEMORY_RESPOND";
	/**开始车轮圈数数据上传信息回复 */
	public static final String ACTION_BEGIN_READ_CN_RESPOND = "com.tentinet.ble.ACTION_BEGIN_READ_CN_RESPOND";
	/**结束车轮圈数数据上传信息回复*/
	public static final String ACTION_END_READ_CN_RESPOND = "com.tentinet.ble.ACTION_END_READ_CN_RESPOND";
	/**本次车轮圈数信息读取结束回复*/
	public static final String ACTION_FINISH_ALL_CN_RESPOND = "com.tentinet.ble.ACTION_FINISH_ALL_CN_RESPOND";	
	/**查询睡眠信息回复 */
	public static final String ACTION_CHECK_SLEEP_MEMORY_RESPOND = "com.tentinet.ble.ACTION_CHECK_CN_MEMORY_RESPOND";
	/**开始睡眠数据上传信息回复 */
	public static final String ACTION_BEGIN_READ_SLEEP_RESPOND = "com.tentinet.ble.ACTION_BEGIN_READ_CN_RESPOND";
	/**结束睡眠数据上传信息回复*/
	public static final String ACTION_END_READ_SLEEP_RESPOND = "com.tentinet.ble.ACTION_END_READ_CN_RESPOND";
	/**本次睡眠信息读取结束回复*/
	public static final String ACTION_FINISH_ALL_SLEEP_RESPOND = "com.tentinet.ble.ACTION_FINISH_ALL_CN_RESPOND";		
	
	//********************记录数据上传****************************
	public static final String EXTRA_RECORD_DATA_INFO_BEAN = "extra_data_info_bean";
	/**步数记录数据上传*/
	public static final String ACTION_STEP_MEMORY_DATA  = "com.tentinet.ble.ACTION_STEP_MEMORY_DATA";
	/**温度记录数据上传*/
	public static final String ACTION_TEMP_MEMORY_DATA = "com.tentinet.ble.ACTION_TEMP_MEMORY_DATA";
	/**温度记录数据上传*/
	public static final String ACTION_BP_MEMORY_DATA = "com.tentinet.ble.ACTION_BP_MEMORY_DATA";
	/**血氧记录数据上传*/
	public static final String ACTION_PO_MEMORY_DATA = "com.tentinet.ble.ACTION_PO_MEMORY_DATA";
	/**心率记录数据上传*/
	public static final String ACTION_HR_MEMORY_DATA = "com.tentinet.ble.ACTION_HR_MEMORY_DATA";
	/**体重记录数据上传*/
	public static final String ACTION_WEIGHT_MEMORY_DATA = "com.tentinet.ble.ACTION_WEIGHT_MEMORY_DATA";
	/**车轮圈数记录数据上传*/
	public static final String ACTION_CN_MEMORY_DATA = "com.tentinet.ble.ACTION_CN_MEMORY_DATA";	
	/**睡眠记录数据上传*/
	public static final String ACTION_SLEEP_MEMORY_DATA = "com.tentinet.ble.ACTION_SLEEP_MEMORY_DATA";		
	
	/**提示信息通知_短信信息*/
	public static final String ACTION_NOTIFY_MSG_RESPOND = "com.tentinet.ble.ACTION_NOTIFY_MSG_RESPOND";
	/**申请数据上传_短信信息*/
	public static final String ACTION_UPLOAD_MSG_RESPOND = "com.tentinet.ble.ACTION_UPLOAD_MSG_RESPOND";
	/**提示信息结束_短信信息*/
	public static final String ACTION_END_MSG_RESPOND = "com.tentinet.ble.ACTION_END_MSG_RESPOND";
	
	/**提示信息通知_来电信息*/
	public static final String ACTION_NOTIFY_CALL_RESPOND = "com.tentinet.ble.ACTION_NOTIFY_CALL_RESPOND";
	/**申请数据上传_来电信息*/
	public static final String ACTION_UPLOAD_CALL_RESPOND = "com.tentinet.ble.ACTION_UPLOAD_CALL_RESPOND";
	/**提示信息结束_来电信息*/
	public static final String ACTION_END_CALL_RESPOND = "com.tentinet.ble.ACTION_END_CALL_RESPOND";
	
	/**提示信息通知_闹钟信息*/
	public static final String ACTION_NOTIFY_ALARM_RESPOND = "com.tentinet.ble.ACTION_NOTIFY_ALARM_RESPOND";
	/**申请数据上传_闹钟信息*/
	public static final String ACTION_UPLOAD_ALARM_RESPOND = "com.tentinet.ble.ACTION_UPLOAD_ALARM_RESPOND";
	/**提示信息结束_闹钟信息*/
	public static final String ACTION_END_ALARM_RESPOND = "com.tentinet.ble.ACTION_END_ALARM_RESPOND";
	
	/**提示信息通知_低电信息*/
	public static final String ACTION_NOTIFY_LOWPOWER_RESPOND = "com.tentinet.ble.ACTION_NOTIFY_LOWPOWER_RESPOND";
	/**申请数据上传_低电信息*/
	public static final String ACTION_UPLOAD_LOWPOWER_RESPOND = "com.tentinet.ble.ACTION_UPLOAD_LOWPOWER_RESPOND";
	/**提示信息结束_低电信息*/
	public static final String ACTION_END_LOWPOER_RESPOND = "com.tentinet.ble.ACTION_END_LOWPOER_RESPOND";
	
	/**BLE通知事件_久坐提醒*/
	public static final String ACTION_INFORM_LONGSEAT = "com.tentinet.ble.ACTION_INFORM_LONGSEAT";
	
	/**BLE通知事件_紧急情况（求救等）*/
	public static final String ACTION_INFORM_EMERGENCY = "com.tentinet.ble.ACTION_INFORM_EMERGENCY";
	
	/**BLE通知事件_接听电话*/
	public static final String ACTION_INFORM_ANSWER_CALL = "com.tentinet.ble.ACTION_INFORM_ANSWER_CALL";
	
	/**BLE通知事件_拒接电话*/
	public static final String ACTION_INFORM_REFUSE_CALL = "com.tentinet.ble.ACTION_INFORM_REFUSE_CALL";
	
	/**BLE通知事件_BLE设备存储信息已满*/
	public static final String ACTION_INFORM_MEMORY_FULL = "com.tentinet.ble.ACTION_INFORM_MEMORY_FULL";
	
	/**BLE通知事件_抓拍*/
	public static final String ACTION_INFORM_SHOOT ="com.tentinet.ble.ACTION_INFORM_SHOOT";
	
	/**BLE通知事件_关闭手机*/
	public static final String ACTION_INFORM_POWEROFF = "com.tentinet.ble.ACTION_INFORM_POWEROFF";
	
	/**BLE设备时间*/
	public static final String ACTION_DEVICE_INFO_TIME = "com.tentinet.ble.ACTION_DEVICE_INFO_TIME";
	
	/**ble设备名称*/
	public static final String ACTION_DEVICE_INFO_NAME = "com.tentinet.ble.ACTION_DEVICE_INFO_NAME";
	
	/**ble设备伴侣名称*/
	public static final String ACTION_DEVICE_INFO_MATE_NAME = "com.tentinet.ble.ACTION_DEVICE_INFO_MATE_NAME";
	
	/**ble设备硬件配置*/
	public static final String ACTION_DEVICE_INFO_CONFIG = "com.tentinet.ble.ACTION_DEVICE_INFO_CONFIG";
	
	/**ble设备用户信息*/
	public static final String ACTION_DEVICE_INFO_USERINFO = "com.tentinet.ble.ACTION_DEVICE_INFO_USERINFO";
	
	/**ble设备电池电量*/
	public static final String ACTION_DEVICE_INFO_POWER = "com.tentinet.ble.ACTION_DEVICE_INFO_POWER";
	
	/**ble设备内存状态*/
	public static final String ACTION_DEVICE_INFO_RAM = "com.tentinet.ble.ACTION_DEVICE_INFO_RAM";
	
	/**ble设备mac*/
	public static final String ACTION_DEVICE_INFO_MAC = "com.tentinet.ble.ACTION_DEVICE_INFO_MAC";
	
	/**ble设备闹钟设置*/
	public static final String ACTION_DEVICE_INFO_ALARMSETTINGS = "com.tentinet.ble.ACTION_DEVICE_INFO_ALARMSETTINGS";
	
	//************************************OTA*****************************************************
	/**OTA状态改变*/
	public static final String ACTION_OTA_UPDATE_STATE = "com.tentinet.ble.ACTION_OTA_UPDATE_STATE";
	/**response 封装*/
	public static final String EXTRA_OTA_STATE = "extraOtaState";
	
	/**开始ota*/
	public static final String ACTION_LOAD_BIN = "com.tentinet.ble.ACTION_LOAD_BIN";
	/**执行新bin文件*/
	public static final String ACTION_EXECUTION = "com.tentinet.ble.ACTION_EXECUTION";
	/**ota过程更新界面*/
	public static final String ACTION_UPDATE_STATE_TO_UI = "com.tentinet.ble.ACTION_UPDATE_STATE_TO_UI";
		
	//******************************************Activity 到 Service广播**************************************************
	public static final String EXTRA_NAME_DATA = "exraData";

	public static final String EXTRA_DEVICENAME_DATA = "exraDeviceName";

	public static final String EXTRA_UUID = "extraUUID";
	
	public static final String EXTRA_LEVEL = "extraLevel";
	/**BLEManager 初始化结束*/
	public static final String ACTION_BLEMANAGER_INIT = "com.tentinet.ble.ACTION_BLEMANAGER_INIT";
	/**关闭gatt连接*/
	public static final String  ACTION_CLOSE_GATT = "com.tentinet.ble.ACTION_CLOSE_GATT";
	/**扫描*/
	public static final String ACTION_START_LE_SCAN = "com.tentinet.ble.ACTION_START_LE_SCAN";
	/**停止扫描*/
	public static final String ACTION_STOP_LE_SCAN = "com.tentinet.ble.ACTION_STOP_LE_SCAN";
	/**连接*/
	public static final String ACTION_CONNECT = "com.tentinet.ble.ACTION_CONNECT";
	/**重连*/
	public static final String ACTION_RECONNECT = "com.tentinet.ble.ACTION_RECONNECT";
	/**稳定连接*/
	public static final String ACTION_STEADY_CONNECT = "com.tentinet.ble.ACTION_STEADY_CONNECT";
	/**断开*/
	public static final String ACTION_DISCONNECT = "com.tentinet.ble.ACTION_DISCONNECT";
	/**打开防丢*/
	public static final String ACTION_START_READ_RSSI = "com.tentinet.ble.ACTION_START_READ_RSSI";	
	/**关闭防丢*/
	public static final String ACTION_STOP_READ_RSSI = "com.tentinet.ble.ACTION_STOP_READ_RSSI";
	/**读取TX Power Level值*/
	public static final String ACTION_READ_TX_POWER_LEVEL = "com.tentinet.ble.ACTION_READ_TX_POWER_LEVEL";	
	/**读取*/
	public static final String ACTION_IMMEDIATE_ALERT_LEVEL = "com.tentinet.ble.ACTION_IMMEDIATE_ALERT_LEVEL";
	/**向ble写数据*/
	public static final String ACTION_WRITE_TO_SERVICE = "com.tentinet.ble.ACTION_WRITE_TO_SERVICE";
	/**读取指定Characteristic*/
	public static final String ACTION_READ_CHARACTERISTIC = "com.tentinet.ble.ACTION_READ_CHARACTERISTIC";
	/**订阅指定Characteristic的Notification*/
	public static final String ACTION_SET_NOTIFICATION_CHARACTERISTIC = "com.tentinet.ble.ACTION_SET_NOTIFICATION_CHARACTERISTIC";
}
