package com.tentinet.healthy.activity;

import android.os.AsyncTask;
import android.webkit.WebView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.ProgressWebView;

/**
 * 关于我们.
 * @author paladin
 */
public class AboutUsActivity extends BaseActivity {

    /**
     * 标题.
     */
    private TitleView view_title;
    /**
     * 关于我们.
     */
    private ProgressWebView web_content;

    @Override
    protected void getData() {

    }

    @Override
    protected int setContent() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void init() {
        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setTitle(R.string.about_us);
        view_title.setBackImageButton();
        web_content = (ProgressWebView) findViewById(R.id.web_content);
        web_content.loadUrl(BaseBiz.SERVER_PATH + "/Mobile/About/us");
    }

    @Override
    protected void registerEvent() {

    }

}
