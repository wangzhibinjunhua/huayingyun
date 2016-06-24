package com.tentinet.healthy.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.activity.LoginActivity;
import com.tentinet.healthy.activity.MainActivity;
import com.tentinet.healthy.activity.MeasureInfoActivity;
import com.tentinet.healthy.adapter.DataAdapter;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.DateUtil;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.widget.PageLoadIng;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 数据视图.
 *
 * @author paladin.
 */
public class DataView extends LinearLayout implements View.OnClickListener {
    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 左右时间按钮
     */
    private ImageView img_left_data, img_right_data;
    /**
     * 右边按钮
     */
    private TextView txt_title_data;

    private ListView dataListView;
    /**
     * 数据适配器
     */
    private DataAdapter dataAdapter;

    private ArrayList<DataBean> datas;
    Calendar calendar = new GregorianCalendar();
    Date date = new Date();

    private UserBiz userBiz;
    String date1Str, date2Str;
    private static int TAGGET_TYPE = DataBean.TYPE_BLOOD_PRESSURE_MONITOR;

    private int measure_type;
    ArrayList<DataBean> tmpDatas = new ArrayList<>();
    private PageLoadIng pageLoadIng;

    public DataView(Context context, int measureType) {
        super(context);
        init(context, measureType);
    }

    public DataView(Context context, AttributeSet attrs, int measureType) {
        super(context, attrs);
        init(context, measureType);
    }

    private void init(final Context context, int measureType) {
        this.context = context;
        this.measure_type = measureType;
        TAGGET_TYPE = measure_type;
        this.addView(LayoutInflater.from(context).inflate(R.layout.item_data, null), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        img_left_data = (ImageView) findViewById(R.id.img_left_data);
        img_right_data = (ImageView) findViewById(R.id.img_right_data);
        txt_title_data = (TextView) findViewById(R.id.txt_title_data);
        dataListView = (ListView) findViewById(R.id.data_list);
        pageLoadIng = (PageLoadIng) findViewById(R.id.data_page_load);
        datas = new ArrayList<DataBean>();
        dataAdapter = new DataAdapter(context, tmpDatas);
        calendar.setTime(date);
        date1Str = new SimpleDateFormat("yyyy-MM-dd").format(date);
        date2Str = DateUtil.getDate("yyyy-MM-dd");
        img_left_data.setOnClickListener(this);
        img_right_data.setOnClickListener(this);
        userBiz = new UserBiz();
        AddTxtDate(0);
        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.logDebugMessage("DATEVIEW" + position);
                if (measure_type == DataBean.TYPE_BLE_ECG) {
                    DataBean bean=datas.get(position);
                    Bundle bundle=new Bundle();
                    bundle.putString("blooding",bean.getBlooding());
                    bundle.putString("result",bean.getEcgResult());
                    IntentUtil.gotoActivity(context, MeasureInfoActivity.class,bundle);
                }
            }
        });
    }

    /**
     * 加或者减时间
     *
     * @param i 加或者减的值  负数为减
     */
    private void AddTxtDate(int i) {
        if (i == 0) {
            txt_title_data.setText(R.string.nowadays);
            img_right_data.setEnabled(false);
            reLoadData(TAGGET_TYPE);
            return;
        }
        calendar.add(calendar.DATE, i);
        date = calendar.getTime();
        calendar.setTime(date);

        date1Str = new SimpleDateFormat("yyyy-MM-dd").format(date);
        date2Str = DateUtil.getDate("yyyy-MM-dd");
        if (DateUtil.timeDateFormat(date1Str, date2Str) >= 0) {
            txt_title_data.setText(context.getString(R.string.nowadays));
            img_right_data.setEnabled(false);
            img_right_data.setImageResource(R.mipmap.data_right);
        } else {
            txt_title_data.setText(DateUtil.getCurrentDate(context,date, "yyyy-MM-dd"));
            img_right_data.setEnabled(true);
            img_right_data.setImageResource(R.mipmap.data_yes_right);
        }
        getDatasByDate();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.img_left_data:
                AddTxtDate(-1);
                break;
            case R.id.img_right_data:
                AddTxtDate(1);
                break;
        }
    }

    private AsynProcessing getDataAsynProcessing = new AsynProcessing() {
        @Override
        protected void before() {

        }

        @Override
        protected Object AsynTask() {
            return userBiz.getUserDeviceDataList(TApplication.user.getPhone(), TAGGET_TYPE + "");
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, context, false);
            if (response.isSuccess()) {
                tmpDatas.clear();
                datas.clear();
                datas.addAll((ArrayList<DataBean>) response.getData());
                getDatasByDate();
            } else {
                datas.clear();
                getDatasByDate();
            }
            LogUtil.logMessage("ykk", response.getInfo());
        }
    };

    /**
     * 重新加载数据
     */
    public void reLoadData(int type) {
        TAGGET_TYPE = type;
        if (TApplication.user.isLogin()) {
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    getDataAsynProcessing.starAsyn();
                }
            }.sendEmptyMessageDelayed(0, 0);
        } else {
            IntentUtil.gotoActivityForResult(context, LoginActivity.class, MainActivity.REQUEST_LOGIN);
            ToastUtil.showLongToast(context, context.getString(R.string.no_login_msg));
        }

    }

    /**
     * 时间获取测量的数据
     */
    private void getDatasByDate() {
        tmpDatas.clear();
        if (datas.size() > 0) {
            for (DataBean bean : datas) {
                if (!StringUtil.isEmpty(bean.getDate())) {
                    if (DateUtil.getTimeMillis(date1Str, "yyyy-MM-dd") == DateUtil.getTimeMillis(bean.getDate(), "yyyy-MM-dd")) {
                        tmpDatas.add(bean);
                    }
                }
            }
        }
        if (tmpDatas.size() <= 0) {
            pageLoadIng.noData();
        } else {
            pageLoadIng.messageAndProgressGone();
        }
        dataListView.setAdapter(dataAdapter);

    }


}
