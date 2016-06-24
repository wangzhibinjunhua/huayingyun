package com.tentinet.healthy.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.tentinet.healthy.R;
import com.tentinet.healthy.activity.AboutUsActivity;
import com.tentinet.healthy.activity.AdviceFeedbackActivity;
import com.tentinet.healthy.activity.DoctorListActivity;
import com.tentinet.healthy.activity.LoginActivity;
import com.tentinet.healthy.activity.MainActivity;
import com.tentinet.healthy.activity.MineDeviceActivity;
import com.tentinet.healthy.activity.ParentActivity;
import com.tentinet.healthy.activity.UpdateMineInfoActivity;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.bean.VersionBean;
import com.tentinet.healthy.biz.BaseBiz;
import com.tentinet.healthy.biz.UserBiz;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.AppUtil;
import com.tentinet.healthy.util.AsynProcessing;
import com.tentinet.healthy.util.CropImageUtils;
import com.tentinet.healthy.util.CustomDialog;
import com.tentinet.healthy.util.IntentUtil;
import com.tentinet.healthy.util.StringUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.widget.CircleImageView;
import com.tentinet.healthy.widget.stemdownloader.Downloader;

/**
 * 我的标签页.
 *
 * @author paladin.
 */
public class MineTab extends RelativeLayout implements View.OnClickListener {

    /**
     * 上下文环境.
     */
    private Context context;
    /**
     * 标题.
     */
    private TitleView view_title;
    /**
     * 用户头像.
     */
    private CircleImageView img_portrait;
    /**
     * 用户昵称.
     */
    private TextView txt_nick;
    /**
     * 用户手机号码.
     */
    private TextView txt_phone;
    /**
     * 登录.
     */
    private TextView txt_login;
    /**
     * 我的设备.
     */
    private LinearLayout btn_mineDevice, btn_parent, btn_feedback, btn_version, btn_mine_info, lin_mine_doctor;

    private TextView txt_dialog_camear, txt_dialog_picture, txt_version_info;


    /**
     * 系统推送.
     */
    private CheckBox check_systemPush;
    /**
     * 关于我们.
     */
    private LinearLayout btn_aboutUs;
    /**
     * 退出登录.
     */
    private Button btn_logout;

    private ToggleButton tb_mine_push;

    private static final String KEY_PUSH = "KEY_PUSH";

    public MineTab(Context context) {
        super(context);
        init(context);
    }

