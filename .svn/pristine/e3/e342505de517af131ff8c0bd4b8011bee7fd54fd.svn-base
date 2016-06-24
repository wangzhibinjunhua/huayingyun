package com.tentinet.healthy.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.tentinet.healthy.R;
import com.tentinet.healthy.adapter.HomeParentAdapter;
import com.tentinet.healthy.adapter.ParentAdapter;
import com.tentinet.healthy.bean.NewsBean;
import com.tentinet.healthy.bean.ParentBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.HomeTab;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.PageLoadIng;
import com.tentinet.healthy.widget.pulltorefresh.PullToRefreshLayout;
import com.tentinet.healthy.widget.pulltorefresh.PullableListView;

import java.util.ArrayList;
/**
 * 亲友页面
 * TODO
 * Author YKK
 * Date 2016/5/11 13:51
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class ParentActivity extends BaseActivity {
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
    private ArrayList<ParentBean> parents;

    private PageLoadIng pageLoadIng;
    private UserBiz userBiz = new UserBiz();
    private boolean isFirstLoadIng = false;
    private ParentAdapter adapter;

    ParentBean parentBean;
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
        parents = new ArrayList<>();
        view_title = (TitleView) findViewById(R.id.view_title);
        //亲友列表
        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.pulllayout_parent);
        lv_parentList = (PullableListView) findViewById(R.id.hometab_lv_parent_list);
        //   pullToRefreshLayout.setPullUpEnable(false);
        pullToRefreshLayout.setOnPullListener(pullListener);
        pageLoadIng = (PageLoadIng) findViewById(R.id.parent_load);
        adapter = new ParentAdapter(this, parents);
        lv_parentList.setAdapter(adapter);
        view_title.setTitle(R.string.parent);
        view_title.setRightOneImageButton(R.mipmap.btn_add, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IntentUtil.gotoActivityForResult(ParentActivity.this, AddParentActivity.class, 1);
            }
        });
        isFirstLoadIng = true;
        getParentList.starAsyn();

    }

    @Override
    protected void registerEvent() {
        view_title.setBackImageButton();
        lv_parentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                parentBean = adapter.getDevice(position);
                if (parentBean == null)
                    return;
                CustomDialog.showOkAndCalcelDialog(ParentActivity.this, getString(R.string.delete_parent), getString(R.string.yes_no_delete) + "？", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteAsyn.starAsyn();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CustomDialog.dismissDialog();
                    }
                });
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
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, ParentActivity.this, false);
            if (response.isSuccess()) {
                parents.addAll((ArrayList<ParentBean>) response.getData());
            }
            if (parents.size() < 1) {
                pageLoadIng.noData();
            } else {
                pageLoadIng.messageAndProgressGone();
                adapter.notifyDataSetChanged();
            }
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        }

    };

    private AsynProcessing deleteAsyn = new AsynProcessing() {
        @Override
        protected void before() {

        }

        @Override
        protected Object AsynTask() {
            return userBiz.deleteParent(TApplication.user.getPhone(), parentBean.getHid());
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, ParentActivity.this, true);
            if (response.isSuccess()) {
                parents.remove(parentBean);
                adapter.notifyDataSetChanged();
            }
            CustomDialog.dismissDialog();
        }
    };
    private PullToRefreshLayout.OnPullListener pullListener = new PullToRefreshLayout.OnPullListener() {
        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            parents.clear();
            page = 1;
            getParentList.starAsyn();
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
            page++;
            getParentList.starAsyn();
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            pullToRefreshLayout.autoRefresh();
        }
    }
}
