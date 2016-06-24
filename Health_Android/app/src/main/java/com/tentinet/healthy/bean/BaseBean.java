package com.tentinet.healthy.bean;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 数据类型基类.
 *
 * @author paladin.
 */
public abstract class BaseBean {

    /**
     * 将json数据放置对应的数据模型.
     *
     * @param json json数据.
     * @param cls  数据模型.
     * @return 相应的数据模型.
     */
    public static BaseBean parsingJsonObject(JSONObject json, Class<? extends BaseBean> cls) {
        BaseBean bean = null;
        try {
            bean = cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (null != bean) {
            bean.setParams(json);
        }
        return bean;
    }

    /**
     * 将json数据放置对应的数据模型列表.
     *
     * @param json json数据.
     * @param cls  数据模型.
     * @return 相应的数据模型列表.
     */
    public static ArrayList<? extends BaseBean> parsingJsonArray(JSONArray json, Class<? extends BaseBean> cls) {
        ArrayList<BaseBean> list = new ArrayList<BaseBean>();
        for (int i = 0; i < json.length(); i++) {
            BaseBean bean = null;
            try {
                bean = parsingJsonObject(json.getJSONObject(i), cls);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (null != bean) {
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * 设置数据映射关系.
     *
     * @param json json数据.
     */
    protected abstract void setParams(JSONObject json);

}
