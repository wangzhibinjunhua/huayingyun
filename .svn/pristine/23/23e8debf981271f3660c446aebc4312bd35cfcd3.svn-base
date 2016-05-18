package com.tentinet.healthy.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.bean.ParentBean;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.HomeTab;
import com.tentinet.healthy.view.TitleView;

/**
 * <h3>Description</h3>
 * 展示设备或亲友详情的页面
 * <h3>Author</h3> Rick Chan
 * <h3>Date</h3> 2016/3/29 17:52
 * <h3>Copyright</h3>Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class DetailActivity extends BaseActivity {

    /**
     * 设备信息
     */
    private DeviceBean deviceBean;
    /**
     * 亲友详情
     */
    private ParentBean parentBean;
    /**
     * 页面展示状态
     */
    private int state;
    /**
     * 标题
     */
    private TitleView view_title;
    /**
     * 亲友布局
     */
    private RelativeLayout parent_layout;
    /**
     * 设备详情布局
     */
    private LinearLayout device_layout;
    /**
     * 设备图标
     */
    private ImageView img_icon_device;
    /**
     * 设备介绍
     */
    private TextView txt_intro_device;
    /**
     * 绑定按钮
     */
    private Button btn_bindNow_device;
    /**
     * parent页面绑定设备
     */
    private RelativeLayout rl_bindDevice;

    @Override
    protected void getData() {
        Bundle extras = getIntent().getExtras();
        int from = extras.getInt(getString(R.string.from));
        if (from == HomeTab.FROM_DEVICE) {
            deviceBean = (DeviceBean) extras.getSerializable(getString(R.string.key_device));
            state = HomeTab.FROM_DEVICE;
        } else if (from == HomeTab.FROM_PARENT) {
            parentBean = (ParentBean) extras.getSerializable(getString(R.string.key_parent));
            state = HomeTab.FROM_PARENT;
        }
    }

    @Override
    protected int setContent() {
        return R.layout.activity_detail;
    }

    @Override
    protected void init() {
        findViews();
        initTitle();

    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        if (state == HomeTab.FROM_PARENT) {
            view_title.setTitle(R.string.parent);
            parent_layout.setVisibility(View.VISIBLE);
            device_layout.setVisibility(View.GONE);

        } else if (state == HomeTab.FROM_DEVICE) {
            view_title.setTitle("设备名称");
            parent_layout.setVisibility(View.GONE);
            device_layout.setVisibility(View.VISIBLE);
        }

        view_title.setLeftImageButton(R.mipmap.ic_launcher, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailActivity.this.finish();
            }
        });
    }

    /**
     * 查找views
     */
    private void findViews() {
        view_title = (TitleView) findViewById(R.id.view_title);
        parent_layout = (RelativeLayout) findViewById(R.id.detail_rl_parentinfo);
        device_layout = (LinearLayout) findViewById(R.id.detail_ll_deviceinfo);
        img_icon_device = (ImageView) findViewById(R.id.detail_img_device);
        txt_intro_device = (TextView) findViewById(R.id.detail_txt_device_intro);
        btn_bindNow_device = (Button) findViewById(R.id.detail_btn_bind_now);

        rl_bindDevice = (RelativeLayout) findViewById(R.id.rl_bind_device_parent);
    }

    @Override
    protected void registerEvent() {
        btn_bindNow_device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //绑定业务
                ToastUtil.showShortToast(DetailActivity.this, getString(R.string.bind_success) + "3秒后跳转", Gravity.CENTER, 0, 0);
                //绑定完成修改首页的已绑定集合内容或者在首页请求设备列表
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        IntentUtil.gotoActivityAndFinish(DetailActivity.this, MainActivity.class);
                    }
                }).start();
            }
        });

        rl_bindDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showShortToast(DetailActivity.this, getString(R.string.bind_device), Gravity.CENTER, 0, 0);
            }
        });
    }
}
