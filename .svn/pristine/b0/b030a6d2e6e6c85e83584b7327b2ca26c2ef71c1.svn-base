package com.tentinet.healthy.interf;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;

import com.tentinet.healthy.exception.BLEException;

import java.util.List;


/**
 * 默认BleManagerListener，
 *
 * @Description TODO
 * @author wutianlin
 * @version 1.0
 * @date 2014-4-14
 * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 *
 */
public class DefaultBleManagerListener implements BleManagerListener{

	@Override
	public void onLeScaned(BluetoothDevice device, int rssi, byte[] scanRecord) {
				
	}

	@Override
	public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
				
	}

	@Override
	public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) throws BLEException {
		if(status==135){
			throw new BLEException("ERR135");
		}else if(status==129){
			throw new BLEException("ERR129");
		}else if(status==133){
			throw new BLEException("ERR133");
		}else if(status==257){
			throw new BLEException("ERR257");
		}else if(status==132){
			throw new BLEException("ERR132");
		}					
	}

	@Override
	public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) throws BLEException {
		if(status==135){
			throw new BLEException("ERR135");
		}else if(status==129){
			throw new BLEException("ERR129");
		}else if(status==133){
			throw new BLEException("ERR133");
		}else if(status==257){
			throw new BLEException("ERR257");
		}else if(status==132){
			throw new BLEException("ERR132");
		}				
	}

	@Override
	public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) throws BLEException {
		if(status==135){
			throw new BLEException("ERR135");
		}else if(status==129){
			throw new BLEException("ERR129");
		}else if(status==133){
			throw new BLEException("ERR133");
		}else if(status==257){
			throw new BLEException("ERR257");
		}else if(status==132){
			throw new BLEException("ERR132");
		}				
	}

	@Override
	public void onReliableWriteCompleted(BluetoothGatt gatt, int status) throws BLEException{
		if(status==135){
			throw new BLEException("ERR135");
		}else if(status==129){
			throw new BLEException("ERR129");
		}else if(status==133){
			throw new BLEException("ERR133");
		}else if(status==257){
			throw new BLEException("ERR257");
		}else if(status==132){
			throw new BLEException("ERR132");
		}				
	}


	@Override
	public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) throws BLEException {
		if(status==135){
			throw new BLEException("ERR135");
		}else if(status==129){
			throw new BLEException("ERR129");
		}else if(status==133){
			throw new BLEException("ERR133");
		}else if(status==257){
			throw new BLEException("ERR257");
		}else if(status==132){
			throw new BLEException("ERR132");
		}				
	}

	@Override
	public void onServicesDiscoveredSuccess(BluetoothGatt gatt, int status, List<BluetoothGattService> gattServices) throws BLEException {
		if(status==135){
			throw new BLEException("ERR135");
		}else if(status==129){
			throw new BLEException("ERR129");
		}else if(status==133){
			throw new BLEException("ERR133");
		}else if(status==257){
			throw new BLEException("ERR257");
		}else if(status==132){
			throw new BLEException("ERR132");
		}			
		
	}

	@Override
	public void onCharacteristicReadSuccess(BluetoothGatt gatt, int status, BluetoothGattCharacteristic characteristic) throws BLEException {
		if(status==135){
			throw new BLEException("ERR135");
		}else if(status==129){
			throw new BLEException("ERR129");
		}else if(status==133){
			throw new BLEException("ERR133");
		}else if(status==257){
			throw new BLEException("ERR257");
		}else if(status==132){
			throw new BLEException("ERR132");
		}		
		
	}

	@Override
	public void onReadRemoteRssiSuccess(BluetoothGatt gatt, int rssi, int status) throws BLEException {
		if(status==135){
			throw new BLEException("ERR135");
		}else if(status==129){
			throw new BLEException("ERR129");
		}else if(status==133){
			throw new BLEException("ERR133");
		}else if(status==257){
			throw new BLEException("ERR257");
		}else if(status==132){
			throw new BLEException("ERR132");
		}		
	}

}
