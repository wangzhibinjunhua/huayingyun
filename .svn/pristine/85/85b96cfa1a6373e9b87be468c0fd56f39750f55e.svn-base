package com.tentinet.healthy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.bean.ParentBean;
import com.tentinet.healthy.util.LogUtil;

import java.util.ArrayList;

/**
 * 我的设备适配器.
 *
 * @author paladin
 */
public class ParentAdapter extends LoadImageBaseAdapter {

    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 我的设备列表.
     */
    private ArrayList<ParentBean> list;

    public ParentAdapter(Context context, ArrayList<ParentBean> list) {
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
        ParentBean device = list.get(position);
        if (null == view) {
            item = new ItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_parent, null);
            item.txt_name = (TextView) view.findViewById(R.id.txt_name);
            item.txt_phone = (TextView) view.findViewById(R.id.txt_phone);

            view.setTag(item);
        } else {
            item = (ItemHolder) view.getTag();
        }
        // TODO 设置设备图片.
        LogUtil.logDebugMessage("position = " + position + "; device = " + device.toString());
        item.txt_name.setText(device.getParentName());
        item.txt_phone.setText(device.getParentPhone());
//        switch (device.getType()) {
//            case DataBean.TYPE_BLOOD_PRESSURE_MONITOR:
//                item.img_image.setImageResource(R.mipmap.icon_bpm);
//                break;
//            case DataBean.TYPE_GLUCOSE_METER:
//                item.img_image.setImageResource(R.mipmap.icon_bgm);
//                break;
//            case DataBean.TYPE_BLE_EAR:
//                item.img_image.setImageResource(R.mipmap.icon_temp);
//                break;
//            case DataBean.TYPE_BLE_OPD:
//                item.img_image.setImageResource(R.mipmap.icon_box);
//                break;
//            case DataBean.TYPE_BLE_ECG:
//                item.img_image.setImageResource(R.mipmap.icon_ecg);
//                break;
//        }

        return view;
    }
    public ParentBean getDevice(int position){
        return list.get(position);
    }
    private class ItemHolder {
        private TextView txt_name;
        private TextView txt_phone;

    }

}
