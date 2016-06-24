package com.tentinet.healthy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.bean.UserBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.EditInputBox;

/**
 * 登录界面.
 *
 * @author paladin.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    /**
     * 标题.
     */
    private TitleView view_title;
    /**
     * 账号.
     */
    private EditInputBox edit_account;
    /**
     * 显示密码.
     */
    private CheckBox check_password;
    /**
     * 密码.
     */
    private EditInputBox edit_password;
    /**
     * 登录按钮.
     */
    private Button btn_login;
    /**
     * 忘记密码.
     */
    private TextView txt_forgetPassword;
    /**
     * 注册.
     */
    private TextView txt_register;
    /**
     * 用户业务.
     */
    private UserBiz biz;


    private ImageView iv_password_show;

    @Override
    protected void getData() {
    }

    @Override
    protected int setContent() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        biz = new UserBiz();
        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setTitle(R.string.login);
        view_title.setBackImageButton(R.mipmap.icon_back_white);
        edit_account = (EditInputBox) findViewById(R.id.edit_account);
        edit_password = (EditInputBox) findViewById(R.id.edit_password);
        check_password = (CheckBox) findViewById(R.id.check_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        txt_forgetPassword = (TextView) findViewById(R.id.txt_forget_password);
        txt_register = (TextView) findViewById(R.id.txt_register);
        iv_password_show = (ImageView) findViewById(R.id.iv_password_show);
        getAccountInfo();
        edit_account.getInputClear().setVisibility(View.GONE);
        edit_password.getInputClear().setVisibility(View.GONE);
    }

    @Override
    protected void registerEvent() {
        check_password.setOnCheckedChangeListener(this);
        btn_login.setOnClickListener(this);
        txt_forgetPassword.setOnClickListener(this);
        txt_register.setOnClickListener(this);
        iv_password_show.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                loginAsyn.starAsyn();
                break;
            case R.id.txt_forget_password:
                Bundle bundleF = new Bundle();
                bundleF.putInt(SetPasswordActivity.KEY_SHOW_CONTENT, SetPasswordActivity.SHOW_FORGET_PASSWORD);
                IntentUtil.gotoActivity(LoginActivity.this, SetPasswordActivity.class, bundleF);
                break;
            case R.id.txt_register:
                Bundle bundleR = new Bundle();
                bundleR.putInt(SetPasswordActivity.KEY_SHOW_CONTENT, SetPasswordActivity.SHOW_REGISTER);
                IntentUtil.gotoActivity(LoginActivity.this, SetPasswordActivity.class, bundleR);
                break;
            case R.id.iv_password_show:
                changeShowPwd();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            edit_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        } else {
            edit_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
    }

    /**
     * 存储账户密码.
     */
    private void changeShowPwd() {
        if (edit_password.getInputEditText().getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            edit_password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            iv_password_show.setImageResource(R.mipmap.login_content_btn_password_pressed);
        } else {
            edit_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            iv_password_show.setImageResource(R.mipmap.login_content_btn_password_default);
        }
    }

    /**
     * 存储账户密码.
     */
    private void saveAccountInfo() {
        TApplication.sp.set(KEY_ACCOUNT, edit_account.getInputContent());
        TApplication.sp.set(KEY_PASSWORD, edit_password.getInputContent());
    }

    /**
     * 提取账户密码.
     */
    private void getAccountInfo() {
        edit_account.setContent(TApplication.sp.get(KEY_ACCOUNT, ""));
        edit_password.setContent(TApplication.sp.get(KEY_PASSWORD, ""));
    }

    /**
     * 登录异步处理对象.
     */
    private AsynProcessing loginAsyn = new AsynProcessing() {

        @Override
        protected void before() {
            CustomDialog.showWaitDialog(LoginActivity.this);
        }

        @Override
        protected Object AsynTask() {
            return biz.login(edit_account.getInputContent(), edit_password.getInputContent());
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, LoginActivity.this, false);
            if (response.isSuccess()) {
                TApplication.user = (UserBean) response.getData();
                saveAccountInfo();
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, MainActivity.class);
                setResult(RESULT_OK, intent);
                finish();
                // IntentUtil.gotoActivityAndFinish(LoginActivity.this,);
            }
            CustomDialog.dismissDialog();
        }

    };

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)edit_password.getInputClear().getLayoutParams();
        params.setMargins(0,0,iv_password_show.getWidth()+15,0);
        edit_password.getInputClear().setLayoutParams(params);
    }

    /**
     * 键:账号.
     */
    public static final String KEY_ACCOUNT = "account";
    /**
     * 键:密码.
     */
    public static final String KEY_PASSWORD = "password";

}
