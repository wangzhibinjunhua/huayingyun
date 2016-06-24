package com.tentinet.healthy.btsdk;

import java.util.HashSet;

import android.content.Context;
import android.os.Bundle;

import com.btsdk.BluetoothRequestor;
import com.btsdk.BluetoothResponder;
import com.btsdk.LocalBluetoothCore;
import com.btsdk.LocalBluetoothListener;
import com.btsdk.RemoteBluetooth;
import com.btsdk.StateCode;

/**
 * @author BtSDK
 */
public final class LocalBluetooth {

	private static final HashSet<RemoteBluetoothResponder> bluetoothResponderSet = new HashSet<RemoteBluetoothResponder>();
	private static final BluetoothResponder bluetoothResponder = new BluetoothResponder() {

		@Override
		public void onRead(StateCode stateCode,
				RemoteBluetooth remoteBluetooth, Bundle bundle) {
			int functionCode = bundle.getInt(BluetoothRequestor.FUNCTION_CODE);
			for (RemoteBluetoothResponder remoteBluetoothResponder : bluetoothResponderSet) {
				if (remoteBluetoothResponder != null) {
					switch (functionCode) {
					case RemoteBluetoothRequestor.BATTERY:
						int value = bundle.getInt("value");
						remoteBluetoothResponder.onReadBattery(stateCode,
								remoteBluetooth, value);
						break;
					default:
						break;
					}
				}
			}
		}

		@Override
		public void onWrite(StateCode stateCode,
				RemoteBluetooth remoteBluetooth, Bundle bundle) {
			int functionCode = bundle.getInt(BluetoothRequestor.FUNCTION_CODE);
			boolean isCompleted = bundle.getBoolean("isCompleted");
			for (RemoteBluetoothResponder remoteBluetoothResponder : bluetoothResponderSet) {
				if (remoteBluetoothResponder != null) {
					switch (functionCode) {
					case RemoteBluetoothRequestor.START_OFFLINE:
						remoteBluetoothResponder.onWriteStartOffline(stateCode,
								remoteBluetooth, isCompleted);
						break;

					case RemoteBluetoothRequestor.WRITE_OFFLINE_DATA_ORDER:
						remoteBluetoothResponder.onWriteOfflineData(stateCode,
								remoteBluetooth, isCompleted);

						break;

					}
				}
			}
		}

		@Override
		public void onSetNotification(StateCode stateCode,
				RemoteBluetooth remoteBluetooth, Bundle bundle) {
			int functionCode = bundle.getInt(BluetoothRequestor.FUNCTION_CODE);
			boolean enable = bundle.getBoolean("enable");
			boolean isCompleted = bundle.getBoolean("isCompleted");
			for (RemoteBluetoothResponder remoteBluetoothResponder : bluetoothResponderSet) {
				if (remoteBluetoothResponder != null) {
					switch (functionCode) {
					case RemoteBluetoothRequestor.SWITCH_NOTIFY:

						remoteBluetoothResponder
								.onSetNotification(stateCode,
										remoteBluetooth, enable, isCompleted);
						break;
					case RemoteBluetoothRequestor.TEMPRATURE:

						remoteBluetoothResponder
								.onSetIndicateOfDegree(stateCode,
										remoteBluetooth, enable, isCompleted);
						break;
					}
				}
			}
		}

		@Override
		public void onReceive(StateCode stateCode,
				RemoteBluetooth remoteBluetooth, Bundle bundle) {
			int functionCode = bundle.getInt(BluetoothRequestor.FUNCTION_CODE);
			for (RemoteBluetoothResponder remoteBluetoothResponder : bluetoothResponderSet) {
				if (remoteBluetoothResponder != null) {
					switch (functionCode) {
					case RemoteBluetoothRequestor.RECIEVE_OFFLINE_DATA:
						byte[] data = bundle.getByteArray("data");

						remoteBluetoothResponder.onReceiveData(stateCode,
								remoteBluetooth, data);
						break;
					case RemoteBluetoothRequestor.TEMPRATURE:
						int degree = bundle.getInt("degree");

						remoteBluetoothResponder.onReceiveDegree(stateCode,
								remoteBluetooth, degree);
						break;

					}
				}
			}
		}

		@Override
		public void onReadRssi(StateCode stateCode,
				RemoteBluetooth remoteBluetooth, int rssi) {

		}
	};

	public static final boolean registerBluetoothResponder(
			RemoteBluetoothResponder remoteBluetoothResponder) {
		return bluetoothResponderSet.add(remoteBluetoothResponder);
	}

	public static final boolean unregisterBluetoothResponder(
			RemoteBluetoothResponder remoteBluetoothResponder) {
		return bluetoothResponderSet.remove(remoteBluetoothResponder);
	}

	public static final void initialize(Context context,
			LocalBluetoothListener listener, String config) {
		LocalBluetoothCore.initialize(context, listener, config,
				bluetoothResponder);
	}

	public static final void startScan() {
		LocalBluetoothCore.startScan();
	}

	public static final void stopScan() {
		LocalBluetoothCore.stopScan();
	}

	public static final void enableBLE(boolean isSilent) {
		LocalBluetoothCore.enableBLE(isSilent);
	}

	public static final void disableBLE() {
		LocalBluetoothCore.disableBLE();
	}

	public static final void connect(RemoteBluetooth remoteBluetooth) {
		LocalBluetoothCore.connect(remoteBluetooth);
	}

	public static final void disconnect(RemoteBluetooth remoteBluetooth) {
		LocalBluetoothCore.disconnect(remoteBluetooth);
	}

	public static final void destory() {
		LocalBluetoothCore.destory();
	}

	public static final boolean isEnabled() {
		return LocalBluetoothCore.isEnabled();
	}

	public static final String getLocalName() {
		return LocalBluetoothCore.getLocalName();
	}

	public static final String getLocalAddress() {
		return LocalBluetoothCore.getLocalAddress();
	}

	public static final boolean checkFeatureSupport(int sample, int benchmark) {
		return LocalBluetoothCore.checkFeatureSupport(sample, benchmark);
	}

}
