package com.tentinet.healthy.util;

import android.content.Intent;
import android.util.Log;

import com.tentinet.healthy.service.BroadcastActions;

import java.util.ArrayList;

public class FilterDataUtils {
	
	/**log开关标志*/
	private static final boolean DBT = true;
	
	/**实时步数数据头 name of extra data*/
	public static final String REAL_TIME_STEP_DATA = "realTimeStepData";
	/**实时温度数据头 name of extra data*/
	public static final String REAL_TIME_TEMP_DATA = "realTimeTempData";
	/**实时血压数据头 name of extra data*/
	public static final String REAL_TIME_BLOOD_PRESSURE_DATA = "realTimeBPData";
	/**实时血氧数据头 name of extra data*/
	public static final String REAL_TIME_PULSE_OXIMETER_DATA = "realTimePOData";
	/**实时心率数据头 name of extra data*/
	public static final String REAL_TIME_HEART_RATE_DATA = "realTimeHRData";
	/**实时脉搏数据头 name of extra data*/
	public static final String REAL_TIME_PULSE_RATE_DATA = "realTimePulseData";
	/**实时体重数据头 name of extra data*/
	public static final String REAL_TIME_WEIGHT_DATA = "realTimeWeightData";
	/**实时车轮圈数数据头 name of extra data*/
	public static final String REAL_TIME_CIRCLE_NUM_DATA = "realTimeCircleData";
	
	/**设备时间 name of extra data*/
	public static final String DEVICE_INFO_DATE_TIME = "dateTime";
	/**设备名称 name of extra data*/
	public static final String DEVICE_INFO_DEVICE_NAME = "deviceName";
	/**设备伴侣名称 name of extra data*/
	public static final String DEVICE_INFO_MATE_DEVICE_NAME = "mateDeviceName";	
	/**设备硬件配置 name of extra data*/
	public static final String DEVICE_INFO_CONFIG = "config";
	/**设备用户信息 name of extra data*/
	public static final String DEVICE_INFO_USER_INFO = "userInfo";
	/**设备电池电量 name of extra data*/
	public static final String DEVICE_INFO_POWER = "power";
	/**设备内存状态 name of extra data*/
	public static final String DEVICE_INFO_RAM = "ram";
	/**设备mac地址 name of extra data*/
	public static final String DEVICE_INFO_MAC = "mac";
	/**设备闹钟设置 name of extra data*/
	public static final String DEVICE_INFO_ALARM_SETTINGS = "alarmSettings";
	
	/**查询记录信息-步数 name of extra data*/
	public static final String CHECK_STEP_RECORD_RESPOND = "checkStepRecord";
	/**查询记录信息-温度 name of extra data*/
	public static final String CHECK_TEMP_RECORD_RESPOND = "checkTempRecord";
	/**查询记录信息-血压 name of extra data*/
	public static final String CHECK_BP_RECORD_RESPOND = "checkBPRecord";
	/**查询记录信息-血氧 name of extra data*/
	public static final String CHECK_PO_RECORD_RESPOND = "checkPORecord";
	/**查询记录信息-心率 name of extra data*/
	public static final String CHECK_HR_RECORD_RESPOND = "checkHRRecord";
	/**查询记录信息-脉搏 name of extra data*/
	public static final String CHECK_PULSE_RECORD_RESPOND = "checkPulseRecord";
	/**查询记录信息-体重 name of extra data*/
	public static final String CHECK_WEIGHT_RECORD_RESPOND = "checkWeightRecord";
	/**查询记录信息-车轮圈数 name of extra data*/
	public static final String CHECK_CN_RECORD_RESPOND = "checkCNRecord";
	/**查询记录信息-睡眠 name of extra data*/
	public static final String CHECK_SLEEP_RECORD_RESPOND = "checkSleepRecord";	
	
