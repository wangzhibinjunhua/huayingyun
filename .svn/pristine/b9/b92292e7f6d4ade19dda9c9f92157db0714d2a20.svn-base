package com.tentinet.healthy.activity;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.adapter.DoctorMsgAdapter;
import com.tentinet.healthy.bean.DoctorMsgBean;
import com.tentinet.healthy.util.DateUtil;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.DoctorListView;

import java.util.ArrayList;

public class DoctorInfoActivity extends BaseActivity {


    private TitleView view_title;

    private TextView txt_ms;

    private DoctorListView lv_message;

    private EditText edit_content;

    private Button btn_send;

    private DoctorMsgAdapter adapter;
    private ArrayList<DoctorMsgBean> doctorMsgs;

    private ScrollView scrollView;
    InputMethodManager imm;
    private Handler scrollHandler;



    @Override
    protected void getData() {

    }

    @Override
    protected int setContent() {
        return R.layout.activity_doctor_info;
    }

    @Override
    protected void init() {
        doctorMsgs = new ArrayList<>();
        view_title = (TitleView) findViewById(R.id.title_doctor);
       // view_title.setTitle(R.string.doctor_list);
        view_title.setBackImageButton();
        txt_ms = (TextView) findViewById(R.id.txt_ms);
        lv_message = (DoctorListView) findViewById(R.id.lv_message);
        edit_content = (EditText) findViewById(R.id.edit_content);
        scrollView = (ScrollView) findViewById(R.id.scroll_doctor);
        btn_send = (Button) findViewById(R.id.btn_send);
        edit_content.clearFocus();
        adapter = new DoctorMsgAdapter(this, doctorMsgs);
        lv_message.setAdapter(adapter);
        addMsg();
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        scrollHandler = new Handler();
    }

    @Override
    protected void registerEvent() {
        txt_ms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_ms.setText("消化内科，泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科...住院科：消化内科，泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科...住院科：消化内科，泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科...住院科：消化内科，泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科...住院科：消化内科，泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科...住院科：消化内科，泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科...住院科：消化内科，泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科...住院科：消化内科，泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科...住院科：消化内科，泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科...住院科：消化内科，泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科...住院科：消化内科，泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科...住院科：消化内科，泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科泌尿内科...");
            }
        });
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtil.isEmpty(edit_content.getText().toString())) {
                    DoctorMsgBean doctorMsgBean = new DoctorMsgBean();
                    doctorMsgBean.setType(1);
                    doctorMsgBean.setContent(edit_content.getText().toString());
                    doctorMsgBean.setDate(DateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                    doctorMsgs.add(doctorMsgBean);
                    edit_content.setText("");
                    adapter.notifyDataSetChanged();
                    imm.hideSoftInputFromWindow(edit_content.getWindowToken(), 0);
                    scrollHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    }, 100);
                }
            }
        });
    }

    private void addMsg() {
        DoctorMsgBean doctorMsgBean = new DoctorMsgBean();
        doctorMsgBean.setType(1);
        doctorMsgBean.setContent("请问王医师，为什么骨折后，下雨天会有痛疼感呢？");
        doctorMsgBean.setDate("2016-06-21 15:09:21");
        doctorMsgs.add(doctorMsgBean);
        doctorMsgBean = new DoctorMsgBean();
        doctorMsgBean.setType(2);
        doctorMsgBean.setContent("你好可能与受伤后局部神经损伤和血供条件差有关。没有可靠治疗方法。如果关节也损伤了，以后恐怕就活动不了了。");
        doctorMsgBean.setDate("2016-06-22 15:09:21");
        doctorMsgs.add(doctorMsgBean);
        doctorMsgBean = new DoctorMsgBean();
        doctorMsgBean.setType(2);
        doctorMsgBean.setContent("你好可能与受伤后局部神经损伤和血供条件差有关。没有可靠治疗方法。如果关节也损伤了，以后恐怕就活动不了了。");
        doctorMsgBean.setDate("2016-06-23 15:09:21");
        doctorMsgs.add(doctorMsgBean);
        doctorMsgBean = new DoctorMsgBean();
        doctorMsgBean.setType(1);
        doctorMsgBean.setContent("请问王医师，为什么骨折后，下雨天会有痛疼感呢？");
        doctorMsgBean.setDate("2016-06-24 15:09:21");
        doctorMsgs.add(doctorMsgBean);
        doctorMsgBean = new DoctorMsgBean();
        doctorMsgBean.setType(1);
        doctorMsgBean.setContent("请问王医师，为什么骨折后，下雨天会有痛疼感呢？");
        doctorMsgBean.setDate("2016-06-25 15:09:21");
        doctorMsgs.add(doctorMsgBean);
        adapter.notifyDataSetChanged();
    }


}
