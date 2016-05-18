package com.tentinet.healthy.util;

import android.util.Log;

/**
 * 日志打印类.
 *
 * @author paladin
 * @Description
 * @date 2014年4月29日
 * @Copyright: Copyright (c) 2014 Shenzhen Tentinet Technology Co., Ltd. Inc.
 * All rights reserved.
 */
public class LogUtil {

    /**
     * 打印开关
     */
    private static boolean logSwitch = true;
    /**
     * 打印标记
     */
    private static String printFlag = "paladin =====> ";

    /**
     * 打开打印.
     *
     * @version 1.0
     * @createTime 2014年4月29日, 下午3:46:10
     * @updateTime 2014年4月29日, 下午3:46:10
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void openLog() {
        logSwitch = true;
    }

    /**
     * 关闭打印.
     *
     * @version 1.0
     * @createTime 2014年4月29日, 下午3:46:19
     * @updateTime 2014年4月29日, 下午3:46:19
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void closeLog() {
        logSwitch = false;
    }

    /**
     * 查看打印功能是否打开.
     *
     * @return 若打开返回true, 否则返回false.
     * @version 1.0
     * @createTime 2014年5月4日, 下午5:35:24
     * @updateTime 2014年5月4日, 下午5:35:24
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static boolean isOpenLog() {
        return logSwitch;
    }

    /**
     * 打印调试信息.
     *
     * @param message 调试信息.
     * @version 1.0
     * @createTime 2014年4月29日, 下午3:46:27
     * @updateTime 2014年4月29日, 下午3:46:27
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void logDebugMessage(String message) {
        if (logSwitch) {
            Log.i("debug", printFlag + message);
        }
    }

    /**
     * 打印信息.
     *
     * @param tag     打印标签.
     * @param message 打印信息.
     * @version 1.0
     * @createTime 2014年4月29日, 下午3:46:42
     * @updateTime 2014年4月29日, 下午3:46:42
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void logMessage(String tag, String message) {
        if (logSwitch) {
            Log.i(tag, printFlag + message);
        }
    }

    /**
     * 打印系统信息.
     *
     * @param message 系统信息.
     * @version 1.0
     * @createTime 2014年4月29日, 下午3:47:05
     * @updateTime 2014年4月29日, 下午3:47:05
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void logSystemMessage(String message) {
        if (logSwitch) {
            Log.i("system", printFlag + message);
        }
    }

    /**
     * 打印错误信息.
     *
     * @param error 错误信息.
     * @version 1.0
     * @createTime 2014年4月29日, 下午3:47:17
     * @updateTime 2014年4月29日, 下午3:47:17
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void logErrorMessage(String error) {
        if (logSwitch) {
            Log.i("error", printFlag + error);
            Log.e("error", printFlag + error);
        }
    }

    /**
     * 打印错误信息.
     *
     * @param error 错误信息.
     * @version 1.0
     * @createTime 2014年4月29日, 下午3:47:17
     * @updateTime 2014年4月29日, 下午3:47:17
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void logErrorMessage(String tag, String error, Throwable e) {
        if (logSwitch) {
            Log.e(tag, printFlag + error, e);
        }
    }

}
