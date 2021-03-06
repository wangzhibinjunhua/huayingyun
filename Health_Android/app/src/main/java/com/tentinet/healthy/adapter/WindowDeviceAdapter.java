package com.tentinet.healthy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.bean.HomeMenuBean;

import java.util.ArrayList;

/**
 * 主页设备适配器
 * <h3>Author</h3> Rick Chan
 * <h3>Date</h3> 2016/3/29 16:34
 * <h3>Copyright</h3>Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class WindowDeviceAdapter extends LoadImageBaseAdapter {

    private Context mContext;

    private ArrayList<DeviceBean> devices;

    public WindowDeviceAdapter(Context context, ArrayList<DeviceBean> devices) {
        mContext = context;
        this.devices = devices;
    }

    @Override
    public int getCount() {
        return devices.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_home_tab_griditem, null);
            viewHolder = new ViewHolder();
            viewHolder.img_icon = (ImageView) convertView.findViewById(R.id.home_tab_imv_ic);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.home_tab_txt_name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置设备名
        DeviceBean bean = devices.get(position);
        viewHolder.tv_name.setText(bean.getDeviceName());//devices.get(position).getDeviceName()
        viewHolder.img_icon.setImageResource(bean.getUrl());
        return convertView;
    }

    private class ViewHolder {
        TextView tv_name;
        ImageView img_icon;
    }
}
