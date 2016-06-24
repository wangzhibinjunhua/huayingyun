package com.tentinet.healthy.activity;

import android.os.Handler;
import android.os.Message;

import com.tentinet.healthy.R;
import com.tentinet.healthy.util.IntentUtil;

/**
 * 加载界面.
 *
 * @author paladin.
 */
public class LoadActivity extends BaseActivity {

    private int waitTime;

    @Override
    protected void getData() {
    }

    @Override
    protected int setContent() {
        return R.layout.activity_load;
    }

    @Override
    protected void init() {
        waitTime = 1;
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void registerEvent() {

    }

    /**
     * 启动线程.
     */
    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            if (waitTime < 3) {
                waitTime++;
                handler.postDelayed(runnable, 1000);
            } else {
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }

    };

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    IntentUtil.gotoActivityAndFinish(LoadActivity.this, MainActivity.class);
                    break;
                default:
                    break;
            }
        }

    };

    @Override
    protected void setBgTranslucentStatus() {
        isTranslucentStatus = false;
        super.setBgTranslucentStatus();
    }
}