	/**开始读取数据包-步数 name of extra data*/
	public static final String BEGIN_READ_STEP_RESPOND = "beginReadStep";
	/**开始读取数据包-温度 name of extra data*/
	public static final String BEGIN_READ_TEMP_RESPOND = "beginReadTemp";
	/**开始读取数据包-血压 name of extra data*/
	public static final String BEGIN_READ_BP_RESPOND = "beginReadBP";
	/**开始读取数据包-血氧 name of extra data*/
	public static final String BEGIN_READ_PO_RESPOND = "beginReadPO";
	/**开始读取数据包-心率 name of extra data*/
	public static final String BEGIN_READ_HR_RESPOND = "beginReadHR";
	/**开始读取数据包-脉搏 name of extra data*/
	public static final String BEGIN_READ_PULSE_RESPOND = "beginReadPulse";
	/**开始读取数据包-体重 name of extra data*/
	public static final String BEGIN_READ_WEIGHT_RESPOND = "beginReadWeight";
	/**开始读取数据包-车轮圈数 name of extra data*/
	public static final String BEGIN_READ_CN_RESPOND = "beginReadCN";
	/**开始读取数据包-睡眠 name of extra data*/
	public static final String BEGIN_READ_SLEEP_RESPOND = "beginReadSleep";	
	
	/**停止读取数据包-步数 name of extra data*/
	public static final String END_READ_STEP_RESPOND = "endReadStep";
	/**停止读取数据包-温度 name of extra data*/
	public static final String END_READ_TEMP_RESPOND = "endReadTemp";
	/**停止读取数据包-血压 name of extra data*/
	public static final String END_READ_BP_RESPOND = "endReadBP";
	/**停止读取数据包-血氧 name of extra data*/
	public static final String END_READ_PO_RESPOND = "endReadPO";
	/**停止读取数据包-心率 name of extra data*/
	public static final String END_READ_HR_RESPOND = "endReadHR";
	/**停止读取数据包-脉搏 name of extra data*/
	public static final String END_READ_PULSE_RESPOND = "endReadPulse";
	/**停止读取数据包-体重 name of extra data*/
	public static final String END_READ_WEIGHT_RESPOND = "endReadWeight";
	/**停止读取数据包-车轮圈数 name of extra data*/
	public static final String END_READ_CN_RESPOND = "endReadCN";
	/**停止读取数据包-睡眠 name of extra data*/
	public static final String END_READ_SLEEP_RESPOND = "endReadSleep";	
	
	/**步数记录包 name of extra data*/
	public static final String STEP_RECORD_PACKAGE = "stepRecordPackage";	
	/**睡眠记录包 name of extra data*/
	public static final String SLEEP_RECORD_PACKAGE = "sleepRecordPackage";
	

	/**表示接收的是那种类型的数据：-1-默认值,1-步数信息，2-温度信息，3-血压信息，4-血氧信息，5-心率信息，6-脉搏信息，7-重量信息，8-车轮圈数，9-睡眠信息*/
	public static int whichTypeData = -1;
	/**提醒文字*/
	public static String promptText;
	
	
	
	
	/**
	 * 处理同步时间回复 cmd-0x01
	 *
	 * @version 1.0
	 * @createTime 2014-3-19,上午9:29:59
	 * @updateTime 2014-3-19,上午9:29:59
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @return Intent with 时间同步Action
	 */
	public static Intent synDateAndTime(){
		if(DBT) Log.d("wutl", "synDateAndTime");
		return new Intent(BroadcastActions.ACTION_DATE_TIME_RESPOND);
	}
	
