package com.tentinet.healthy.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.tentinet.healthy.R;
import com.tentinet.healthy.interf.OnImageLoadedListener;

/**
 * 类描述:
 *
 * @author 王治粮
 * @date 2016/3/10,13:38
 * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class AsyncImageSetter {
    /**
     * 图片加载工具
     */
    private static UniversalImageLoader imageLoader;

    /**
     * 设置头像
     *
     * @param imageView 图片控件
     * @param imageUrl  图片路径
     * @version 1.0
     * @createTime 2016/3/10  13:55
     * @updateTime 2016/3/10  13:55
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public static void setPortrait(ImageView imageView, String imageUrl) {
        loadImages(imageView, imageUrl, null, R.mipmap.def_portrait, 10000);
    }

    /**
     * 设置图片
     *
     * @param imageView 图片控件
     * @param imageUrl  图片路径
     * @version 1.0
     * @createTime 2016/3/10  13:46
     * @updateTime 2016/3/10  13:46
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public static void setImage(ImageView imageView, String imageUrl) {
        loadImages(imageView, imageUrl, null, R.mipmap.def_picture, 0);
    }

    /**
     * 设置图片
     *
     * @param imageView 图片控件
     * @param imageUrl  图片路径
     * @param scaleType 图片显示类型
     * @version 1.0
     * @createTime 2016/3/10  13:46
     * @updateTime 2016/3/10  13:46
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public static void setImage(ImageView imageView, String imageUrl, ImageView.ScaleType scaleType) {
        loadImages(imageView, imageUrl, scaleType, R.mipmap.def_picture, 0);
    }


    /**
     * 设置本地媒体库图片
     *
     * @param imageView 图片控件
     * @param imageUrl  图片路径
     * @version 1.0
     * @createTime 2016/3/15  15:51
     * @updateTime 2016/3/15  15:51
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public static void setMediaImages(final ImageView imageView, String imageUrl) {
        if (null == imageLoader) {
            imageLoader = new UniversalImageLoader();
        }


        imageLoader.disPlayImage(imageView, "file://" + imageUrl, imageLoader.getSimpleOptions(R.mipmap.def_picture,0, false, false), new OnImageLoadedListener() {
            @Override
            public void loadFail(ImageView imgView) {
            }

            @Override
            public Bitmap processing(Bitmap bitmap) {
                return null;
            }

            @Override
            public void loadComplete(ImageView imgView, Bitmap bitmap) {
                if (null != imgView && null != bitmap) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
    }

    /**
     * 加载图片
     *
     * @param imageView 图片加载控件
     * @param imageUrl  图片路径
     * @param scaleType 图片显示类型
     * @param radius    圆角弧度
     * @version 1.0
     * @createTime 2016/3/10  13:47
     * @updateTime 2016/3/10  13:47
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    private static void loadImages(final ImageView imageView, String imageUrl, final ImageView.ScaleType scaleType, int defaultimg, final int radius) {
        if (null == imageLoader) {
            imageLoader = new UniversalImageLoader();
        }

        if (null != scaleType) {
            imageView.setScaleType(scaleType);
        }
        imageLoader.disPlayImage(imageView, imageUrl, imageLoader.getSimpleOptions(defaultimg,radius, true, true), new OnImageLoadedListener() {
            @Override
            public void loadFail(ImageView imgView) {
            }

            @Override
            public Bitmap processing(Bitmap bitmap) {
                if (null == bitmap) {
                    return null;
                }
                if (radius > 1000) {
                    return ImageUtil.toRound(bitmap, ImageUtil.TO_ROUND);
                } else if (radius > 0) {
                    return ImageUtil.toRound(bitmap, radius);
                }
                return null;
            }

            @Override
            public void loadComplete(ImageView imgView, Bitmap bitmap) {
                if (null != imgView && null != bitmap) {
                    imgView.setImageBitmap(bitmap);
                }
            }
        });
    }


}
