package com.tentinet.healthy.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.adapter.CallCenterAdapter;
import com.tentinet.healthy.bean.CallCenterBean;
import com.tentinet.healthy.view.TitleView;

import java.util.ArrayList;

public class CallCneterActivity extends BaseActivity {

    private ListView lv_call_center;

    private CallCenterAdapter callCenterAdapter;

    private ArrayList<CallCenterBean> arrayList;

    private TitleView title_view;
    @Override
    protected void getData() {

    }

    @Override
    protected int setContent() {
        return R.layout.activity_call_cneter;
    }

    @Override
    protected void init() {
        title_view=(TitleView)findViewById(R.id.title_view);
        title_view.setTitle(R.string.call_center);
        title_view.setBackImageButton();
        lv_call_center = (ListView) findViewById(R.id.lv_call_center);
        arrayList=new ArrayList<>();
        callCenterAdapter=new CallCenterAdapter(CallCneterActivity.this,arrayList);
        lv_call_center.setAdapter(callCenterAdapter);
    }

    @Override
    protected void registerEvent() {
        arrayList.add(new CallCenterBean("匪警","110 ",""));
        arrayList.add(new CallCenterBean("火警","119 ",""));
        arrayList.add(new CallCenterBean("急救中心","120",""));
        arrayList.add(new CallCenterBean("交通事故告警","122",""));
        arrayList.add(new CallCenterBean("查号台","114",""));
        callCenterAdapter.notifyDataSetChanged();
        lv_call_center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CallCenterBean bean=arrayList.get(position);
                if (bean==null){
                    return;
                }
                // 只进入拨号界面，不拨打
//                Uri uri = Uri.parse("tel:" + bean.getPhone());
//                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
//                startActivity(intent);
            }
        });
    }
}
