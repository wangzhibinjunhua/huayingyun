package com.tentinet.healthy.util;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.tentinet.healthy.interf.OnImageLoadedListener;

/**
 * 类描述:图片加载工具类
 *
 * @author 王治粮
 * @date 2016/3/9,16:28
 * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class UniversalImageLoader {

    /**
     * String imageUri = "http://site.com/image.png"; // 网络图片
     * String imageUri = "file:///mnt/sdcard/image.png"; //SD卡图片
     * String imageUri = "content://media/external/audio/albumart/13"; // 媒体文件夹
     * String imageUri = "assets://image.png"; // assets
     * String imageUri = "drawable://" + R.drawable.image; //  drawable文件
     **/


    /**
     * 加载图片，获取位图对象
     *
     * @param imageUrl 图片路径
     * @param position 图片位置
     * @param callBack 图片加载回调
     * @version 1.0
     * @createTime 2016/3/15  16:50
     * @updateTime 2016/3/15  16:50
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public void disPlayImage(String imageUrl, int position, ImageCallback callBack) {
        Bitmap bitmap = ImageLoader.getInstance().loadImageSync(imageUrl);
        if (null != bitmap && null != callBack) {
            callBack.imageLoaded(bitmap, imageUrl, position);
        }
    }

    /**
     * 加载图片
     *
     * @version 1.0
     * @createTime 2016/3/9  17:39
     * @updateTime 2016/3/9  17:39
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public void disPlayImage(ImageView imageView, String imageUrl, DisplayImageOptions options, final OnImageLoadedListener listener) {
        ImageLoader.getInstance().displayImage(imageUrl, imageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUrl, View view) {
            }

            @Override
            public void onLoadingFailed(String imageUrl, View view, FailReason failReason) {
                if (null != listener) {
                    listener.loadFail((ImageView) view);
                }
            }

            @Override
            public void onLoadingComplete(String imageUrl, View view, Bitmap bitmap) {
                Bitmap temp = null;
                if (null == bitmap && null != listener) {
                    listener.loadFail((ImageView) view);
                }

                if (null != listener) {
                    temp = listener.processing(bitmap);
                }


                if (null != listener && null != temp) {
                    listener.loadComplete((ImageView) view, temp);
                } else if (null != listener) {
                    listener.loadComplete((ImageView) view, bitmap);
                }

            }

            @Override
            public void onLoadingCancelled(String imageUrl, View view) {
            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String s, View view, int currSize, int imageSize) {
            }
        });
    }


    /**
     * 获取图片的配置
     *
     * @version 1.0
     * @createTime 2016/3/9  17:03
     * @updateTime 2016/3/9  17:03
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public DisplayImageOptions getSimpleOptions(int defaultimg,int radius, boolean isMemory, boolean isDisk) {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultimg)//加载图片时的图片
                .showImageForEmptyUri(defaultimg)//没有图片资源时的默认图片
                .showImageOnFail(defaultimg)//加载失败时的图片
                .cacheInMemory(isMemory)//启用内存缓存
                .cacheOnDisk(isDisk)//启动外存缓存
                .imageScaleType(ImageScaleType.EXACTLY)//设置图片的显示方式
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(radius))
                .build();
        return options;
    }


    /**
     * 加载图片回调接口
     *
     * @version 1.0
     * @createTime 2016/3/15  16:43
     * @updateTime 2016/3/15  16:43
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public interface ImageCallback {

        /**
         * 图片加载完成之后回调该接口
         *
         * @param position  图片在list的位置
         * @param bitmap    图片位图对象 ，null加载失败，否则返回得到的位图对象
         * @param imagePath 图片路径
         * @version 1.0
         * @createTime 2016/3/15  16:44
         * @updateTime 2016/3/15  16:44
         * @createAuthor 王治粮
         * @updateAuthor 王治粮
         * @updateInfo
         */
        public void imageLoaded(Bitmap bitmap, String imagePath, int position);

    }

}
