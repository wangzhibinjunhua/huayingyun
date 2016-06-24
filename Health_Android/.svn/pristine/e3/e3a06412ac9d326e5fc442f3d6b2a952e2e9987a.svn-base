package com.tentinet.healthy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.NewsBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.util.AsyncImageSetter;
import com.tentinet.healthy.util.LogUtil;

import java.util.ArrayList;

/**
 * 血糖适配器.
 * @author paladin.
 */
public class ClucoseAdapter extends LoadImageBaseAdapter {

    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 资讯内容.
     */
    private ArrayList<DataBean> list;

    public ClucoseAdapter(Context context, ArrayList<DataBean> list) {
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
        DataBean news = list.get(position);
        if (null == view) {
            item = new ItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_list_clucose, null);
            item.txt_emp = (TextView) view.findViewById(R.id.txt_clucose_empt);
            item.txt_bre_after = (TextView) view.findViewById(R.id.txt_clucose_bre_after);
            item.txt_lunch_front = (TextView) view.findViewById(R.id.txt_clucose_lunch_front);
            item.txt_lunch_after = (TextView) view.findViewById(R.id.txt_clucose_lunch_after);
            item.txt_dinner_front = (TextView) view.findViewById(R.id.txt_clucose_dinner_front);
            item.txt_dinner_after = (TextView) view.findViewById(R.id.txt_clucose_dinner_after);
            item.txt_sleep = (TextView) view.findViewById(R.id.txt_clucose_sleep);
            view.setTag(item);
        } else {
            item = (ItemHolder) view.getTag();

        }

        return view;
    }

    private class ItemHolder {
        private TextView txt_emp;
        private TextView txt_bre_after;
        private TextView txt_lunch_front;
        private TextView txt_lunch_after;
        private TextView txt_dinner_front;
        private TextView txt_dinner_after;
        private TextView txt_sleep;
    }

}
