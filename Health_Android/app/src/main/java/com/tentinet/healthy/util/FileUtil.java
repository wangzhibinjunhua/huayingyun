package com.tentinet.healthy.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.tentinet.healthy.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FileUtil {

    private static final String TAG = FileUtil.class.getSimpleName();

    private static String APP_FILE = "healthy";
    private static final String LOG_FILE = "log";

    private static final String IMAGE_FILE = "image";

    private static String appPath;

    public static void initFile(Context mcontext) {

        APP_FILE = mcontext.getString(R.string.app_name);

        appPath = getAppPath(mcontext);
        Log.d(TAG, "getCurrentLogPath logPath = " + getCurrentLogPath());
        if (!isFileExists(appPath)) {
            createDir(appPath);
        }
    }

    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        return (null == sdDir) ? null : sdDir.toString();
    }

    public static String getAppPath(Context mcontext) {
        String fileDir = mcontext.getFilesDir().getAbsolutePath();
        if (fileDir.lastIndexOf("/") > 0) {
            fileDir = fileDir.substring(0, fileDir.lastIndexOf("/"));
        }
        return fileDir;
    }


    /**
     * 获取图片缓存路径
     *
     * @return 缓存路径File
     */
    public static File getImageCacheFile() {
        String path = null;
        if (isSDExists()) {
            path = getSDPath();
        } else {
            path = appPath;
        }
        if (null == path) {
            return null;
        }
        String logPath = path + File.separator + APP_FILE + File.separator
                + IMAGE_FILE;
        if (isFileExists(logPath)) {
            createDir(logPath);
        }

        return new File(logPath);
    }

    /**
     * 获取内部存储缓存目录
     *
     * @param mContext 上下文对象
     * @return 缓存目录
     */
    public static String getCacheDir(Context mContext) {
        //File file =  mContext.getCacheDir();
        Log.e(TAG, "PackageName = " + mContext.getExternalCacheDir());

        return mContext.getExternalCacheDir().getAbsolutePath();
//		return "/data/data/" + "com.snscity.member" + "/cache";
    }

    public static boolean isSDExists() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    public static String getCurrentLogPath() {
        String path = null;
        if (isSDExists()) {
            path = getSDPath();
        } else {
            path = appPath;
        }
        if (null == path) {
            return null;
        }
        String logPath = path + File.separator + APP_FILE + File.separator
                + LOG_FILE;
        if (isFileExists(logPath)) {
            createDir(logPath);
        }
        return logPath;
    }

    public static boolean isFileExists(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        if (null == file || !file.exists()) {
            return false;
        }
        return true;
    }

    public static File createDir(String fullDir) {
        File file = new File(fullDir + File.separator);
        if (!file.exists()) {
            boolean isSucceed = file.mkdirs();
            if (!isSucceed) {
                Log.e("FileUtils:", "creat  dir failed " + fullDir);
            }
        }
        return file;
    }

    /**
     * 创建文件
     *
     * @param filePath
     */
    public static void createFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        File file = new File(filePath);
        if (null == file || file.exists()) {
            return;
        }
        file.mkdir();
    }

    /**
     * 删除文件
     *
     * @param filePath
     */
    public static void deleteFile(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        File file = new File(filePath);
        if (null == file || !file.exists()) {
            return;
        }
        file.delete();
    }

    public static void deleteFile(File file) {
        try {
            if (null == file || !file.exists()) {
                return;
            }
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断文件名是否是yyyyMMdd的日期格式
     *
     * @param filename
     */
    public static boolean isFileNamedWithDate(String filename) {
        Pattern pattern = Pattern.compile("\\d{4}\\-{1}\\d{2}\\-{1}\\d{2}");
        Matcher matcher = pattern.matcher(filename);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 日志文件超过24小时删除
     *
     * @param filePath
     */
    public static void deleteFileByDate(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isDirectory()) {
            return;
        }
//        String currentTime = TimeUtil.getTime();
//        File[] files = file.listFiles();
//        if (null == files) {
//            return;
//        }
//        for (File f : files) {
//            if (null == f) {
//                continue;
//            }
//            if (isFileNamedWithDate(f.getName())) {
//                String fileName = f.getName();
//                fileName = fileName.substring(0, fileName.lastIndexOf("."));
//                if (TimeUtil.compareDate(fileName, currentTime)) {
//                    deleteFile(f);
//                }
//            }
//        }
    }

    /**
     * 将字符保存在指定文件中
     *
     * @param filePath
     * @param buffer
     */
    public static void saveToFile(String filePath, StringBuffer buffer) {
        if (null == filePath || null == buffer || buffer.length() == 0) {
            return;
        }
        File crashFile = new File(filePath);
        boolean isSucceed = false;
        if (!crashFile.exists()) {
            File parentDir = new File(crashFile.getParent());
            if (!parentDir.exists()) {
                isSucceed = parentDir.mkdirs();
                if (!isSucceed) {
                    Log.e(TAG, "saveCrashInfo2File mkdirs failed");
                }
            }
            try {
                isSucceed = crashFile.createNewFile();
                if (!isSucceed) {
                    Log.e(TAG, "saveCrashInfo2File createNewFile failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OutputStreamWriter out = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            out = new OutputStreamWriter(fos, "UTF-8");
            out.write(buffer.toString());
            out.flush();
        } catch (IOException e) {
            Log.e("readfile", e.getMessage());
            if (crashFile.exists()) {
                deleteFile(crashFile);
            }
        } finally {
            try {
                if (null != fos) {
                    fos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception exto) {
                Log.e("readfile", exto.getMessage());
            }
        }
    }
}
