package com.tentinet.healthy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.view.TitleView;

/**
 * 页面加载框
 * TODO
 * Author YKK
 * Date 2016/4/26 16:38
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class PageLoadIng extends RelativeLayout {
    public PageLoadIng(Context context) {
        super(context);
        init(context);
    }

    public PageLoadIng(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private Context context;

    /**
     * 加载文字
     */
    private TextView tv_msg;
    /**
     * 加载进度框
     */
    private ProgressBar progressBar;

    private void init(Context context) {
        this.context = context;
        this.addView(LayoutInflater.from(context).inflate(R.layout.item_page_loading, null), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        tv_msg = (TextView) findViewById(R.id.tv_page_msg);
        setTvMsg(R.string.loading);
        progressBar = (ProgressBar) findViewById(R.id.page_progress);

    }

    /**
     * 设置加载文字
     */
    public void setTvMsg(String msg) {
        if (tv_msg != null) {
            tv_msg.setText(msg);
        }
    }

    /**
     * 设置加载文字
     */
    public void setTvMsg(int msg) {
        if (tv_msg != null) {
            tv_msg.setText(msg);
        }
    }

    /**
     * 暂无数据
     */
    public void noData(){
        setTvMsg(context.getString(R.string.no_datas));
        progressBar.setVisibility(GONE);
    }

    /**
     * 文字提示
     */
    public void noData(String msg){
        setTvMsg(msg);
        progressBar.setVisibility(GONE);
    }

    /**
     * 都不不显示
     */
    public void messageAndProgressGone(){
        setTvMsg("");
        progressBar.setVisibility(GONE);
    }

    /**
     * 显示和文字
     */
    public void showPageLoad(String msg){
        setTvMsg(msg);
        progressBar.setVisibility(VISIBLE);
    }

    /**
     * 文本点击事件
     * @param listener
     */
    public void setMsgListener(OnClickListener listener){
        tv_msg.setOnClickListener(listener);
    }


}
