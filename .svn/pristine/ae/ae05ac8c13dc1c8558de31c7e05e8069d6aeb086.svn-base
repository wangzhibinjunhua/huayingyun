package com.tentinet.healthy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/6/21 15:28
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class DoctorListView extends ListView {
    public DoctorListView(Context context) {
        super(context);
    }

    public DoctorListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DoctorListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
      super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
