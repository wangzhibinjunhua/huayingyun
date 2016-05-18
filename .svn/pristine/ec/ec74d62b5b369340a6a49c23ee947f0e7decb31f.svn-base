package com.tentinet.healthy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.ParentBean;

import java.util.ArrayList;

/**
 * <h3>Description</h3>
 * 首页亲友列表适配器
 * <h3>Author</h3> Rick Chan
 * <h3>Date</h3> 2016/3/29 16:27
 * <h3>Copyright</h3>Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class HomeParentAdapter extends LoadImageBaseAdapter {

    private Context mContext;

    private ArrayList<ParentBean> parents;

    public HomeParentAdapter(Context context, ArrayList<ParentBean> parents) {
        mContext = context;
        this.parents = parents;
    }

    @Override
    public int getCount() {
        return parents.size();
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mine_device, null);
            viewHolder = new ViewHolder();
            viewHolder.img_icon = (ImageView) convertView.findViewById(R.id.img_image);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.txt_device);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置设备名
        viewHolder.tv_name.setText("parent");//parents.get(position).getParentName()
        //设置设备图片
//        viewHolder.img_icon.setImageDrawable();

        return convertView;
    }

    private class ViewHolder {
        TextView tv_name;
        ImageView img_icon;
    }
}
