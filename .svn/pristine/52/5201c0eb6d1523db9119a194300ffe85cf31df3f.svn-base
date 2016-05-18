package com.tentinet.healthy.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * <h3>Description</h3>
 * TODO
 * <h3>Author</h3> （你的姓名）
 * <h3>Date</h3> 2016/3/28 16:08
 * <h3>Copyright</h3> Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class ImageLoaderUtil {
    /**
     * 构建基本DisplayImageOptions.Builder
     *
     * @param loadingImg 加载中的图片
     * @param emptyImg   为空的图片
     * @param failImg    失败的图片
     * @return
     */
    public static DisplayImageOptions.Builder getOptionBuilder(int loadingImg, int emptyImg, int failImg) {
        DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder()
                .showImageOnLoading(loadingImg) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(emptyImg)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(failImg)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
                        //.decodingOptions(BitmapFactory.Options decodingOptions)//设置图片的解码配置
                .delayBeforeLoading(0)//int delayInMillis为你设置的下载前的延迟时间
                        //设置图片加入缓存前，对bitmap进行设置
                        //.preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new SimpleBitmapDisplayer())//不推荐用！！！！是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100));//是否图片加载好后渐入的动画时间，可能会出现闪动
        return builder;
    }

    /**
     * 获取圆角option
     *
     * @param loadingImg
     * @param emptyImg
     * @param failImg
     * @return
     */
    public static DisplayImageOptions getRoundOption(int loadingImg, int emptyImg, int failImg) {
        return getOptionBuilder(loadingImg, emptyImg, failImg).displayer(new RoundedBitmapDisplayer(20)).build();
    }

    /**
     * 获取默认option
     *
     * @param loadingImg
     * @param emptyImg
     * @param failImg
     * @return
     */
    public static DisplayImageOptions getDefaultOption(int loadingImg, int emptyImg, int failImg) {
        return getOptionBuilder(loadingImg, emptyImg, failImg).build();
    }
}