	/**
	 * 处理设置设备回复 cmd-0x02
	 *
	 * @version 1.0
	 * @createTime 2014-3-19,上午9:31:41
	 * @updateTime 2014-3-19,上午9:31:41
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param b SubCmd
	 * @return Intent with 设置设备Action
	 */
	public static Intent setDeviceInfo(byte b){
		Intent intent = new Intent();
		switch(b){
		case 0x01:
			if(DBT) Log.d("wutl", "setNameRespond");
			intent.setAction(BroadcastActions.ACTION_SETNAME_RESPOND);
			break;
		case 0x02:
			if(DBT) Log.d("wutl", "setUserInfoRespond");
			intent.setAction(BroadcastActions.ACTION_USERINFO_RESPOND);
			break;
		case 0x03:
			if(DBT) Log.d("wutl", "getPairdDeviceRespond");
			intent.setAction(BroadcastActions.ACTION_SETPAIRDDEVICE_RESPOND);
			break;
		case 0x04:
			if(DBT) Log.d("wutl", "setAlarmRespond");
			intent.setAction(BroadcastActions.ACTION_SETALARM_RESPOND);
			break;
		case 0x05:
			if(DBT) Log.d("wutl", "setLongSeatRespond");
			intent.setAction(BroadcastActions.ACTION_SETLONGSEATTIME_RESPOND);
			break;			
		}
		return intent;
	}
	

	
	/**
	 * 申请实时数据监控ble端回复 
	 *
	 * @version 1.0
	 * @createTime 2014-3-19,下午2:35:03
	 * @updateTime 2014-3-19,下午2:35:03
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param intent 
	 * @param type 信息类型
	 */
	public static void registerRespond(Intent intent,byte type){
		switch(type){
		case 0x01:
			if(DBT) Log.d("wutl", "registerStepRespond");
			intent.setAction(BroadcastActions.ACTION_REGISTER_STEP_RESPOND);
			break;
		case 0x02:
			if(DBT) Log.d("wutl", "registerTempRespond");
			intent.setAction(BroadcastActions.ACTION_REGISTER_TEMP_RESPOND);
			break;
		case 0x03:
			if(DBT) Log.d("wutl", "registerBPRespond");
			intent.setAction(BroadcastActions.ACTION_REGISTER_BP_RESPOND);
			break;
		case 0x04:
			if(DBT) Log.d("wutl", "registerPORespond");
			intent.setAction(BroadcastActions.ACTION_REGISTER_PO_RESPOND);
			break;
		case 0x05:
			if(DBT) Log.d("wutl", "registerHRRespond");
			intent.setAction(BroadcastActions.ACTION_REGISTER_HR_RESPOND);
			break;
		case 0x06:
			if(DBT) Log.d("wutl", "registerPulseRespond");
			intent.setAction(BroadcastActions.ACTION_REGISTER_PULSE_RESPOND);
			break;
		case 0x07:
			if(DBT) Log.d("wutl", "registerWeightRespond");
			intent.setAction(BroadcastActions.ACTION_REGISTER_WEIGHT_RESPOND);
			break;
		case 0x08:
			if(DBT) Log.d("wutl", "registerCNRespond");
			intent.setAction(BroadcastActions.ACTION_REGISTER_CN_RESPOND);
			break;
		}		
	}
	
