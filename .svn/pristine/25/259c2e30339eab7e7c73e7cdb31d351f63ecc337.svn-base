package com.tentinet.healthy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.NewsBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AsyncImageSetter;
import com.tentinet.healthy.util.LogUtil;

import java.util.ArrayList;

/**
 * 资讯适配器.
 * @author paladin.
 */
public class NewsAdapter extends LoadImageBaseAdapter {

    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 资讯内容.
     */
    private ArrayList<NewsBean> list;

    public NewsAdapter(Context context, ArrayList<NewsBean> list) {
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
        NewsBean news = list.get(position);
        if (null == view) {
            item = new ItemHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_news, null);
            item.img_image = (ImageView) view.findViewById(R.id.img_image);
            item.txt_title = (TextView) view.findViewById(R.id.txt_title);
            item.txt_time = (TextView) view.findViewById(R.id.txt_time);
            view.setTag(item);
        } else {
            item = (ItemHolder) view.getTag();
        }
        AsyncImageSetter.setImage(item.img_image,BaseBiz.SERVER_PATH+news.getImage());
        LogUtil.logDebugMessage("image=="+ BaseBiz.SERVER_PATH+news.getImage());
        item.txt_title.setText(news.getTitle());
        item.txt_time.setText(news.getTime());
        return view;
    }

    private class ItemHolder {
        private ImageView img_image;
        private TextView txt_title;
        private TextView txt_time;
    }

}
