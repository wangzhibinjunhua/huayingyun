package com.tentinet.healthy.btsdk;

import com.btsdk.RemoteBluetooth;
import com.btsdk.StateCode;

/**
 * @author BTSDK
 */
public interface RemoteBluetoothResponder {
	public void onReceiveDegree(StateCode stateCode,
								RemoteBluetooth remoteBluetooth, int value);

	public void onReadBattery(StateCode stateCode,
							  RemoteBluetooth remoteBluetooth, int level);

	public void onWriteStartOffline(StateCode stateCode,
									RemoteBluetooth remoteBluetooth, boolean isCompleted);

	public void onWriteOfflineData(StateCode stateCode,
								   RemoteBluetooth remoteBluetooth, boolean isCompleted);

	public void onReceiveData(StateCode stateCode,
							  RemoteBluetooth remoteBluetooth, byte[] data);

	public void onSetIndicateOfDegree(StateCode stateCode,
									  RemoteBluetooth remoteBluetooth, boolean enable, boolean isCompleted);

	public void onSetNotification(StateCode stateCode,
								  RemoteBluetooth remoteBluetooth, boolean enable, boolean isCompleted);

}
