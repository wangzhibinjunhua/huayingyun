package com.tentinet.healthy.interf;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tentinet.healthy.service.BroadcastActions;
import com.tentinet.healthy.util.FilterDataUtils;
import com.tentinet.healthy.util.LogUtil;


public class LeResponse {



    private Context mContext;

    public LeResponse(Context context) {
        mContext = context;
    }

    /**
     * 该广播不携带数据信息，只广播状态信息，例如连接状态等
     *
     * @param action
     * @version 1.0
     * @createTime 2014-3-18,上午10:32:27
     * @updateTime 2014-3-18,上午10:32:27
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void broadcastUpdate(final String action) {
        final Intent intent = new Intent(action);
        mContext.sendBroadcast(intent);
    }

    /**
     * 该方法和broadcastUpdate(final BluetoothGattCharacteristic characteristic)的区别
     * 是该方法不对数据进行区分，而broadcastUpdate(final BluetoothGattCharacteristic characteristic)
     * 是根据PDU structure definition对数据进行过滤处理，分发广播，因为时被回调方法调用，因此需根据情况选择具体调用
     * 哪个方法，当然也可以扩展
     *
     * @param action
     * @param characteristic
     * @version 1.0
     * @createTime 2014-3-18,上午10:30:57
     * @updateTime 2014-3-18,上午10:30:57
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void broadcastUpdate(final String action, final BluetoothGattCharacteristic characteristic) {
        Intent intent = new Intent(action);
        if (action == BroadcastActions.ACTION_DATA_AVAILABLE) {
            final byte[] data = characteristic.getValue();
            if (data != null && data.length > 0) {
                final StringBuilder stringBuilder = new StringBuilder(data.length);
                for(byte byteChar : data){
                    stringBuilder.append(String.format("%02X", byteChar)); //16进制
                }
                LogUtil.logMessage("ykk", "RX:" + stringBuilder.toString());
                intent.putExtra(BroadcastActions.EXTRA_DATA, stringBuilder.toString());
                intent.putExtra(BroadcastActions.EXTRA_UUID, characteristic.getUuid().toString());
            }
        }
        mContext.sendBroadcast(intent);
    }

    /**
     * 扫描到设备的处理
     *
     * @param device
     * @param rssi
     * @version 1.0
     * @createTime 2014-4-15,下午1:26:25
     * @updateTime 2014-4-15,下午1:26:25
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void broadcastUpdate(final BluetoothDevice device, final int rssi) {
        Intent intent = new Intent();
        intent.setAction(BroadcastActions.ACTION_UPDATE_DEVICES);
        intent.putExtra(BroadcastActions.UPDATE_DEVICES, device);
        intent.putExtra(BroadcastActions.VALUE_RSSI, rssi);
        mContext.sendBroadcast(intent);
    }

    /**
     * 传递rssi值
     *
     * @param rssi
     * @version 1.0
     * @createTime 2014-4-17,上午10:35:35
     * @updateTime 2014-4-17,上午10:35:35
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void deliverRssi(int rssi) {
        Intent intent = new Intent();
        intent.setAction(BroadcastActions.ACTION_READ_RSSI);
        intent.putExtra(BroadcastActions.VALUE_RSSI, rssi);
        mContext.sendBroadcast(intent);
    }





}
