package com.tentinet.healthy.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.RegexUtil;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.TitleView;

/**
 * 添加亲友
 * TODO
 * Author YKK
 * Date 2016/5/11 13:51
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class AddParentActivity extends BaseActivity implements View.OnClickListener {
    private TitleView view_title;
    private EditText edit_parent_name, edit_parent_phone;

    private Button btn_submit;
    private UserBiz userBiz = new UserBiz();

    @Override
    protected void getData() {

    }

    @Override
    protected int setContent() {
        return R.layout.activity_add_parent;
    }

    @Override
    protected void init() {
        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setTitle(R.string.add_parent);
        view_title.setBackImageButton();
        edit_parent_name = (EditText) findViewById(R.id.edit_parent_name);
        edit_parent_phone = (EditText) findViewById(R.id.edit_parent_phone);
        btn_submit = (Button) findViewById(R.id.btn_submit);
    }

    @Override
    protected void registerEvent() {
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_submit:
                checkData();
                break;
        }
    }

    private void checkData() {
        if (StringUtil.isEmpty(edit_parent_name.getText().toString())) {
            ToastUtil.showLongToast(AddParentActivity.this, getString(R.string.enter_name));
            return;
        }
        if (StringUtil.isEmpty(edit_parent_phone.getText().toString())) {
            ToastUtil.showLongToast(AddParentActivity.this, getString(R.string.enter_phone));
            return;
        }
        if (!RegexUtil.isMobileNO(edit_parent_phone.getText().toString())) {
            ToastUtil.showLongToast(AddParentActivity.this, getString(R.string.enter_ok_phone));
            return;
        }
        addParentAsyn.starAsyn();
    }

    private AsynProcessing addParentAsyn = new AsynProcessing() {
        @Override
        protected void before() {
            CustomDialog.showWaitDialog(AddParentActivity.this);
        }

        @Override
        protected Object AsynTask() {
            return userBiz.addParent(TApplication.user.getPhone(), edit_parent_name.getText().toString(), edit_parent_phone.getText().toString());
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, AddParentActivity.this, false);
            ToastUtil.showLongToast(AddParentActivity.this, response.getInfo());
            if (response.isSuccess()) {
                edit_parent_name.setText("");
                edit_parent_phone.setText("");
            }
            CustomDialog.dismissDialog();
        }
    };
}
