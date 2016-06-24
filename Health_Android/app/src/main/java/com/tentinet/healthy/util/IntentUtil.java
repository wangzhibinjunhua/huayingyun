package com.tentinet.healthy.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.tentinet.healthy.R;


/**
 * 跳转工具类.
 *
 * @author paladin
 * @Description
 * @date 2014年6月18日
 * @Copyright: Copyright (c) 2014 Shenzhen Tentinet Technology Co., Ltd. Inc.
 * All rights reserved.
 */
public class IntentUtil {


    /**
     * 跳转至指定activity.
     *
     * @param context   上下文环境.
     * @param gotoClass 跳转至界面.
     * @version 1.0
     * @createTime 2013-9-23,下午2:49:59
     * @updateTime 2013-9-23,下午2:49:59
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void gotoActivity(Context context, Class<?> gotoClass) {
        Intent intent = new Intent();
        intent.setClass(context, gotoClass);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 携带传递数据跳转至指定activity.
     *
     * @param context   上下文环境.
     * @param gotoClass 跳转至界面.
     * @param bundle    携带数据.
     * @version 1.0
     * @createTime 2013-9-23,下午2:51:00
     * @updateTime 2013-9-23,下午2:51:00
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void gotoActivity(Context context, Class<?> gotoClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, gotoClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 跳转至指定activity,并关闭当前activity.
     *
     * @param context   上下文环境.
     * @param gotoClass 跳转至界面.
     * @version 1.0
     * @createTime 2013-9-23,下午2:49:59
     * @updateTime 2013-9-23,下午2:49:59
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void gotoActivityAndFinish(Context context, Class<?> gotoClass) {
        Intent intent = new Intent();
        intent.setClass(context, gotoClass);
        context.startActivity(intent);
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 携带传递数据跳转至指定activity,并关闭当前activity.
     *
     * @param context   上下文环境.
     * @param gotoClass 跳转至界面.
     * @param bundle    携带数据.
     * @version 1.0
     * @createTime 2013-9-23,下午2:49:59
     * @updateTime 2013-9-23,下午2:49:59
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void gotoActivityAndFinish(Context context, Class<?> gotoClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, gotoClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 携带传递数据跳转至指定activity,并关闭当前activity.
     *
     * @param context   上下文环境.
     * @param gotoClass 跳转至界面.
     * @version 1.0
     * @createTime 2013-9-23,下午2:49:59
     * @updateTime 2013-9-23,下午2:49:59
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void gotoActivityToTop(Context context, Class<?> gotoClass) {
        Intent intent = new Intent();
        intent.setClass(context, gotoClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 携带传递数据跳转至指定activity,并关闭当前activity.
     *
     * @param context   上下文环境.
     * @param gotoClass 跳转至界面.
     * @param bundle    携带数据.
     * @version 1.0
     * @createTime 2013-9-23,下午2:49:59
     * @updateTime 2013-9-23,下午2:49:59
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void gotoActivityToTop(Context context, Class<?> gotoClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, gotoClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 单例模式跳转至指定activity
     *
     * @param context   上下文环境.
     * @param gotoClass 跳转至界面.
     * @param context   上下文
     * @param gotoClass 目标activity
     * @version 1.0
     * @createTime 2013-10-14,下午5:00:02
     * @updateTime 2013-10-14,下午5:00:02
     * @createAuthor 罗文忠
     * @updateAuthor 罗文忠
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static void gotoActivityToTopAndFinish(Context context, Class<?> gotoClass) {
        Intent intent = new Intent();
        intent.setClass(context, gotoClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 单例模式跳转并携带数据
     *
     * @param context
     * @param gotoClass
     * @param bundle
     * @version 1.0
     * @createTime 2014年1月7日, 下午6:38:18
     * @updateTime 2014年1月7日, 下午6:38:18
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static void gotoActivityToTopAndFinish(Context context, Class<?> gotoClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(context, gotoClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        ((Activity) context).finish();
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 跳转至指定activity.
     *
     * @param context     上下文环境.
     * @param gotoClass   跳转至界面.
     * @param requestCode 请求码.
     * @version 1.0
     * @createTime 2013-10-15,上午11:28:23
     * @updateTime 2013-10-15,上午11:28:23
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void gotoActivityForResult(Context context, Class<?> gotoClass, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, gotoClass);
        ((Activity) context).startActivityForResult(intent, requestCode);
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 携带传递数据跳转至指定activity.
     *
     * @param context     上下文环境.
     * @param gotoClass   跳转至界面.
     * @param bundle      携带数据.
     * @param requestCode 请求码.
     * @version 1.0
     * @createTime 2013-10-15,上午11:28:23
     * @updateTime 2013-10-15,上午11:28:23
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void gotoActivityForResult(Context context, Class<?> gotoClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, gotoClass);
        intent.putExtras(bundle);
        ((Activity) context).startActivityForResult(intent, requestCode);
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 携带传递数据跳转至指定activity.
     *
     * @param context     上下文环境.
     * @param gotoClass   跳转至界面.
     * @param bundle      携带数据.
     * @param requestCode 请求码.
     * @version 1.0
     * @createTime 2015年7月23日, 上午10:11:07
     * @updateTime 2015年7月23日, 上午10:11:07
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public static void gotoActivityToTopForResult(Context context, Class<?> gotoClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(context, gotoClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtras(bundle);
        ((Activity) context).startActivityForResult(intent, requestCode);
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

    /**
     * 跳转到发送短信界面
     *
     * @param context
     * @param phoneNum
     * @param content
     * @version 1.0
     * @createTime 2013-12-19,上午10:12:04
     * @updateTime 2013-12-19,上午10:12:04
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static void gotoSendMsmActivity(Context context, String phoneNum, String content) {
        Uri uri = Uri.parse("smsto:" + phoneNum);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", content);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(R.anim.enter_exit, R.anim.enter_enter);
    }

}
