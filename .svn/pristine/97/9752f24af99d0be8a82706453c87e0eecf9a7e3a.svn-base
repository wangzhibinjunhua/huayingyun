package com.tentinet.healthy.interf;


import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Context;


import com.tentinet.healthy.util.HexBytesUtils;
import com.tentinet.healthy.util.Text2MatrixUtils;

import java.util.Calendar;
import java.util.TimeZone;

public class LeRequest {	
	
	
	/**ble管理类*/
	private BleManager bleManager;

	/**
	 * 构造函数
	 * 
	 * @version 1.0
	 * @createTime 2014-3-18,下午1:27:28
	 * @updateTime 2014-3-18,下午1:27:28
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param context 上下文，当前activity
	 */
	public LeRequest(Context context,BleManagerListener listener){
		bleManager = new BleManager(context,listener);
	}
	
	public LeRequest(BleManager bleManager){
		this.bleManager = bleManager;
	}
	
	
	/**
	 * 回收gatt，蓝牙奔溃时调用
	 *
	 * @version 1.0
	 * @createTime 2014-4-3,下午1:57:17
	 * @updateTime 2014-4-3,下午1:57:17
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 */
	public void closeGatt(){
		bleManager.close();
	}
	
	
	/**
	 * 开始扫描
	 *
	 * @version 1.0
	 * @createTime 2014-3-26,下午4:51:24
	 * @updateTime 2014-3-26,下午4:51:24
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 */
	public void startLeScan(){
		bleManager.startLeScan();
	}
	
	/**
	 * 停止扫描
	 *
	 * @version 1.0
	 * @createTime 2014-3-26,下午4:51:37
	 * @updateTime 2014-3-26,下午4:51:37
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 */
	public void stopLeScan(){
		bleManager.stopLeScan();
	}
	
	/**
	 * 根据Service扫描蓝牙设备
	 *
	 * @version 1.0
	 * @createTime 2014-4-14,上午9:35:26
	 * @updateTime 2014-4-14,上午9:35:26
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param uuids
	 */
	public void startLeScanByService(String[] uuids){
		bleManager.startLeScanByUUID(uuids);
	}
	
	
	/**
	 * 连接
	 *
	 * @version 1.0
	 * @createTime 2014-3-26,下午4:51:48
	 * @updateTime 2014-3-26,下午4:51:48
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param address
	 */
	public void connect(String address){
		bleManager.connect(address);
	}	
	
	
	/**
	 * 重连 
	 *
	 * @version 1.0
	 * @createTime 2014-4-17,下午4:48:56
	 * @updateTime 2014-4-17,下午4:48:56
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 */
	public void reconnect(){
		bleManager.reconnect();
	}
	
	
	/**
	 * 断开
	 *
	 * @version 1.0
	 * @createTime 2014-3-26,下午4:52:04
	 * @updateTime 2014-3-26,下午4:52:04
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 */
	public void disconnect(){
		bleManager.disconnect();
	}
	

	
	/**
	 * 向ble写数据
	 *
	 * @version 1.0
	 * @createTime 2014-3-26,下午6:52:58
	 * @updateTime 2014-3-26,下午6:52:58
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param data
	 */
	public void write16Bytes(BluetoothGattCharacteristic chara,byte data[]){
		bleManager.writeValue(chara, data);
	}
	
	/**
	 * 读取BluetoothGattCharacteristic
	 *
	 * @version 1.0
	 * @createTime 2014-3-26,下午7:14:32
	 * @updateTime 2014-3-26,下午7:14:32
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gattCharacteristic
	 */
	public void readCharactersitc(BluetoothGattCharacteristic gattCharacteristic){
		bleManager.readCharacteristic(gattCharacteristic);
	}
	
	/**
	 * 订阅BluetoothGattCharacteristic
	 *
	 * @version 1.0
	 * @createTime 2014-3-26,下午7:14:47
	 * @updateTime 2014-3-26,下午7:14:47
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gattCharacteristic
	 * @param enable
	 */
	public void setNotification(BluetoothGattCharacteristic gattCharacteristic,boolean enable){
		bleManager.setCharacteristicNotification(gattCharacteristic, enable);
	}
	
