package com.tentinet.healthy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.adapter.UpdateMineAdapter;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.bean.UpdateMineDialogBean;
import com.tentinet.healthy.bean.UserBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.DatePickerFragment;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.PageLoadIng;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * 个人信息
 * TODO
 * Author YKK
 * Date 2016/5/11 13:51
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class UpdateMineInfoActivity extends BaseActivity implements View.OnClickListener, DatePickerFragment.LoginInputListener {


    private TextView txt_update_name, txt_update_name_val;
    private TextView txt_update_sex, txt_update_sex_val;
    private TextView txt_update_nation, txt_update_nation_val;
    private TextView txt_update_bir, txt_update_bir_val;
    private TextView txt_update_edu, txt_update_edu_val;
    private TextView txt_update_mar, txt_update_mar_val;
    private TextView txt_update_pro, txt_update_pro_val;
    private TextView txt_update_certtype, txt_update_certtype_val;
    private TextView txt_update_certtnum, txt_update_certtnum_val;
    private TextView txt_update_soc, txt_update_soc_val;
    private TextView txt_update_address, txt_update_address_val;
    private TextView txt_update_phone, txt_update_phone_val;
    private TitleView view_title;


    public static String EDIT_KEY = "EDIT_KEY";
    public static String EDIT_VALUE = "EDIT_VALUE";
    public static String EDIT_TYPE = "EDIT_TYPE";
    public static String EDIT_NUMBER = "EDIT_NUMBER";
    public static String EDIT_PHONE = "EDIT_PHONE";
    public static String EDIT_CERT = "EDIT_CERT";
    public static String EDIT_ID = "EDIT_ID";
    private final int REQUESTCODE_NAME = 1;
    private final int REQUESTCODE_NATION = 2;
    private final int REQUESTCODE_PRO = 3;
    private final int REQUESTCODE_CERTNUM = 4;
    private final int REQUESTCODE_SOC = 5;
    private final int REQUESTCODE_ADDRESS = 6;
    private final int REQUESTCODE_PHONE = 7;
    private String result = "";
    private UserBiz biz;
    String[] sexArr, curtArr, marArr, edtArr;

    @Override
    protected void getData() {

    }

    @Override
    protected int setContent() {
        return R.layout.activity_update_mine_info;
    }

    @Override
    protected void init() {
        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setBackImageButton();
        view_title.setTitle(R.string.update_mine_info);
        txt_update_name = (TextView) findViewById(R.id.txt_update_name);
        txt_update_name_val = (TextView) findViewById(R.id.txt_update_name_val);
        txt_update_sex = (TextView) findViewById(R.id.txt_update_sex);
        txt_update_sex_val = (TextView) findViewById(R.id.txt_update_sex_val);
        txt_update_nation = (TextView) findViewById(R.id.txt_update_nation);
        txt_update_nation_val = (TextView) findViewById(R.id.txt_update_nation_val);
        txt_update_bir = (TextView) findViewById(R.id.txt_update_bir);
        txt_update_bir_val = (TextView) findViewById(R.id.txt_update_bir_val);
        txt_update_edu = (TextView) findViewById(R.id.txt_update_edu);
        txt_update_edu_val = (TextView) findViewById(R.id.txt_update_edu_val);
        txt_update_mar = (TextView) findViewById(R.id.txt_update_mar);
        txt_update_mar_val = (TextView) findViewById(R.id.txt_update_mar_val);
        txt_update_pro = (TextView) findViewById(R.id.txt_update_pro);
        txt_update_pro_val = (TextView) findViewById(R.id.txt_update_pro_val);
        txt_update_certtype = (TextView) findViewById(R.id.txt_update_certtype);
        txt_update_certtype_val = (TextView) findViewById(R.id.txt_update_certtype_val);
        txt_update_certtnum = (TextView) findViewById(R.id.txt_update_certtnum);
        txt_update_certtnum_val = (TextView) findViewById(R.id.txt_update_certtnum_val);
        txt_update_soc = (TextView) findViewById(R.id.txt_update_soc);
        txt_update_soc_val = (TextView) findViewById(R.id.txt_update_soc_val);
        txt_update_address = (TextView) findViewById(R.id.txt_update_address);
        txt_update_address_val = (TextView) findViewById(R.id.txt_update_address_val);
        txt_update_phone = (TextView) findViewById(R.id.txt_update_phone);
        txt_update_phone_val = (TextView) findViewById(R.id.txt_update_phone_val);
        txt_update_name.setText(Html.fromHtml(String.format("<font color=\"%s\">%s</font>", "#FF1493", "*") + " " + getString(R.string.mine_name)));
        txt_update_sex.setText(Html.fromHtml(String.format("<font color=\"%s\">%s</font>", "#FF1493", "*") + " " + getString(R.string.mine_sex)));
        txt_update_bir.setText(Html.fromHtml(String.format("<font color=\"%s\">%s</font>", "#FF1493", "*") + " " + getString(R.string.mine_bir)));
        txt_update_certtype.setText(Html.fromHtml(String.format("<font color=\"%s\">%s</font>", "#FF1493", "*") + " " + getString(R.string.mine_certype)));
        txt_update_certtnum.setText(Html.fromHtml(String.format("<font color=\"%s\">%s</font>", "#FF1493", "*") + " " + getString(R.string.mine_cernum)));
        txt_update_soc.setText(getString(R.string.mine_soc));
        txt_update_phone.setText(Html.fromHtml(String.format("<font color=\"%s\">%s</font>", "#FF1493", "*") + " " + getString(R.string.mine_phone)));
        txt_update_nation.setText(getString(R.string.mine_nation));
        txt_update_edu.setText(getString(R.string.mine_education));
        txt_update_mar.setText(getString(R.string.mine_mar));
        txt_update_pro.setText(getString(R.string.mine_pro));
        txt_update_address.setText(getString(R.string.mine_address));
        biz = new UserBiz();
        view_title.setRightButton(R.string.advice_feedback_submit, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkData())
                    return;
                userBean.setName(txt_update_name_val.getText().toString());
                userBean.setSex((int) txt_update_sex_val.getTag());
                userBean.setNation(txt_update_nation_val.getText().toString());
                userBean.setBirthday(txt_update_bir_val.getText().toString());
                userBean.setEducation((int) txt_update_edu_val.getTag());
                userBean.setMarry((int) txt_update_mar_val.getTag());
                userBean.setProfession(txt_update_pro_val.getText().toString());
                userBean.setCertificateType((int) txt_update_certtype_val.getTag());
                userBean.setCertificateNumber(txt_update_certtnum_val.getText().toString());
                userBean.setSsNumber(txt_update_soc_val.getText().toString());
                userBean.setAddress(txt_update_address_val.getText().toString());
                userBean.setPhone(txt_update_phone_val.getText().toString());
                asynProcessing.starAsyn();
            }
        });
        sexArr = getResources().getStringArray(R.array.sex_array);
        curtArr = getResources().getStringArray(R.array.curt_array);
        marArr = getResources().getStringArray(R.array.mar_array);
        edtArr = getResources().getStringArray(R.array.edu_array);
        userBean = new UserBean();
        showUserInfo();

    }

    /**
     * 显示用户信息
     */
    private void showUserInfo() {
        userBean = TApplication.user;
        if (!StringUtil.isEmpty(userBean.getName())) {
            txt_update_name_val.setText(userBean.getName());
        }
        if (!StringUtil.isEmpty(userBean.getNation())) {
            txt_update_nation_val.setText(userBean.getNation());
        }
        if (!StringUtil.isEmpty(userBean.getBirthday())) {
            txt_update_bir_val.setText(userBean.getBirthday());
        }
        if (!StringUtil.isEmpty(userBean.getProfession())) {
            txt_update_pro_val.setText(userBean.getProfession());
        }
        if (!StringUtil.isEmpty(userBean.getCertificateNumber())) {
            txt_update_certtnum_val.setText(userBean.getCertificateNumber());
        }
        if (!StringUtil.isEmpty(userBean.getAddress())) {
            txt_update_address_val.setText(userBean.getAddress());
        }
        if (!StringUtil.isEmpty(userBean.getSsNumber())) {
            txt_update_soc_val.setText(userBean.getSsNumber());
        }
        txt_update_phone_val.setText(userBean.getPhone());
        txt_update_sex_val.setText(sexArr[userBean.getSex()]);
        txt_update_sex_val.setTag(userBean.getSex());
        txt_update_edu_val.setText(edtArr[userBean.getEducation() - 1]);
        txt_update_edu_val.setTag(userBean.getEducation());
        txt_update_mar_val.setText(marArr[userBean.getMarry() - 1]);
        txt_update_mar_val.setTag(userBean.getMarry());
        txt_update_certtype_val.setText(curtArr[userBean.getCertificateType() - 1]);
        txt_update_certtype_val.setTag(userBean.getCertificateType());
    }

    @Override
    protected void registerEvent() {
        txt_update_name_val.setOnClickListener(this);
        txt_update_sex_val.setOnClickListener(this);
        txt_update_nation_val.setOnClickListener(this);
        txt_update_edu_val.setOnClickListener(this);
        txt_update_mar_val.setOnClickListener(this);
        txt_update_pro_val.setOnClickListener(this);
        txt_update_certtype_val.setOnClickListener(this);
        txt_update_certtnum_val.setOnClickListener(this);
        txt_update_soc_val.setOnClickListener(this);
        txt_update_phone_val.setOnClickListener(this);
        txt_update_address_val.setOnClickListener(this);
        txt_update_bir_val.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    DatePickerFragment datePickerFragment = new DatePickerFragment();
                    if (TextUtils.isEmpty(txt_update_bir_val.getText())) {
                        final Calendar c = Calendar.getInstance();
                        int year = c.get(Calendar.YEAR);
                        int month = c.get(Calendar.MONTH);
                        int day = c.get(Calendar.DAY_OF_MONTH);
                        datePickerFragment.setYear(year);
                        datePickerFragment.setMonth(month);
                        datePickerFragment.setDay(day);
                    } else {
                        String[] temp = txt_update_bir_val.getText().toString().split("-");
                        int year = new Integer(temp[0]).intValue();
                        int month = new Integer(temp[1]).intValue();
                        int day = new Integer(temp[2]).intValue();
                        datePickerFragment.setYear(year);
                        datePickerFragment.setMonth(month - 1);
                        datePickerFragment.setDay(day);
                    }
                    datePickerFragment.show(getFragmentManager(), "Dialog");
                }
                return true;
            }

        });
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        int id = v.getId();
        switch (id) {
            case R.id.txt_update_name_val:
                bundle.putString(EDIT_KEY, getString(R.string.mine_name));
                bundle.putString(EDIT_VALUE, txt_update_name_val.getText().toString());
                IntentUtil.gotoActivityForResult(UpdateMineInfoActivity.this, EditMineInfoActivity.class, bundle, REQUESTCODE_NAME);
                break;
            case R.id.txt_update_nation_val:
                bundle.putString(EDIT_KEY, getString(R.string.mine_nation));
                bundle.putString(EDIT_VALUE, txt_update_nation_val.getText().toString());
                IntentUtil.gotoActivityForResult(UpdateMineInfoActivity.this, EditMineInfoActivity.class, bundle, REQUESTCODE_NATION);
                break;
            case R.id.txt_update_pro_val:
                bundle.putString(EDIT_KEY, getString(R.string.mine_pro));
                bundle.putString(EDIT_VALUE, txt_update_pro_val.getText().toString());
                IntentUtil.gotoActivityForResult(UpdateMineInfoActivity.this, EditMineInfoActivity.class, bundle, REQUESTCODE_PRO);
                break;
            case R.id.txt_update_certtnum_val:
                bundle.putString(EDIT_KEY, getString(R.string.mine_cernum));
                bundle.putString(EDIT_VALUE, txt_update_certtnum_val.getText().toString());
                bundle.putString(EDIT_TYPE, EDIT_CERT);
                bundle.putString(EDIT_ID, txt_update_certtype_val.getTag() + "");
                IntentUtil.gotoActivityForResult(UpdateMineInfoActivity.this, EditMineInfoActivity.class, bundle, REQUESTCODE_CERTNUM);
                break;
            case R.id.txt_update_soc_val:
                bundle.putString(EDIT_KEY, getString(R.string.mine_soc));
                bundle.putString(EDIT_VALUE, txt_update_soc_val.getText().toString());
                bundle.putString(EDIT_TYPE, EDIT_NUMBER);
                IntentUtil.gotoActivityForResult(UpdateMineInfoActivity.this, EditMineInfoActivity.class, bundle, REQUESTCODE_SOC);
                break;
            case R.id.txt_update_address_val:
                bundle.putString(EDIT_KEY, getString(R.string.mine_address));
                bundle.putString(EDIT_VALUE, txt_update_address_val.getText().toString());
                IntentUtil.gotoActivityForResult(UpdateMineInfoActivity.this, EditMineInfoActivity.class, bundle, REQUESTCODE_ADDRESS);
                break;
            case R.id.txt_update_phone_val:
                bundle.putString(EDIT_KEY, getString(R.string.mine_phone));
                bundle.putString(EDIT_VALUE, txt_update_phone_val.getText().toString());
                bundle.putString(EDIT_TYPE, EDIT_PHONE);
                IntentUtil.gotoActivityForResult(UpdateMineInfoActivity.this, EditMineInfoActivity.class, bundle, REQUESTCODE_PHONE);
                break;
            case R.id.txt_update_sex_val:
                //获取array的数组
                ArrayList<UpdateMineDialogBean> sexs = new ArrayList<UpdateMineDialogBean>();
                for (int i = 0; i < sexArr.length; i++) {
                    //界面被选中的加入到集合中
                    if (sexArr[i].equals(txt_update_sex_val.getText().toString())) {
                        sexs.add(new UpdateMineDialogBean(sexArr[i], true, i));
                    } else {
                        sexs.add(new UpdateMineDialogBean(sexArr[i], false, i));
                    }
                }
                showDialog(txt_update_sex_val, getString(R.string.mine_edit_change) + getString(R.string.mine_sex), sexs);
                break;
            case R.id.txt_update_edu_val:
                //获取array的数组
                ArrayList<UpdateMineDialogBean> edus = new ArrayList<UpdateMineDialogBean>();
                for (int i = 0; i < edtArr.length; i++) {
                    //界面被选中的加入到集合中
                    if (edtArr[i].equals(txt_update_edu_val.getText().toString())) {
                        edus.add(new UpdateMineDialogBean(edtArr[i], true, i + 1));
                    } else {
                        edus.add(new UpdateMineDialogBean(edtArr[i], false, i + 1));
                    }
                }
                showDialog(txt_update_edu_val, getString(R.string.mine_edit_change) + getString(R.string.mine_education), edus);
                break;
            case R.id.txt_update_mar_val:
                //获取array的数组
                ArrayList<UpdateMineDialogBean> mars = new ArrayList<UpdateMineDialogBean>();
                for (int i = 0; i < marArr.length; i++) {
                    //界面被选中的加入到集合中
                    if (marArr[i].equals(txt_update_mar_val.getText().toString())) {
                        mars.add(new UpdateMineDialogBean(marArr[i], true, i + 1));
                    } else {
                        mars.add(new UpdateMineDialogBean(marArr[i], false, i + 1));
                    }
                }
                showDialog(txt_update_mar_val, getString(R.string.mine_edit_change) + getString(R.string.mine_mar), mars);
                break;
            case R.id.txt_update_certtype_val:
                //获取array的数组
                ArrayList<UpdateMineDialogBean> curts = new ArrayList<UpdateMineDialogBean>();
                for (int i = 0; i < curtArr.length; i++) {
                    //界面被选中的加入到集合中
                    if (curtArr[i].equals(txt_update_certtype_val.getText().toString())) {
                        curts.add(new UpdateMineDialogBean(curtArr[i], true, i + 1));
                    } else {
                        curts.add(new UpdateMineDialogBean(curtArr[i], false, i + 1));
                    }
                }
                showDialog(txt_update_certtype_val, getString(R.string.mine_edit_change) + getString(R.string.mine_certype), curts);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
            return;
        result = data.getStringExtra(EDIT_VALUE);
        if (StringUtil.isEmpty(result))
            return;
        switch (requestCode) {
            case REQUESTCODE_NAME:
                txt_update_name_val.setText(result);
                break;
            case REQUESTCODE_NATION:
                txt_update_nation_val.setText(result);
                break;
            case REQUESTCODE_PRO:
                txt_update_pro_val.setText(result);
                break;
            case REQUESTCODE_CERTNUM:
                txt_update_certtnum_val.setText(result);
                break;
            case REQUESTCODE_SOC:
                txt_update_soc_val.setText(result);
                break;
            case REQUESTCODE_ADDRESS:
                txt_update_address_val.setText(result);
                break;
            case REQUESTCODE_PHONE:
                txt_update_phone_val.setText(result);
                break;
        }
    }

    @Override
    public void onLoginInputComplete(int year, int month, int day) {
        txt_update_bir_val.setText(String.valueOf(year) + "-" + (month + 1) + "-" + day);
    }


    //选择框适配器
    UpdateMineAdapter adapter;
    //选中框数据源
    ArrayList<UpdateMineDialogBean> datas;
    private TextView txt_dialog_title;
    private Button btn_ok;
    //listview被选的数据
    UpdateMineDialogBean bean;
    private ListView lv_update_mine;
    private PageLoadIng page_dialog_update_mine;
    private UserBean userBean;


    /**
     * 选中框（性别，教育，婚姻，证件类型）
     *
     * @param textView 被点击的对象
     * @param title    标题
     * @param data     数据源
     */
    private void showDialog(final TextView textView, String title, ArrayList<UpdateMineDialogBean> data) {
        bean = null;
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_update_mine, null);
        txt_dialog_title = (TextView) view.findViewById(R.id.txt_dialog_title);
        txt_dialog_title.setText(title);
        page_dialog_update_mine = (PageLoadIng) findViewById(R.id.page_dialog_update_mine);
        lv_update_mine = (ListView) view.findViewById(R.id.lv_update_mine);
        btn_ok = (Button) view.findViewById(R.id.btn_ok);
        datas = data;
        adapter = new UpdateMineAdapter(this, datas);
        lv_update_mine.setAdapter(adapter);
        lv_update_mine.setSelection(((int) textView.getTag()) - 1);
        CustomDialog.showDefaultDialog(this, view, true);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean != null) {
                    textView.setText(bean.getName());
                    textView.setTag(bean.getIndex());
                }
                CustomDialog.dismissDialog();
            }
        });
        lv_update_mine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bean = adapter.getBean(position);
                if (bean != null) {
                    //设置被选
                    bean.setUrl(true);
                    //移除被选
                    datas.remove(position);
                    for (UpdateMineDialogBean updateMineDialogBean : datas) {
                        updateMineDialogBean.setUrl(false);
                    }
                    //讲被选加入数据源
                    datas.add(position, bean);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private AsynProcessing asynProcessing = new AsynProcessing() {
        @Override
        protected void before() {
            CustomDialog.showWaitDialog(UpdateMineInfoActivity.this);
        }

        @Override
        protected Object AsynTask() {
            return biz.submitUserInfo(TApplication.user.getPhone(), userBean);
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, UpdateMineInfoActivity.this, true);
            if (response.isSuccess()) {
                TApplication.user = userBean;
            }
            CustomDialog.dismissDialog();
        }
    };

    private boolean checkData() {
        boolean isCheck = true;
        if (StringUtil.isEmpty(txt_update_name_val.getText().toString())) {
            ToastUtil.showShortToast(UpdateMineInfoActivity.this, getString(R.string.update_mine_msg) + getString(R.string.mine_name));
            isCheck = false;
            return isCheck;
        }
        if (StringUtil.isEmpty(txt_update_sex_val.getText().toString())) {
            ToastUtil.showShortToast(UpdateMineInfoActivity.this, getString(R.string.update_mine_msg) + getString(R.string.mine_sex));
            isCheck = false;
            return isCheck;
        }
        if (StringUtil.isEmpty(txt_update_bir_val.getText().toString())) {
            ToastUtil.showShortToast(UpdateMineInfoActivity.this, getString(R.string.update_mine_msg) + getString(R.string.mine_bir));
            isCheck = false;
            return isCheck;
        }
        if (StringUtil.isEmpty(txt_update_certtype_val.getText().toString())) {
            ToastUtil.showShortToast(UpdateMineInfoActivity.this, getString(R.string.update_mine_msg) + getString(R.string.mine_certype));
            isCheck = false;
            return isCheck;
        }
        if (StringUtil.isEmpty(txt_update_certtnum_val.getText().toString())) {
            ToastUtil.showShortToast(UpdateMineInfoActivity.this, getString(R.string.update_mine_msg) + getString(R.string.mine_cernum));
            isCheck = false;
            return isCheck;
        }
        if (StringUtil.isEmpty(txt_update_phone_val.getText().toString())) {
            ToastUtil.showShortToast(UpdateMineInfoActivity.this, getString(R.string.update_mine_msg) + getString(R.string.mine_phone));
            isCheck = false;
            return isCheck;
        }
        return isCheck;
    }
}
