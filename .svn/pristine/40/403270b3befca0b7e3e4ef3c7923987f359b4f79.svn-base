package com.tentinet.healthy.interf;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;


import com.tentinet.healthy.exception.BLEException;

import java.util.List;


/**
 * 监听ble的回调，任何需要监听ble回调的都可实现该接口
 *
 * @Description TODO
 * @author wutianlin
 * @version 1.0
 * @date 2014-4-18
 * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 * 
 */
public interface BleManagerListener {
	
	/**
	 * 监听ble扫描回调
	 *
	 * @version 1.0
	 * @createTime 2014-4-18,下午3:56:52
	 * @updateTime 2014-4-18,下午3:56:52
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param device 扫描到设备
	 * @param rssi  信号强度
	 * @param scanRecord 
	 */
	public void onLeScaned(final BluetoothDevice device,
						   final int rssi,
						   final byte[] scanRecord);
	
	/**
	 * 监听连接状态的改变
	 *
	 * @version 1.0
	 * @createTime 2014-4-18,下午4:07:25
	 * @updateTime 2014-4-18,下午4:07:25
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gatt BluetoothGatt		
	 * @param status 状态码
	 * @param newState 连接状态
	 * @throws BLEException 异常
	 */
	public void onConnectionStateChange(final BluetoothGatt gatt,
										final int status,
										final int newState) throws BLEException; 
	
	/**
	 * 监听Service搜索结束
	 *
	 * @version 1.0
	 * @createTime 2014-4-18,下午4:11:45
	 * @updateTime 2014-4-18,下午4:11:45
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gatt BluetoothGatt
	 * @param status 状态码
	 * @param gattServices Service列表
	 * @throws BLEException
	 */
	public void onServicesDiscoveredSuccess(final BluetoothGatt gatt,
											final int status,
											final List<BluetoothGattService> gattServices) throws BLEException;
	
	/**
	 * 读取Characteristic
	 *
	 * @version 1.0
	 * @createTime 2014-4-18,下午4:12:36
	 * @updateTime 2014-4-18,下午4:12:36
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gatt BluetoothGatt
	 * @param status 状态码
	 * @param characteristic 读取的Characteristic
	 * @throws BLEException
	 */
	public void onCharacteristicReadSuccess(final BluetoothGatt gatt,
											final int status,
											final BluetoothGattCharacteristic characteristic) throws BLEException;
	
	/**
	 * 监听Characteristic变化
	 *
	 * @version 1.0
	 * @createTime 2014-4-18,下午4:13:55
	 * @updateTime 2014-4-18,下午4:13:55
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gatt
	 * @param characteristic 
	 */
	public void onCharacteristicChanged(final BluetoothGatt gatt,
										final BluetoothGattCharacteristic characteristic);
	
	/**
	 * 监听读取RSSI是否成功
	 *
	 * @version 1.0
	 * @createTime 2014-4-18,下午4:14:58
	 * @updateTime 2014-4-18,下午4:14:58
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gatt
	 * @param rssi
	 * @param status
	 * @throws BLEException
	 */
	public void onReadRemoteRssiSuccess(final BluetoothGatt gatt,
										final int rssi,
										final int status) throws BLEException;
	
	/**
	 * 监听写Characteristic状态
	 *
	 * @version 1.0
	 * @createTime 2014-4-18,下午4:15:22
	 * @updateTime 2014-4-18,下午4:15:22
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gatt
	 * @param characteristic
	 * @param status
	 * @throws BLEException
	 */
	public void onCharacteristicWrite(final BluetoothGatt gatt,
									  final BluetoothGattCharacteristic characteristic,
									  final int status) throws BLEException;
	
	/**
	 * 监听读取Descriptor状态
	 *
	 * @version 1.0
	 * @createTime 2014-4-18,下午4:15:58
	 * @updateTime 2014-4-18,下午4:15:58
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gatt
	 * @param descriptor
	 * @param status
	 * @throws BLEException
	 */
	public void onDescriptorRead(final BluetoothGatt gatt,
								 final BluetoothGattDescriptor descriptor,
								 final int status) throws BLEException;
	
	/**
	 * 监听写Descriptor变化
	 *
	 * @version 1.0
	 * @createTime 2014-4-18,下午4:16:27
	 * @updateTime 2014-4-18,下午4:16:27
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gatt
	 * @param descriptor
	 * @param status
	 * @throws BLEException
	 */
	public void onDescriptorWrite(final BluetoothGatt gatt,
								  final BluetoothGattDescriptor descriptor,
								  final int status) throws BLEException;
	
	/**
	 * 监听可靠些完成状态
	 *
	 * @version 1.0
	 * @createTime 2014-4-18,下午4:16:55
	 * @updateTime 2014-4-18,下午4:16:55
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param gatt
	 * @param status
	 * @throws BLEException
	 */
	public void onReliableWriteCompleted(final BluetoothGatt gatt,
										 final int status) throws BLEException;	

}
