package com.tentinet.healthy.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池操作类.
 * 
 * @Description
 * @author paladin
 * @date 2014年4月29日
 * @Copyright: Copyright (c) 2014 Shenzhen Tentinet Technology Co., Ltd. Inc.
 *             All rights reserved.
 */
public class ThreadPoolUtil {

	/** 线程池 */
	private ExecutorService pool;

	/**
	 * 获取单线程的线程池.
	 * 
	 * @version 1.0
	 * @createTime 2014年4月29日,上午11:07:41
	 * @updateTime 2014年4月29日,上午11:07:41
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void creatSingleThreadPool() {
		pool = Executors.newSingleThreadExecutor();
	}

	/**
	 * 获取单线程的线程池.
	 * 
	 * @version 1.0
	 * @createTime 2014年4月29日,上午11:07:41
	 * @updateTime 2014年4月29日,上午11:07:41
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public ExecutorService getSingleThreadPool() {
		if (null == pool) {
			creatSingleThreadPool();
		}
		return pool;
	}

	/**
	 * 获取指定线程的线程池.
	 * 
	 * @version 1.0
	 * @createTime 2014年4月29日,上午11:07:41
	 * @updateTime 2014年4月29日,上午11:07:41
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void getSpecifyThreadPool(int threadNumber) {
		pool = Executors.newFixedThreadPool(threadNumber);
	}

	/**
	 * 获取无限制线程的线程池.
	 * 
	 * @version 1.0
	 * @createTime 2014年4月29日,上午11:09:23
	 * @updateTime 2014年4月29日,上午11:09:23
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public ExecutorService getCachedThreadPool() {
		if (null == pool) {
			pool = Executors.newCachedThreadPool();
		}
		return pool;
	}

	/**
	 * 获取当前类的线程池.
	 * 
	 * @return 返回当前类的线程池.
	 * @version 1.0
	 * @createTime 2014年4月29日,下午3:02:22
	 * @updateTime 2014年4月29日,下午3:02:22
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public ExecutorService getThreadPool() {
		return pool;
	}

	/**
	 * 像线程池里放入执行线程.
	 * 
	 * @param thread
	 *            执行线程.
	 * @version 1.0
	 * @createTime 2014年4月29日,上午11:09:40
	 * @updateTime 2014年4月29日,上午11:09:40
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void executeThread(Thread thread) {
		if (null != pool && null != thread) {
			pool.execute(thread);
		}
	}

	/**
	 * 关闭线程池.
	 * 
	 * @version 1.0
	 * @createTime 2014年4月29日,上午11:07:26
	 * @updateTime 2014年4月29日,上午11:07:26
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void closeThreadPool() {
		if (null != pool) {
			pool.shutdown();
		}
	}

}