	/**
	 * 订阅Notification，写入BluetoothGattDescriptor
	 *
	 * @version 1.0
	 * @createTime 2014-4-14,上午9:40:40
	 * @updateTime 2014-4-14,上午9:40:40
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gattCharacteristic
	 * @param descriptor
	 * @param enable
	 */
	public void setNotification(BluetoothGattCharacteristic gattCharacteristic,
			BluetoothGattDescriptor descriptor,boolean enable){
		bleManager.setCharacteristicNotification(gattCharacteristic, descriptor, enable);
	}
	
	
	/**
	 * 
	 * 同步时间 
	 * 
	 * @version 1.0
	 * @createTime 2014-3-18,下午1:28:32
	 * @updateTime 2014-3-18,下午1:28:32
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gattCharacteristic 可写characteristic
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 时
	 * @param minute 分
	 * @param second 秒
	 * @param weekday 星期几
	 *  
	 */
	public void synDateTime(BluetoothGattCharacteristic gattCharacteristic,
				int year,int month,int day,int hour,int minute,int second,int weekday){
		byte []send = new byte[9];
		send[0] = (byte)Integer.parseInt("01", 16);
		byte []tmp = HexBytesUtils.int2bytes(year);
		send[1] = tmp[0];		
		send[2] = tmp[1];
		send[3] = (byte)(0xff&month);
		send[4] = (byte)(0xff&day);
		send[5] = (byte)(0xff&hour);
		send[6] = (byte)(0xff&minute);
		send[7] = (byte)(0xff&second);	
		send[8] = (byte)(0xff&weekday);
		bleManager.writeValue(gattCharacteristic, send);
	}
	
	/**
	 * 同步系统时间
	 *
	 * @version 1.0
	 * @createTime 2014-3-27,下午2:56:00
	 * @updateTime 2014-3-27,下午2:56:00
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 *@param gattCharacteristic 可写BluetoothGattCharacteristic
	 */
	public void synDateTime(BluetoothGattCharacteristic gattCharacteristic){
		Calendar synCal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
		int year = synCal.get(Calendar.YEAR);
		int month = synCal.get(Calendar.MONTH);
		int day = synCal.get(Calendar.DAY_OF_MONTH);
		int hour = synCal.get(Calendar.HOUR_OF_DAY);
		int minute = synCal.get(Calendar.MINUTE);
		int second = synCal.get(Calendar.SECOND);
		int weekday = synCal.get(Calendar.DAY_OF_WEEK);
		byte []send = new byte[9];
		send[0] = (byte)Integer.parseInt("01", 16);		
		byte []tmp = HexBytesUtils.int2bytes(year);
		send[1] = tmp[0];		
		send[2] = tmp[1];
		send[3] = (byte)(0xff&month);
		send[4] = (byte)(0xff&day);
		send[5] = (byte)(0xff&hour);
		send[6] = (byte)(0xff&minute);
		send[7] = (byte)(0xff&second);	
		send[8] = (byte)(0xff&weekday);
		bleManager.writeValue(gattCharacteristic, send);
	}
	
	
	/**
	 * 设置设备名称，设备名称不超过18个字节
	 * 
	 * @version 1.0
	 * @createTime 2014-3-18,下午1:29:24
	 * @updateTime 2014-3-18,下午1:29:24
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gattCharacteristic 可写BluetoothGattCharacteristic
	 * @param name 设备名称
	 * @return 是否正确设置名称
	 */
	public boolean setDeviceName(BluetoothGattCharacteristic gattCharacteristic,String name){		
		byte[] tmp = HexBytesUtils.getUnicodeBytes(name);
		if(tmp.length>18){
			return false;
		}
		byte []send = new byte[tmp.length+2];
		send[0] = (byte)Integer.parseInt("02", 16);
		send[1] = (byte)Integer.parseInt("01", 16);
		for(int i=0;i<tmp.length;i++){
			send[i+2] = tmp[i];
		}
		bleManager.writeValue(gattCharacteristic, send);
		return true;
	}
	
	/**
	 * 
	 * 设置用户信息
	 * 
	 * @version 1.0
	 * @createTime 2014-3-18,下午1:30:37
	 * @updateTime 2014-3-18,下午1:30:37
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 *@param gattCharacteristic 可写BluetoothGattCharacteristic
	 * @param isMale true：男 false：女
	 * @param age 年龄
	 * @param weight 体重
	 * @param height 身高
	 * 
	 */
	public void setDeviceUserInfo(BluetoothGattCharacteristic gattCharacteristic,
			boolean isMale,int age,int weight,int height ){
		byte []send = new byte[6];
		send[0] = (byte)Integer.parseInt("02", 16);
		send[1] = (byte)Integer.parseInt("02", 16);		
		send[2] =  isMale? (byte)Integer.parseInt("01", 16):(byte)Integer.parseInt("02", 16);
		send[3] = (byte)(0xff&age);
		send[4] = (byte)(0xff&weight);
		send[5] = (byte)(0xff&height);
		bleManager.writeValue(gattCharacteristic, send);
	}
	
