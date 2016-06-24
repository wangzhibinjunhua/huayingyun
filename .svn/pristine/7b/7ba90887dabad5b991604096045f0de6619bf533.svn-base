package com.tentinet.healthy.biz;

import android.text.method.NumberKeyListener;

import com.tentinet.healthy.activity.SetPasswordActivity;
import com.tentinet.healthy.bean.BaseBean;
import com.tentinet.healthy.bean.DataBean;
import com.tentinet.healthy.bean.DeviceBean;
import com.tentinet.healthy.bean.HealthAssessBean;
import com.tentinet.healthy.bean.HealthAssessECGBean;
import com.tentinet.healthy.bean.HealthAssessEarBean;
import com.tentinet.healthy.bean.HealthAssessPodBean;
import com.tentinet.healthy.bean.HealthAssessSugerBean;
import com.tentinet.healthy.bean.NewsBean;
import com.tentinet.healthy.bean.ParentBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.bean.UserBean;
import com.tentinet.healthy.bean.VersionBean;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.DateUtil;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.util.StringUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 用户业务逻辑.
 *
 * @author paladin.
 */
public class UserBiz extends BaseBiz {

    /**
     * 登录.
     *
     * @param account  账号.
     * @param password 密码.
     * @return 返回登录请求信息.
     */
    public ResponseBean login(String account, String password) {
        HashMap<String, String> params = getSystemParams(METHOD_LOGIN);
        params.put(KEY_PHONE, account);
        params.put(KEY_PASSWORD, password);
        params.put(KEY_SIGN, sign(METHOD_LOGIN));
        ResponseBean response = sendPost(params);
        if (response.isSuccess()) {
            // TODO 可能因json中缺少某个参数儿抛出异常，中断后续参数的解析儿导致某些参数为空，需要进行处理。
            UserBean user = null;
            try {
                user = (UserBean) BaseBean.parsingJsonObject(new JSONObject(response.getData().toString()), UserBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            user.login();
            response.setData(user);
        }
        return response;
    }

    /**
     * 获取验证码.
     *
     * @param phone 手机号码.
     * @return 返回服务器响应数据.
     */
    public ResponseBean getCode(String phone, int type) {
        HashMap<String, String> params = null;
        if (type == SetPasswordActivity.SHOW_REGISTER) {
            params = getSystemParams(METHOD_GET_CODE);
            params.put(KEY_PHONE, phone);
            params.put(KEY_SIGN, sign(METHOD_GET_CODE));
        } else {
            params = getSystemParams(METHOD_GET_FORGET_CODE);
            params.put(KEY_PHONE, phone);
            params.put(KEY_SIGN, sign(METHOD_GET_FORGET_CODE));
        }
        return sendPost(params);
    }

    /**
     * 提交验证码.
     *
     * @param phone 手机号码.
     * @param code  验证码.
     * @return 返回请求提交验证码信息.
     */
    public ResponseBean submitCode(String phone, String code, int flag) {
        HashMap<String, String> params = null;
        if (flag == SetPasswordActivity.SHOW_REGISTER) {
            params = getSystemParams(METHOD_SUBMIT_CODE);
            params.put(KEY_PHONE, phone);
            params.put(KEY_CODE, code);
            params.put(KEY_SIGN, sign(METHOD_SUBMIT_CODE));
        } else {
            params = getSystemParams(METHOD_FORGET_SUBMIT_CODE);
            params.put(KEY_PHONE, phone);
            params.put(KEY_CODE, code);
            params.put(KEY_SIGN, sign(METHOD_FORGET_SUBMIT_CODE));
        }
        return sendPost(params);
    }

    /**
     * 设置用户密码.
     *
     * @param phone    手机号码.
     * @param key      验证过验证码获得的秘钥.
     * @param password 密码.
     * @return 请求设置用户密码的返回信息.
     */
    public ResponseBean setPassword(String phone, String key, String password, int flag) {
        HashMap<String, String> params = null;
        if (flag == SetPasswordActivity.SHOW_REGISTER) {
            params = getSystemParams(METHOD_SET_PASSWORD);
            params.put(KEY_PHONE, phone);
            params.put(KEY_KEY, key);
            params.put(KEY_PASSWORD, password);
            params.put(KEY_SIGN, sign(METHOD_SET_PASSWORD));
        } else {
            params = getSystemParams(METHOD_FORGETSET_PASSWORD);
            params.put(KEY_PHONE, phone);
            params.put(KEY_KEY, key);
            params.put(KEY_PASSWORD, password);
            params.put(KEY_SIGN, sign(METHOD_FORGETSET_PASSWORD));
        }
        return sendPost(params);
    }

    /**
     * 获取用户设备列表.
     *
     * @param phone 用户手机号码.
     * @return 返回请求用户设备列表信息.
     */
    public ResponseBean getUserDeviceList(String phone) {
        HashMap<String, String> params = getSystemParams(METHOD_GET_USER_DEVICE_LIST);
        params.put(KEY_PHONE, phone);
        params.put(KEY_SIGN, sign(METHOD_GET_USER_DEVICE_LIST, TApplication.user.getToken()));
        ResponseBean response = sendPost(params);
        if (response.isSuccess()) {
            // TODO 可能因json中缺少某个参数儿抛出异常，中断后续参数的解析儿导致某些参数为空，需要进行处理。
            ArrayList<DeviceBean> list = null;
            try {
                if (StringUtil.isEmpty(response.getData().toString()) || response.getData() == null) {
                    list = new ArrayList<>();
                    response.setData(list);
                    return response;
                }
                list = (ArrayList<DeviceBean>) BaseBean.parsingJsonArray(new JSONArray(response.getData().toString()), DeviceBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            response.setData(list);
        }
        return response;
    }

    /**
     * 提交用户测量数据.
     *
     * @param phone      手机号码.
     * @param deviceType 设备类型.
     * @param data       上传的测量数据.
     * @return 提交用户测量数据的响应信息.
     */
    public ResponseBean submitUserMeasurementData(String phone, String deviceType, DataBean data) {
        HashMap<String, String> params = getSystemParams(METHOD_SUBMIT_USER_MEASUREMENT_DATA);
        params.put(KEY_PHONE, phone);
        params.put(KEY_DEVICE_TYPE, deviceType);
// TODO 需要提交的设备数据 params.put()
        params.put(KEY_SIGN, sign(METHOD_SUBMIT_USER_MEASUREMENT_DATA, TApplication.user.getToken()));
        switch (Integer.valueOf(deviceType)) {
            case DataBean.TYPE_BLOOD_PRESSURE_MONITOR:
                params.put(KEY_HEART_RATE, data.getPulse());
                params.put(KEY_HIGHPRESSURE, data.getSys());
                params.put(KEY_LOW_PRESSURE, data.getDia());
                break;
            case DataBean.TYPE_GLUCOSE_METER:
                params.put(KEY_BLOOD_SUGAR_PERIOD, data.getPeriod());
                params.put(KEY_BLOOD_SUGAR, data.getBloodSugar());
                break;
            case DataBean.TYPE_BLE_EAR:
                params.put(KEY_EAR_TEMPERATRUE, data.getEarTemperature());
                break;
            case DataBean.TYPE_BLE_ECG:
                params.put(KEY_ECG_BLOODING, data.getBlooding());
                params.put(KEY_ECG_BLOOD_PULSE, data.getBloodPulse());
                params.put(KEY_ECG_RESULT, data.getEcgResult());
                break;
            case DataBean.TYPE_BLE_OPD:
                params.put(KEY_ECG_BLOODSPO2, data.getBloodSpO2());
                params.put(KEY_ECG_BLOOD_PULSE, data.getBloodPulse());
                break;
        }
        params.put(KEY_UNIX, String.valueOf(DateUtil.getTimeMillis(data.getDate(), "yyyy-MM-dd HH:mm:ss")));
        return sendPost(params);
    }

    /**
     * 绑定设备
     *
     * @param phone      手机号码.
     * @param deviceType 类型
     * @param address    mac地址
     * @return
     */
    public ResponseBean bindDevice(String phone, String deviceType, String address) {
        HashMap<String, String> params = getSystemParams(METHOD_BIND_DEVICE);
        params.put(KEY_PHONE, phone);
        params.put(KEY_DEVICE_TYPE, deviceType);
        params.put(KEY_DEVICE_ADDRESS, address);
// TODO 需要提交的设备数据 params.put()
        params.put(KEY_SIGN, sign(METHOD_BIND_DEVICE, TApplication.user.getToken()));
        return sendPost(params);
    }

    /**
     * 解除绑定设备
     *
     * @param phone      手机号码.
     * @param deviceType 类型
     * @param address    mac地址
     * @return
     */
    public ResponseBean unBindDevice(String phone, String deviceType, String address) {
        HashMap<String, String> params = getSystemParams(METHOD_UNBIND_DEVICE);
        params.put(KEY_PHONE, phone);
        params.put(KEY_DEVICE_TYPE, deviceType);
        params.put(KEY_DEVICE_ADDRESS, address);
// TODO 需要提交的设备数据 params.put()
        params.put(KEY_SIGN, sign(METHOD_UNBIND_DEVICE, TApplication.user.getToken()));
        return sendPost(params);
    }

    /**
     * 解除绑定设备
     *
     * @param phone      手机号码.
     * @param hid     亲友hid
     * @return
     */
    public ResponseBean deleteParent(String phone, String hid) {
        HashMap<String, String> params = getSystemParams(METHOD_DELETE_PARENT);
        params.put(KEY_PHONE, phone);
        params.put(KEY_PARENT_HID, hid);
// TODO 需要提交的设备数据 params.put()
        params.put(KEY_SIGN, sign(METHOD_DELETE_PARENT, TApplication.user.getToken()));
        return sendPost(params);
    }


    /**
     * 提交用户图片
     *
     * @param phone 手机号码.
     * @return 提交用户图片的响应信息.
     */
    public ResponseBean submitUserPicture(String phone, String picture, HashMap<String, File> files) {
        HashMap<String, String> params = getSystemParams(METHOD_SUBMIT_USER_PICTURE);
        params.put(KEY_PHONE, phone);
        params.put(KEY_AVATAR, picture);
// TODO 需要提交的设备数据 params.put()
        params.put(KEY_SIGN, sign(METHOD_SUBMIT_USER_PICTURE, TApplication.user.getToken()));
        return uploapFile(params, files);
    }


    /**
     * 提交用户图片
     *
     * @param phone 手机号码.
     * @return 提交用户图片的响应信息.
     */
    public ResponseBean healthAssess(String phone, String deviceType, String device_id) {
        HashMap<String, String> params = getSystemParams(METHOD_HEALTH_ASSESS);
        params.put(KEY_PHONE, phone);
        params.put(KEY_DEVICE_TYPE, deviceType);
        params.put(KEY_DEVICE_ID, device_id);
// TODO 需要提交的设备数据 params.put()
        params.put(KEY_SIGN, sign(METHOD_HEALTH_ASSESS, TApplication.user.getToken()));
        ResponseBean response = sendPost(params);
        if (response.isSuccess()) {
            // TODO 可能因json中缺少某个参数儿抛出异常，中断后续参数的解析儿导致某些参数为空，需要进行处理。
            switch (deviceType) {
                case DataBean.TYPE_BLOOD_PRESSURE_MONITOR + "":
                    HealthAssessBean user = null;
                    try {
                        user = (HealthAssessBean) BaseBean.parsingJsonObject(new JSONObject(response.getData().toString()), HealthAssessBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    response.setData(user);
                    break;
                case DataBean.TYPE_BLE_EAR + "":
                    HealthAssessEarBean earBean = null;
                    try {
                        earBean = (HealthAssessEarBean) BaseBean.parsingJsonObject(new JSONObject(response.getData().toString()), HealthAssessEarBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    response.setData(earBean);
                    break;
                case DataBean.TYPE_BLE_OPD + "":
                    HealthAssessPodBean podBean = null;
                    try {
                        podBean = (HealthAssessPodBean) BaseBean.parsingJsonObject(new JSONObject(response.getData().toString()), HealthAssessPodBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    response.setData(podBean);
                    break;
                case DataBean.TYPE_BLE_ECG + "":
                    HealthAssessECGBean ecgBean = null;
                    try {
                        ecgBean = (HealthAssessECGBean) BaseBean.parsingJsonObject(new JSONObject(response.getData().toString()), HealthAssessECGBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    response.setData(ecgBean);
                    break;
                case DataBean.TYPE_GLUCOSE_METER + "":
                    HealthAssessSugerBean sugerBean = null;
                    try {
                        sugerBean = (HealthAssessSugerBean) BaseBean.parsingJsonObject(new JSONObject(response.getData().toString()), HealthAssessSugerBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    response.setData(sugerBean);
                    break;
            }
        }
        return response;
    }


    /**
     * 版本更新
     *
     * @param code 版本号
     * @return
     */
    public ResponseBean checkVerion(String code) {
        HashMap<String, String> params = getSystemParams(METHOD_CHECK_VERION);
        params.put(KEY_VERION_CODE, code);
        params.put(KEY_SIGN, sign(METHOD_CHECK_VERION));
        ResponseBean response = sendPost(params);
        if (response.isSuccess()) {
            // TODO 可能因json中缺少某个参数儿抛出异常，中断后续参数的解析儿导致某些参数为空，需要进行处理。
            VersionBean versionBean = null;
            try {
                versionBean = (VersionBean) BaseBean.parsingJsonObject(new JSONObject(response.getData().toString()), VersionBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            response.setData(versionBean);
        }
        return response;
    }

    /**
     * 提交用户反馈
     *
     * @param phone   手机号码.
     * @param content 反馈内容.
     * @return 提交用户测量数据的响应信息.
     */
    public ResponseBean submitUserAdvice(String phone, String content) {
        HashMap<String, String> params = getSystemParams(METHOD_SUBMIT_USER_ADVICE);
        params.put(KEY_PHONE, phone);
        params.put(KEY_ADVICE_CONTENT, content);
// TODO 需要提交的设备数据 params.put()
        params.put(KEY_SIGN, sign(METHOD_SUBMIT_USER_ADVICE, TApplication.user.getToken()));
        return sendPost(params);
    }

    /**
     * 提交用户资料
     *
     * @param phone 手机号码.
     * @param bean  反馈内容.
     * @return 提交用户测量数据的响应信息.
     */
    public ResponseBean submitUserInfo(String phone, UserBean bean) {
        HashMap<String, String> params = getSystemParams(METHOD_SUBMIT_USER_INFO);
        params.put(KEY_PHONE, phone);
        params.put(KEY_NAME,bean.getName());
        params.put(KEY_NICKNAME,bean.getNickname());
        params.put(KEY_SEX,bean.getSex()+"");
        params.put(KEY_NATION,bean.getNation());
        params.put(KEY_BIRTHADY,bean.getBirthday());
        params.put(KEY_EDUCATION,bean.getEducation()+"");
        params.put(KEY_MARRY,bean.getMarry()+"");
        params.put(KEY_PROFESSION,bean.getProfession());
        params.put(KEY_CERTIFICATETYPE,bean.getCertificateType()+"");
        params.put(KEY_CERTIFICATENUMBER,bean.getCertificateNumber());
        params.put(KEY_SSNUMBER,bean.getSsNumber());
        params.put(KEY_ADDRESS,bean.getAddress());
        params.put(KEY_SIGN, sign(METHOD_SUBMIT_USER_INFO, TApplication.user.getToken()));
        return sendPost(params);
    }

    /**
     * 获取用户设备数据列表.
     *
     * @param phone 手机号码.
     * @param type  设备类型.
     * @return 返回请求用户设备数据列表信息.
     */
    public ResponseBean getUserDeviceDataList(String phone, String type) {
        HashMap<String, String> params = getSystemParams(METHOD_GET_USER_DEVICE_DATA_LIST);
        params.put(KEY_PHONE, phone);
//        params.put(KEY_PAGE, "1");
//        params.put(KEY_COUNT, KEY_ROWS);
        params.put(KEY_DEVICE_TYPE, type);
        params.put(KEY_SIGN, sign(METHOD_GET_USER_DEVICE_DATA_LIST, TApplication.user.getToken()));
        ResponseBean response = sendPost(params);
        if (response.isSuccess()) {
            // TODO 可能因json中缺少某个参数儿抛出异常，中断后续参数的解析儿导致某些参数为空，需要进行处理。
            ArrayList<DataBean> list = null;
            try {
                if (StringUtil.isEmpty(response.getData().toString())) {
                    list = new ArrayList<>();
                    response.setData(list);
                    return response;
                }

                list = (ArrayList<DataBean>) BaseBean.parsingJsonArray(new JSONArray(response.getData().toString()), DataBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            response.setData(list);
        }
        return response;
    }

    /**
     * 获取亲友列表.
     *
     * @return 返回亲友列表信息.
     */
    public ResponseBean getParentList(String mobile,String page) {
        HashMap<String, String> params = getSystemParams(METHOD_GET_PARENT);
        params.put(KEY_PHONE, mobile);
        params.put(KEY_PAGE,page);
        params.put(KEY_COUNT,KEY_ROWS);
        params.put(KEY_SIGN, sign(METHOD_GET_PARENT, TApplication.user.getToken()));
        ResponseBean response = sendPost(params);
        LogUtil.logDebugMessage(response.toString());
        if (response.isSuccess()) {
            ArrayList<ParentBean> list = null;
            try {
                if (StringUtil.isEmpty(response.getData().toString())) {
                    list = new ArrayList<>();
                    response.setData(list);
                    return response;
                }
                // TODO 可能因json中缺少某个参数儿抛出异常，中断后续参数的解析儿导致某些参数为空，需要进行处理。
                list = (ArrayList<ParentBean>) BaseBean.parsingJsonArray(new JSONArray(response.getData().toString()), ParentBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            response.setData(list);
        }
        return response;
    }

    /**
     * 获取亲友列表.
     *
     * @return 返回亲友列表信息.
     */
    public ResponseBean getEduList(String mobile) {
        HashMap<String, String> params = getSystemParams(METHOD_EDUCATION_LIST);
        params.put(KEY_PHONE, mobile);
        params.put(KEY_SIGN, sign(METHOD_EDUCATION_LIST));
        ResponseBean response = sendPost(params);
        LogUtil.logDebugMessage(response.toString());
        if (response.isSuccess()) {
            ArrayList<ParentBean> list = null;
            try {
                if (StringUtil.isEmpty(response.getData().toString())) {
                    list = new ArrayList<>();
                    response.setData(list);
                    return response;
                }
                // TODO 可能因json中缺少某个参数儿抛出异常，中断后续参数的解析儿导致某些参数为空，需要进行处理。
                list = (ArrayList<ParentBean>) BaseBean.parsingJsonArray(new JSONArray(response.getData().toString()), ParentBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            response.setData(list);
        }
        return response;
    }

    /**
     * 提交用户亲友
     *
     * @param phone       手机号码.
     * @param name        姓名.
     * @param parentPhone 亲友号码
     * @return
     */
    public ResponseBean addParent(String phone, String name, String parentPhone) {
        HashMap<String, String> params = getSystemParams(METHOD_ADD_PARENT);
        params.put(KEY_PHONE, phone);
        params.put(KEY_PARENT_NAME, name);
        params.put(KEY_PARENT_PHONE, parentPhone);
// TODO 需要提交的设备数据 params.put()
        params.put(KEY_SIGN, sign(METHOD_ADD_PARENT, TApplication.user.getToken()));
        return sendPost(params);
    }

    /**
     * 键：账号.
     */
    private final String KEY_ACCOUNT = "username";
    /**
     * 键：密码.
     */
    private final String KEY_PASSWORD = "password";
    /**
     * 键：手机号码.
     */
    private final String KEY_PHONE = "mobile";
    /**
     * 键：验证码.
     */
    private final String KEY_CODE = "vCode";
    /**
     * 键：设置密码秘钥.
     */
    private final String KEY_KEY = "vkey";
    /**
     * 键：高压.
     */
    private final String KEY_HIGHPRESSURE = "highPressure";
    /**
     * 键：低压.
     */
    private final String KEY_LOW_PRESSURE = "lowPressure";
    /**
     * 键：心率.
     */
    private final String KEY_HEART_RATE = "heartRate";
    /**
     * 键：血糖.
     */
    private final String KEY_BLOOD_SUGAR = "bloodSugar";
    /**
     * 键：时间.
     */
    private final String KEY_BLOOD_SUGAR_PERIOD = "period";

    /**
     * 键：体温
     */
    private final String KEY_EAR_TEMPERATRUE = "earTemperature";

    /**
     * 键：心电波纹
     */
    private final String KEY_ECG_BLOODING = "blooding";
    /**
     * 键：血氧
     */
    private final String KEY_ECG_BLOODSPO2 = "bloodSpO2";
    /**
     * 键：心率
     */
    private final String KEY_ECG_BLOOD_PULSE = "bloodPulse";
    /**
     * 键：心电结果
     */
    private final String KEY_ECG_RESULT = "ecgResult";
    /**
     * 键：时间戳.
     */
    private final String KEY_UNIX = "unixTime";
    /**
     * 键：设备ID.
     */
    private final String KEY_DEVICE_ID = "device_id";
    /**
     * 键：设备类型.
     */
    private final String KEY_DEVICE_TYPE = "devicetype";
    /**
     * 登录接口.
     */
    private final String METHOD_LOGIN = "user.login";
    /**
     * 获取验证码接口.
     */
    private final String METHOD_GET_CODE = "user.getVCode";


    /**
     * 获取忘记密码验证码接口.
     */
    private final String METHOD_GET_FORGET_CODE = "user.forgetpw.code.get";
    /**
     * 提交手机验证码接口.
     */
    private final String METHOD_SUBMIT_CODE = "user.verifyMoblie";
    /**
     * 提交忘记密码手机验证码接口.
     */
    private final String METHOD_FORGET_SUBMIT_CODE = "user.forgetpw.code.verify";
    /**
     * 设置密码接口.
     */
    private final String METHOD_SET_PASSWORD = "user.register";
    /**
     * 设置忘记密码密码接口.
     */
    private final String METHOD_FORGETSET_PASSWORD = "user.forgetpw.password.reset";
    /**
     * 获取用户设备列表接口.
     */
    private final String METHOD_GET_USER_DEVICE_LIST = "user.device.list";
    /**
     * 获取用户设备的测量数据列表接口..
     */
    private final String METHOD_GET_USER_DEVICE_DATA_LIST = "user.device.data.list.get";
    /**
     * 提交用户测量数据接口.
     */
    private final String METHOD_SUBMIT_USER_MEASUREMENT_DATA = "user.device.data.save";
    /**
     * 图片上传
     */
    private final String METHOD_SUBMIT_USER_PICTURE = "user.avatar.set";

    /**
     * 健康评估
     */
    private final String METHOD_HEALTH_ASSESS = "user.device.data.report";

    /**
     * 版本更新
     */
    private final String METHOD_CHECK_VERION = "app.version.check";

    /**
     * 提交用户反馈接口
     */
    private final String METHOD_GET_PARENT = "user.friend.list";

    /**
     * 提交用户反馈接口
     */
    private final String METHOD_SUBMIT_USER_ADVICE = "user.suggest.submit";
    /**
     * 提交用户资料接口
     */
    private final String METHOD_SUBMIT_USER_INFO = "user.profile.edit";
    /**
     * 图片
     */
    private final String KEY_AVATAR = "avatar";

    /**
     * 键：mac address
     */
    private final String KEY_DEVICE_ADDRESS = "address";

    /**
     * 绑定设备
     */
    private final String METHOD_BIND_DEVICE = "user.device.bound";

    /**
     * 解除绑定设备
     */
    private final String METHOD_UNBIND_DEVICE = "user.device.unbound";
    /**
     * 删除好友
     */
    private final String METHOD_DELETE_PARENT = "user.friend.del";

    /**
     * 添加亲友
     */
    private final String METHOD_ADD_PARENT = "user.friend.add";

    /**
     * 键：版本
     */
    private final String KEY_VERION_CODE = "code";

    /**
     * 键：反馈内容.
     */
    private final String KEY_ADVICE_CONTENT = "content";

    /**
     * 键：姓名
     */
    private final String KEY_PARENT_NAME = "name";

    /**
     * 键：亲友号码
     */
    private final String KEY_PARENT_PHONE = "friendsMobile";


    /**
     * 键：hid
     */
    private final String KEY_PARENT_HID = "hid";


    /**
     * 键：获取教育程度列表
     */
    private final String METHOD_EDUCATION_LIST = "system.base.certificateType.list";

    private final String KEY_NAME = "name";
    private final String KEY_NICKNAME = "nickname";
    private final String KEY_USERNAME = "username";
    private final String KEY_BIRTHADY = "birthday";
    private final String KEY_CERTIFICATENUMBER = "certificateNumber";
    private final String KEY_NATION = "nation";
    private final String KEY_EDUCATION = "education";
    private final String KEY_MARRY = "marry";
    private final String KEY_PROFESSION = "profession";
    private final String KEY_CERTIFICATETYPE = "certificateType";
    private final String KEY_SSNUMBER = "ssNumber";
    private final String KEY_ADDRESS = "address";
    private final String KEY_SEX = "sex";
}
