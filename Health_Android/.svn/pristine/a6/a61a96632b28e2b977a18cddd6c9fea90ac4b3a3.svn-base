package com.tentinet.healthy.activity;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.adapter.DoctorListAdapter;
import com.tentinet.healthy.adapter.ParentAdapter;
import com.tentinet.healthy.bean.DoctorBean;
import com.tentinet.healthy.bean.ParentBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.PageLoadIng;
import com.tentinet.healthy.widget.pulltorefresh.PullToRefreshLayout;
import com.tentinet.healthy.widget.pulltorefresh.PullableListView;

import java.util.ArrayList;

/**
 * 医生列表页面
 * TODO
 * Author YKK
 * Date 2016/5/11 13:51
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class DoctorListActivity extends BaseActivity {
    /**
     * 标题栏
     */
    TitleView view_title;
    /**
     * 亲友列表
     */
    private PullToRefreshLayout pullToRefreshLayout;

    private PullableListView lv_parentList;
    /**
     * 亲友数据列表
     */
    private ArrayList<DoctorBean> doctors;

    private PageLoadIng pageLoadIng;
    private UserBiz userBiz = new UserBiz();
    private boolean isFirstLoadIng = false;
    private DoctorListAdapter adapter;

    DoctorBean doctorBean;
    private int page = 1;

    @Override
    protected void getData() {

    }

    @Override
    protected int setContent() {
        return R.layout.activity_parent;
    }

    @Override
    protected void init() {
        doctors = new ArrayList<>();
        view_title = (TitleView) findViewById(R.id.view_title);
        //亲友列表
        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.pulllayout_parent);
        lv_parentList = (PullableListView) findViewById(R.id.hometab_lv_parent_list);
           pullToRefreshLayout.setPullUpEnable(false);
        pullToRefreshLayout.setOnPullListener(pullListener);
        pageLoadIng = (PageLoadIng) findViewById(R.id.parent_load);
        adapter = new DoctorListAdapter(this, doctors);
        lv_parentList.setAdapter(adapter);
        view_title.setTitle(R.string.doctor_list);
        view_title.setRightOneImageButton(R.mipmap.btn_add, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              //  IntentUtil.gotoActivityForResult(DoctorListActivity.this, AddParentActivity.class, 1);
            }
        });
        isFirstLoadIng = true;
     //   getParentList.starAsyn();
        addDoctor();

    }

    @Override
    protected void registerEvent() {
        view_title.setBackImageButton();
        lv_parentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                doctorBean = adapter.getDevice(position);
                if (doctorBean == null)
                    return;
                IntentUtil.gotoActivityForResult(DoctorListActivity.this, DoctorInfoActivity.class, 1);

            }
        });
    }


    /**
     * 获取资讯列表信息异步.
     */
    private AsynProcessing getParentList = new AsynProcessing() {

        @Override
        protected void before() {

        }

        @Override
        protected Object AsynTask() {
            return userBiz.getParentList(TApplication.user.getPhone(), page + "");
            // return
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, DoctorListActivity.this, false);
            if (response.isSuccess()) {
                doctors.addAll((ArrayList<DoctorBean>) response.getData());
            }
            if (doctors.size() < 1) {
                pageLoadIng.noData();
            } else {
                pageLoadIng.messageAndProgressGone();
                adapter.notifyDataSetChanged();
            }
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        }

    };


    private PullToRefreshLayout.OnPullListener pullListener = new PullToRefreshLayout.OnPullListener() {
        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
          //  doctors.clear();
         //   page = 1;
          //  getParentList.starAsyn();
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
         //   page++;
         //   getParentList.starAsyn();
        }

    };

    private void addDoctor() {
        DoctorBean bean = new DoctorBean();
        bean.setImgUrl("");
        bean.setName("张三");
        bean.setIntroduce("住院科：消化内科，泌尿内科.....");
        bean.setSex("女");
        bean.setType("主治医师");
        doctors.add(bean);
        bean = new DoctorBean();
        bean.setImgUrl("");
        bean.setName("张三");
        bean.setIntroduce("住院科：消化内科，泌尿内科.....");
        bean.setSex("女");
        bean.setType("主治医师");
        doctors.add(bean);
        bean = new DoctorBean();
        bean.setImgUrl("");
        bean.setName("张三");
        bean.setIntroduce("住院科：消化内科，泌尿内科.....");
        bean.setSex("女");
        bean.setType("主治医师");
        doctors.add(bean);
        bean = new DoctorBean();
        bean.setImgUrl("");
        bean.setName("张三");
        bean.setIntroduce("住院科：消化内科，泌尿内科.....");
        bean.setSex("女");
        bean.setType("主治医师");
        doctors.add(bean);
        bean = new DoctorBean();
        bean.setImgUrl("");
        bean.setName("张三");
        bean.setIntroduce("住院科：消化内科，泌尿内科.....");
        bean.setSex("女");
        bean.setType("主治医师");
        doctors.add(bean);

        if (doctors.size() < 1) {
            pageLoadIng.noData();
        } else {
            pageLoadIng.messageAndProgressGone();
            adapter.notifyDataSetChanged();
        }
       // pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
      //  pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
    }
}
