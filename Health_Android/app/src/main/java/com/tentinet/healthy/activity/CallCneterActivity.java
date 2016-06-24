package com.tentinet.healthy.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.adapter.CallCenterAdapter;
import com.tentinet.healthy.bean.CallCenterBean;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.view.HomeTab;
import com.tentinet.healthy.view.TitleView;

import java.util.ArrayList;

/**
 * 呼叫中心
 * TODO
 * Author YKK
 * Date 2016/5/11 13:51
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */

public class CallCneterActivity extends BaseActivity {

    private ListView lv_call_center;

    private CallCenterAdapter callCenterAdapter;

    private ArrayList<CallCenterBean> arrayList;

    private TitleView title_view;
    private String content;

    @Override
    protected void getData() {
        content = getIntent().getExtras().getString(HomeTab.KEY_NAME);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_call_cneter;
    }

    @Override
    protected void init() {
        title_view = (TitleView) findViewById(R.id.title_view);
        title_view.setTitle(R.string.call_center);
        title_view.setBackImageButton();
        lv_call_center = (ListView) findViewById(R.id.lv_call_center);
        arrayList = new ArrayList<>();
        callCenterAdapter = new CallCenterAdapter(CallCneterActivity.this, arrayList);
        lv_call_center.setAdapter(callCenterAdapter);
    }

    @Override
    protected void registerEvent() {
        lv_call_center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CallCenterBean bean = arrayList.get(position);
                if (bean == null) {
                    return;
                }
                // 只进入拨号界面，不拨打
//                Uri uri = Uri.parse("tel:" + bean.getPhone());
//                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
//                startActivity(intent);
            }
        });
        addNetContent();
    }

    private void addNetContent() {
        if (StringUtil.isEmpty(content))
            finish();
        String[] arr = null, arrVal = null;
        switch (content) {
            case HomeTab.KEY_OLD:
                arr = getResources().getStringArray(R.array.old_name_array);
                arrVal = getResources().getStringArray(R.array.old_name_val_array);
                callCenterAdapter.setType(1);
                title_view.setTitle(R.string.old_clothes);
                break;
            case HomeTab.KEY_FOOD:
                arr = getResources().getStringArray(R.array.food_name_array);
                arrVal = getResources().getStringArray(R.array.food_name_val_array);
                callCenterAdapter.setType(1);
                title_view.setTitle(R.string.health_food);
                break;
            case HomeTab.KEY_TRAVEL:
                arr = getResources().getStringArray(R.array.travel_name_array);
                arrVal = getResources().getStringArray(R.array.travel_name_val_array);
                callCenterAdapter.setType(1);
                title_view.setTitle(R.string.travel_services);
                break;
            case HomeTab.KEY_TRIP:
                arr = getResources().getStringArray(R.array.trip_name_array);
                arrVal = getResources().getStringArray(R.array.trip_name_val_array);
                callCenterAdapter.setType(1);
                title_view.setTitle(R.string.trip_services);
                break;
            case HomeTab.KEY_MEDICAL:
                arr = getResources().getStringArray(R.array.medical_name_array);
                arrVal = getResources().getStringArray(R.array.medical_name_val_array);
                callCenterAdapter.setType(1);
                title_view.setTitle(R.string.medical_service);
                break;
            case HomeTab.KEY_HOME:
                arr = getResources().getStringArray(R.array.home_name_array);
                arrVal = getResources().getStringArray(R.array.home_name_val_array);
                callCenterAdapter.setType(1);
                title_view.setTitle(R.string.home_service);
                break;
            case HomeTab.KEY_CALL:
                arr = getResources().getStringArray(R.array.call_name_array);
                arrVal = getResources().getStringArray(R.array.call_name_val_array);
                callCenterAdapter.setType(0);
                break;
        }
        for (int i = 0; i < arr.length; i++) {
            arrayList.add(new CallCenterBean(arr[i], arrVal[i], ""));
        }

        callCenterAdapter.notifyDataSetChanged();
    }
}