	/**
	 * 设置配对手环名称信息，配对手环名称不超过18个字节
	 * 
	 * @version 1.0
	 * @createTime 2014-3-18,下午1:31:32
	 * @updateTime 2014-3-18,下午1:31:32
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * @param gattCharacteristic 可写BluetoothGattCharacteristic
	 * @param name 配对手环名称(前六个字节代表MAC地址，剩下12个字节则代表名称，过长截取)
	 * @return 是否正确设置名称
	 */
	public void setDevicePairdDevieName(BluetoothGattCharacteristic gattCharacteristic,String address,String name){
		
		byte[] addrs = HexBytesUtils.getMacAddressBytes(address); 
		
		String legalName = name;
		if(name.length()>6){
			legalName = name.substring(0, 6);
		}
		byte[] tmp = HexBytesUtils.getUnicodeBytes(legalName);

		byte []send = new byte[tmp.length+addrs.length+2];
		send[0] = (byte)Integer.parseInt("02", 16);
		send[1] = (byte)Integer.parseInt("03", 16);
		
		for(int i=0;i<addrs.length;i++){
			send[i+2] = addrs[i];
		}
		for(int i=0;i<tmp.length;i++){
			send[i+2+addrs.length] = tmp[i];
		}
		bleManager.writeValue(gattCharacteristic, send);
	}
	
	/**
	 * 
	 * 设置闹钟设置
	 * 该方法闹钟性质用一个字节来表示，字节各bit位对应星期一至星期天，以及总开关状态
	 * @version 1.0
	 * @createTime 2014-3-18,下午1:34:37
	 * @updateTime 2014-3-18,下午1:34:37
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gattCharacteristic 可写BluetoothGattCharacteristic
	 * @param number 闹钟编号
	 * @param enable 设置或者删除 send[2] bit4-bit7 0x01-设置，0x00-删除
	 * @param isOpen (8bits) 闹钟性质 分别对应星期一至星期天，以及总开关
	 * @param hour 时
	 * @param minute 分
	 */
	public void setAlarmSettings(BluetoothGattCharacteristic gattCharacteristic,
			int number,boolean enable,int isOpen,int hour,int minute){
		byte []send = new byte[6];
		send[0] = (byte)Integer.parseInt("02", 16);
		send[1] = (byte)Integer.parseInt("04", 16);
		send[2] = (byte) ((number&0x0f)|(enable? 0x10:0x00));	
		send[3] = (byte)(0xff&isOpen);
		send[4] = (byte)(0xff&hour);
		send[5] = (byte)(0xff&minute);
		bleManager.writeValue(gattCharacteristic, send);
	}
	
