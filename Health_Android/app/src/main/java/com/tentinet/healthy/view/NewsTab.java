package com.tentinet.healthy.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.tentinet.healthy.R;
import com.tentinet.healthy.activity.NewsActivity;
import com.tentinet.healthy.adapter.NewsAdapter;
import com.tentinet.healthy.bean.NewsBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.NewsBiz;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.widget.PageLoadIng;
import com.tentinet.healthy.widget.pulltorefresh.PullToRefreshLayout;
import com.tentinet.healthy.widget.pulltorefresh.PullableListView;

import java.util.ArrayList;

/**
 * 资讯标签页.
 *
 * @author paladin.
 */
public class NewsTab extends RelativeLayout implements AdapterView.OnItemClickListener {

    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 标题.
     */
    private TitleView view_title;
    /**
     * 资讯列表.
     */
    private PullableListView list_news;
    private PullToRefreshLayout pullToRefreshLayout;
    private NewsAdapter adapter;
    private ArrayList<NewsBean> list;

    private PageLoadIng progressBar;
    /**
     * 资讯异步.
     */
    private NewsBiz news;
    /**
     * 当前页.
     */
    private int page;

    private boolean isFirstLoadIng = false;

    public NewsTab(Context context) {
        super(context);
        init(context);
    }

    public NewsTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public NewsTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化.
     *
     * @param context 上下文环境.
     */
    private void init(Context context) {
        this.context = context;
        page = 1;
        news = new NewsBiz();
        list = new ArrayList<NewsBean>();
        adapter = new NewsAdapter(context, list);
        this.addView(LayoutInflater.from(context).inflate(R.layout.tab_news, null), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        isFirstLoadIng = true;
        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setTitle(R.string.news);
        pullToRefreshLayout = (PullToRefreshLayout) findViewById(R.id.pulllayout_news);
        list_news = (PullableListView) pullToRefreshLayout.getPullableView();
        pullToRefreshLayout.setOnPullListener(pullListener);
        progressBar = (PageLoadIng) findViewById(R.id.tab_news_load);
        list_news.setAdapter(adapter);
        registerEvent();
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                getNewsList.starAsyn();
            }
        }.sendEmptyMessageDelayed(0, 500);
    }

    /**
     * 注册监听.
     */
    private void registerEvent() {
        list_news.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putString(NewsActivity.KEY_NEWS_ID, list.get(position).getId());
        IntentUtil.gotoActivity(context, NewsActivity.class, bundle);
    }

    /**
     * 获取资讯列表信息异步.
     */
    private AsynProcessing getNewsList = new AsynProcessing() {

        @Override
        protected void before() {

        }

        @Override
        protected Object AsynTask() {
            return news.getNewsList(String.valueOf(page));
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, context, false);
            if (response.isSuccess()) {
                if (page == 1) {
                    list.clear();
                }
                list.addAll((ArrayList<NewsBean>) response.getData());
            }
            if (list.size()<1){
                progressBar.noData();
            }else {
                progressBar.messageAndProgressGone();
                adapter.notifyDataSetChanged();
            }
            pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
            pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
        }

    };

    private PullToRefreshLayout.OnPullListener pullListener = new PullToRefreshLayout.OnPullListener() {
        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            page = 1;
            getNewsList.starAsyn();
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
            page++;
            getNewsList.starAsyn();
        }
    };

}
