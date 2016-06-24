package com.tentinet.healthy.widget.stemdownloader;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.tentinet.healthy.interf.TApplication;

public class DownLoadBroadcastReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        long myDwonloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        long tmpId = 0;
        long refernece = TApplication.sp.get("downloadId",tmpId);

        if (refernece == myDwonloadID) {

            String serviceString = Context.DOWNLOAD_SERVICE;

            DownloadManager dManager = (DownloadManager) context.getSystemService(serviceString);

            Intent install = new Intent(Intent.ACTION_VIEW);

            Uri downloadFileUri = dManager.getUriForDownloadedFile(myDwonloadID);

            install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");

            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(install);
        }

    }
}