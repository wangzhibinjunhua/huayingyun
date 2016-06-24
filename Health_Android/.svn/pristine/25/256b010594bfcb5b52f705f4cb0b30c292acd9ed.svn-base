package com.tentinet.healthy.util;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Json操作类.
 * 
 * @Description 提供Json操作的相关方法.
 * @author paladin
 * @date 2013-5-29
 */
public class JsonUtil {

	/**
	 * 解析JsonObject.
	 * 
	 * @param json
	 *            需要解析的json信息.
	 * @param title
	 *            需要解析的键值.
	 * @return 解析完成的信息.
	 * @throws JSONException
	 *             Json解析发生错误时抛出异常.
	 * 
	 * @version 1.1
	 * @createTime 2013-3-11,下午4:18:28
	 * @updateTime 2014-9-11,下午4:59:34
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public HashMap<String, String> resolvingJsonObject(String json, String[] title) throws JSONException {
		HashMap<String, String> map = new HashMap<String, String>();
		if (!StringUtil.isEmpty(json) && (null != title && title.length > 0)) {
			JSONObject jsonObject = new JSONObject(json);
			for (int i = 0; i < title.length; i++) {
				map.put(title[i], jsonObject.getString(title[i]));
			}
		}
		return map;
	}

	/**
	 * 解析JsonObject.
	 * 
	 * @param json
	 *            需要解析的json信息.
	 * @return 解析完成的信息.
	 * @throws JSONException
	 *             Json解析发生错误时抛出异常.
	 * 
	 * @version 1.1
	 * @createTime 2013-3-11,下午4:23:33
	 * @updateTime 2014-9-11,下午4:59:34
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, String> resolvingJsonObject(String json) throws JSONException {
		HashMap<String, String> map = new HashMap<String, String>();
		if (!StringUtil.isEmpty(json)) {
			JSONObject jsonObject = new JSONObject(json);
			Iterator<String> it = jsonObject.keys();
			while (it.hasNext()) {
				String key = it.next().toString();
				map.put(key, jsonObject.getString(key));
			}
		}
		return map;
	}

	/**
	 * 解析JsonArray.
	 * 
	 * @param json
	 *            需要解析的json信息.
	 * @param title
	 *            需要解析的键值.
	 * @return 解析完成的信息.
	 * @throws JSONException
	 *             Json解析发生错误时抛出异常.
	 * 
	 * @version 1.1
	 * @createTime 2013-3-11,下午4:56:06
	 * @updateTime 2014-9-11,下午4:59:34
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public ArrayList<HashMap<String, String>> resolvingJsonArray(String json, String[] title) throws JSONException {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (!StringUtil.isEmpty(json) && (null != title && title.length > 0)) {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				list.add(resolvingJsonObject(jsonArray.getString(i), title));
			}
		}
		return list;
	}

	/**
	 * 解析JsonArray.
	 * 
	 * @param json
	 *            需要解析的json信息.
	 * @return 解析完成的信息.
	 * @throws JSONException
	 *             Json解析发生错误时抛出异常.
	 * 
	 * @version 1.1
	 * @createTime 2013-3-11,下午4:57:55
	 * @updateTime 2014-9-11,下午4:59:34
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public ArrayList<HashMap<String, String>> resolvingJsonArray(String json) throws JSONException {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		if (!StringUtil.isEmpty(json)) {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				list.add(resolvingJsonObject(jsonArray.getString(i)));
			}
		}
		return list;
	}

	/**
	 * 封装JsonObject.
	 * 
	 * @param map
	 *            封装信息.
	 * @return Json数据.
	 * @throws JSONException
	 *             Json封装发生错误时抛出异常.
	 * 
	 * @version 1.0
	 * @createTime 2013-3-11,下午5:01:19
	 * @updateTime 2013-3-11,下午5:01:19
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public String packageJsonObject(HashMap<String, Object> map) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		Set<Entry<String, Object>> set = map.entrySet();
		Iterator<Entry<String, Object>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			jsonObject.put(entry.getKey(), entry.getValue());
		}
		return jsonObject.toString();
	}

	/**
	 * 封装JsonArray.
	 * 
	 * @param list
	 *            封装信息.
	 * @return Json数据.
	 * @throws JSONException
	 *             Json封装发生错误时抛出异常.
	 * 
	 * @version 1.0
	 * @createTime 2013-5-30,下午5:47:17
	 * @updateTime 2013-5-30,下午5:47:17
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public String packageJsonArray(ArrayList<HashMap<String, Object>> list) throws JSONException {
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++)
			jsonArray.put(packageJsonObject(list.get(i)));
		return jsonArray.toString();
	}

}
