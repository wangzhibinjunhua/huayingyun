package com.tentinet.healthy.btsdk;

import android.os.Bundle;

import com.btsdk.BluetoothRequestor;
import com.btsdk.RemoteBluetooth;

/**
 * @author BTSDK
 */
public class RemoteBluetoothRequestor {
	static final int TEMPRATURE = 1;
	static final int BATTERY = 2;
	static final int START_OFFLINE = 3;
	static final int SWITCH_NOTIFY = 4;
	static final int RECIEVE_OFFLINE_DATA = 5;
	static final int WRITE_OFFLINE_DATA_ORDER = 6;

	public final static void setIndicateOfDegree(
			RemoteBluetooth remoteBluetooth, boolean enable, int timeOut) {
		Bundle bundle = new Bundle();
		bundle.putInt(BluetoothRequestor.FUNCTION_CODE, TEMPRATURE);
		bundle.putBoolean("enable", enable);
		BluetoothRequestor.setNotification(remoteBluetooth, timeOut, bundle);
	}

	public final static void readBatteryLevel(RemoteBluetooth remoteBluetooth,
			int timeOut) {
		Bundle bundle = new Bundle();
		bundle.putInt(BluetoothRequestor.FUNCTION_CODE, BATTERY);
		BluetoothRequestor.read(remoteBluetooth, timeOut, bundle);
	}

	public final static void writeStartOffline(RemoteBluetooth remoteBluetooth,
			int timeOut) {
		Bundle bundle = new Bundle();
		bundle.putInt(BluetoothRequestor.FUNCTION_CODE, START_OFFLINE);
		BluetoothRequestor.write(remoteBluetooth, timeOut, bundle);
	}

	public final static void writeOfflineData(RemoteBluetooth remoteBluetooth,
			int timeOut) {
		Bundle bundle = new Bundle();
		bundle.putInt(BluetoothRequestor.FUNCTION_CODE, WRITE_OFFLINE_DATA_ORDER);
		BluetoothRequestor.write(remoteBluetooth, timeOut, bundle);
	}

	public final static void setNotification(RemoteBluetooth remoteBluetooth,
			boolean enable, int timeOut) {
		Bundle bundle = new Bundle();
		bundle.putInt(BluetoothRequestor.FUNCTION_CODE, SWITCH_NOTIFY);
		bundle.putBoolean("enable", enable);
		BluetoothRequestor.setNotification(remoteBluetooth, timeOut, bundle);
	}

}
