package com.tentinet.healthy.activity;

import com.tentinet.healthy.R;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.ProgressWebView;
/**
 * 意见反馈和协议
 * TODO
 * Author YKK
 * Date 2016/5/11 13:51
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class ServiceTermsActivity extends BaseActivity {


    private TitleView view_title;
    private ProgressWebView web_content;
    private String content = "";
    private String title = "";

    @Override
    protected void getData() {
        content = getIntent().getExtras().getString(SetPasswordActivity.KEY_USER);
        title = getIntent().getExtras().getString(SetPasswordActivity.KEY_USER_TITLE);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_service_terms;
    }

    @Override
    protected void init() {
        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setTitle(title);
        view_title.setBackImageButton();
        web_content = (ProgressWebView) findViewById(R.id.web_content);
        web_content.loadUrl(BaseBiz.SERVER_PATH + content);
    }

    @Override
    protected void registerEvent() {

    }
}
