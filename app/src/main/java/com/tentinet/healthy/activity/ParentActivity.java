package com.tentinet.healthy.activity;


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
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.HomeTab;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.PageLoadIng;
import com.tentinet.healthy.widget.pulltorefresh.PullToRefreshLayout;
import com.tentinet.healthy.widget.pulltorefresh.PullableListView;

import java.util.ArrayList;

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
        pullToRefreshLayout.setPullUpEnable(false);
        pullToRefreshLayout.setOnPullListener(pullListener);
        pageLoadIng = (PageLoadIng) findViewById(R.id.parent_load);
        adapter = new ParentAdapter(this, parents);
        lv_parentList.setAdapter(adapter);
        view_title.setTitle(R.string.parent);
        view_title.setRightOneImageButton(R.mipmap.btn_add, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                IntentUtil.gotoActivity(ParentActivity.this, AddParentActivity.class);
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
//                ToastUtil.showShortToast(ParentActivity.this, "position = " + position + "跳转亲友界面");
//                Bundle bundle = new Bundle();
//                bundle.putInt(ParentActivity.this.getString(R.string.from), HomeTab.FROM_PARENT);
//                bundle.putSerializable(ParentActivity.this.getString(R.string.key_parent), parents.get(position));
//                IntentUtil.gotoActivity(ParentActivity.this, DetailActivity.class, bundle);
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
            return userBiz.getParentList(TApplication.user.getPhone());
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
    private PullToRefreshLayout.OnPullListener pullListener = new PullToRefreshLayout.OnPullListener() {
        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            parents.clear();
            getParentList.starAsyn();
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

        }

    };
}
