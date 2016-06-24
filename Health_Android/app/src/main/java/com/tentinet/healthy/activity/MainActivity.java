package com.tentinet.healthy.activity;

import android.app.Activity;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.bean.UserBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.btsdk.LocalBluetooth;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.service.BleManagerService;
import com.tentinet.healthy.service.BroadcastActions;
import com.tentinet.healthy.service.TempService;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CropImageUtils;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.ImageUtil;
import com.tentinet.healthy.util.PhotoPathUtils;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.DeviceView;
import com.tentinet.healthy.view.HomeTab;
import com.tentinet.healthy.view.MineTab;
import com.tentinet.healthy.view.NewsTab;

import java.io.File;
import java.util.HashMap;

/**
 * 主界面.
 *
 * @author paladin.
 */
public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    /**
     * 内容页.
     */
    private RelativeLayout relative_content;
    /**
     * 切换标签页.
     */
    private RadioGroup group_tab;
    /**
     * 首页标签页.
     */
    private HomeTab tab_home;
    /**
     * 数据标签页.
     */
    private DeviceView tab_data;
    /**
     * 新闻标签页.
     */
    private NewsTab tab_news;
    /**
     * 我的标签页.
     */
    private MineTab tab_mine;
    /**
     * 请求码：打开蓝牙
     */
    public static final int REQUEST_ENABLE_BT = 5;
    UserBiz userBiz = new UserBiz();
    HashMap<String, File> hashMap = new HashMap<String, File>();

    @Override
    protected void getData() {
    }

    Uri uri;

    private int checkIndex = 0;

    private int checkOldIndex = -1;
    File file;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_LOGIN == requestCode && resultCode == RESULT_OK) {
            if (tab_mine == null) {
                tab_mine = new MineTab(MainActivity.this);
            }
            tab_mine.updateUserLoginStatus();
        } else if (REQUEST_ENABLE_BT == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                Intent intent = new Intent(BroadcastActions.ACTION_START_LE_SCAN);
                sendBroadcast(intent);
            } else {
                /** 用户未同意开启蓝牙，或蓝牙打开错误 */
                ToastUtil.showLongToast(this, "The bluetooth is not enable");
            }
        } else if (CropImageUtils.IMAGE_CODE == requestCode) {
            CropImageUtils.cropPicture(data, MainActivity.this);

        } else if (CropImageUtils.REQUEST_CropPictureActivity == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                if (CropImageUtils.cropImageUri != null) {
                    file = new File(PhotoPathUtils.getPath(MainActivity.this,
                            CropImageUtils.cropImageUri));
                    hashMap.put(TApplication.user.getPhone() + "", file);
                    asynProcessing.starAsyn();
                }
            }
        }
    }

    //截取图片
    public void cropImage(Uri uri, int requestCode) {
        int dp = 500;

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);//输出是X方向的比例
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高，切忌不要再改动下列数字，会卡死
        intent.putExtra("outputX", dp);//输出X方向的像素
        intent.putExtra("outputY", dp);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);//设置为不返回数据
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected int setContent() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        relative_content = (RelativeLayout) findViewById(R.id.relative_content);
        group_tab = (RadioGroup) findViewById(R.id.group_tab);
        tab_home = new HomeTab(MainActivity.this);
        changeView(tab_home);
        tab_home.setItemClickListener(itemClickListener);
        initTempService();
    }

    @Override
    protected void registerEvent() {
        group_tab.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radio_home:
                if (null == tab_home) {
                    tab_home = new HomeTab(MainActivity.this);
                }
                checkIndex = 0;
                changeView(tab_home);
                break;
            case R.id.radio_data:
                if (null == tab_data) {
                    tab_data = new DeviceView(MainActivity.this);
                }
                checkIndex = 1;
                changeView(tab_data);
                break;
            case R.id.radio_news:
                if (null == tab_news) {
                    tab_news = new NewsTab(MainActivity.this);
                }
                checkIndex = 2;
                changeView(tab_news);
                break;
            case R.id.radio_mine:
                if (null == tab_mine) {
                    tab_mine = new MineTab(MainActivity.this);
                }
                checkIndex = 3;
                changeView(tab_mine);
                break;
        }
    }

    /**
     * 切换视图.
     *
     * @param view 视图.
     */
    private void changeView(final View view) {
        relative_content.removeAllViews();
        relative_content.addView(view);
        if (checkOldIndex > -1) {
            Animation translateAnimation = null;
            if (checkIndex >= checkOldIndex) {
                translateAnimation = AnimationUtils.loadAnimation(this, R.anim.exit_enter);
            } else {
                translateAnimation = AnimationUtils.loadAnimation(this, R.anim.enter_exit);
            }
            relative_content.startAnimation(translateAnimation);
        }
        checkOldIndex = checkIndex;
    }

    /**
     * 登录请求码.
     */
    public static final int REQUEST_LOGIN = 1;


    /**
     * HOME griditem点击的回调
     */
    private HomeTab.ItemClickListener itemClickListener = new HomeTab.ItemClickListener() {
        @Override
        public void onClick() {
            if (null == tab_data) {
                tab_data = new DeviceView(MainActivity.this);
            }

            changeView(tab_data);
            group_tab.check(R.id.radio_data);
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // System.exit(0);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showShortToast(getApplicationContext(), getString(R.string.exit_msg));
            exitTime = System.currentTimeMillis();
        } else {
            for (Activity activity : TApplication.activityList) {
                activity.finish();
            }
            unregisterReceiver(loginOutReceiver);
            //关闭Gatt连接
            Intent intent = new Intent(BroadcastActions.ACTION_CLOSE_GATT);
            sendBroadcast(intent);
            Intent service = new Intent(this, BleManagerService.class);
            stopService(service);
            Intent tempService = new Intent(this, TempService.class);
            unbindService(conn);
            stopService(tempService);
            //   LocalBluetooth.disableBLE();
            LocalBluetooth.destory();
            finish();
            System.exit(0);
        }
    }


    private AsynProcessing asynProcessing = new AsynProcessing() {
        @Override
        protected void before() {
            CustomDialog.showWaitDialog(MainActivity.this);
        }

        @Override
        protected Object AsynTask() {
            return userBiz.submitUserPicture(TApplication.user.getPhone(), TApplication.user.getPhone(), hashMap);
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, MainActivity.this, true);
            if (response.isSuccess()) {
                tab_mine.setUserImage(ImageUtil.loadBitmap(PhotoPathUtils.getPath(MainActivity.this, CropImageUtils.cropImageUri)));
            }
            CustomDialog.dismissDialog();
        }
    };

    /**
     * 初始化体温贴
     */
    private void initTempService() {
        Intent intent = new Intent(this, TempService.class);
        startService(intent);
        bindService(intent, this.conn, BIND_AUTO_CREATE);
        login();
        registerReceiver(loginOutReceiver, loginOutFilter());
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TempService.tempService = ((TempService.TempBind) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private void login() {
        account = TApplication.sp.get(LoginActivity.KEY_ACCOUNT, "");
        password = TApplication.sp.get(LoginActivity.KEY_PASSWORD, "");
        if (StringUtil.isEmpty(account) || StringUtil.isEmpty(password))
            return;
        loginAsyn.starAsyn();
    }

    private String account, password;
    private AsynProcessing loginAsyn = new AsynProcessing() {

        @Override
        protected void before() {

        }

        @Override
        protected Object AsynTask() {
            return userBiz.login(account, password);
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, MainActivity.this, false);
            if (response.isSuccess()) {
                TApplication.user = (UserBean) response.getData();
                if (tab_mine == null) {
                    tab_mine = new MineTab(MainActivity.this);
                }
                tab_mine.updateUserLoginStatus();
            }
        }
    };
    //接收退出登录广播
    private BroadcastReceiver loginOutReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BaseBiz.STATUS_SIGN_ERROR + "")) {
                TApplication.user.logout();
                if (tab_mine == null) {
                    tab_mine = new MineTab(MainActivity.this);
                }
                tab_mine.updateUserLoginStatus();
                changeView(tab_mine);
                group_tab.check(R.id.radio_mine);
            }
        }
    };

    private IntentFilter loginOutFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BaseBiz.STATUS_SIGN_ERROR + "");
        return intentFilter;
    }

}
