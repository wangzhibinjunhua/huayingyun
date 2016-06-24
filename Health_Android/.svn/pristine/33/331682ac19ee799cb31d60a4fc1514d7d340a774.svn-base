package com.tentinet.healthy.widget.stemdownloader;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.tentinet.healthy.R;
import com.tentinet.healthy.interf.TApplication;
import com.tentinet.healthy.util.SharedPreferencesUtil;
import com.tentinet.healthy.util.ToastUtil;


/**
 * 下载帮助类
 * Created by 馋猫 on 2015/5/21.
 */
public class Downloader {
    public static final String KEY_NAME_DOWNLOAD_ID = "downloadId";  //正在下载的sharedpreference的存储ID
    private Context context;
    private String downUrl;
    private String title = "OEM";
    private String path = "download";
    private String fileName = "OEM_USER.apk";
    private String description = "";

    public Downloader(Context context, String downUrl) {
        this.context = context;
        this.downUrl = downUrl;
        this.title = context.getString(R.string.app_name);
        this.fileName = context.getString(R.string.app_name);
    }

    public Downloader(Context context, String downUrl, String title, String path, String fileName, String description) {
        this.context = context;
        this.downUrl = downUrl;
        this.title = title;
        this.path = path;
        this.description = description;
        this.fileName = fileName;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void start() {
        if (downUrl != null && !downUrl.equals("")) {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri uri = Uri.parse(downUrl);
            DownloadManager.Request request = new DownloadManager.Request(uri);
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);//网络类型
            request.setAllowedOverRoaming(true);   //移动网络是否允许漫游下载
            request.setTitle(title);//下载标题
            request.setDescription(description);//下载描述
            request.setDestinationInExternalPublicDir(path, fileName);
            request.setVisibleInDownloadsUi(true);//是否显示当前下载 在系统的下载界面上
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);//通知栏提示模式 下载中和下载完成都提醒
            request.setMimeType("application/vnd.android.package-archive");//打开模式
            // 设置为可被媒体扫描器找到
            request.allowScanningByMediaScanner();
            // 设置为可见和可管理
            request.setVisibleInDownloadsUi(true);
            long id = downloadManager.enqueue(request);
            TApplication.sp.set(KEY_NAME_DOWNLOAD_ID, id);//将下载ID进行存储
        } else {
            ToastUtil.showLongToast(context, context.getString(R.string.updata_lianjie));
        }
    }

}