    public MineTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MineTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化.
     *
     * @param context 上下文环境.
     */
    private void init(Context context) {
        this.context = context;
        this.addView(LayoutInflater.from(context).inflate(R.layout.tab_mine, null), new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        view_title = (TitleView) findViewById(R.id.view_title);
        //   view_title.setTitle(R.string.mine);
        view_title.setBackground(R.color.font_color_green_default);
        txt_nick = (TextView) findViewById(R.id.txt_nick);
        txt_phone = (TextView) findViewById(R.id.txt_phone);
        txt_login = (TextView) findViewById(R.id.txt_login);
        btn_mineDevice = (LinearLayout) findViewById(R.id.lin_mine_device);
        btn_aboutUs = (LinearLayout) findViewById(R.id.lin_about_us);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_parent = (LinearLayout) findViewById(R.id.lin_mine_parent);
        img_portrait = (CircleImageView) findViewById(R.id.img_portrait);
        btn_feedback = (LinearLayout) findViewById(R.id.lin_advice_feedback);
        btn_version = (LinearLayout) findViewById(R.id.lin_check_version);
        txt_version_info = (TextView) findViewById(R.id.txt_version_info);
        btn_mine_info = (LinearLayout) findViewById(R.id.lin_mine_info);
        lin_mine_doctor = (LinearLayout) findViewById(R.id.lin_mine_doctor);
        tb_mine_push = (ToggleButton) findViewById(R.id.tb_mine_push);
        tb_mine_push.setChecked(TApplication.sp.get(KEY_PUSH, false));
        registerEvent();
        updateUserLoginStatus();
    }

    private void registerEvent() {
        txt_login.setOnClickListener(this);
        btn_mineDevice.setOnClickListener(this);
        btn_aboutUs.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_parent.setOnClickListener(this);
        img_portrait.setOnClickListener(this);
        btn_feedback.setOnClickListener(this);
        btn_version.setOnClickListener(this);
        txt_version_info.setText(context.getString(R.string.current_version) + "：V" + AppUtil.getVersionName(context));
        lin_mine_doctor.setOnClickListener(this);
        //   txt_version_info.setOnClickListener(this);
        btn_mine_info.setOnClickListener(this);
        tb_mine_push.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TApplication.sp.set(KEY_PUSH, isChecked);
                if (isChecked) {
                    //选中
                } else {
                    //未选中
                }
            }
        });
    }

    /**
     * 更新用户登录状态.
     */
    public void updateUserLoginStatus() {
        if (TApplication.user.isLogin()) {
            // TODO 加载用户头像.
            if (StringUtil.isEmpty(TApplication.user.getName())) {
                txt_nick.setVisibility(GONE);
            } else {
                txt_nick.setVisibility(VISIBLE);
                txt_nick.setText(TApplication.user.getName());
            }
            txt_phone.setText(TApplication.user.getPhone());
            txt_login.setVisibility(View.GONE);
            btn_logout.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(BaseBiz.SERVER_PATH + TApplication.user.getPortrait(), img_portrait);
        } else {
            txt_nick.setVisibility(View.GONE);
            txt_nick.setVisibility(View.GONE);
            txt_login.setVisibility(View.VISIBLE);
            btn_logout.setVisibility(View.GONE);
            txt_phone.setText("");
            img_portrait.setImageResource(R.mipmap.mine_picture);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_login:
                IntentUtil.gotoActivityForResult(context, LoginActivity.class, MainActivity.REQUEST_LOGIN);
                break;
            case R.id.lin_mine_device:
                defaultNoLogin(MineDeviceActivity.class);
                break;
            case R.id.lin_about_us:
                IntentUtil.gotoActivity(context, AboutUsActivity.class);
                break;
            case R.id.btn_logout:
                CustomDialog.showOkAndCalcelDialog(context, context.getString(R.string.logout), context.getString(R.string.yes_no_login) + "？", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TApplication.user.logout();
                        TApplication.sp.set(LoginActivity.KEY_ACCOUNT, "");
                        TApplication.sp.set(LoginActivity.KEY_PASSWORD, "");
                        updateUserLoginStatus();
                        CustomDialog.dismissDialog();
                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CustomDialog.dismissDialog();
                    }
                });

                break;
            case R.id.lin_mine_parent:
                defaultNoLogin(ParentActivity.class);
                break;

            case R.id.img_portrait:
                if (TApplication.user.isLogin()) {
                    // CustomDialog.showWaitDialog(context);
                    showSelectImageDialog(context);
                } else {
                    IntentUtil.gotoActivityForResult(context, LoginActivity.class, MainActivity.REQUEST_LOGIN);
                }
                break;
            case R.id.lin_check_version:
                //  case R.id.txt_version_info:
                asynProcessing.starAsyn();
                break;
            case R.id.lin_advice_feedback:
                defaultNoLogin(AdviceFeedbackActivity.class);
                break;
            case R.id.lin_mine_info:
                defaultNoLogin(UpdateMineInfoActivity.class);
                break;
            case R.id.lin_mine_doctor:
                defaultNoLogin(DoctorListActivity.class);
                break;
        }
    }

    View imgView;

    /**
     * 显示选择图片对话框
     * <p/>
     * * @param context 上下文环境.
     */
    public void showSelectImageDialog(final Context context) {
        imgView = LayoutInflater.from(context).inflate(R.layout.dialog_image, null);
        txt_dialog_camear = (TextView) imgView.findViewById(R.id.txt_dialog_camear);
        txt_dialog_picture = (TextView) imgView.findViewById(R.id.txt_dialog_picture);
        CustomDialog.showDefaultDialog(context, imgView, true);
        txt_dialog_picture.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // ImageUtil.getImageForAlbum(context);
                CropImageUtils.takeFromGallery((Activity) context, CropImageUtils
                        .IMAGE_CODE);
                CustomDialog.dismissDialog();

            }
        });
        txt_dialog_camear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImageUtils.takeFromCamera((Activity) context, CropImageUtils
                        .IMAGE_CODE, CropImageUtils.fileName);
                CustomDialog.dismissDialog();
            }
        });
    }

    public void setUserImage(Bitmap bitmap) {
        img_portrait.setImageBitmap(bitmap);
    }


    UserBiz userBiz = new UserBiz();
    private AsynProcessing asynProcessing = new AsynProcessing() {
        @Override
        protected void before() {
            CustomDialog.showWaitDialog(context);
        }

        @Override
        protected Object AsynTask() {
            String version = AppUtil.getVersionName(context);
            version = version.replace(".", "");
            int len = version.length();
            //version为8位，不足补0
            String tmp = "";
            for (int i = 0; i < 8 - len; i++) {
                tmp += "0";
            }
            version = version + tmp;
            return userBiz.checkVerion(version);
        }

        @Override
        protected void after(Object obj) {
            ResponseBean response = (ResponseBean) obj;
            BaseBiz.processResponse(response, context, false);
            CustomDialog.dismissDialog();
            if (response.isSuccess()) {
                final VersionBean versionBean = (VersionBean) response.getData();
                CustomDialog.showOkAndCalcelDialog(context, context.getString(R.string.check_version), versionBean.getDescription(), new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Downloader downloader = new Downloader(context, BaseBiz.SERVER_PATH + versionBean.getFile());
                        downloader.start();
                        CustomDialog.dismissDialog();
                    }
                }, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CustomDialog.dismissDialog();
                    }
                });
            } else {
                ToastUtil.showLongToast(context, context.getString(R.string.version_msg));
            }
        }
    };

    private void defaultNoLogin(Class<?> gotoClass) {
        if (TApplication.user.isLogin()) {
            IntentUtil.gotoActivity(context, gotoClass);
        } else {
            IntentUtil.gotoActivityForResult(context, LoginActivity.class, MainActivity.REQUEST_LOGIN);
        }

    }

}
