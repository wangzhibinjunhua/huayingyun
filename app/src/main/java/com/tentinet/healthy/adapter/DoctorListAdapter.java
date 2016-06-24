package com.tentinet.healthy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DoctorBean;
import com.tentinet.healthy.bean.ParentBean;
import com.tentinet.healthy.util.AsyncImageSetter;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.widget.CircleImageView;

import java.util.ArrayList;

/**
 * 亲友适配器.
 *
 * @author paladin
 */
public class DoctorListAdapter extends LoadImageBaseAdapter {

    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 我的设备列表.
     */
    private ArrayList<DoctorBean> list;

    public DoctorListAdapter(Context context, ArrayList<DoctorBean> list) {
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
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ItemHolder item;
        DoctorBean device = list.get(position);
        if (null == view) {
            item = new ItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_doctor_list, null);
            item.txt_name = (TextView) view.findViewById(R.id.txt_name);
            item.txt_type = (TextView) view.findViewById(R.id.txt_type);
            item.txt_sex = (TextView) view.findViewById(R.id.txt_sex);
            item.txt_introduce = (TextView) view.findViewById(R.id.txt_introduce);
            item.img_image = (CircleImageView) view.findViewById(R.id.img_image);
            view.setTag(item);
        }
        item = (ItemHolder) view.getTag();
        item.txt_introduce.setText(device.getIntroduce());
        item.txt_name.setText(device.getName());
        item.txt_sex.setText(device.getSex());
        item.txt_type.setText(device.getType());
        AsyncImageSetter.setImage(item.img_image, "", ImageView.ScaleType.CENTER_CROP);
        // TODO 设置设备图片.
        LogUtil.logDebugMessage("position = " + position + "; device = " + device.toString());
        return view;
    }

    public DoctorBean getDevice(int position) {
        return list.get(position);
    }

    private class ItemHolder {
        private CircleImageView img_image;
        private TextView txt_name;
        private TextView txt_type;
        private TextView txt_sex;
        private TextView txt_introduce;

    }

}
