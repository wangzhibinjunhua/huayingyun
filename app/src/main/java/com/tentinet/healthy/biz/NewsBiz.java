package com.tentinet.healthy.biz;

import com.tentinet.healthy.bean.BaseBean;
import com.tentinet.healthy.bean.NewsBean;
import com.tentinet.healthy.bean.ResponseBean;
import com.tentinet.healthy.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 资讯业务逻辑.
 *
 * @author paladin.
 */
public class NewsBiz extends BaseBiz {

    /**
     * 获取资讯列表.
     *
     * @param page 资讯信息当前页.
     * @return 返回资讯列表信息.
     */
    public ResponseBean getNewsList(String page) {
        HashMap<String, String> params = getSystemParams(METHOD_GET_NEWS_LIST);
        params.put(KEY_PAGE, page);
        params.put(KEY_COUNT, "1");
        params.put(KEY_SIGN, sign(METHOD_GET_NEWS_LIST));

        ResponseBean response = sendPost(params);
        LogUtil.logDebugMessage(response.toString());
        if (response.isSuccess()) {
            ArrayList<NewsBean> list = null;
            try {
                // TODO 可能因json中缺少某个参数儿抛出异常，中断后续参数的解析儿导致某些参数为空，需要进行处理。
                list = (ArrayList<NewsBean>) BaseBean.parsingJsonArray(new JSONArray(response.getData().toString()), NewsBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            response.setData(list);
        }
        return response;
    }

    /**
     * 获取资讯详情.
     *
     * @param id 资讯id.
     * @return
     */
    public ResponseBean getNewsInfo(String id) {
        HashMap<String, String> params = getSystemParams(METHOD_GET_NEWS_INFO);
        params.put(KEY_NEWS_ID, id);
        params.put(KEY_SIGN, sign(METHOD_GET_NEWS_INFO));

        ResponseBean response = sendPost(params);
        if (response.isSuccess()) {
            try {
                // TODO 可能因json中缺少某个参数儿抛出异常，中断后续参数的解析儿导致某些参数为空，需要进行处理。
                BaseBean.parsingJsonObject(new JSONObject(response.getData().toString()), NewsBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 获取资讯列表方法.
     */
    private final String METHOD_GET_NEWS_LIST = "news.list.get";
    /**
     * 获取资讯详情方法.
     */
    private final String METHOD_GET_NEWS_INFO = "";
    /**
     * 键：资讯id.
     */
    private final String KEY_NEWS_ID = "";

    private final String KEY_COUNT="count";
}
