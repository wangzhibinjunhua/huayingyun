package com.tentinet.healthy.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.tentinet.healthy.R;
import com.tentinet.healthy.interf.TApplication;

/**
 * Activity基类.
 *
 * @author paladin.
 */
public abstract class BaseActivity extends AppCompatActivity {


    private LinearLayout lin_base_parent;
    private View parentView;



    public boolean isTranslucentStatus=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setBgTranslucentStatus();
        super.onCreate(savedInstanceState);
        TApplication.activityList.add(this);
        getData();
        loadLayout();
        init();
        registerEvent();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        TApplication.activityList.remove(this);
    }

    /**
     * 获取Intent传递数据.
     *
     * @author paladin.
     * @date 2016/3/14 16:08
     */
    protected abstract void getData();

    /**
     * 设置界面XML.
     *
     * @return 返回界面XML引用.
     * @author paladin.
     * @date 2016/3/14 16:08
     */
    protected abstract int setContent();

    /**
     * 初始化界面空间.
     *
     * @author paladin.
     * @date 2016/3/14 16:08
     */
    protected abstract void init();

    /**
     * 注册界面空间监听.
     *
     * @author paladin.
     * @date 2016/3/14 16:08
     */
    protected abstract void registerEvent();

    /**
     * 沉浸模式
     */
    protected void setBgTranslucentStatus() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(isTranslucentStatus);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.title_background);//通知栏所需颜色
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        //透明导航栏
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 加载content
     */
    private void loadLayout() {
        parentView = LayoutInflater.from(this).inflate(R.layout.activity_base, null);
        lin_base_parent = (LinearLayout) parentView.findViewById(R.id.base_parent_lin);
        lin_base_parent.removeAllViews();
        lin_base_parent.addView(LayoutInflater.from(this).inflate(setContent(), null));
        setContentView(parentView);
    }

}
