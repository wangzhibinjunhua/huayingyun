package com.tentinet.healthy.activity;

import android.widget.ListView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.adapter.DataAdapter;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.view.TitleView;

import java.util.ArrayList;

/**
 * 测量数据界面.
 *
 * @author paladin
 */
public class DataActivity extends BaseActivity {

    /**
     * 标题.
     */
    private TitleView view_title;
    /**
     * 数据列表.
     */
    private ListView list_data;
    private DataAdapter adapter;
    private ArrayList<DataBean> list;
    /**
     * 我的业务逻辑.
     */
    private UserBiz user;
    /**
     * 设备ID.
     */
    private String id;
    /**
     * 设备类型.
     */
    private int type;

    @Override
    protected void getData() {
        type = this.getIntent().getIntExtra(KEY_DEVICE_TYPE, 0);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_data;
    }

    @Override
    protected void init() {
        list = new ArrayList<DataBean>();
        user = new UserBiz();

        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setTitle(R.string.data);
        view_title.setBackButton();
        list_data = (ListView) findViewById(R.id.list_data);
        adapter = new DataAdapter(DataActivity.this, list);
        list_data.setAdapter(adapter);

        getDataAsyn.starAsyn();
    }

    @Override
    protected void registerEvent() {

    }

    /**
     * 获取数据异步处理对象.
     */
    private AsynProcessing getDataAsyn = new AsynProcessing() {

        @Override
        protected void before() {

        }

        @Override
        protected Object AsynTask() {
            return user.getUserDeviceDataList(TApplication.user.getPhone(), String.valueOf(type));
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, DataActivity.this, false);
            if (response.isSuccess()) {
                list.addAll((ArrayList<DataBean>) response.getData());
                adapter.notifyDataSetChanged();
            }
        }

    };

    /**
     * 键：设备ID.
     */
    public static final String KEY_DEVICE_ID = "deviceID";
    /**
     * 键：设备类型.
     */
    public static final String KEY_DEVICE_TYPE = "deviceType";

}
