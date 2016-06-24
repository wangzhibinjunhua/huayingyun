package com.tentinet.healthy.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.creative.FingerOximeter.IFingerOximeterCallBack;
import com.tentinet.healthy.R;
import com.tentinet.healthy.adapter.HomeDeviceAdapter;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.HomeTab;
import com.tentinet.healthy.view.MeasurementView;
import com.tentinet.healthy.view.TitleView;

import java.util.ArrayList;

public class SearchDeviceActivity extends BaseActivity {
    /**
     * 标题栏
     */
    TitleView view_title;
    /**
     * 搜索设备列表
     */
    private ListView lv_searchDeviceList;
    /**
     * 设备数据集合
     */
    private ArrayList<DeviceBean> searchedDevices;


    private LinearLayout linearLayout;



    @Override
    protected void getData() {

    }

    @Override
    protected int setContent() {

        return R.layout.activity_search_device;
    }

    @Override
    protected void init() {

        searchedDevices = new ArrayList<>();
//        searchedDevices.add(new DeviceBean());
//        searchedDevices.add(new DeviceBean());
//        searchedDevices.add(new DeviceBean());
        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setBackImageButton(R.mipmap.icon_back_white);
//        //搜索设备列表
        lv_searchDeviceList = (ListView) findViewById(R.id.hometab_lv_search_device);
        lv_searchDeviceList.setAdapter(new HomeDeviceAdapter(this, searchedDevices));
        view_title.setTitle(R.string.bind_smart_device);
    }

    @Override
    protected void registerEvent() {
     //   view_title.setBackButton();
        lv_searchDeviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showShortToast(SearchDeviceActivity.this, "position = " + position + "跳转设备详情");
                Bundle bundle = new Bundle();
                bundle.putInt(SearchDeviceActivity.this.getString(R.string.from), HomeTab.FROM_DEVICE);
                bundle.putSerializable(getResources().getString(R.string.key_device), searchedDevices.get(position));
                IntentUtil.gotoActivity(SearchDeviceActivity.this, DetailActivity.class, bundle);
            }
        });
    }
}
