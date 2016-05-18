package com.tentinet.healthy.util;

import android.os.Handler;
import android.os.Message;

/**
 * handler封装工具类
 * 
 * @author 罗文忠
 * @date 2013-03-23
 * 
 * @updaateAuthor wujainxing
 * @updaateInfo 添加了取消操作的检测
 * @date 2014-03-17
 */
public class HandlerUtil {

	/**
	 * 发送消息.
	 * 
	 * @param handler
	 *            异步处理对象.
	 * @param what
	 *            消息.
	 * @author 罗文忠
	 * @version 1.0, 2013-3-23
	 */
	public static void sendMessage(Handler handler, int what) {
		Message message = handler.obtainMessage();
		message.what = what;
		handler.sendMessage(message);
	}

	/**
	 * 发送消息.
	 * 
	 * @param handler
	 *            异步处理对象.
	 * @param what
	 *            消息.
	 * @param object
	 *            传递对象.
	 * @author 罗文忠
	 * @version 1.0, 2013-3-23
	 */
	public static void sendMessage(Handler handler, int what, Object object) {
		Message message = handler.obtainMessage();
		message.what = what;
		message.obj = object;
		handler.sendMessage(message);
	}

	/**
	 * 发送消息
	 * 
	 * @version 1.0
	 * @createTime 2014年2月21日 下午3:27:38
	 * @updateTime 2014年2月21日 下午3:27:38
	 * @createAuthor liuyue
	 * @updateAuthor liuyue
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param handler
	 * @param what
	 * @param arg1
	 * @param object
	 */
	public static void sendMessage(Handler handler, int what, int arg1, Object object) {
		Message message = handler.obtainMessage();
		message.what = what;
		message.obj = object;
		message.arg1 = arg1;
		handler.sendMessage(message);
	}

	/**
	 * 发送消息.
	 * 
	 * @param handler
	 *            异步处理对象.
	 * @param what
	 *            消息.
	 * @param arg1
	 *            消息.
	 * @param arg2
	 *            消息.
	 * @author 罗文忠
	 * @version 1.0, 2013-3-23
	 */
	public static void sendMessage(Handler handler, int what, int arg1, int arg2) {
		Message message = handler.obtainMessage();
		message.what = what;
		message.arg1 = arg1;
		message.arg2 = arg2;
		handler.sendMessage(message);
	}

	/**
	 * 发送消息.
	 * 
	 * @param handler
	 *            异步处理对象.
	 * @param what
	 *            消息.
	 * @param arg1
	 *            消息.
	 * @param arg2
	 *            消息.
	 * @param object
	 *            传递对象.
	 * @author 罗文忠
	 * @version 1.0, 2013-3-23
	 */
	public static void sendMessage(Handler handler, int what, int arg1, int arg2, Object object) {
		Message message = handler.obtainMessage();
		message.what = what;
		message.arg1 = arg1;
		message.arg2 = arg2;
		message.obj = object;
		handler.sendMessage(message);
	}

	/**
	 * 发送全局消息
	 * 
	 * @version 1.0
	 * @createTime 2013-8-15,下午3:05:54
	 * @updateTime 2013-8-15,下午3:05:54
	 * @createAuthor 罗文忠
	 * @updateAuthor 罗文忠
	 * @updateInfo
	 * 
	 * @param what
	 */
	public static void sendEmptyMessage(int what) {
		Handler handler = new Handler();
		handler.sendEmptyMessage(what);
	}

}
