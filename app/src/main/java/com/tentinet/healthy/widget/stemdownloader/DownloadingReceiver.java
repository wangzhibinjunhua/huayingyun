package com.tentinet.healthy.widget.stemdownloader;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;
import com.tentinet.healthy.interf.TApplication;

/**
 * 系统下载器下载文件的接收器
 * Created by 馋猫 on 2015/5/21.
 */
public class DownloadingReceiver extends BroadcastReceiver {
    private DownloadManager downloadManager;
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                // 判断这个id与之前的id是否相等，如果相等说明是之前的那个要下载的文件
        if (completeDownloadId == TApplication.sp.get(Downloader.KEY_NAME_DOWNLOAD_ID,0)){
            if (action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) { //下载完成
                Toast.makeText(context, "下载完成", Toast.LENGTH_SHORT).show();
                complete(context, completeDownloadId);
            } else if (action.equals(DownloadManager.ACTION_NOTIFICATION_CLICKED)) { //通知栏被点击
                    Toast.makeText(context, "通知栏被点击了", Toast.LENGTH_SHORT).show();//下载完成
            }
        }
    }

    private void complete(Context context, long completeDownloadId) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(completeDownloadId);
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Cursor cursor = downloadManager.query(query);
        int columnCount = cursor.getColumnCount();
        String path = null;
        // 这里把所有的列都打印一下，有什么需求，就怎么处理,文件的本地路径就是path
        while (cursor.moveToNext()) {
            for (int j = 0; j < columnCount; j++) {
                String columnName = cursor.getColumnName(j);
                String string = cursor.getString(j);
                if (columnName.equals("local_uri")) {
                    path = string;
                }
                if (string != null) {
                    Log.e("馋猫", columnName + ": " + string);
                } else {
                    Log.e("馋猫", columnName + ": null");
                }
            }
        }
        cursor.close();
        //如果sdcard不可用时下载下来的文件，那么这里将是一个内容提供者的路径，这里打印出来，有什么需求就怎么样处理
        assert path != null;
        if ("content:".startsWith(path)) {
            cursor = context.getContentResolver().query(Uri.parse(path), null, null, null, null);
            columnCount = cursor.getColumnCount();
            while (cursor.moveToNext()) {
                for (int j = 0; j < columnCount; j++) {
                    String columnName = cursor.getColumnName(j);
                    String string = cursor.getString(j);
                    if (string != null) {
                        Log.e("馋猫", columnName + ": " + string);
                    } else {
                        Log.e("馋猫", columnName + ": null");
                    }
                }
            }
            cursor.close();
        }
        installSystem(context, path);
    }

    private void installSystem(Context context, String path) {
        Intent intent1 = new Intent();
        intent1.setAction(Intent.ACTION_VIEW);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.e("馋猫", path);
        intent1.setDataAndType(Uri.parse(path), "application/vnd.android.package-archive");
        context.startActivity(intent1);
    }
}
