package com.tentinet.healthy.interf;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.tentinet.healthy.bean.UserBean;
import com.tentinet.healthy.exception.AppException;
import com.tentinet.healthy.util.LogUtil;
import com.tentinet.healthy.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paladin.
 */
public class TApplication extends Application {

    /**
     * 全局上下文环境.
     */
    public static Context CONTEXT;
    /**
     * SP读写工具.
     */
    public static SharedPreferencesUtil sp;
    /**
     * 用户信息.
     */
    public static UserBean user;
    /**
     * 文件根目录
     */
    public static final String BASE_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Health/";
    /**
     * 图片文件目录
     */
    public static final String IMAGE_FILE_PATH = BASE_FILE_PATH + "image/";

    /**
     * 保存全部activity,便于管理
     */
    public static List<Activity> activityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT = getApplicationContext();
        sp = new SharedPreferencesUtil(SP_NAME, SharedPreferencesUtil.PRIVATE, CONTEXT);
        user = new UserBean();
        LogUtil.openLog(); // 正式发布请注释次程序语句.
        initImageLoader(CONTEXT);
        AppException appException = AppException.getInstance();
        appException.init(CONTEXT);
    }

    /**
     * SP文件名.
     */
    private final String SP_NAME = "healthy";

    public void initImageLoader(Context context) {
        //缓存文件的目录
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions 内存缓存文件的最大长宽
                .diskCacheExtraOptions(480, 800, null)  // 本地缓存的详细信息(缓存的最大长宽)，最好不要设置这个
                .threadPriority(Thread.NORM_PRIORITY - 2) // default 设置当前线程的优先级
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)  // 内存缓存的最大值
                .memoryCacheSizePercentage(13) // default
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
                .diskCacheFileCount(100)  // 可以缓存的文件数量
                // default为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .imageDownloader(new BaseImageDownloader(context)) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs() // 打印debug log
                .build(); //开始构建

        ImageLoader.getInstance().init(config);
    }

}
