package com.tentinet.healthy.activity;


import android.graphics.Color;
import android.graphics.pdf.PdfDocument;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.adapter.HealthAssessAdapter;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.HealthAssessBean;
import com.tentinet.healthy.bean.HealthAssessCharBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.PageLoadIng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class HealthAssessActivity extends BaseActivity {
    /**
     * 设备类别
     */
    private int measure_type = DataBean.TYPE_BLOOD_PRESSURE_MONITOR;
    /**
     * extra key
     */
    private static final String KEY_MEASURE_TYPE = "KEY_MEASURE_TYPE";

    private TitleView title_View;

    private GridView gridView;

    private HealthAssessAdapter assessAdapter;

    private ArrayList<HealthGrid> datas;

    private LineChartView mLineChartView;
    private List<PointValue> mPointValues;
    private List<PointValue> mPointValues2;
    private List<AxisValue> mAxisValues;

    private TextView txt_health_explain, txt_health_result;

    private PageLoadIng pageLoadIng;

    private String device_id;

    /**
     * 设备id
     */
    private static final String KEY_DEVICE_ID = "KEY_DEVICE_ID";

    @Override
    protected void getData() {
        measure_type = getIntent().getExtras().getInt(KEY_MEASURE_TYPE, measure_type);
        device_id = getIntent().getExtras().getString(KEY_DEVICE_ID);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_health_assess;
    }

    @Override
    protected void init() {
        this.title_View = (TitleView) findViewById(R.id.health_assess_title);
        title_View.setBackImageButton(R.mipmap.icon_back_white);
        title_View.setTitle(R.string.health_assess);
        gridView = (GridView) findViewById(R.id.gid_health_data);
        pageLoadIng = (PageLoadIng) findViewById(R.id.data_page_load);
        mLineChartView = (LineChartView) findViewById(R.id.char_health_assess);
        txt_health_explain = (TextView) findViewById(R.id.txt_health_explain);
        txt_health_result = (TextView) findViewById(R.id.txt_health_result);
        datas = new ArrayList<>();
        assessAdapter = new HealthAssessAdapter(this, datas);
        gridView.setAdapter(assessAdapter);
        mPointValues = new ArrayList<>();
        mAxisValues = new ArrayList<>();
        mPointValues2 = new ArrayList<>();
        new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // notifyData();
                submitProcessing.starAsyn();
            }
        }.sendEmptyMessageDelayed(0, 0);

        bean = new DataBean();
        bean.setBloodSugar(String.valueOf(5.3));
        bean.setPeriod("1");
        bean.setType(DataBean.TYPE_GLUCOSE_METER);
        bean.setDate("2016-05-11 10:01:30");
        //  submitProcessing2.starAsyn();
    }

    DataBean bean;
    private AsynProcessing submitProcessing2 = new AsynProcessing() {
        @Override
        protected void before() {
            CustomDialog.showWaitDialog(HealthAssessActivity.this);
        }

        @Override
        protected Object AsynTask() {
            return biz.submitUserMeasurementData(TApplication.user.getPhone(), bean.getType() + "", bean);
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, HealthAssessActivity.this, false);
            if (response.isSuccess()) {
                ToastUtil.showLongToast(HealthAssessActivity.this, getString(R.string.blood_measurement_success));
            }
            LogUtil.logMessage("ykk", response.getInfo());
            CustomDialog.dismissDialog();

        }
    };

    @Override
    protected void registerEvent() {

    }

    private void notifyData(HealthAssessBean healthAssessBean) {

        // notifyBlood();
        switch (measure_type) {
            case DataBean.TYPE_BLOOD_PRESSURE_MONITOR:
                notifyBlood(healthAssessBean);
                break;
            case DataBean.TYPE_GLUCOSE_METER:
                notifyGlucose();
                break;
            case DataBean.TYPE_BLE_EAR:
                notifyEar();
                break;
        }

    }

    /**
     * 评估血压健康值
     */
    private void notifyBlood(HealthAssessBean healthAssessBean) {
        HealthGrid bean = new HealthGrid();
        bean.setName(getString(R.string.normal_result));
        bean.setResult(setHtmlFont1(healthAssessBean.getNormalTimes() + ""));
        bean.setMsg(setHtmlFont1(getString(R.string.secondary)));
        datas.add(bean);
        bean = new HealthGrid();
        bean.setName(getString(R.string.high_result));
        bean.setResult(setHtmlFont2(healthAssessBean.getUnnormalTimes() + ""));
        bean.setMsg(setHtmlFont2(getString(R.string.secondary)));
        datas.add(bean);

        bean = new HealthGrid();
        bean.setName(getString(R.string.pressure_high_valut));
        bean.setResult(setHtmlFont2(healthAssessBean.getHighPressureMax()));
        bean.setMsg(setHtmlFont2("mmHg"));
        datas.add(bean);

        bean = new HealthGrid();
        bean.setName(getString(R.string.pressure_low_valut));
        bean.setResult(setHtmlFont2(healthAssessBean.getLowPressureMax()));
        bean.setMsg(setHtmlFont2("mmHg"));
        datas.add(bean);

        bean = new HealthGrid();
        bean.setName(getString(R.string.pressure_average));
        bean.setResult(setHtmlFont2(healthAssessBean.getHighPressureAverage() + "/" + healthAssessBean.getLowPressureAverage()));
        bean.setMsg(setHtmlFont2("mmHg"));
        datas.add(bean);

        bean = new HealthGrid();
        bean.setName(getString(R.string.Heart_rate_average));
        bean.setResult(setHtmlFont2(healthAssessBean.getHeartRateAverage()));
        bean.setMsg(setHtmlFont2(getString(R.string.Heart_rate_average_unit)));
        datas.add(bean);
        assessAdapter.notifyDataSetChanged();
        setHealthExplainAndResult(getString(R.string.blood_pressure), healthAssessBean.getResults());
        showChar("mmHg", healthAssessBean);

    }

    /**
     * 评估血糖健康值
     */
    private void notifyGlucose() {
        HealthGrid bean = new HealthGrid();
        bean.setName("正常血糖结果");
        bean.setResult("3");
        bean.setMsg("次");
        datas.add(bean);
        bean = new HealthGrid();
        bean.setName("偏高血糖结果");
        bean.setResult("5");
        bean.setMsg("次");
        datas.add(bean);

        bean = new HealthGrid();
        bean.setName("偏低血糖结果");
        bean.setResult("5");
        bean.setMsg("次");
        datas.add(bean);

        bean = new HealthGrid();
        bean.setName("最高血糖值");
        bean.setResult("9.7");
        bean.setMsg("mmol/L");
        datas.add(bean);

        bean = new HealthGrid();
        bean.setName("最低血糖值");
        bean.setResult("6.1");
        bean.setMsg("mmol/L");
        datas.add(bean);

        bean = new HealthGrid();
        bean.setName("平均血糖值");
        bean.setResult("7.1");
        bean.setMsg("mmol/L");
        datas.add(bean);
        assessAdapter.notifyDataSetChanged();
        // setHealthExplainAndResult("血糖");
        ///  showChar("mmol/L");

    }

    /**
     * 评估血糖健康值
     */
    private void notifyEar() {
        HealthGrid bean = new HealthGrid();
        bean.setName("正常体温结果");
        bean.setResult("13");
        bean.setMsg("次");
        datas.add(bean);
        bean = new HealthGrid();
        bean.setName("偏高体温结果");
        bean.setResult("5");
        bean.setMsg("次");
        datas.add(bean);

        bean = new HealthGrid();
        bean.setName("偏低体温结果");
        bean.setResult("2");
        bean.setMsg("次");
        datas.add(bean);

        bean = new HealthGrid();
        bean.setName("最高体温值");
        bean.setResult("40.1");
        bean.setMsg("℃");
        datas.add(bean);

        bean = new HealthGrid();
        bean.setName("最低体温值");
        bean.setResult("36.1");
        bean.setMsg("℃");
        datas.add(bean);

        bean = new HealthGrid();
        bean.setName("平均体温值");
        bean.setResult("36.8");
        bean.setMsg("℃");
        datas.add(bean);
        assessAdapter.notifyDataSetChanged();
        //  setHealthExplainAndResult("体温");
        // showChar("℃");

    }

    /**
     * 统计图
     */
    private void showChar(String asxiyName, HealthAssessBean healthAssessBean) {
        if (healthAssessBean.getData() == null || healthAssessBean.getData().size() < 1) {
            return;
        }
        ArrayList<String> highData = new ArrayList<>();
        ArrayList<String> lowData = new ArrayList<>();
        ArrayList<HealthAssessCharBean> charData = healthAssessBean.getData();
        Collections.sort(charData, new HealthAssessCharBean());
        for (HealthAssessCharBean bean : charData) {
            highData.add(bean.getHighPressure());
            lowData.add(bean.getLowPressure());
        }


        mLineChartView.startDataAnimation();
        for (int i = 0; i < highData.size(); i++) {
            mPointValues.add(new PointValue(i, Float.parseFloat(highData.get(i))));
            //   mAxisValues.add(new AxisValue(i).setLabel(i + "")); //为每个对应的i设置相应的label(显示在X轴)
        }
        Line line = new Line(mPointValues).setColor(Color.BLUE).setCubic(false);
        List<Line> lines = new ArrayList<Line>();
        lines.add(line);
        line.setHasLabels(true);
        line.setHasLabelsOnlyForSelected(false);
        for (int i = 0; i < lowData.size(); i++) {
            mPointValues2.add(new PointValue(i, Float.parseFloat(lowData.get(i))));
            //  mAxisValues.add(new AxisValue(i).setLabel(i + "__")); //为每个对应的i设置相应的label(显示在X轴)
        }

        Line line2 = new Line(mPointValues2).setColor(Color.RED).setCubic(false);
        lines.add(line2);
        line2.setHasLabels(true);
        line2.setHasLabelsOnlyForSelected(false);
        LineChartData data = new LineChartData();
        data.setLines(lines);
        Axis axisY = new Axis();  //Y轴
        axisY.setHasLines(true);
        axisY.setName(asxiyName);
        data.setAxisYLeft(axisY);
//        Axis axisX=new Axis();
//        data.setAxisXBottom(axisX);
        //设置行为属性，支持缩放、滑动以及平移
        mLineChartView.setInteractive(true);
        mLineChartView.setZoomType(ZoomType.HORIZONTAL);
        mLineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        mLineChartView.setLineChartData(data);

    }

    private String setHtmlFont1(String str) {
        if (StringUtil.isEmpty(str))
            return "";
        return String.format("<font color=\"%s\">%s</font>", "#228B22", str);
    }

    private String setHtmlFont2(String str) {
        if (StringUtil.isEmpty(str))
            return "";
        return String.format("<font color=\"%s\">%s</font>", "#FF1493", str);
    }

    private void setHealthExplainAndResult(String formatStr, String result) {
        String explain = getString(R.string.healty_assess_head_msg);
        String tmpExplain = String.format(explain, formatStr);
        txt_health_explain.setText(tmpExplain);
        explain = getString(R.string.healty_assess_foot_msg);
        tmpExplain = String.format(explain, formatStr, result);
        txt_health_result.setText(tmpExplain);
    }

    UserBiz biz = new UserBiz();
    private HealthAssessBean healthAssessBean;
    private AsynProcessing submitProcessing = new AsynProcessing() {
        @Override
        protected void before() {

        }

        @Override
        protected Object AsynTask() {
            return biz.healthAssess(TApplication.user.getPhone(), measure_type + "", device_id);
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, HealthAssessActivity.this, false);
            if (response.isSuccess()) {
                healthAssessBean = (HealthAssessBean) response.getData();
                notifyData(healthAssessBean);
                pageLoadIng.setVisibility(View.GONE);
            } else {
                pageLoadIng.noData();
            }
            LogUtil.logMessage("ykk", response.getInfo());
        }
    };

    public class HealthGrid {
        private String name;
        private String result;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        private String msg;
    }
}