	/**
	 * 停止实时数据监控ble端回复
	 *
	 * @version 1.0
	 * @createTime 2014-3-19,下午2:35:43
	 * @updateTime 2014-3-19,下午2:35:43
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param intent
	 * @param type 信息类型
	 */
	public static void unregisterRespond(Intent intent,byte type){
		switch(type){
		case 0x01:
			if(DBT) Log.d("wutl", "unregisterStepRespond");
			intent.setAction(BroadcastActions.ACTION_UNREGISTER_STEP_RESPOND);
			break;
		case 0x02:
			if(DBT) Log.d("wutl", "unregisterTempRespond");
			intent.setAction(BroadcastActions.ACTION_UNREGISTER_TEMP_RESPOND);
			break;
		case 0x03:
			if(DBT) Log.d("wutl", "unregisterBPRespond");
			intent.setAction(BroadcastActions.ACTION_UNREGISTER_BP_RESPOND);
			break;
		case 0x04:
			if(DBT) Log.d("wutl", "unregisterPORespond");
			intent.setAction(BroadcastActions.ACTION_UNREGISTER_PO_RESPOND);
			break;
		case 0x05:
			if(DBT) Log.d("wutl", "unregisterHRRespond");
			intent.setAction(BroadcastActions.ACTION_UNREGISTER_HR_RESPOND);
			break;
		case 0x06:
			if(DBT) Log.d("wutl", "unregisterPulseRespond");
			intent.setAction(BroadcastActions.ACTION_UNREGISTER_PULSE_RESPOND);
			break;
		case 0x07:
			if(DBT) Log.d("wutl", "unregisterWeightRespond");
			intent.setAction(BroadcastActions.ACTION_UNREGISTER_WEIGHT_RESPOND);
			break;
		case 0x08:
			if(DBT) Log.d("wutl", "unregisterCNRespond");
			intent.setAction(BroadcastActions.ACTION_UNREGISTER_CN_RESPOND);
			break;
		}				
	}
	

	

	
	/**
	 * notifyFromAppRespond(byte[] data)
	 *app端发送提示信息，ble端回复的数据处理
	 *cmd -0x08,0x09,0x0A 
	 * @version 1.0
	 * @createTime 2014-3-19,下午3:22:35
	 * @updateTime 2014-3-19,下午3:22:35
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param data ble端回复数据byte数组
	 * @return
	 */
	public static Intent notifyFromAppRespond(byte[] data){
		Intent intent = new Intent();
		switch(data[0]){
		case 0x08:
			switch(data[1]){
			case 0x01:
				if(DBT) Log.d("wutl","msg respond");
				intent.setAction(BroadcastActions.ACTION_NOTIFY_MSG_RESPOND);
				break;
			case 0x02:
				if(DBT) Log.d("wutl","call respond");
				intent.setAction(BroadcastActions.ACTION_NOTIFY_CALL_RESPOND);
				break;
			case 0x03:
				if(DBT) Log.d("wutl","lp respond");
				intent.setAction(BroadcastActions.ACTION_NOTIFY_LOWPOWER_RESPOND);
				break;
			case 0x04:
				if(DBT) Log.d("wutl","alarm respond");
				intent.setAction(BroadcastActions.ACTION_NOTIFY_ALARM_RESPOND);
				break;
			}
			break;
		case 0x09:
			switch(data[1]){
			case 0x01:
				if(DBT) Log.d("wutl","upload msg respond");
				intent.setAction(BroadcastActions.ACTION_UPLOAD_MSG_RESPOND);
				break;
			case 0x02:
				if(DBT) Log.d("wutl","upload call respond");
				intent.setAction(BroadcastActions.ACTION_UPLOAD_CALL_RESPOND);
				break;
			case 0x03:
				if(DBT) Log.d("wutl","upload lp respond");
				intent.setAction(BroadcastActions.ACTION_UPLOAD_LOWPOWER_RESPOND);
				break;
			case 0x04:
				if(DBT) Log.d("wutl","upload alarm respond");
				intent.setAction(BroadcastActions.ACTION_UPLOAD_ALARM_RESPOND);
				break;
			}
			break;
		case 0x0A:
			switch(data[1]){
			case 0x01:
				if(DBT) Log.d("wutl","end msg respond");
				intent.setAction(BroadcastActions.ACTION_END_MSG_RESPOND);
				break;
			case 0x02:
				if(DBT) Log.d("wutl","end call respond");
				intent.setAction(BroadcastActions.ACTION_END_CALL_RESPOND);
				break;
			case 0x03:
				if(DBT) Log.d("wutl","end lp respond");
				intent.setAction(BroadcastActions.ACTION_END_LOWPOER_RESPOND);
				break;
			case 0x04:
				if(DBT) Log.d("wutl","end alarm respond");
				intent.setAction(BroadcastActions.ACTION_END_ALARM_RESPOND);
				break;
			}
			break;
		}
		return intent;
	}
	
