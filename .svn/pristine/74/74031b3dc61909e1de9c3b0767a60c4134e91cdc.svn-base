package com.tentinet.healthy.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.activity.HealthAssessActivity;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.bean.HealthAssessBean;

import java.util.ArrayList;

/**
 * 设备评估适配器
 * <h3>Author</h3> Rick Chan
 * <h3>Date</h3> 2016/3/29 16:34
 * <h3>Copyright</h3>Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class HealthAssessAdapter extends LoadImageBaseAdapter {

    private Context mContext;

    private ArrayList<HealthAssessActivity.HealthGrid> healthAssessBeens;

    public HealthAssessAdapter(Context context, ArrayList<HealthAssessActivity.HealthGrid> healthAssessBeens) {
        mContext = context;
        this.healthAssessBeens = healthAssessBeens;
    }

    @Override
    public int getCount() {
        return healthAssessBeens.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_health_assess_griditem, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.health_txt_name);
            viewHolder.tv_result = (TextView) convertView.findViewById(R.id.health_txt_result);
            viewHolder.tv_msg = (TextView) convertView.findViewById(R.id.health_txt_result_msg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置设备名
        HealthAssessActivity.HealthGrid bean = healthAssessBeens.get(position);
        viewHolder.tv_name.setText(bean.getName());//devices.get(position).getDeviceName()
        viewHolder.tv_result.setText(Html.fromHtml(bean.getResult()));
        viewHolder.tv_msg.setText(Html.fromHtml(bean.getMsg()));
        return convertView;
    }

    private class ViewHolder {
        TextView tv_result;
        TextView tv_name;
        TextView tv_msg;
    }
}
