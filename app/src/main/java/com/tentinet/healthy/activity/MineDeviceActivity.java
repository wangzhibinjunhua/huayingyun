package com.tentinet.healthy.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.adapter.MineDeviceAdapter;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.PageLoadIng;

import java.util.ArrayList;

/**
 * 我的设备界面.
 *
 * @author paladin
 */
public class MineDeviceActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    /**
     * 标题.
     */
    private TitleView view_title;
    /**
     * 我的设备列表.
     */
    private ListView list_mineDevice;
    private MineDeviceAdapter adapter;
    private ArrayList<DeviceBean> list;

    private int deviceType;
    /**
     * 用户业务逻辑.
     */
    private UserBiz user;

    private DeviceBean device;

    private PageLoadIng pageLoadIng;

    @Override
    protected void getData() {

    }

    @Override
    protected int setContent() {
        return R.layout.activity_mine_device;
    }

    @Override
    protected void init() {
        user = new UserBiz();
        list = new ArrayList<DeviceBean>();
        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setTitle(R.string.mine_device);
        view_title.setBackImageButton(R.mipmap.icon_back_white);
        list_mineDevice = (ListView) findViewById(R.id.list_mine_device);
        pageLoadIng = (PageLoadIng) findViewById(R.id.mine_device_load);
        adapter = new MineDeviceAdapter(MineDeviceActivity.this, list);
        list_mineDevice.setAdapter(adapter);
        getMineDeviceAsyn.starAsyn();
        view_title.setRightOneImageButton(R.mipmap.btn_add, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TApplication.user.isLogin()) {
                    IntentUtil.gotoActivity(MineDeviceActivity.this, SearchDeviceActivity.class);
                } else {
                    IntentUtil.gotoActivityForResult(MineDeviceActivity.this, LoginActivity.class, MainActivity.REQUEST_LOGIN);
                }

            }
        });

    }

    @Override
    protected void registerEvent() {
        list_mineDevice.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        device = adapter.getDevice(position);
        CustomDialog.showOkAndCalcelDialog(MineDeviceActivity.this, getString(R.string.unbound_device), getString(R.string.yes_no_unbound) + "？", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindDeviceAsyn.starAsyn();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.dismissDialog();
            }
        });
    }

    /**
     * 请求我的设备异步.
     */
    private AsynProcessing getMineDeviceAsyn = new AsynProcessing() {

        @Override
        protected void before() {

        }

        @Override
        protected Object AsynTask() {
            return user.getUserDeviceList(TApplication.user.getPhone());
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, MineDeviceActivity.this, false);
            if (response.isSuccess()) {
                list.addAll((ArrayList<DeviceBean>) response.getData());
            }
            if (list.size() < 1) {
                pageLoadIng.noData();
            } else {
                pageLoadIng.messageAndProgressGone();
                adapter.notifyDataSetChanged();
            }
        }

    };


    private AsynProcessing unbindDeviceAsyn = new AsynProcessing() {
        @Override
        protected void before() {

        }

        @Override
        protected Object AsynTask() {
            return user.unBindDevice(TApplication.user.getPhone(), device.getType() + "", device.getAddress());
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, MineDeviceActivity.this, true);
            if (response.isSuccess()) {
                list.remove(device);
                adapter.notifyDataSetChanged();
            }
            CustomDialog.dismissDialog();
        }
    };


}
