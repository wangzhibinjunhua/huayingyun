package com.tentinet.healthy.exception;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.util.Log;

import com.tentinet.healthy.activity.LoadActivity;
import com.tentinet.healthy.util.FileUtil;
import com.tentinet.healthy.util.LogUtil;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/5/11 15:49
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class AppException extends Exception implements UncaughtExceptionHandler {
    public static final String TAG = AppException.class.getSimpleName();

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1;

    /**
     * 上下文参数
     */
    private Context context;

    /**
     * crash日志保存文件名
     */
    private String crashLogFileName = "crashReport.txt";

    /**
     * 搜集的系统设备信息
     */
    private Map<String, String> infos = new HashMap<String, String>();
    /**
     * 异常消息.
     */
    private String msg = null;

    private UncaughtExceptionHandler mDefaultHandler;

    private static AppException instance = null;

    /**
     * 获取APP异常崩溃处理对象
     *
     * @return
     */
    public static AppException getInstance() {
        if (instance == null) {
            instance = new AppException();
        }
        return instance;
    }

    public AppException() {

    }

    public void init(Context context) {
        this.context = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 用一个消息构造异常类.
     *
     * @param message 异常的消息
     */
    public AppException(String message) {
        super(message);
        msg = message;
    }

    /**
     * 描述：获取异常信息.
     *
     * @return the message
     */
    @Override
    public String getMessage() {
        return msg;
    }

    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        LogUtil.logMessage(TAG, "uncaughtException crash = " + ex.getMessage());
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            ex.printStackTrace();
            Intent intent = new Intent(context, LoadActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex Throwable
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return true;
        }
        // 使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                // Toast 显示需要出现在一个线程的消息队列中
                Looper.prepare();
//				ToastUtils.showToast(mContext, mContext.getString(R.string.app_crashed));
                Looper.loop();
            }
        }.start();

        // 收集设备信息
        saveCrashInfo2File(context, ex);
        return true;
    }

    public void saveCrashInfo2File(Context ctx, Throwable ex) {
        collectPackageInfo(ctx);
        collectDeviceInfo(ctx);

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : this.infos.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        Writer writer = new StringWriter();

        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);

        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();

        String result = writer.toString();

        sb.append(result);
        try {
            String crashFileDir = FileUtil.getCurrentLogPath()
                    + File.separator + this.crashLogFileName;

            FileUtil.deleteFile(crashFileDir);

            FileUtil.saveToFile(crashFileDir, sb);
        } catch (Exception e) {
            LogUtil.logErrorMessage(TAG, "an error occured while writing file...", e);
        }
    }

    private void collectPackageInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), 1);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;

                String versionCode = pi.versionCode + "";
                this.infos.put("versionName", versionName);
                this.infos.put("versionCode", versionCode);
                SimpleDateFormat formatter = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());
                String str = formatter.format(curDate);
                this.infos.put("CrashTime", str);
            }
        } catch (Exception e) {
            LogUtil.logErrorMessage(TAG, "an error occured when collect package info", e);
        }
    }

    private void collectDeviceInfo(Context ctx) {
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                this.infos.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                LogUtil.logErrorMessage(TAG, "an error occured when collect device info", e);
            }
        }
    }
}