	/**
	 *设置闹钟设置
	 * @version 1.0
	 * @createTime 2014-3-18,下午2:31:02
	 * @updateTime 2014-3-18,下午2:31:02
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gattCharacteristic 可写BluetoothGattCharacteristic
	 * @param number 闹钟编号 send[2] bit0-bit3 (从0到9，最多10组)
	 * @param enable 设置或者删除 send[2] bit4-bit7 0x01-设置，0x00-删除
	 * @param isOpens boolean数组，分别对应星期一至星期天以及总开关
	 * @param hour 时
	 * @param minute 分
	 */
	 public void setAlarmSettings(BluetoothGattCharacteristic gattCharacteristic,
			 int number,boolean enable,boolean []isOpens,int hour,int minute){
		byte []send = new byte[6];
		send[0] = (byte)Integer.parseInt("02", 16);
		send[1] = (byte)Integer.parseInt("04", 16);
		send[2] = (byte) ((number&0x0f)|(enable? 0x10:0x00));		
		send[3] = (byte)(((isOpens[0]? 1:0)+(isOpens[1]? 2:0)+(isOpens[2]? 4:0)+(isOpens[3]? 8:0)+(isOpens[4]? 16:0)
				+(isOpens[5]? 32:0)+(isOpens[6]? 64:0)+(isOpens[7]? 128:0))&0xff);
		send[4] = (byte)(0xff&hour);
		send[5] = (byte)(0xff&minute);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	
	 /**
	  * 设置久坐提醒时长
	  *
	  * @version 1.0
	  * @createTime 2014-3-25,下午5:54:31
	  * @updateTime 2014-3-25,下午5:54:31
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param minute 分钟数
	  */
	 public void setLongSeatAlertTime(BluetoothGattCharacteristic gattCharacteristic,int minute){
		 byte []send = new byte[3];
		 send[0] = (byte)Integer.parseInt("02", 16);
		 send[1] = (byte)Integer.parseInt("05", 16);
		 send[2] = (byte) (minute&0xff);
		 bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 申请实时数据监控
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:11:57
	  * @updateTime 2014-3-18,下午3:11:57
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param type 1-步数信息，2-温度信息，3-血压信息，4-血氧信息，5-心率信息，6-脉搏信息，7-重量信息，8-车轮圈数
	  */
	 public void registerRealMonitoring(BluetoothGattCharacteristic gattCharacteristic,int type){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("03", 16);
		send[1] = (byte)(0xff&type);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * unregisterRealMonitoring(int type) 停止实时数据监控
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:23:55
	  * @updateTime 2014-3-18,下午3:23:55
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param type 1-步数信息，2-温度信息，3-血压信息，4-血氧信息，5-心率信息，6-脉搏信息，7-重量信息，8-车轮圈数
	  */
	 public void unregisterRealMonitoring(BluetoothGattCharacteristic gattCharacteristic,int type){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("04", 16);
		send[1] = (byte)(0xff&type);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	/**
	 * registerRealStep()
	 * 申请步数信息实时监控
	 * 
	 * @version 1.0
	 * @createTime 2014-3-18,下午1:39:20
	 * @updateTime 2014-3-18,下午1:39:20
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gattCharacteristic 可写BluetoothGattCharacteristic
	 *
	 */
	public void registerRealStep(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("03", 16);
		send[1] = (byte)Integer.parseInt("01", 16);
		bleManager.writeValue(gattCharacteristic, send);
	}
	
	/**
	 * 
	 * 停止步数信息实时监控
	 * 
	 * @version 1.0
	 * @createTime 2014-3-18,下午1:40:19
	 * @updateTime 2014-3-18,下午1:40:19
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param gattCharacteristic 可写BluetoothGattCharacteristic
	 */
	public void unregisterRealStep(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("04", 16);
		send[1] = (byte)Integer.parseInt("01", 16);
		bleManager.writeValue(gattCharacteristic, send);
	}
	
	/**
	 * registerRealTemp() 申请温度信息实时监控
	 * @version 1.0
	 * @createTime 2014-3-18,下午1:54:11
	 * @updateTime 2014-3-18,下午1:54:11
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param gattCharacteristic 可写BluetoothGattCharacteristic
	 */
	 public void registerRealTemp(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("03", 16);
		send[1] = (byte)Integer.parseInt("02", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 停止温度信息实时监控
	  * 
	  * @version 1.0
	  * @createTime 2014-3-18,下午1:54:48
	  * @updateTime 2014-3-18,下午1:54:48
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void unregisterRealTemp(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("04", 16);
		send[1] = (byte)Integer.parseInt("02", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 申请血压信息实时监控
	  * @version 1.0
	  * @createTime 2014-3-18,下午1:55:19
	  * @updateTime 2014-3-18,下午1:55:19
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void registerRealBP(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("03", 16);
		send[1] = (byte)Integer.parseInt("03", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	
	 /**
	  * 停止血压信息实时监控
	  * @version 1.0
	  * @createTime 2014-3-18,下午1:56:19
	  * @updateTime 2014-3-18,下午1:56:19
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void unregisterRealBP(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("04", 16);
		send[1] = (byte)Integer.parseInt("03", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 申请血氧信息实时监控
	  * @version 1.0
	  * @createTime 2014-3-18,下午1:56:45
	  * @updateTime 2014-3-18,下午1:56:45
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * 
	  */
	 public void registerRealPO(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("03", 16);
		send[1] = (byte)Integer.parseInt("04", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 停止血氧信息实时监控
	  * 
	  * @version 1.0
	  * @createTime 2014-3-18,下午1:57:20
	  * @updateTime 2014-3-18,下午1:57:20
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void unregisterRealPO(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("04", 16);
		send[1] = (byte)Integer.parseInt("04", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 申请心率信息实时监控
	  * @version 1.0
	  * @createTime 2014-3-18,下午1:58:09
	  * @updateTime 2014-3-18,下午1:58:09
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  *
	  */
	 public void registerRealHR(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("03", 16);
		send[1] = (byte)Integer.parseInt("05", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 停止心率信息实时监控
	  * @version 1.0
	  * @createTime 2014-3-18,下午1:58:42
	  * @updateTime 2014-3-18,下午1:58:42
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void unregisterRealHR(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("04", 16);
		send[1] = (byte)Integer.parseInt("05", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 申请脉搏信息实时监控
	  * @version 1.0
	  * @createTime 2014-3-18,下午1:59:25
	  * @updateTime 2014-3-18,下午1:59:25
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  *
	  */
	 public void  registerRealPulse(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("03", 16);
		send[1] = (byte)Integer.parseInt("06", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 停止脉搏信息实时监控
	  * 
	  * @version 1.0
	  * @createTime 2014-3-18,下午1:59:53
	  * @updateTime 2014-3-18,下午1:59:53
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void unregisterRealPulse(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("04", 16);
		send[1] = (byte)Integer.parseInt("06", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 申请体重信息实时监控
	  * 
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:00:28
	  * @updateTime 2014-3-18,下午2:00:28
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void registerRealWeight(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("03", 16);
		send[1] = (byte)Integer.parseInt("07", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 停止体重信息实时监控
	  * 
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:00:57
	  * @updateTime 2014-3-18,下午2:00:57
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void unregisterRealWeight(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("04", 16);
		send[1] = (byte)Integer.parseInt("07", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 申请车轮圈数信息实时监控
	  * 
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:01:26
	  * @updateTime 2014-3-18,下午2:01:26
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void registerRealCN(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("03", 16);
		send[1] = (byte)Integer.parseInt("08", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 停止车轮圈数信息实时监控
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:01:57
	  * @updateTime 2014-3-18,下午2:01:57
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void unregisterRealCN(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("04", 16);
		send[1] = (byte)Integer.parseInt("08", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 查询记录信息
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:27:49
	  * @updateTime 2014-3-18,下午3:27:49
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param type 1-步数信息，2-温度信息，3-血压信息，4-血氧信息，5-心率信息，6-脉搏信息，7-重量信息，8-车轮圈数,9-睡眠信息
	  */
	 public void checkTypeMemory(BluetoothGattCharacteristic gattCharacteristic,int type){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("05", 16);
		send[1] = (byte)(0xff&type);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *   查询步数记录信息
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:46:10
	  * @updateTime 2014-3-18,下午2:46:10
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  *@param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void checkStepMemory(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("05", 16);
		send[1] = (byte)Integer.parseInt("01", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  查询温度记录信息
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:47:02
	  * @updateTime 2014-3-18,下午2:47:02
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  *@param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void checkTempMemory(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("05", 16);
		send[1] = (byte)Integer.parseInt("02", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  查询血压记录信息
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:47:26
	  * @updateTime 2014-3-18,下午2:47:26
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  *@param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void checkBPMemory(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("05", 16);
		send[1] = (byte)Integer.parseInt("03", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  查询血氧记录信息
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:47:56
	  * @updateTime 2014-3-18,下午2:47:56
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  *@param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void checkPOMemory(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("05", 16);
		send[1] = (byte)Integer.parseInt("04", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  查询心率记录信息
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:48:15
	  * @updateTime 2014-3-18,下午2:48:15
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void checkHRMemory(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("05", 16);
		send[1] = (byte)Integer.parseInt("05", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  查询脉搏记录信息
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:48:38
	  * @updateTime 2014-3-18,下午2:48:38
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void checkPulseMemory(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("05", 16);
		send[1] = (byte)Integer.parseInt("06", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 查询体重记录信息
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:49:01
	  * @updateTime 2014-3-18,下午2:49:01
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void checkWeightMemory(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("05", 16);
		send[1] = (byte)Integer.parseInt("07", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 查询车轮圈数记录信息
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:49:27
	  * @updateTime 2014-3-18,下午2:49:27
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void checkCNMemory(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("05", 16);
		send[1] = (byte)Integer.parseInt("08", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }

	 /**
	  *  查询睡眠记录信息
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:49:27
	  * @updateTime 2014-3-18,下午2:49:27
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void checkSleepMemory(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("05", 16);
		send[1] = (byte)Integer.parseInt("09", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }	 
	 
	 /**
	  * 开始读取数据包
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:29:30
	  * @updateTime 2014-3-18,下午3:29:30
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param type 1-步数信息，2-温度信息，3-血压信息，4-血氧信息，5-心率信息，6-脉搏信息，7-重量信息，8-车轮圈数，9-睡眠信息
	  * @param index 所取当前数据包编号
	  */
	 public void startReadData(BluetoothGattCharacteristic gattCharacteristic,int type,int index){
		byte []send = new byte[4];
		send[0] = (byte)Integer.parseInt("06", 16);
		send[1] = (byte)(0xff&type);
		byte []tmp = HexBytesUtils.int2bytes(index);
		send[2] = tmp[0];
		send[3] = tmp[1];
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 
	 /**
	  *  开始读取步数数据包
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:50:29
	  * @updateTime 2014-3-18,下午2:50:29
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param index 所取当前数据包的编号
	  */
	 public void startReadStepData(BluetoothGattCharacteristic gattCharacteristic,int index){
		byte []send = new byte[4];
		send[0] = (byte)Integer.parseInt("06", 16);
		send[1] = (byte)Integer.parseInt("01", 16);
		byte []tmp = HexBytesUtils.int2bytes(index);
		send[2] = tmp[0];
		send[3] = tmp[1];
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  开始读取温度数据包
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:52:00
	  * @updateTime 2014-3-18,下午2:52:00
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param index 所取当前数据包的编号
	  */
	 public void startReadTempData(BluetoothGattCharacteristic gattCharacteristic,int index){
		byte []send = new byte[4];
		send[0] = (byte)Integer.parseInt("06", 16);
		send[1] = (byte)Integer.parseInt("02", 16);
		byte []tmp = HexBytesUtils.int2bytes(index);
		send[2] = tmp[0];
		send[3] = tmp[1];
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  开始读取血压数据包
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:53:39
	  * @updateTime 2014-3-18,下午2:53:39
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param index 所取当前数据包的编号
	  */
	 public void startReadBPData(BluetoothGattCharacteristic gattCharacteristic,int index){
		byte []send = new byte[4];
		send[0] = (byte)Integer.parseInt("06", 16);
		send[1] = (byte)Integer.parseInt("03", 16);
		byte []tmp = HexBytesUtils.int2bytes(index);
		send[2] = tmp[0];
		send[3] = tmp[1];
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  开始读取血氧数据包
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:54:37
	  * @updateTime 2014-3-18,下午2:54:37
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param index 所取当前数据包的编号
	  */
	 public void startReadPOData(BluetoothGattCharacteristic gattCharacteristic,int index){
		byte []send = new byte[4];
		send[0] = (byte)Integer.parseInt("06", 16);
		send[1] = (byte)Integer.parseInt("04", 16);
		byte []tmp = HexBytesUtils.int2bytes(index);
		send[2] = tmp[0];
		send[3] = tmp[1];
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  开始读取心率数据包
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:55:16
	  * @updateTime 2014-3-18,下午2:55:16
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param index 所取当前数据包编号
	  */
	 public void startReadHRData(BluetoothGattCharacteristic gattCharacteristic,int index){
		byte []send = new byte[4];
		send[0] = (byte)Integer.parseInt("06", 16);
		send[1] = (byte)Integer.parseInt("05", 16);
		byte []tmp = HexBytesUtils.int2bytes(index);
		send[2] = tmp[0];
		send[3] = tmp[1];
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  开始读取心率数据包
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:56:33
	  * @updateTime 2014-3-18,下午2:56:33
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param index 所取当前数据包编号
	  */
	 public void startReadPulseData(BluetoothGattCharacteristic gattCharacteristic,int index){
		byte []send = new byte[4];
		send[0] = (byte)Integer.parseInt("06", 16);
		send[1] = (byte)Integer.parseInt("06", 16);
		byte []tmp = HexBytesUtils.int2bytes(index);
		send[2] = tmp[0];
		send[3] = tmp[1];
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  开始读取体重数据包
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午2:59:11
	  * @updateTime 2014-3-18,下午2:59:11
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param index 所取当前数据包编号
	  */
	 public void startReadWeightData(BluetoothGattCharacteristic gattCharacteristic,int index){
		byte []send = new byte[4];
		send[0] = (byte)Integer.parseInt("06", 16);
		send[1] = (byte)Integer.parseInt("07", 16);
		byte []tmp = HexBytesUtils.int2bytes(index);
		send[2] = tmp[0];
		send[3] = tmp[1];
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  开始读取车轮圈数数据包
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:00:01
	  * @updateTime 2014-3-18,下午3:00:01
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param index 所取当前数据包编号
	  */
	 public void startReadCNData(BluetoothGattCharacteristic gattCharacteristic,int index){
		byte []send = new byte[4];
		send[0] = (byte)Integer.parseInt("06", 16);
		send[1] = (byte)Integer.parseInt("08", 16);
		byte []tmp = HexBytesUtils.int2bytes(index);
		send[2] = tmp[0];
		send[3] = tmp[1];
		bleManager.writeValue(gattCharacteristic, send);
	 }

	 /**
	  *  开始读取睡眠信息数据包
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:00:01
	  * @updateTime 2014-3-18,下午3:00:01
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param index 所取当前数据包编号
	  */
	 public void startReadSleepData(BluetoothGattCharacteristic gattCharacteristic,int index){
		byte []send = new byte[4];
		send[0] = (byte)Integer.parseInt("06", 16);
		send[1] = (byte)Integer.parseInt("09", 16);
		byte []tmp = HexBytesUtils.int2bytes(index);
		send[2] = tmp[0];
		send[3] = tmp[1];
		bleManager.writeValue(gattCharacteristic, send);
	 }	 
	 
	 /**
	  *  本次记录读取结束
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:31:16
	  * @updateTime 2014-3-18,下午3:31:16
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param type 1-步数信息，2-温度信息，3-血压信息，4-血氧信息，5-心率信息，6-脉搏信息，7-重量信息，8-车轮圈数，9-睡眠信息
	  */
	 public void finishRead(BluetoothGattCharacteristic gattCharacteristic,int type){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("07", 16);
		send[1] = (byte)(0xff&type);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  本次步数记录读取结束
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:01:13
	  * @updateTime 2014-3-18,下午3:01:13
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  *@param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void finishReadStep(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("07", 16);
		send[1] = (byte)Integer.parseInt("01", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  本次温度记录读取结束
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:01:50
	  * @updateTime 2014-3-18,下午3:01:50
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void finishReadTemp(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("07", 16);
		send[1] = (byte)Integer.parseInt("02", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  本次血压记录读取结束
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:02:17
	  * @updateTime 2014-3-18,下午3:02:17
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void finishReadBP(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("07", 16);
		send[1] = (byte)Integer.parseInt("03", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  本次血氧记录读取结束
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:03:14
	  * @updateTime 2014-3-18,下午3:03:14
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void finishReadPO(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("07", 16);
		send[1] = (byte)Integer.parseInt("04", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  本次心率记录数据读取结束
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:03:51
	  * @updateTime 2014-3-18,下午3:03:51
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void finishReadHR(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("07", 16);
		send[1] = (byte)Integer.parseInt("05", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  本次脉搏记录数据读取结束
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:05:04
	  * @updateTime 2014-3-18,下午3:05:04
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void finishReadPulse(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("07", 16);
		send[1] = (byte)Integer.parseInt("06", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  本次体重记录数据读取结束
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:05:29
	  * @updateTime 2014-3-18,下午3:05:29
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void finishReadWeight(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("07", 16);
		send[1] = (byte)Integer.parseInt("07", 16);
		bleManager.writeValue(gattCharacteristic, send); 
	 }
	 
	 /**
	  *  本次车轮圈数记录数据读取结束
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:06:07
	  * @updateTime 2014-3-18,下午3:06:07
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void finishReadCN(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("07", 16);
		send[1] = (byte)Integer.parseInt("08", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }

	 /**
	  *  本次睡眠记录数据读取结束
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:06:07
	  * @updateTime 2014-3-18,下午3:06:07
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  */
	 public void finishReadSleep(BluetoothGattCharacteristic gattCharacteristic){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("07", 16);
		send[1] = (byte)Integer.parseInt("09", 16);
		bleManager.writeValue(gattCharacteristic, send);
	 }	 
	 
	 /**
	  * APP端发送提示信息 -提示信息通知
	  * 无图像提示，无LED闪烁提示
	  * @version 1.0
	  * @createTime 2014-3-24,下午5:59:00
	  * @updateTime 2014-3-24,下午5:59:00
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param type 1-短信信息，2-来电信息，3-闹钟信息，4-低电信息
	  * @param motorState 无马达响应-0x00 ，开启马达提示-0x01
	  * @param motorTimes 马达提示次数，最大设置为10；超过10 都按照震动常开处理
	  */
	 public void promptNotifyNone(BluetoothGattCharacteristic gattCharacteristic,
			 int type, boolean motorState,int motorTimes){
		 byte[] tmp = new byte[5];
		 tmp[0] = 0x08;
		 tmp[1] = (byte)(0xff&type);
		 tmp[2] = (byte) (motorState? 0x00:0x01);
		 tmp[3] = (byte)(0xff&motorTimes);
		 tmp[4] = 0x00;
		 bleManager.writeValue(gattCharacteristic, tmp);
	 }
	 
	 /**
	  * APP端发送提示信息 -提示信息通知
	  *	无图形提示，提供LED闪烁提示
	  * @version 1.0
	  * @createTime 2014-3-24,下午6:00:52
	  * @updateTime 2014-3-24,下午6:00:52
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param type 1-短信信息，2-来电信息，3-闹钟信息，4-低电信息
	  * @param motorState 无马达响应-0x00 ，开启马达提示-0x01
	  * @param motorTimes 马达提示次数
	  * @param ledTimes LED闪烁次数
	  */
	 public void promptNotifyLED(BluetoothGattCharacteristic gattCharacteristic,
			 int type, boolean motorState,int motorTimes,int ledTimes){
		 byte[] tmp = new byte[6];
		 tmp[0] = 0x08;
		 tmp[1] = (byte)(0xff&type);
		 tmp[2] = (byte) (motorState? 0x00:0x01);
		 tmp[3] = (byte)(0xff&motorTimes);
		 tmp[4] = 0x01;
		 tmp[5] = (byte)(0xff&ledTimes);
		 bleManager.writeValue(gattCharacteristic, tmp);
	 }
	 
	 /**
	  * 提示信息通知
	  *
	  * @version 1.0
	  * @createTime 2014-3-24,下午5:51:14
	  * @updateTime 2014-3-24,下午5:51:14
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param type  1-短信信息，2-来电信息，3-闹钟信息，4-低电信息
	  * @param motorState 无马达响应-0x00 ，开启马达提示-0x01
	  * @param motorTimes 马达提示次数，最大设置为10；超过10 都按照震动常开处理	  * 
	  * @param imgWidth 图像宽度
	  * @param imgHeight 图像高度
	  * @param imgNum 图像个数
	  */
	 public void promptNotify(BluetoothGattCharacteristic gattCharacteristic,
			 int type, boolean motorState,int motorTimes, int imgWidth,int imgHeight,int imgNum){
		 byte[] tmp = new byte[8];
		 tmp[0] = 0x08;
		 tmp[1] = (byte)(0xff&type);
		 tmp[2] = (byte) (motorState? 0x00:0x01);
		 tmp[3] = (byte)(0xff&motorTimes);
		 tmp[4] = 0x02;
		 tmp[5] = (byte)(0xff&imgWidth);
		 tmp[6] = (byte)(0xff&imgHeight);
		 tmp[7] = (byte)(0xff&imgNum);
		 bleManager.writeValue(gattCharacteristic, tmp);
	 }

	 /**
	  *  申请数据上传
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午4:41:00
	  * @updateTime 2014-3-18,下午4:41:00
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param type 1-短信信息，2-来电信息，3-闹钟信息，4-低电信息
	  * @param index index从0一直到Image_Nunber-1
	  */
	 public void applyUpload(BluetoothGattCharacteristic gattCharacteristic,int type,int index){
		byte []send = new byte[3];
		send[0] = (byte)Integer.parseInt("09", 16);
		send[1] = (byte)(0xff&type);
		send[2] = (byte)(0xff&index);
		bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  * 
	  *
	  * @version 1.0
	  * @createTime 2014-3-27,上午9:55:07
	  * @updateTime 2014-3-27,上午9:55:07
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param text
	  */
	 public void sendImageData(BluetoothGattCharacteristic gattCharacteristic,String text){
		 Text2MatrixUtils text2Matrix = new Text2MatrixUtils();
		 byte[] data = text2Matrix.textToMatrixBytes(text);
		 bleManager.writeValue(gattCharacteristic, data);
	 }
	 
	 
	 /**
	  * 发送20个字节的图像数据到ble
	  *
	  * @version 1.0
	  * @createTime 2014-4-8,下午2:29:02
	  * @updateTime 2014-4-8,下午2:29:02
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param bytes
	  */
	 public void sendImageBytes(BluetoothGattCharacteristic gattCharacteristic,byte[] bytes){
		 bleManager.writeValue(gattCharacteristic, bytes);
	 }
	 
	 
	 /**
	  *  提示信息结束
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:39:04
	  * @updateTime 2014-3-18,下午3:39:04
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param type 1-短信信息，2-来电信息，3-闹钟信息，4-低电信息
	  */
	 public void endPromptNofity(BluetoothGattCharacteristic gattCharacteristic,int type){
			byte []send = new byte[2];
			send[0] = (byte)Integer.parseInt("0A", 16);
			send[1] = (byte)(0xff&type);
			bleManager.writeValue(gattCharacteristic, send);
	 }
	 
	 /**
	  *  回复BLE通知事件
	  *
	  * @version 1.0
	  * @createTime 2014-3-18,下午3:42:02
	  * @updateTime 2014-3-18,下午3:42:02
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  * 
	  * @param gattCharacteristic 可写BluetoothGattCharacteristic
	  * @param type 1-久坐提醒，2-紧急情况（求救等），3-接听电话，4-拒接电话，5-BLE设备存储信息已满，6-抓拍，7-关闭手机
	  */
	public void respondBLENotify(BluetoothGattCharacteristic gattCharacteristic,int type){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("0B", 16);
		send[1] = (byte)(0xff&type);
		bleManager.writeValue(gattCharacteristic, send);
	}
	
	/**
	 * 读取设备信息
	 *
	 * @version 1.0
	 * @createTime 2014-3-18,下午3:46:59
	 * @updateTime 2014-3-18,下午3:46:59
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param gattCharacteristic 可写BluetoothGattCharacteristic
	 * @param type 1-读取BLE设备时间，2-读取BLE设备名称，3-读取BLE设备伴侣名称，4-读取设备硬件配置，5-读取BLE设备用户信息，
	 * 			 	6-读取BLE设备电池电量，7-读取BLE设备内存状态，8-读取BLE设备MAC，9-读取闹钟设置
	 */
	public void readBLEDeviceInfo(BluetoothGattCharacteristic gattCharacteristic,int type){
		byte []send = new byte[2];
		send[0] = (byte)Integer.parseInt("0C", 16);
		send[1] = (byte)(0xff&type);
		bleManager.writeValue(gattCharacteristic, send);
	}
}
