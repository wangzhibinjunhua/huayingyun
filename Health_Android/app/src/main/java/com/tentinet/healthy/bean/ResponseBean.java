package com.tentinet.healthy.bean;

import com.tentinet.healthy.biz.BaseBiz;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 响应请求信息.
 * 
 * @Description
 * @author paladin
 * @date 2014年10月23日
 * @Copyright: Copyright (c) 2014 Shenzhen Tentinet Technology Co., Ltd. All
 *             rights reserved.
 */
public class ResponseBean extends BaseBean {

	/** 响应状态码 */
	private int status;
	/** 响应信息 */
	private String info;
	/** 响应数据 */
	private Object data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int statues) {
		this.status = statues;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 判断请求是否成功.
	 * 
	 * @return ture为请求成功,false为请求是吧.
	 * @version 1.0
	 * @createTime 2014年10月23日,上午10:01:32
	 * @updateTime 2014年10月23日,上午10:01:32
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public boolean isSuccess() {
		if (BaseBiz.STATUS_SUCCESS == status) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 当前已是全部内容.
	 * 
	 * @return
	 * @version 1.0
	 * @createTime 2014年11月5日,下午6:13:38
	 * @updateTime 2014年11月5日,下午6:13:38
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public boolean isAll() {
		if (STATUS_IS_ALL == status) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected void setParams(JSONObject json) {
		try {
			setStatus(json.getInt("status"));
			setInfo(json.getString("info"));
			setData(json.getString("data"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "ResponseBean [status=" + status + ", info=" + info + ", data=" + data + "]";
	}

	/** 已是全部内容 */
	private final int STATUS_IS_ALL = 60039;
	/** 网络异常响应码 */
	public static final int STATUS_NET_ERROR = -1;
	/** 服务器异常响应码 */
	public static final int STATUS_SERVER_ERROR = -2;

}
