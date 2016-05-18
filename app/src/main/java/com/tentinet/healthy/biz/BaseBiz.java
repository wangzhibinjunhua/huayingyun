package com.tentinet.healthy.biz;

import android.content.Context;

import com.tentinet.healthy.R;
import com.tentinet.healthy.bean.BaseBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.EncryptionUtil;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.util.NetUtil;
import com.tentinet.healthy.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

/**
 * 业务逻辑基类.
 *
 * @author paladin.
 */
public class BaseBiz {

    protected NetUtil net = new NetUtil();

    /**
     * 获取请求系统级参数.
     *
     * @return 系统级参数.
     */
    protected HashMap<String, String> getSystemParams(String method) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(KEY_APP_KEY, APP_KEY);
        params.put(KEY_VERSION, VERSION);
        params.put(KEY_METHOD, method);
        return params;
    }

    /**
     * 签名.
     *
     * @param method 方法地址.
     * @return
     */
    protected String sign(String method) {
        String sign = KEY_APP_KEY + "=" + APP_KEY + "&" + KEY_METHOD + "=" + method + "&" + KEY_VERSION + "=" + VERSION + "&" + KEY_SECRET + "=" + SECRET;
        return EncryptionUtil.md5Encrypt(sign);
    }

    /**
     * 登录签名.
     *
     * @param method 方法地址.
     * @param token  用户token.
     * @return
     */
    protected String sign(String method, String token) {
        String sign = KEY_APP_KEY + "=" + APP_KEY + "&" + KEY_METHOD + "=" + method + "&" + KEY_VERSION + "=" + VERSION + "&" + KEY_SECRET + "=" +
                SECRET + "&" + KEY_TOKEN + "=" + token;
        return EncryptionUtil.md5Encrypt(sign);
    }

    /**
     * post请求.
     *
     * @return 返回服务器响应信息或本地错误信息.
     * @author paladin.
     * @date 2016/3/17 17:50
     */
    protected ResponseBean sendPost(HashMap<String, String> params) {
        LogUtil.logDebugMessage("address = " + SERVER_API + "; params = " + params.toString() + ";");
        ResponseBean response = null;
        String result = null;
        try {
            result = net.sendPost(SERVER_API, TIME_OUT, params);
            response = (ResponseBean) BaseBean.parsingJsonObject(new JSONObject(result), ResponseBean.class);
        } catch (IOException e) {
            if (null == response) {
                response = new ResponseBean();
            }
            response.setStatus(STATUS_IO_EXCEPTION);
            response.setInfo(TApplication.CONTEXT.getString(R.string.error_io_exception));
        } catch (TimeoutException e) {
            if (null == response) {
                response = new ResponseBean();
            }
            response.setStatus(STATUS_TIME_OUT);
            response.setInfo(TApplication.CONTEXT.getString(R.string.error_time_out));
        } catch (JSONException e) {
            if (null == response) {
                response = new ResponseBean();
                response.setStatus(STATUS_IO_EXCEPTION);
                response.setInfo(TApplication.CONTEXT.getString(R.string.error_io_exception));
            }
        }
        return response;
    }

    /**
     * 提示响应信息内容.
     *
     * @param response      服务器响应信息.
     * @param context       上下文环境.
     * @param successIsShow 成功信息是否提示.
     * @return
     */
    public static int processResponse(ResponseBean response, Context context, boolean successIsShow) {
        switch (response.getStatus()) {
            case STATUS_SUCCESS:
                if (successIsShow) {
                    ToastUtil.showShortToast(context, response.getInfo());
                }
                break;
            default:
                ToastUtil.showShortToast(context, response.getInfo());
                break;
        }
        return response.getStatus();
    }


    /**
     * post请求.
     *
     * @return 返回服务器响应信息或本地错误信息.
     * @author paladin.
     * @date 2016/3/17 17:50
     */
    protected ResponseBean uploapFile(HashMap<String, String> params, HashMap<String, File> files) {
        LogUtil.logDebugMessage("address = " + SERVER_API + "; params = " + params.toString() + ";");
        ResponseBean response = null;
        String result = null;
        try {
            result = net.sendMultyPartRequest(SERVER_API, params, files);
            response = (ResponseBean) BaseBean.parsingJsonObject(new JSONObject(result), ResponseBean.class);
        } catch (IOException e) {
            if (null == response) {
                response = new ResponseBean();
            }
            response.setStatus(STATUS_IO_EXCEPTION);
            response.setInfo(TApplication.CONTEXT.getString(R.string.error_io_exception));
        } catch (JSONException e) {
            if (null == response) {
                response = new ResponseBean();
                response.setStatus(STATUS_IO_EXCEPTION);
                response.setInfo(TApplication.CONTEXT.getString(R.string.error_io_exception));
            }
        }
        return response;
    }


    /**
     * 服务器地址(测试).
     */
    public static final String SERVER_PATH = "http://health.tentinet.com/";
    // public static final String SERVER_PATH = "http://10.10.5.61/";
    /**
     * API地址.
     */
    private final String SERVER_API = SERVER_PATH + "Router/Rest";


    private final String KEY_APP_KEY = "app_key";
    private final String KEY_METHOD = "method";
    private final String KEY_VERSION = "v";
    private final String KEY_SECRET = "secret";
    private final String KEY_TOKEN = "token";
    protected final String KEY_SIGN = "sign";
    protected final String KEY_PAGE = "page";
    private final String APP_KEY = "10";
    private final String VERSION = "1.0";
    private final String SECRET = "^^R%$jsdf1231*AKD&602Llsafdasf$#~dfsgsdf93764532O0";

    /**
     * 超时时间.
     */
    private final int TIME_OUT = 10000;
    /**
     * 相应成功状态.
     */
    public final static int STATUS_SUCCESS = 1;
    /**
     * 数据异常.(本地状态)
     */
    private final int STATUS_IO_EXCEPTION = -1;
    /**
     * 响应超时.(本地状态)
     */
    private final int STATUS_TIME_OUT = -2;
}
