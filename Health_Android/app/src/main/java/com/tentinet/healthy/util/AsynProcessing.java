package com.tentinet.healthy.util;

import android.os.Handler;
import android.os.Message;


import java.util.Random;
import java.util.concurrent.ExecutorService;

/**
 * 异步处理类.
 *
 * @author paladin
 * @date 2014/4/29
 * @copyright: Copyright (c) 2014 - 2016 Shenzhen Tentinet Technology Co., Ltd. Inc.
 * All rights reserved.
 */
public abstract class AsynProcessing {

    /**
     * 消息ID
     */
    private int what_message;
    /**
     * 异步任务线程
     */
    private Thread thread;
    /**
     * 是否中断线程
     */
    private boolean isInterrupt = false;
    /**
     * 是否开启.
     */
    private boolean isStar = false;

    /**
     * 初始化.
     */
    private void init() {
        what_message = new Random().nextInt(100000);
        before(); // 异步任务前准备.
        thread = initThread(); // 初始化异步线程.
    }

    /**
     * 开始执行异步.
     *
     * @author paladin.
     * @date 2016/3/17 18:15
     */
    public void starAsyn() {
        init();
        thread.start();
    }

    /**
     * 将异步任务放入线程池工具队列中等待执行.
     *
     * @param pool 线程池工具对象.
     * @author paladin.
     * @date 2016/3/17 18:15
     */
    public void starAsyn(ThreadPoolUtil pool) {
        init();
        pool.executeThread(thread);
    }

    /**
     * 将异步任务放入线程池队列中等待执行.
     *
     * @param pool 线程池工具对象.
     * @author paladin.
     * @date 2016/3/17 18:15
     */
    public void starAsyn(ExecutorService pool) {
        init();
        pool.submit(thread);
    }

    /**
     * 初始化异步任务.
     *
     * @return 异步任务线程.
     * @version 1.0
     * @createTime 2014年4月29日, 下午3:21:14
     * @updateTime 2014年4月29日, 下午3:21:14
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    private Thread initThread() {
        return new Thread(new Runnable() { // 线程执行异步任务.

            @Override
            public void run() {
                if (isInterrupt) { // 判断线程是否中断.
                    return;
                }
                Message message = handler.obtainMessage();
                message.what = what_message; // 设置异步消息ID.
                message.obj = AsynTask(); // 异步任务.
                if (isInterrupt) { // 判断线程是否中断.
                    return;
                } else {
                    handler.sendMessage(message); // 异步任务完成后发送通知.
                }
            }
        });
    }

    /**
     * 终端当前线程.
     *
     * @version 1.0
     * @createTime 2014年4月29日, 下午3:14:48
     * @updateTime 2014年4月29日, 下午3:14:48
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public void stopThread() {
        isInterrupt = true; // 中断线程开关.
        if (null != thread) {
            thread.interrupt(); // 终端线程.
        }

    }

    /**
     * 是否已经终端当前异步任务.
     *
     * @return
     * @version 1.0
     * @createTime 2015年1月9日, 下午6:23:40
     * @updateTime 2015年1月9日, 下午6:23:40
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public boolean isStop() {
        return isInterrupt;
    }

    /**
     * 异步任务准备.
     *
     * @version 1.0
     * @createTime 2014年4月29日, 上午10:47:11
     * @updateTime 2014年4月29日, 上午10:47:11
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    protected abstract void before();

    /**
     * 异步任务.
     *
     * @return 异步任务结果, 需要传递的参数.
     * @version 1.0
     * @createTime 2014年4月29日, 上午10:47:20
     * @updateTime 2014年4月29日, 上午10:47:20
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    protected abstract Object AsynTask();

    /**
     * 收到异步任务结束通知后的处理.
     *
     * @param obj 异步任务结果.
     * @version 1.0
     * @createTime 2014年4月29日, 上午10:47:45
     * @updateTime 2014年4月29日, 上午10:47:45
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    protected abstract void after(Object obj);

    /**
     * 异步任务处理对象
     */
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (what_message == msg.what) {
                if (!isInterrupt) {
                    after(msg.obj);
//                    if (null != thread) {
//                        thread.
//                    }
                }
            }
        }

    };

}
