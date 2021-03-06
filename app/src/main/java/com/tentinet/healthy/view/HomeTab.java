package com.tentinet.healthy.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.tentinet.healthy.R;
import com.tentinet.healthy.activity.CallCneterActivity;
import com.tentinet.healthy.activity.DetailActivity;
import com.tentinet.healthy.activity.LoginActivity;
import com.tentinet.healthy.activity.MainActivity;
import com.tentinet.healthy.activity.MeasureInfoActivity;
import com.tentinet.healthy.activity.ParentActivity;
import com.tentinet.healthy.activity.SearchDeviceActivity;
import com.tentinet.healthy.adapter.HomeDeviceAdapter;
import com.tentinet.healthy.adapter.HomeGridAdapter;
import com.tentinet.healthy.adapter.HomeParentAdapter;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.bean.HomeMenuBean;
import com.tentinet.healthy.bean.ParentBean;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.util.ToastUtil;

import java.util.ArrayList;

/**
 * 首页标签页.
 *
 * @author paladin.
 */
public class HomeTab extends RelativeLayout {
    /**
     * 主页
     */
    private static final int MAIN_PAGE = 0;
    /**
     * 主页设备列表
     */
    private static final int DEVICE_LIST = 1;
    /**
     * 主页亲友列表
     */
    private static final int PARENT_LIST = 2;
    /**
     * 搜索设备列表
     */
    private static final int SEARCH_DEVICE = 3;
    /**
     * 从parent列表跳转
     */
    public static final int FROM_PARENT = 1;
    /**
     * 从device列表跳转
     */
    public static final int FROM_DEVICE = 2;

    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 标题.
     */
    private TitleView view_title;
    /**
     * 绑定设备按钮
     */
    private Button btn_findDevice;
    /**
     * 无设备布局
     */
    private RelativeLayout rl_no_device;
    /**
     * 设备列表
     */
    private ListView lv_deviceList;

    /**
     * 已绑定设备集合
     */
    private ArrayList<HomeMenuBean> bindedDevices;

    private HomeGridAdapter gridAdapter;


    private GridView gridView;

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private ItemClickListener itemClickListener;

    public HomeTab(Context context) {
        super(context);
        init(context);
    }

