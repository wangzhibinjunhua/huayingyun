package com.tentinet.healthy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.view.DeviceView;

import java.util.ArrayList;

/**
 * 我的设备适配器.
 *
 * @author paladin
 */
public class MineDeviceAdapter extends LoadImageBaseAdapter {

    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 我的设备列表.
     */
    private ArrayList<DeviceBean> list;

    public MineDeviceAdapter(Context context, ArrayList<DeviceBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View view, ViewGroup parent) {
        ItemHolder item;
        DeviceBean device = list.get(position);
        if (null == view) {
            item = new ItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_mine_device, null);
            item.img_image = (ImageView) view.findViewById(R.id.img_image);
            item.txt_device = (TextView) view.findViewById(R.id.txt_device);

            view.setTag(item);
        } else {
            item = (ItemHolder) view.getTag();
        }
        // TODO 设置设备图片.
        LogUtil.logDebugMessage("position = " + position + "; device = " + device.toString());
      //  item.txt_device.setText(device.getDeviceName());
        switch (device.getType()) {
            case DataBean.TYPE_BLOOD_PRESSURE_MONITOR:
                item.img_image.setImageResource(R.mipmap.icon_bpm);
                item.txt_device.setText(context.getString(R.string.txt_window_ble_blood));
                break;
            case DataBean.TYPE_GLUCOSE_METER:
                item.img_image.setImageResource(R.mipmap.icon_bgm);
                item.txt_device.setText(context.getString(R.string.txt_window_ble_glucometer));
                break;
            case DataBean.TYPE_BLE_EAR:
                item.img_image.setImageResource(R.mipmap.icon_temp);
                item.txt_device.setText(context.getString(R.string.txt_window_ble_ear));
                break;
            case DataBean.TYPE_BLE_OPD:
                item.img_image.setImageResource(R.mipmap.icon_box);
                item.txt_device.setText(context.getString(R.string.txt_window_ble_opd));
                break;
            case DataBean.TYPE_BLE_ECG:
                item.img_image.setImageResource(R.mipmap.icon_ecg);
                item.txt_device.setText(context.getString(R.string.txt_window_ble_ecg));
                break;
        }

        return view;
    }
    public DeviceBean getDevice(int position){
        return list.get(position);
    }
    private class ItemHolder {
        private ImageView img_image;
        private TextView txt_device;

    }

}
