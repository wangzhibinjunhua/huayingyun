package com.tentinet.healthy.activity;

import com.tentinet.healthy.R;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.ProgressWebView;

/**
 * 资讯界面.
 * @author paladin.
 */
public class NewsActivity extends BaseActivity {

    /**
     * 资讯id.
     */
    private String id;
    /**
     * 标题.
     */
    private TitleView view_title;
    /**
     * 咨询详情.
     */
    private ProgressWebView web_content;

    @Override
    protected void getData() {
        id = this.getIntent().getStringExtra(KEY_NEWS_ID);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_news;
    }

    @Override
    protected void init() {
        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setTitle(R.string.news_info);
        view_title.setBackImageButton();
        web_content = (ProgressWebView) findViewById(R.id.web_content);
        web_content.loadUrl(BaseBiz.SERVER_PATH + "/Mobile/News/detail/hid/" + id);
    }

    @Override
    protected void registerEvent() {

    }

    /**
     * 键：资讯ID.
     */
    public static final String KEY_NEWS_ID = "newID";

}