    public HomeTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HomeTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化数据.
     *
     * @param context 上下文环境.
     */
    private void init(final Context context) {
        this.context = context;
        this.addView(LayoutInflater.from(context).inflate(R.layout.tab_home, null), new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view_title = (TitleView) findViewById(R.id.hometab_title_main);
        //初始化设备数据集合
        bindedDevices = new ArrayList<>();
        gridView = (GridView) findViewById(R.id.tab_home_giv);
        gridAdapter = new HomeGridAdapter(context, bindedDevices);
        gridView.setAdapter(gridAdapter);

        initTitle();

        //首页无设备
        rl_no_device = (RelativeLayout) findViewById(R.id.hometab_rl_no_device);
        //首页跳转绑定设备
        btn_findDevice = (Button) findViewById(R.id.hometab_btn_bind_device);
        //首页设备列表
        lv_deviceList = (ListView) findViewById(R.id.hometab_lv_device_list);

        addBindDevice();

        registerListener();
    }

    private void addBindDevice() {
        HomeMenuBean bean = new HomeMenuBean();
        bean.setUrl(R.mipmap.home_health);
        bean.setName(context.getString(R.string.health_management));

        bindedDevices.add(bean);
        bean = new HomeMenuBean();
        bean.setName(context.getString(R.string.health_food));
        bean.setUrl(R.mipmap.home_food);
        bindedDevices.add(bean);

        bean = new HomeMenuBean();
        bean.setName(context.getString(R.string.old_clothes));
        bean.setUrl(R.mipmap.home_old_clothing);
        bindedDevices.add(bean);

        bean = new HomeMenuBean();
        bean.setName(context.getString(R.string.home_service));
        bean.setUrl(R.mipmap.home_home);
        bindedDevices.add(bean);

        bean = new HomeMenuBean();
        bean.setName(context.getString(R.string.trip_services));
        bean.setUrl(R.mipmap.home_run_safe);
        bindedDevices.add(bean);

        bean = new HomeMenuBean();
        bean.setName(context.getString(R.string.travel_services));
        bean.setUrl(R.mipmap.home_travel);
        bindedDevices.add(bean);

        bean = new HomeMenuBean();
        bean.setName(context.getString(R.string.medical_service));
        bean.setUrl(R.mipmap.home_medical);
        bindedDevices.add(bean);

        bean = new HomeMenuBean();
        bean.setName(context.getString(R.string.call_center));
        bean.setUrl(R.mipmap.home_call_center);
        bindedDevices.add(bean);
        gridAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        view_title.setTitle(R.string.home);
        view_title.setRightTwoImageButton(R.mipmap.icon_comment_like_normal, new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TApplication.user.isLogin()) {
                    IntentUtil.gotoActivity(context, ParentActivity.class);
                } else {
                    IntentUtil.gotoActivityForResult(context, LoginActivity.class, MainActivity.REQUEST_LOGIN);
                }

            }
        });
        view_title.setRightOneImageButton(R.mipmap.btn_add, new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (TApplication.user.isLogin()) {
                    IntentUtil.gotoActivity(context, SearchDeviceActivity.class);
                } else {
                    IntentUtil.gotoActivityForResult(context, LoginActivity.class, MainActivity.REQUEST_LOGIN);
                }

            }
        });
    }

    /**
     * 设置监听
     * <h3>time</h3> 3/29
     */
    private void registerListener() {
        btn_findDevice.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示搜索页面
                IntentUtil.gotoActivity(context, SearchDeviceActivity.class);
            }
        });

        lv_deviceList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtil.showShortToast(context, "position = " + position + "跳转设备测量结果");
                Bundle bundle = new Bundle();
                bundle.putInt(context.getString(R.string.from), FROM_DEVICE);
                bundle.putSerializable(getResources().getString(R.string.key_device), bindedDevices.get(position));
                IntentUtil.gotoActivity(context, DetailActivity.class, bundle);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                Uri content_url = null;
                if (position == 0) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            itemClickListener.onClick();
                        }
                    }, 300);

                } else if (position == 1) {
//                    content_url = Uri.parse("http://www.zgbjspw.roboo.com/");
//                    intent.setData(content_url);
//                    context.startActivity(intent);
                    bundle.putString(KEY_NAME, KEY_FOOD);
                    IntentUtil.gotoActivity(context, CallCneterActivity.class, bundle);
                } else if (position == 2) {
//                    content_url = Uri.parse("http://channel.jd.com/men.html");
//                    intent.setData(content_url);
//                    context.startActivity(intent);
                    bundle.putString(KEY_NAME, KEY_OLD);
                    IntentUtil.gotoActivity(context, CallCneterActivity.class, bundle);
                } else if (position == 3) {
//                    content_url = Uri.parse("http://www.51baomu.cn/");
//                    intent.setData(content_url);
//                    context.startActivity(intent);
                    bundle.putString(KEY_NAME, KEY_HOME);
                    IntentUtil.gotoActivity(context, CallCneterActivity.class, bundle);
                } else if (position == 4) {
//                    content_url = Uri.parse("http://www.12306.cn/mormhweb/");
//                    intent.setData(content_url);
//                    context.startActivity(intent);
                    bundle.putString(KEY_NAME, KEY_TRIP);
                    IntentUtil.gotoActivity(context, CallCneterActivity.class, bundle);
                } else if (position == 5) {
//                    content_url = Uri.parse("http://www.szcits.cn/");
//                    intent.setData(content_url);
//                    context.startActivity(intent);
                    bundle.putString(KEY_NAME, KEY_TRAVEL);
                    IntentUtil.gotoActivity(context, CallCneterActivity.class, bundle);
                } else if (position == 6) {
//                    content_url = Uri.parse("http://www.91160.com/");
//                    intent.setData(content_url);
//                    context.startActivity(intent);
                    bundle.putString(KEY_NAME, KEY_MEDICAL);
                    IntentUtil.gotoActivity(context, CallCneterActivity.class, bundle);
                } else if (position == 7) {
                    bundle.putString(KEY_NAME, KEY_CALL);
                    IntentUtil.gotoActivity(context, CallCneterActivity.class, bundle);
                }
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.FLAG_FALLBACK:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }


    public interface ItemClickListener {
        public void onClick();
    }

    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_OLD = "KEY_OLD";
    public static final String KEY_FOOD = "KEY_FOOD";
    public static final String KEY_HOME = "KEY_HOME";
    public static final String KEY_TRIP = "KEY_TRIP";
    public static final String KEY_TRAVEL = "KEY_TRAVEL";
    public static final String KEY_MEDICAL = "KEY_MEDICAL";
    public static final String KEY_CALL = "KEY_CALL";
}
