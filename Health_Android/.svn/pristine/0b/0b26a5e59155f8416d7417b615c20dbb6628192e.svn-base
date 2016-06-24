package com.tentinet.healthy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DoctorBean;
import com.tentinet.healthy.bean.DoctorMsgBean;
import com.tentinet.healthy.util.AsyncImageSetter;
import com.tentinet.healthy.util.DisplayUtils;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.widget.CircleImageView;

import java.util.ArrayList;

/**
 * 亲友适配器.
 *
 * @author paladin
 */
public class DoctorMsgAdapter extends LoadImageBaseAdapter {

    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 我的设备列表.
     */
    private ArrayList<DoctorMsgBean> list;

    private int width=0;

    public DoctorMsgAdapter(Context context, ArrayList<DoctorMsgBean> list) {
        this.context = context;
        this.list = list;
        width= DisplayUtils.dip2px(context,50.0f);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LeftItemHolder leftItem = null;
        DoctorMsgBean device = list.get(position);
        if (view == null) {
            leftItem = new LeftItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_doctor_msg_left_list, null);
            leftItem.txt_content = (TextView) view.findViewById(R.id.txt_content);
            leftItem.txt_date = (TextView) view.findViewById(R.id.txt_date);
            leftItem.img_image = (CircleImageView) view.findViewById(R.id.img_image);
            leftItem.params = (RelativeLayout.LayoutParams) leftItem.txt_content.getLayoutParams();
            leftItem.imgParams=(RelativeLayout.LayoutParams) leftItem.img_image.getLayoutParams();
            view.setTag(leftItem);
        }
        leftItem = (LeftItemHolder) view.getTag();
        if (device.getType() == 1) {
            leftItem.params.removeRule(RelativeLayout.LEFT_OF);
            leftItem.params.addRule(RelativeLayout.RIGHT_OF,leftItem.img_image.getId());
            leftItem.imgParams.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            leftItem.imgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,RelativeLayout.TRUE);
            leftItem.params.rightMargin=width;
            leftItem.params.leftMargin=0;
            leftItem.txt_content.setBackgroundResource(R.mipmap.chatfrom_bg_focused);
        } else if (device.getType() == 2) {
            leftItem.params.removeRule(RelativeLayout.RIGHT_OF);
            leftItem.params.addRule(RelativeLayout.LEFT_OF,leftItem.img_image.getId());
            leftItem.imgParams.removeRule(RelativeLayout.ALIGN_PARENT_LEFT);
            leftItem.imgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
            leftItem.txt_content.setBackgroundResource(R.mipmap.chatto_bg_focused);
            leftItem.params.leftMargin=width;
            leftItem.params.rightMargin=0;
        }
        leftItem.txt_content.setLayoutParams(leftItem.params);
        leftItem.img_image.setLayoutParams(leftItem.imgParams);
        leftItem.txt_content.setText(device.getContent());
        leftItem.txt_date.setText(device.getDate());
        AsyncImageSetter.setImage(leftItem.img_image, "", ImageView.ScaleType.CENTER_CROP);
        return view;
    }


    public DoctorMsgBean getDevice(int position) {
        return list.get(position);
    }

    private class LeftItemHolder {
        private CircleImageView img_image;
        private TextView txt_content;
        private TextView txt_date;
        private RelativeLayout.LayoutParams params;
        private RelativeLayout.LayoutParams imgParams;

    }


}
