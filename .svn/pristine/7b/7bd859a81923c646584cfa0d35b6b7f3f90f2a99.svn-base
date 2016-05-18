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
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.TitleView;

public class AdviceFeedbackActivity extends BaseActivity implements View.OnClickListener {


    private TitleView titleView;

    private Button btn_submit;

    private UserBiz userBiz;

    private EditText edit_advice_content;

    @Override
    protected void getData() {

    }

    @Override
    protected int setContent() {
        return R.layout.activity_advice_feedback;
    }

    @Override
    protected void init() {
        titleView = (TitleView) findViewById(R.id.advice_view_title);
        titleView.setTitle(R.string.advice_feedback);
        titleView.setBackImageButton();
        btn_submit = (Button) findViewById(R.id.btn_submit);
        edit_advice_content = (EditText) findViewById(R.id.edit_advice_content);
        userBiz = new UserBiz();
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
                if (StringUtil.isEmpty(edit_advice_content.getText().toString())) {
                    ToastUtil.showLongToast(AdviceFeedbackActivity.this, getString(R.string.hite_advice_feedback));
                    return;
                }
                asynProcessing.starAsyn();
                break;
        }
    }

    private AsynProcessing asynProcessing = new AsynProcessing() {
        @Override
        protected void before() {
            CustomDialog.showWaitDialog(AdviceFeedbackActivity.this);
        }

        @Override
        protected Object AsynTask() {
            return userBiz.submitUserAdvice(TApplication.user.getPhone(), edit_advice_content.getText().toString());
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, AdviceFeedbackActivity.this, false);
            ToastUtil.showLongToast(AdviceFeedbackActivity.this, response.getInfo());
             if (response.isSuccess()) {
                 edit_advice_content.setText("");
            }
            CustomDialog.dismissDialog();
            finish();
        }
    };
}
