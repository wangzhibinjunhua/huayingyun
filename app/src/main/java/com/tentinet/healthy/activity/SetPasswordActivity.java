package com.tentinet.healthy.activity;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.RegexUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.TitleView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 注册界面.
 *
 * @author paladin.
 */
public class SetPasswordActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 标题.
     */
    private TitleView view_title;
    /**
     * 获取验证码布局.
     */
    private LinearLayout linear_first;
    /**
     * 获取验证码.
     */
    private Button btn_get_code;
    /**
     * 手机.
     */
    private EditText edit_phone;
    private String phone;
    /**
     * 提交验证码布局.
     */
    private LinearLayout linear_second;
    /**
     * 手机提示信息.
     */
    private TextView txt_send_tip;
    /**
     * 验证码.
     */
    private EditText edit_code;
    /**
     * 时间提示.
     */
    private TextView txt_time_tip;
    /**
     * 提交验证码.
     */
    private Button btn_submit_code;
    /**
     * 设置密码布局.
     */
    private LinearLayout linear_third;
    /**
     * 设置密码.
     */
    private EditText edit_password;
    /**
     * 完成注册.
     */
    private Button btn_register_complete;
    /**
     * 验证码通过秘钥.
     */
    private String key;
    /**
     * 注册步骤.
     */
    private int steps;
    /**
     * 验证码发送等待时间.
     */
    private int wait_time;
    /**
     * 用户业务逻辑.
     */
    private UserBiz biz;
    /**
     * 异步处理对象.
     */
    private Handler handler = new Handler();
    /**
     * 显示内容标记.
     */
    private int flag;

    /**
     * 注册第一步,第二步，第三步
     */
    private TextView txt_register_first, txt_register_second, txt_register_thirdly;

    @Override
    protected void getData() {
        flag = this.getIntent().getIntExtra(KEY_SHOW_CONTENT, 0);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_set_password;
    }

    @Override
    protected void init() {
        steps = 1;
        wait_time = WAIT_TIME;
        biz = new UserBiz();
        view_title = (TitleView) findViewById(R.id.view_title);
        if (SHOW_REGISTER == flag) {
            view_title.setTitle(R.string.register);
        } else if (SHOW_FORGET_PASSWORD == flag) {
            view_title.setTitle(R.string.forget_password);
        }
        view_title.setBackImageButton(R.mipmap.icon_back_white, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (steps) {
                    case 1:
                        handler.removeCallbacks(timer);
                        SetPasswordActivity.this.finish();
                        break;
                    case 2:
                        back2GetCode();
                        break;
                    case 3:
                        back2SubmitCode();
                        break;
                }
            }

        });
        linear_first = (LinearLayout) findViewById(R.id.linear_first);
        edit_phone = (EditText) findViewById(R.id.edit_phone);
        btn_get_code = (Button) findViewById(R.id.btn_get_code);
        linear_second = (LinearLayout) findViewById(R.id.linear_second);
        txt_send_tip = (TextView) findViewById(R.id.txt_send_tip);
        edit_code = (EditText) findViewById(R.id.edit_code);
        txt_time_tip = (TextView) findViewById(R.id.txt_time_tip);
        btn_submit_code = (Button) findViewById(R.id.btn_submit_code);
        linear_third = (LinearLayout) findViewById(R.id.linear_third);
        edit_password = (EditText) findViewById(R.id.edit_password);
        btn_register_complete = (Button) findViewById(R.id.btn_register_complete);
        txt_register_first = (TextView) findViewById(R.id.txt_register_first);
        txt_register_second = (TextView) findViewById(R.id.txt_register_second);
        txt_register_thirdly = (TextView) findViewById(R.id.txt_register_thirdly);
        setStep(steps);
    }

    @Override
    protected void registerEvent() {
        btn_get_code.setOnClickListener(this);
        btn_submit_code.setOnClickListener(this);
        btn_register_complete.setOnClickListener(this);
        edit_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!RegexUtil.isMobileNO(s.toString())) {
                    btn_get_code.setEnabled(false);
                }else {
                    btn_get_code.setEnabled(true);
                }
            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_code:
                phone = edit_phone.getText().toString();
                if (RegexUtil.isMobileNO(phone)) {
                    getCodeAsyn.starAsyn();
                }else {
                    ToastUtil.showLongToast(SetPasswordActivity.this,getString(R.string.phone_error_msg));
                }
                break;
            case R.id.btn_submit_code:
                submitCodeAsyn.starAsyn();
                break;
            case R.id.btn_register_complete:
                setPasswordAsyn.starAsyn();
                break;
        }
    }

    /**
     * 获取验证码完成.
     *
     * @author paladin.
     * @date 2016/3/16 18:14
     */
    private void getCodeComplete() {
        steps = 2;
        linear_first.setVisibility(View.GONE);
        txt_send_tip.setText(String.format(getString(R.string.phone_tip), phone));
        if (txt_time_tip.getVisibility() == View.INVISIBLE) {
            txt_time_tip.setVisibility(View.VISIBLE);
            txt_time_tip.setText(String.format(getString(R.string.time_tip), wait_time));
            handler.postDelayed(timer, 1000);
        }
        edit_code.setText("123456");
        linear_second.setVisibility(View.VISIBLE);
        setStep(steps);
    }

    /**
     * 返回至获取验证码.
     *
     * @author paladin.
     * @date 2016/3/16 18:27
     */
    private void back2GetCode() {
        steps = 1;
        linear_second.setVisibility(View.GONE);
        linear_first.setVisibility(View.VISIBLE);
        setStep(steps);
    }

    /**
     * 提交验证码完成.
     *
     * @author paladin.
     * @date 2016/3/16 18:14
     */
    private void submitCodeComplete() {
        steps = 3;
        linear_second.setVisibility(View.GONE);
        linear_third.setVisibility(View.VISIBLE);
        setStep(steps);
    }

    /**
     * 返回至提交验证码.
     *
     * @author paladin.
     * @date 2016/3/16 18:27
     */
    private void back2SubmitCode() {
        steps = 2;
        linear_third.setVisibility(View.GONE);
        linear_second.setVisibility(View.VISIBLE);
        setStep(steps);
    }

    /**
     * 注册完成.
     *
     * @author paladin.
     * @date 2016/3/16 18:14
     */
    private void registerComplete() {
        SetPasswordActivity.this.finish();
    }

    /**
     * 计时器.
     */
    private Runnable timer = new Runnable() {

        @Override
        public void run() {
            wait_time--;
            if (wait_time < 0) {
                handler.removeCallbacks(timer);
                wait_time = WAIT_TIME;
                txt_time_tip.setVisibility(View.INVISIBLE);
            } else {
                txt_time_tip.setText(String.format(getString(R.string.time_tip), wait_time));
                handler.postDelayed(timer, 1000);
            }
        }

    };

    /**
     * 获取验证码异步.
     *
     * @author paladin.
     * @date 2016/3/17 18:12
     */
    private AsynProcessing getCodeAsyn = new AsynProcessing() {

        @Override
        protected void before() {
        }

        @Override
        protected Object AsynTask() {
            return biz.getCode(phone);
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            if (response.isSuccess()) {
                getCodeComplete();
            }
        }
    };

    /**
     * 提交验证码异步.
     *
     * @author paladin.
     * @date 2016/03/28 19:36
     */
    private AsynProcessing submitCodeAsyn = new AsynProcessing() {

        @Override
        protected void before() {

        }

        @Override
        protected Object AsynTask() {
            return biz.submitCode(phone, edit_code.getText().toString());
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, SetPasswordActivity.this, false);
            if (response.isSuccess()) {
                try {
                    key = new JSONObject(response.getData().toString()).getString("vkey");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                submitCodeComplete();
            }
        }

    };

    /**
     * 设置用户密码异步.
     *
     * @author paladin.
     * @date 2016/03/28 19:42
     */
    private AsynProcessing setPasswordAsyn = new AsynProcessing() {

        @Override
        protected void before() {

        }

        @Override
        protected Object AsynTask() {
            return biz.setPassword(phone, key, edit_password.getText().toString());
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, SetPasswordActivity.this, true);
            if (response.isSuccess()) {
                registerComplete();
            }
        }

    };

    /**
     * 注册步数
     */
    private void setStep(int step) {
        if (step == 1) {
            txt_register_first.setTextColor(getResources().getColor(R.color.font_color_green_default));
            txt_register_second.setTextColor(getResources().getColor(R.color.font_color_gray));
            txt_register_thirdly.setTextColor(getResources().getColor(R.color.font_color_gray));
        }
        if (step == 2) {
            txt_register_second.setTextColor(getResources().getColor(R.color.font_color_green_default));
            txt_register_first.setTextColor(getResources().getColor(R.color.font_color_gray));
            txt_register_thirdly.setTextColor(getResources().getColor(R.color.font_color_gray));
        }
        if (step == 3) {
            txt_register_thirdly.setTextColor(getResources().getColor(R.color.font_color_green_default));
            txt_register_first.setTextColor(getResources().getColor(R.color.font_color_gray));
            txt_register_second.setTextColor(getResources().getColor(R.color.font_color_gray));
        }
    }

    /**
     * 等待时间.
     */
    private final int WAIT_TIME = 120;
    /**
     * 键：显示内容.
     */
    public static final String KEY_SHOW_CONTENT = "shouContent";
    /**
     * 显示注册.
     */
    public static final int SHOW_REGISTER = 1;
    /**
     * 显示忘记密码.
     */
    public static final int SHOW_FORGET_PASSWORD = 2;
}