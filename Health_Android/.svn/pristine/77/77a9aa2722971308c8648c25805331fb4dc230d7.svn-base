package com.tentinet.healthy.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.CallCenterBean;
import com.tentinet.healthy.util.LogUtil;

import java.util.ArrayList;

/**
 * 呼叫中心设备适配器.
 *
 * @author paladin
 */
public class CallCenterAdapter extends LoadImageBaseAdapter {

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    private int type = 0;

    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 我的设备列表.
     */
    private ArrayList<CallCenterBean> list;

    public CallCenterAdapter(Context context, ArrayList<CallCenterBean> list) {
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
        final ItemHolder item;
        final CallCenterBean device = list.get(position);
        if (null == view) {
            item = new ItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_call_center, null);
            item.txt_name = (TextView) view.findViewById(R.id.txt_call_name);
            item.txt_phone = (TextView) view.findViewById(R.id.txt_call_phone);
            item.iv_call_icon = (ImageView) view.findViewById(R.id.iv_call_phone);

            view.setTag(item);
        } else {
            item = (ItemHolder) view.getTag();
        }
        // TODO 设置设备图片.
        LogUtil.logDebugMessage("position = " + position + "; device = " + device.toString());
        item.txt_name.setText(device.getName());
        item.txt_phone.setText(device.getPhone());
        if (type == 1) {
            item.iv_call_icon.setImageResource(R.mipmap.icon_goto);
        }
        item.iv_call_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 只进入拨号界面，不拨打
                if (type == 0) {
                    Uri uri = Uri.parse("tel:" + device.getPhone());
                    Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                    context.startActivity(intent);
                } else if (type == 1) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(device.getPhone());
                    intent.setData(content_url);
                    context.startActivity(intent);
                }
            }
        });
        return view;
    }

    public CallCenterBean getDevice(int position) {
        return list.get(position);
    }

    private class ItemHolder {
        private TextView txt_name;
        private TextView txt_phone;
        private ImageView iv_call_icon;

    }

}
