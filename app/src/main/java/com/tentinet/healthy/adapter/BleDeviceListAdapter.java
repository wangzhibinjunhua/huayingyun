package com.tentinet.healthy.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.util.StringUtil;

import java.util.ArrayList;

/**
 * 类描述:蓝牙设备列表适配器
 *
 * @author 王治粮
 * @date 2015/12/28,14:30
 * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class BleDeviceListAdapter extends BaseAdapter {
    /**
     * 上下文对象
     */
    private Context mContext;

    public ArrayList<DeviceBean> getList_Device() {
        return list_Device;
    }

    /**
     * 蓝牙设备列表
     */
    private ArrayList<DeviceBean> list_Device;
    /**
     * 蓝牙设备列表
     */
    private ArrayList<DeviceBean> tmp_list_Device;

    /**
     * 信号强度
     */
    private ArrayList<Integer> list_Rssi;

    public String tmpDevice = "BMP";

    public BleDeviceListAdapter(Context context,ArrayList<DeviceBean> tmp) {
        this.mContext = context;
        this.list_Device = new ArrayList<>();
        this.list_Rssi = new ArrayList<>();
        tmp_list_Device =tmp;
    }

    /**
     * 添加蓝牙设备到列表
     *
     * @param device 蓝牙设备信息属性
     * @param rssi   信号强度
     * @version 1.0
     * @createTime 2015/12/28  15:53
     * @updateTime 2015/12/28  15:53
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public void addDevice(DeviceBean device, int rssi) {
        if (!list_Device.contains(device)) {
            this.list_Device.add(device);
            this.list_Rssi.add(rssi);
        }

    }


    /**
     * 获取指定列表位置的蓝牙设备属性
     *
     * @version 1.0
     * @createTime 2015/12/28  16:03
     * @updateTime 2015/12/28  16:03
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public DeviceBean getDevice(int position) {
        return tmp_list_Device.get(position);
    }


    /**
     * 清空列表
     *
     * @version 1.0
     * @createTime 2015/12/28  16:17
     * @updateTime 2015/12/28  16:17
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public void clear() {
        this.list_Device.clear();
        this.list_Rssi.clear();
    }

    @Override
    public int getCount() {
        return tmp_list_Device.size();
    }

    @Override
    public Object getItem(int i) {
        return tmp_list_Device.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (null == view) {
            holder = new ViewHolder();
            view = View.inflate(mContext, R.layout.item_ble_deceive_listview, null);
            holder.txt_name = (TextView) view.findViewById(R.id.txt_device_name);
            holder.txt_address = (TextView) view.findViewById(R.id.txt_device_address);
            holder.txt_rssi = (TextView) view.findViewById(R.id.txt_rssi);
            holder.txt_bind=(TextView)view.findViewById(R.id.txt_device_bind) ;
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (tmp_list_Device.size()>0) {
            DeviceBean deceive = tmp_list_Device.get(i);
            if (deceive != null) {
                String deceiveName = deceive.getDeviceName();
                if (null != deceiveName && deceiveName.length() > 0)
                    holder.txt_name.setText(deceiveName);
                else
                    holder.txt_name.setText("unknown");
                holder.txt_address.setText(deceive.getAddress());
                holder.txt_rssi.setText(list_Rssi.get(i) + " db");
                if (deceive.isBind()){
                    holder.txt_bind.setText(mContext.getString(R.string.bound));
                }else {
                    holder.txt_bind.setText(mContext.getString(R.string.unbound));
                }
            }
        }

        return view;

    }

    /**
     * 内部View容器类
     *
     * @author 王治粮
     * @date 2015/12/28  14:48
     * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
     */
    private class ViewHolder {


        /**
         * 设备名称
         */
        private TextView txt_name;
        /**
         * 设备地址
         */
        private TextView txt_address;
        /**
         * 信号强度
         */
        private TextView txt_rssi;

        /**
         * 是否绑定
         */
        private TextView txt_bind;
    }


}
