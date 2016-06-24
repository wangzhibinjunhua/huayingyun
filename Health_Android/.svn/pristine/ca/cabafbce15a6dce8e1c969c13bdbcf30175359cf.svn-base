package com.tentinet.healthy.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.tentinet.healthy.R;
import com.tentinet.healthy.adapter.ClucoseAdapter;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.widget.PageLoadIng;

import java.util.ArrayList;

/**
 * 血糖数据view
 * TODO
 * Author YKK
 * Date 2016/5/4 14:01
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class GlucoseView extends RelativeLayout {

    private Context context;

    private ListView data_list;

    private PageLoadIng pageLoadIng;

    private ClucoseAdapter glucoseAdapter;

    private ArrayList<DataBean>datas;

    public GlucoseView(Context context) {
        super(context);
        init(context);
    }

    public GlucoseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private  void init(Context context){
        this.context=context;
        this.addView(LayoutInflater.from(context).inflate(R.layout.item_clucose, null), new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        data_list=(ListView) findViewById(R.id.data_list);
        pageLoadIng=(PageLoadIng)findViewById(R.id.data_page_load);
        datas=new ArrayList<>();
        glucoseAdapter=new ClucoseAdapter(context,datas);
        data_list.setAdapter(glucoseAdapter);
        DataBean bean=new DataBean();
        bean.setBlooding("11");
        datas.add(bean);
        glucoseAdapter.notifyDataSetChanged();

    }
}
