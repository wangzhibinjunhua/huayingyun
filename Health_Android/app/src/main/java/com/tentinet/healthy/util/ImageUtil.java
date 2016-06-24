package com.tentinet.healthy.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.tentinet.healthy.interf.TApplication;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片工具.
 *
 * @author paladin
 * @Description
 * @date 2014年5月14日
 * @Copyright: Copyright (c) 2014 Shenzhen Tentinet Technology Co., Ltd. Inc.
 * All rights reserved.
 */
public class ImageUtil {
    /**
     * 圆形
     */
    public static final int TO_ROUND = -1;
    /**
     * 图片的最大尺寸，任意边长大于960，都将进行压缩
     */
    private static int maxSize = 960;
    /**
     * 缩略图的图片的最大尺寸，所有图片都会生成缩略图
     */
    private static final int thumMaxSize = 300;

    /**
     * 小于这个尺寸则不压缩
     */
    private static final int minSize = 100;

    /**
     * 图片压缩比例
     */
    private int compressScale = 50;
    /**
     * 不是本地
     */
    private String suffix = "";

    /**
     * 位图转换为字节.
     *
     * @param format 图片格式.(JPG或PNG)
     * @param bitmap 位图.
     * @return 返回图片的字节数组.
     * @version 1.0
     * @createTime 2014年8月14日, 上午10:33:01
     * @updateTime 2014年8月14日, 上午10:33:01
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public byte[] bitmap2Bytes(CompressFormat format, Bitmap bitmap) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(format, 100, output);
        return output.toByteArray();
    }

    /**
     * 字节转换为图片.
     *
     * @param bt 图片字节数组.
     * @return 返回图片位图.
     * @version 1.0
     * @createTime 2014年8月14日, 上午10:35:10
     * @updateTime 2014年8月14日, 上午10:35:10
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public Bitmap bytes2Bimap(byte[] bt) {
        if (bt.length != 0) {
            return BitmapFactory.decodeByteArray(bt, 0, bt.length);
        } else {
            return null;
        }
    }

    /**
     * drawable转bitmap
     *
     * @version 1.0
     * @createTime 2016/3/10  14:02
     * @updateTime 2016/3/10  14:02
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth(); // 获取drawable长.
        int h = drawable.getIntrinsicHeight(); // 获取drawable宽.
        Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Config.ARGB_8888 : Config.RGB_565; // 取drawable的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(w, h, config); // 建立对应bitmap
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 裁剪图片.
     *
     * @param bitmap   位图.
     * @param toWidth  裁剪宽度.
     * @param toHeight 裁剪高度.
     * @return
     * @version 1.0
     * @createTime 2014年7月10日, 下午5:03:03
     * @updateTime 2014年7月10日, 下午5:03:03
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static Bitmap cutImage(Bitmap bitmap, int toWidth, int toHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        boolean isOriginalWidthBiger = width > toWidth ? true : false;
        boolean isOriginalHeightBiger = height > toHeight ? true : false;
        Rect src = new Rect(isOriginalWidthBiger ? (width - toWidth) / 2 : 0, isOriginalHeightBiger ? (height - toHeight) / 2 : 0, isOriginalWidthBiger ? (width - toWidth) / 2 + toWidth : width,
                isOriginalHeightBiger ? (height - toHeight) / 2 + toHeight : height); // 计算原图片的显示区域矩阵.
        Rect dst = new Rect(0, 0, toWidth, toHeight); // 完成后的图片矩阵.
        return clip(bitmap, src, dst, 0);
    }

    /**
     * 裁剪图片
     *
     * @param bitmap   位图对象
     * @param toWidth  裁剪后宽度
     * @param toHeight 裁剪后高度
     * @return
     * @version 1.0
     * @createTime 2015年7月7日, 下午12:42:44
     * @updateTime 2015年7月7日, 下午12:42:44
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public static Bitmap cutReSizeImage(Bitmap bitmap, int toWidth, int toHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        boolean isOriginalHeightBiger = height > toHeight ? true : false;
        Rect src = new Rect(0, 0, width, isOriginalHeightBiger ? height - (int) (height - toHeight) : height); // 计算原图片的显示区域矩阵.
        Rect dst = new Rect(0, 0, toWidth, toHeight); // 完成后的图片矩阵.
        return clip(bitmap, src, dst, 0);
    }

    /**
     * 裁剪圆角图片/圆形.
     *
     * @param bitmap 位图
     * @param pixels 圆角长度.
     * @return
     * @version 1.0
     * @createTime 2014年7月10日, 下午5:09:28
     * @updateTime 2014年7月10日, 下午5:09:28
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static Bitmap toRound(Bitmap bitmap, int pixels) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        boolean isWidthSmaller = width < height ? true : false;
        int diameter = width < height ? width : height;
        Rect src = new Rect(isWidthSmaller ? 0 : (width - diameter) / 2, isWidthSmaller ? (height - diameter) / 2 : 0, isWidthSmaller ? width : diameter + (width - diameter) / 2,
                isWidthSmaller ? diameter + (height - diameter) / 2 : height); // 计算原图片的显示区域矩阵.
        Rect dst = new Rect(0, 0, diameter, diameter); // 完成后的图片矩阵.
        if (TO_ROUND == pixels) {
            return clip(bitmap, src, dst, diameter / 2);
        } else {
            return clip(bitmap, src, dst, pixels);
        }
    }

    /**
     * 裁剪图片并裁剪圆角/圆形.
     *
     * @param bitmap   位图.
     * @param toWidth  指定宽度.
     * @param toHeight 指定高度.
     * @param pixels   圆角长度.
     * @return
     * @version 1.0
     * @createTime 2014年7月10日, 下午5:23:47
     * @updateTime 2014年7月10日, 下午5:23:47
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static Bitmap cutImageToRound(Bitmap bitmap, int toWidth, int toHeight, int pixels) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        boolean isOriginalWidthBiger = width > toWidth ? true : false;
        boolean isOriginalHeightBiger = height > toHeight ? true : false;
        int diameter = width < height ? width : height;
        int smallWidth = width < toWidth ? width : toWidth;
        int smallHeight = height < toHeight ? height : toHeight;
        int small = smallWidth < smallHeight ? smallWidth : smallHeight;
        Rect src;
        Rect dst;
        if (TO_ROUND == pixels) {
            src = new Rect(small < width ? (width - small) / 2 : 0, small < height ? (height - small) / 2 : 0, small < width ? small + (width - small) / 2 : small, small < height ? small
                    + (height - small) / 2 : small); // 计算原图片的显示区域矩阵.
            dst = new Rect(0, 0, toWidth, toHeight); // 完成后的图片矩阵.
            return clip(bitmap, src, dst, diameter / 2);
        } else {
            src = new Rect(isOriginalWidthBiger ? (width - toWidth) / 2 : 0, isOriginalHeightBiger ? (height - toHeight) / 2 : 0, isOriginalWidthBiger ? (width - toWidth) / 2 + toWidth : width,
                    isOriginalHeightBiger ? (height - toHeight) / 2 + toHeight : height); // 计算原图片的显示区域矩阵.
            dst = new Rect(0, 0, toWidth, toHeight); // 完成后的图片矩阵.
            return clip(bitmap, src, dst, pixels);
        }
    }

    /**
     * 裁剪图片.
     *
     * @param bitmap 位图.
     * @param src    截取图片的矩阵
     * @param dst    显示图片的矩阵
     * @param pixels 圆角长度.
     * @return
     * @version 1.0
     * @createTime 2014年7月10日, 下午4:56:38
     * @updateTime 2014年7月10日, 下午4:56:38
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    private static Bitmap clip(Bitmap bitmap, Rect src, Rect dst, int pixels) {
        Bitmap output = Bitmap.createBitmap(dst.width(), dst.height(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(new RectF(dst), pixels, pixels, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        if (null != bitmap && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
        return output;
    }

    /**
     * 降低图片质量压缩图片.
     *
     * @param bitmap 压缩图片.
     * @param size   压缩质量,单位kb.
     * @return 压缩完成的图片.
     * @author 刘艺谋
     * @version 1.0, 2013-3-7
     */
    public static Bitmap compressImage(Bitmap bitmap, int size) {
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中.
        int flag = baos.toByteArray().length;
        if (flag / 1024 > size) { // 判断如果压缩后图片是否大于size,大于进行压缩.
            int options = (size * 1024 * 100) / flag;
            baos.reset();// 重置baos即清空baos
            bitmap.compress(CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中.
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中.
        Bitmap bm = BitmapFactory.decodeStream(bais, null, null);// 把ByteArrayInputStream数据生成图片.
        bitmap.recycle();
        return bm;
    }

    /**
     * 按指定尺寸缩放图片.
     *
     * @param path   图片路径.
     * @param width  指定长度.
     * @param height 指定高度.
     * @return 处理完成的图片.
     * @author 刘艺谋
     * @version 1.0, 2013-3-7
     */
    public static Bitmap scaleImage(String path, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回bitmap为空
        options.inJustDecodeBounds = false;
        int w = options.outWidth;
        int h = options.outHeight;
        int scale = width / w < height / h ? width / w : height / h;
        options.inSampleSize = scale; // 设置缩放比例
        bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }

//	/**
//	 * 根据固定宽度比缩放图片
//	 *
//	 * @param bitmap
//	 *            位图对象
//	 * @param newWidth
//	 *            图片固定宽度
//	 * @return
//	 * @version 1.0
//	 * @createTime 2015年7月7日,上午9:57:05
//	 * @updateTime 2015年7月7日,上午9:57:05
//	 * @createAuthor 王治粮
//	 * @updateAuthor 王治粮
//	 * @updateInfo
//	 *
//	 */
//	public static Bitmap resizeBitmap(Bitmap bitmap, int newWidth, boolean isClip) {
//		if (null == bitmap) {
//			bitmap = BitmapFactory.decodeResource(Tapplication.context.getResources(), R.drawable.picture_default_640);
//		}
//		// 是否裁剪图片
//		int originWidth = bitmap.getWidth();
//		int originHeight = bitmap.getHeight();
//		float scale = (float) newWidth / originWidth;
//		int newHeight = (int) Math.floor(originHeight * scale);
//		LogUtil.logDebugMessage("resizeBitmap==>>" + (isClip && newHeight > newWidth));
//		if (isClip && newHeight > newWidth) {
//			int side = (int) Math.floor(newWidth / scale);
//			bitmap = cutReSizeImage(bitmap, side, side);
//			newHeight = newWidth;
//		}
//		bitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);
//		return bitmap;
//	}

    /**
     * 按指定尺寸缩放图片.
     *
     * @param size   压缩质量,单位kb.
     * @param width  指定长度.
     * @param height 指定高度.
     * @return 处理完成的图片.
     * @author 刘艺谋
     * @version 1.0, 2013-3-7
     */
    public static Bitmap scaleImage(Bitmap bitmap, int size, int width, int height) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 100, baos);
        int flag = baos.toByteArray().length;
        if (flag / 1024 > size) { // 判断如果图片大于size,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset(); // 重置baos即清空baos
            bitmap.compress(CompressFormat.JPEG, (size * 1024 * 100) / flag, baos); // 计算压缩百分比，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bm = BitmapFactory.decodeStream(bais, null, options);
        options.inJustDecodeBounds = false;
        int w = options.outWidth;
        int h = options.outHeight;
        int scale = width / w < height / h ? width / w : height / h;
        options.inSampleSize = scale;// 设置缩放比例
        bais = new ByteArrayInputStream(baos.toByteArray());
        bm = BitmapFactory.decodeStream(bais, null, options);
        bitmap.recycle();
        return bm;// 压缩好比例大小后再进行质量压缩
    }

    /**
     * 跳转相机获取图片.
     *
     * @param context  上下文环境.
     * @param pathTemp 图片的缓存路径.
     * @version 1.0
     * @createTime 2014年10月29日, 上午9:55:17
     * @updateTime 2014年10月29日, 上午9:55:17
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void getImageForCamera(Context context, String pathTemp) {
//		MemoryCache memoryCache = new MemoryCache();
//        memoryCache.get(pathTemp);
        if (StringUtil.isEmpty(pathTemp)){
            pathTemp="file:///sdcard/health/"+System.currentTimeMillis()+".jpg";
        }
        Uri uri = Uri.fromFile(new File(pathTemp));
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        ((Activity) context).startActivityForResult(intent, REQUEST_IMAGE_FOR_CAMERA);
    }

    /**
     * 跳转相机获取图片.
     *
     * @param context  上下文环境.
     * @param pathTemp 图片的缓存路径.
     * @version 1.0
     * @createTime 2014年10月29日, 上午9:55:17
     * @updateTime 2014年10月29日, 上午9:55:17
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void getImageForCamera(Context context) {
//		MemoryCache memoryCache = new MemoryCache();
//        memoryCache.get(pathTemp);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    //    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        ((Activity) context).startActivityForResult(intent, REQUEST_IMAGE_FOR_CAMERA);
    }

    /**
     * 跳转相册获取图片.
     *
     * @param context 上下文环境.
     * @version 1.0
     * @createTime 2014年10月29日, 上午11:30:47
     * @updateTime 2014年10月29日, 上午11:30:47
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void getImageForAlbum(Context context) {
        // Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
        Intent getImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // getImage.addCategory(Intent.CATEGORY_OPENABLE);
        // getImage.setType("image/*");
        // ((Activity)
        // context).startActivityForResult(Intent.createChooser(getImage,
        // "Select Picture"), REQUEST_IMAGE_FOR_ALBUM);
        ((Activity) context).startActivityForResult(getImage, REQUEST_IMAGE_FOR_ALBUM);

    }

    /**
     * 跳转相册获取视频.
     * <h3>Version</h3> 1.0
     * <h3>CreateTime</h3> 2016/3/28,10:36
     * <h3>UpdateTime</h3> 2016/3/28,10:36
     * <h3>CreateAuthor</h3> （郑梓笙）
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
     */
    public static void getVideoAlbum(Context context) {
        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        innerIntent.setType("video/*");
        Intent wrapperIntent = Intent.createChooser(innerIntent, null);
        ((Activity) context)
                .startActivityForResult(wrapperIntent, REQUEST_VIDEO_FOR_ALBUM);

    }

    /**
     * 调用系统切图.
     *
     * @param data
     * @param path
     * @param side
     * @version 1.0
     * @createTime 2014年10月30日, 上午10:15:42
     * @updateTime 2014年10月30日, 上午10:15:42
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public static void gotoCut(Context context, Uri data, String path, int side) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(data, "image/*"); // data是图库选取文件传回的参数
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", side);
        intent.putExtra("outputY", side);
        // 图片格式
        intent.putExtra("outputFormat", CompressFormat.JPEG.toString());
        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
        ((Activity) context).startActivityForResult(intent, REQUEST_IMAGE_CUT);
    }

    /************* 添加的方法 ****************/
    /**
     * 处理多张图片
     *
     * @param srcPaths
     * @return
     * @version 1.0
     * @createTime 2014年1月17日, 下午7:54:16
     * @updateTime 2014年1月17日, 下午7:54:16
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public String compressImages(String srcPaths) {

        LogUtil.logDebugMessage("srcPaths===>" + srcPaths);
        if (TextUtils.isEmpty(srcPaths)) {
            return "";
        }

        String images[] = srcPaths.split(",");
        String compressImges = "";
        String imageName;
        for (int i = 0; i < images.length; i++) {
            imageName = System.currentTimeMillis() + ".jpg";
            if (i == 0) {
                compressImges += compressImage(images[i], imageName);
            } else {
                compressImges += "," + compressImage(images[i], imageName);
            }
        }

        return compressImges;
    }

    /**
     * 压缩图片
     *
     * @param srcPath       原图路径
     * @param fileName      图片名称
     * @param compressScale 图片压缩比例
     * @return
     * @version 1.0
     * @createTime 2014年6月10日, 下午4:35:01
     * @updateTime 2014年6月10日, 下午4:35:01
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public String compressImage(String srcPath, String fileName, int compressScale) {
        this.compressScale = compressScale;
        return compressImage(srcPath, fileName);
    }

    /**
     * 压缩图片(本地)
     *
     * @param srcPath       原图路径
     * @param fileName      图片名称
     * @param compressScale 图片压缩比例
     * @return
     * @version 1.0
     * @createTime 2014年6月10日, 下午4:35:01
     * @updateTime 2014年6月10日, 下午4:35:01
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public String compressImage(String srcPath, String fileName, int compressScale, String suffix) {
        this.compressScale = compressScale;
        this.suffix = suffix;
        return compressImage(srcPath, fileName);
    }

    /**
     * 压缩图片并保存到相应的文件夹中
     *
     * @param fileName 文件名
     * @param srcPath  源图绝对路径
     * @version 1.0
     * @createTime 2013-11-5,下午3:22:38
     * @updateTime 2013-11-5,下午3:22:38
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public String compressImage(String srcPath, String fileName) {

        if (!TextUtils.isEmpty(suffix)) {
            srcPath = srcPath.replace(suffix, ".jpg");
        }

        Bitmap bitmap = getBitmap(srcPath);

        // 如果图片加载失败，则返回空
        if (bitmap == null) {
            return "";
        }

        // 获取原图的显示方向
        int digree = getImageDigree(srcPath);

        // 旋转图片的显示方向
        Matrix matrix = new Matrix();
        matrix.postRotate(digree);

        // 原始位图的宽高参数
        int srcWidth = bitmap.getWidth();
        int srcHeight = bitmap.getHeight();

        if (srcWidth < minSize && srcHeight < minSize) {
            return srcPath;
        }

        // 压缩图片的尺寸属性对象
        Size newSize = getNewSize(srcWidth, srcHeight);
        // 缩略图的尺寸属性对象
        Size thumSize = getThumSize(srcWidth, srcHeight);

        if (thumSize.height == 0 || thumSize.width == 0) {
            return srcPath;
        }
        // 压缩图的画布区域
        Rect newDst = new Rect(0, 0, newSize.width, newSize.height);
        // 缩略图的画布区域
        Rect thumDst = new Rect(0, 0, thumSize.width, thumSize.height);
        // 原图的裁剪区域
        Rect src = new Rect(0, 0, srcWidth, srcHeight);

        // ******************缩略图对象******************//
        Bitmap thumBitmap;
        if (digree == 90 || digree == 270) {// 垂直显示
            thumBitmap = Bitmap.createBitmap(thumSize.height, thumSize.width, Config.RGB_565);
        } else {// 横向显示
            thumBitmap = Bitmap.createBitmap(thumSize.width, thumSize.height, Config.RGB_565);
        }
        // 绘制缩略图
        Canvas canvas = new Canvas(thumBitmap);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.save();
        // 旋转画布
        rotate(canvas, digree, thumSize);
        canvas.drawBitmap(bitmap, src, thumDst, null);
        canvas.restore();
        // 保存生成的位图
        saveBitmapToFile(TApplication.IMAGE_FILE_PATH, fileName, thumBitmap);
        // 回收位图空间，释放内存
        thumBitmap.recycle();
        System.gc();

        // *****************压缩图对象*******************//
        Bitmap newBitmap;
        if (digree == 90 || digree == 270) {// 垂直显示
            newBitmap = Bitmap.createBitmap(newSize.height, newSize.width, Config.RGB_565);
        } else {// 横向显示
            newBitmap = Bitmap.createBitmap(newSize.width, newSize.height, Config.RGB_565);
        }
        // 绘制压缩图
        canvas = new Canvas(newBitmap);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.save();
        // 旋转画布
        rotate(canvas, digree, newSize);
        canvas.drawBitmap(bitmap, src, newDst, null);
        canvas.restore();
        // 保存生成的位图
        saveBitmapToFile(TApplication.IMAGE_FILE_PATH, fileName, newBitmap);
        // 回收位图空间，释放内存
        newBitmap.recycle();
        bitmap.recycle();
        System.gc();

        if (TextUtils.isEmpty(suffix)) {
            return TApplication.IMAGE_FILE_PATH + fileName;
        } else {
            return (TApplication.IMAGE_FILE_PATH + fileName).replace(".jpg", suffix);
        }
    }

    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth  参考宽度
     * @param reqHeight 参考高度
     * @return
     * @version 1.0
     * @createTime 2014年1月17日, 下午7:49:52
     * @updateTime 2014年1月17日, 下午7:49:52
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 根据路径获得图片并压缩，返回bitmap用于显示
     *
     * @param filePath 图片路径
     * @return
     * @version 1.0
     * @createTime 2014年1月17日, 下午7:50:21
     * @updateTime 2014年1月17日, 下午7:50:21
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static Bitmap loadBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, maxSize, maxSize);
        BitmapFactory.decodeFile(filePath, options);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 旋转画布
     *
     * @param canvas 画布
     * @param digree 旋转角度
     * @param size   画布尺寸
     * @Ps: 画布旋转之后，整个坐标系也会跟随旋转，所以旋转之后的平移要考虑坐标系的变动，不然平移必然是错位的
     * @version 1.0
     * @createTime 2013-12-9,下午3:56:05
     * @updateTime 2013-12-9,下午3:56:05
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    private void rotate(Canvas canvas, int digree, Size size) {

        switch (digree) {
            case 0: // 正横拍
                break;
            case 90:// 正竖拍
                canvas.rotate(90, canvas.getWidth() / 2, canvas.getHeight() / 2);
                canvas.translate(-Math.abs(size.height - size.width) / 2, Math.abs(size.height - size.width) / 2);
                break;
            case 180:// 反横拍
                canvas.rotate(180, canvas.getWidth() / 2, canvas.getHeight() / 2);
                break;
            case 270:// 反竖拍
                canvas.rotate(-90, canvas.getWidth() / 2, canvas.getHeight() / 2);
                canvas.translate(-Math.abs(size.height - size.width) / 2, Math.abs(size.height - size.width) / 2);
                break;

            default:
                break;
        }

    }

    /**
     * 获取压缩图片的尺寸大小属性
     *
     * @param srcWidth  原始图片的宽度
     * @param srcHeight 原始图片的高度
     * @return
     * @version 1.0
     * @createTime 2013-11-5,下午3:05:20
     * @updateTime 2013-11-5,下午3:05:20
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    private Size getNewSize(int srcWidth, int srcHeight) {
        Size newSize = new Size();
        // 判断图片形状
        if (srcWidth >= srcHeight) {
            if (srcWidth >= maxSize) {
                newSize.width = maxSize;
            } else {
                newSize.width = srcWidth;
            }
            newSize.height = newSize.width * srcHeight / srcWidth;
        } else {
            if (srcHeight >= maxSize) {
                newSize.height = maxSize;
            } else {
                newSize.height = srcWidth;
            }
            newSize.width = newSize.height * srcWidth / srcHeight;
        }
        return newSize;
    }

    /**
     * 获取缩略图的尺寸大小属性
     *
     * @param srcWidth  原图的宽度
     * @param srcHeight 原图的高度
     * @return
     * @version 1.0
     * @createTime 2013-11-5,下午3:09:23
     * @updateTime 2013-11-5,下午3:09:23
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    private Size getThumSize(int srcWidth, int srcHeight) {
        Size thumSize = new Size();
        if (srcWidth >= srcHeight) {// 判断图片形状
            if (srcWidth >= thumMaxSize) {
                thumSize.width = thumMaxSize;
            } else {
                thumSize.width = srcWidth;
            }
            thumSize.height = thumSize.width * srcHeight / srcWidth;
        } else {
            if (srcHeight >= thumMaxSize) {
                thumSize.height = thumMaxSize;
            } else {
                thumSize.height = srcWidth;
            }
            thumSize.width = thumSize.height * srcWidth / srcHeight;
        }

        return thumSize;
    }

    /**
     * 保存位图到本地文件
     *
     * @param saveParentPath // 文件保存目录
     * @param fileName       文件名称
     * @param bitmap         位图对象
     * @version 1.0
     * @createTime 2013-11-5,下午2:33:35
     * @updateTime 2013-11-5,下午2:33:35
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void saveBitmapToFile(String saveParentPath, String fileName, Bitmap bitmap) {

        try {
            File saveimg = new File(saveParentPath + fileName);
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(saveimg));
            bitmap.compress(CompressFormat.JPEG, compressScale, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取位图
     *
     * @param path 图片路径
     * @return
     * @version 1.0
     * @createTime 2013-11-6,下午9:01:29
     * @updateTime 2013-11-6,下午9:01:29
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    private Bitmap getBitmap(String path) {

        if (TextUtils.isEmpty(path)) {// 文件路径为空
            return null;
        }

        return loadBitmap(path);

    }

    /**
     * 获取图片的显示方向
     *
     * @param imagePath
     * @return
     * @version 1.0
     * @createTime 2013-11-6,下午9:23:10
     * @updateTime 2013-11-6,下午9:23:10
     * @createAuthor CodeApe
     * @updateAuthor CodeApe
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    private int getImageDigree(String imagePath) {
        int digree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
            exif = null;
        }
        if (exif != null) {
            // 读取图片中相机方向信息
            int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            // 计算旋转角度
            switch (ori) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    digree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    digree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    digree = 270;
                    break;
                default:
                    digree = 0;
                    break;
            }
        }

        return digree;

    }

    /**
     * 尺寸类
     *
     * @author CodeApe
     * @version 1.0
     * @Description TODO
     * @date 2013-11-5
     * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd.
     * Inc. All rights reserved.
     */
    private class Size {
        /**
         * 宽度
         */
        private int width;
        /**
         * 高度
         */
        private int height;

        @Override
        public String toString() {
            return "Size [width=" + width + ", height=" + height + "]";
        }
    }

    /**
     * 水平方向模糊度
     */
    private static float hRadius = 10;
    /**
     * 竖直方向模糊度
     */
    private static float vRadius = 10;
    /**
     * 模糊迭代度
     */
    private static int iterations = 6;

    /**
     * 图片高斯模糊处理
     *
     * @param bmp     位图对象
     * @param context 上下文对象
     * @return
     * @version 1.0
     * @createTime 2014年8月16日, 上午9:34:49
     * @updateTime 2014年8月16日, 上午9:34:49
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public static Bitmap BlurImages(Bitmap bmp, Context context) {

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] inPixels = new int[width * height];
        int[] outPixels = new int[width * height];
        Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        bmp.getPixels(inPixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < iterations; i++) {
            blur(inPixels, outPixels, width, height, hRadius);
            blur(outPixels, inPixels, height, width, vRadius);
        }
        blurFractional(inPixels, outPixels, width, height, hRadius);
        blurFractional(outPixels, inPixels, height, width, vRadius);
        bitmap.setPixels(inPixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    /**
     * 图片高斯模糊算法
     *
     * @param in
     * @param out
     * @param width
     * @param height
     * @param radius
     * @version 1.0
     * @createTime 2014年8月16日, 上午9:27:47
     * @updateTime 2014年8月16日, 上午9:27:47
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public static void blur(int[] in, int[] out, int width, int height, float radius) {
        int widthMinus1 = width - 1;
        int r = (int) radius;
        int tableSize = 2 * r + 1;
        int divide[] = new int[256 * tableSize];

        for (int i = 0; i < 256 * tableSize; i++)
            divide[i] = i / tableSize;

        int inIndex = 0;

        for (int y = 0; y < height; y++) {
            int outIndex = y;
            int ta = 0, tr = 0, tg = 0, tb = 0;

            for (int i = -r; i <= r; i++) {
                int rgb = in[inIndex + clamp(i, 0, width - 1)];
                ta += (rgb >> 24) & 0xff;
                tr += (rgb >> 16) & 0xff;
                tg += (rgb >> 8) & 0xff;
                tb += rgb & 0xff;
            }

            for (int x = 0; x < width; x++) {
                out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16) | (divide[tg] << 8) | divide[tb];

                int i1 = x + r + 1;
                if (i1 > widthMinus1)
                    i1 = widthMinus1;
                int i2 = x - r;
                if (i2 < 0)
                    i2 = 0;
                int rgb1 = in[inIndex + i1];
                int rgb2 = in[inIndex + i2];

                ta += ((rgb1 >> 24) & 0xff) - ((rgb2 >> 24) & 0xff);
                tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000)) >> 16;
                tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00)) >> 8;
                tb += (rgb1 & 0xff) - (rgb2 & 0xff);
                outIndex += height;
            }
            inIndex += width;
        }
    }

    /**
     * 图片高斯模糊算法
     *
     * @param in
     * @param out
     * @param width
     * @param height
     * @param radius
     * @version 1.0
     * @createTime 2014年8月16日, 上午9:37:35
     * @updateTime 2014年8月16日, 上午9:37:35
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public static void blurFractional(int[] in, int[] out, int width, int height, float radius) {
        radius -= (int) radius;
        float f = 1.0f / (1 + 2 * radius);
        int inIndex = 0;

        for (int y = 0; y < height; y++) {
            int outIndex = y;

            out[outIndex] = in[0];
            outIndex += height;
            for (int x = 1; x < width - 1; x++) {
                int i = inIndex + x;
                int rgb1 = in[i - 1];
                int rgb2 = in[i];
                int rgb3 = in[i + 1];

                int a1 = (rgb1 >> 24) & 0xff;
                int r1 = (rgb1 >> 16) & 0xff;
                int g1 = (rgb1 >> 8) & 0xff;
                int b1 = rgb1 & 0xff;
                int a2 = (rgb2 >> 24) & 0xff;
                int r2 = (rgb2 >> 16) & 0xff;
                int g2 = (rgb2 >> 8) & 0xff;
                int b2 = rgb2 & 0xff;
                int a3 = (rgb3 >> 24) & 0xff;
                int r3 = (rgb3 >> 16) & 0xff;
                int g3 = (rgb3 >> 8) & 0xff;
                int b3 = rgb3 & 0xff;
                a1 = a2 + (int) ((a1 + a3) * radius);
                r1 = r2 + (int) ((r1 + r3) * radius);
                g1 = g2 + (int) ((g1 + g3) * radius);
                b1 = b2 + (int) ((b1 + b3) * radius);
                a1 *= f;
                r1 *= f;
                g1 *= f;
                b1 *= f;
                out[outIndex] = (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
                outIndex += height;
            }
            out[outIndex] = in[width - 1];
            inIndex += width;
        }
    }

    public static int clamp(int x, int a, int b) {
        return (x < a) ? a : (x > b) ? b : x;
    }


    /**
     * 从指定文件路径中加载位图
     *
     * @param path
     * @return
     * @version 1.0
     * @createTime 2015年5月25日, 上午10:57:02
     * @updateTime 2015年5月25日, 上午10:57:02
     * @createAuthor 王治粮
     * @updateAuthor 王治粮
     * @updateInfo
     */
    public static Bitmap getBitmapFromPath(String path) {
        Bitmap bitmap = null;
        BitmapFactory.Options bfOptions = new BitmapFactory.Options();
        bfOptions.inDither = false;
        bfOptions.inPurgeable = true;
        bfOptions.inInputShareable = true;
        bfOptions.inTempStorage = new byte[32 * 1024];

        File file = new File(path);
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            bitmap = null;
            e.printStackTrace();
        }
        try {
            if (fs != null)
                bitmap = BitmapFactory.decodeFileDescriptor(fs.getFD(), null, bfOptions);
        } catch (IOException e) {
            bitmap = null;
            e.printStackTrace();
        } finally {
            if (fs != null) {
                try {
                    fs.close();
                } catch (IOException e) {
                    bitmap = null;
                    e.printStackTrace();
                }
            }
        }
        return bitmap;

    }

    /**
     * 拍照获取图片请求码
     */
    public static final int REQUEST_IMAGE_FOR_CAMERA = 11;
    /**
     * 相册获取图片请求码
     */
    public static final int REQUEST_IMAGE_FOR_ALBUM = 12;
    /**
     * 截取图片请求码
     */
    public static final int REQUEST_IMAGE_CUT = 3;
    /**
     * 相册获取视频请求码
     */
    public static final int REQUEST_VIDEO_FOR_ALBUM = 4;
}