	/**
	 * notifyFromBLE(byte[] data)
	 * BLE设备端发送提示信息给APP
	 * @version 1.0
	 * @createTime 2014-3-19,下午3:31:52
	 * @updateTime 2014-3-19,下午3:31:52
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param data ble设备端发送的提示信息
	 * @return
	 */
	public static Intent notifyFromBLE(byte[] data){
		Intent intent = new Intent();
		switch(data[1]){
		case 0x01:
			if(DBT) Log.d("wutl","ACTION_INFORM_LONGSEAT");
			intent.setAction(BroadcastActions.ACTION_INFORM_LONGSEAT);
			break;
		case 0x02:
			if(DBT) Log.d("wutl","ACTION_INFORM_EMERGENCY");
			intent.setAction(BroadcastActions.ACTION_INFORM_EMERGENCY);
			break;
		case 0x03:
			if(DBT) Log.d("wutl","ACTION_INFORM_ANSWER_CALL");
			intent.setAction(BroadcastActions.ACTION_INFORM_ANSWER_CALL);
			break;
		case 0x04:
			if(DBT) Log.d("wutl","ACTION_INFORM_REFUSE_CALL");
			intent.setAction(BroadcastActions.ACTION_INFORM_REFUSE_CALL);
			break;
		case 0x05:
			if(DBT) Log.d("wutl","ACTION_INFORM_MEMORY_FULL");
			intent.setAction(BroadcastActions.ACTION_INFORM_MEMORY_FULL);
			break;
		case 0x06:
			if(DBT) Log.d("wutl","ACTION_INFORM_SHOOT");
			intent.setAction(BroadcastActions.ACTION_INFORM_SHOOT);
			break;
		case 0x07:
			if(DBT) Log.d("wutl","ACTION_INFORM_POWEROFF");
			intent.setAction(BroadcastActions.ACTION_INFORM_POWEROFF);
			break;
		}
		return intent;
	}
	

	/**
	 * byte数组转换成字符串
	 * 
	 *
	 * @version 1.0
	 * @createTime 2014-3-20,上午9:02:07
	 * @updateTime 2014-3-20,上午9:02:07
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param data 读取ble设备名称回复byte数组
	 * @return
	 */
	public static String getNameFromBytes(byte []data){
		byte []tmp = new byte[data.length-8];
		for(int i=0;i<data.length-8;i++){
			tmp[i] = data[i+8];
		}
		return HexBytesUtils.getUnicodeString(tmp);
	}
	
	/**
	 * 将byte数组封装成MAC地址信息
	 *
	 * @version 1.0
	 * @createTime 2014-3-20,上午9:04:01
	 * @updateTime 2014-3-20,上午9:04:01
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param data 读取ble设备mac地址回复byte数组
	 * @return
	 */
	public static String getMacFromBytes(byte []data){
		return String.format("%02X", data[2])+":"+
				String.format("%02X", data[3])+":"+
				String.format("%02X", data[4])+":"+
				String.format("%02X", data[5])+":"+
				String.format("%02X", data[6])+":"+
				String.format("%02X", data[7]);
	}

	/**
	 * 
	 *本次记录读取结束回复 （finish all）
	 *
	 * @version 1.0
	 * @createTime 2014-3-20,下午3:35:30
	 * @updateTime 2014-3-20,下午3:35:30
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param intent
	 * @param data
	 */
	public static void finishAllRecordRespond(Intent intent,byte []data){
		switch(data[1]){
		case 0x01:
			intent.setAction(BroadcastActions.ACTION_FINISH_ALL_STEP_RESPOND);
			break;
		case 0x02:
			intent.setAction(BroadcastActions.ACTION_FINISH_ALL_TEMP_RESPOND);
			break;
		case 0x03:
			intent.setAction(BroadcastActions.ACTION_FINISH_ALL_BP_RESPOND);
			break;
		case 0x04:
			intent.setAction(BroadcastActions.ACTION_FINISH_ALL_PO_RESPOND);
			break;
		case 0x05:
			intent.setAction(BroadcastActions.ACTION_FINISH_ALL_HR_RESPOND);
			break;
		case 0x06:
			intent.setAction(BroadcastActions.ACTION_FINISH_ALL_PULSE_RESPOND);
			break;
		case 0x07:
			intent.setAction(BroadcastActions.ACTION_FINISH_ALL_WEIGHT_RESPOND);
			break;
		case 0x08:
			intent.setAction(BroadcastActions.ACTION_FINISH_ALL_CN_RESPOND);
			break;
		case 0x09:
			intent.setAction(BroadcastActions.ACTION_FINISH_ALL_SLEEP_RESPOND);
			break;
		}
	}
	


	
	public static int getWhichTypeData(){
		return whichTypeData;
	}
	


}
