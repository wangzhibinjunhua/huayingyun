package com.tentinet.healthy.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.service.BleManagerService;
import com.tentinet.healthy.service.BroadcastActions;
import com.tentinet.healthy.util.AppUtil;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.ImageUtil;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.util.NetUtil;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.DeviceView;
import com.tentinet.healthy.view.HomeTab;
import com.tentinet.healthy.view.MineTab;
import com.tentinet.healthy.view.NewsTab;

import java.io.File;
import java.io.FileNotFoundException;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_LOGIN == requestCode && resultCode == RESULT_OK) {
            if (tab_mine == null) {
                tab_mine = new MineTab(MainActivity.this);
            }
            //changeView(tab_mine);
           // group_tab.check(R.id.radio_mine);
            tab_mine.updateUserLoginStatus();
        } else if (REQUEST_ENABLE_BT == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                Intent intent = new Intent(BroadcastActions.ACTION_START_LE_SCAN);
                sendBroadcast(intent);
            } else {
                /** 用户未同意开启蓝牙，或蓝牙打开错误 */
                ToastUtil.showLongToast(this, "The bluetooth is not enable");
                //  finish();
            }
        } else if (ImageUtil.REQUEST_IMAGE_FOR_ALBUM == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                uri = data.getData();
                if (uri != null) {
                    cropImage(uri, ImageUtil.REQUEST_IMAGE_CUT);
                }
            }
        } else if (ImageUtil.REQUEST_IMAGE_FOR_CAMERA == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap b = (Bitmap) extras.get("data");
                uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), b, null, null));
                if (uri != null) {
                    cropImage(uri, ImageUtil.REQUEST_IMAGE_CUT);
                }
                //  uri = data.getParcelableExtra(MediaStore.EXTRA_OUTPUT);
            }
        } else if (ImageUtil.REQUEST_IMAGE_CUT == requestCode) {
            if (resultCode == Activity.RESULT_OK) {
                if (uri != null) {
                    if (!StringUtil.isEmpty(getUriPath(uri))) {


                        hashMap.put(TApplication.user.getPhone() + "", new File(getUriPath(uri)));
                        asynProcessing.starAsyn();
                    }
                    //  tab_mine.setUserImage(ImageUtil.loadBitmap(getUriPath(uri)));
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
        //   relative_content.setVisibility(View.INVISIBLE);
        relative_content.addView(view);

        if (checkOldIndex > -1) {
            Animation translateAnimation = null;
            if (checkIndex >= checkOldIndex) {
                LogUtil.logDebugMessage("111111");
                translateAnimation = AnimationUtils.loadAnimation(this, R.anim.exit_enter);
            } else {
                LogUtil.logDebugMessage("22222");
                translateAnimation = AnimationUtils.loadAnimation(this, R.anim.enter_exit);
            }
            relative_content.startAnimation(translateAnimation);
        }

        checkOldIndex = checkIndex;
//

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
        //关闭Gatt连接
        Intent intent = new Intent(BroadcastActions.ACTION_CLOSE_GATT);
        sendBroadcast(intent);
        Intent service = new Intent(this, BleManagerService.class);
        stopService(service);
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
            ToastUtil.showShortToast(getApplicationContext(), "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            for (Activity activity : TApplication.activityList) {
                activity.finish();
            }
            finish();
            System.exit(0);
        }
    }

    private String getUriPath(Uri Uri) {


        String[] proj = {MediaStore.Images.Media.DATA};

        Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);

        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        actualimagecursor.moveToFirst();

        String img_path = actualimagecursor.getString(actual_image_column_index);
        return img_path;
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
            BaseBiz.processResponse(response, MainActivity.this, false);
            if (response.isSuccess()) {
                ToastUtil.showLongToast(MainActivity.this, response.getInfo());
                tab_mine.setUserImage(ImageUtil.loadBitmap(getUriPath(uri)));
            }
            CustomDialog.dismissDialog();
        }
    };
}