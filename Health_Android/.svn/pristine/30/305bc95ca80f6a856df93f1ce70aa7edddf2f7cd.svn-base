package com.tentinet.healthy.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;

/**
 * 文字转矩阵工具
 * 
 * @Description TODO
 * @author CodeApe
 * @version 1.0
 * @date 2014年3月24日
 * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc.
 *             All rights reserved.
 * 
 */
public class Text2MatrixUtils {

	/** 图片(矩阵)宽度 */
	private int width = 96;
	/** 图片(矩阵)高度 */
	private int height = 16;

	/** 用于位运算的字节数组，分别代表0-7bit的0值，下标8为全1 */
	private byte[] bitwise = { (byte) 0xFE, (byte) 0xFD, (byte) 0xFB, (byte) 0xF7, (byte) 0xEF, (byte) 0xDF, (byte) 0xBF, (byte) 0x7F,(byte)0xFF };

	/** 生成的位图 */
	private Bitmap bitmap;

	public Text2MatrixUtils() {

	}

	/**
	 * 有参构造函数
	 * 
	 * @version 1.0
	 * @createTime 2014年3月24日,下午8:19:27
	 * @updateTime 2014年3月24日,下午8:19:27
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param width
	 *            图片(矩阵)宽度
	 * @param height
	 *            图片(矩阵)高度
	 */
	public Text2MatrixUtils(int width, int height) {
		this.height = height;
		this.width = width;
	}

	/**
	 *根据文本生成bitmap
	 * 
	 * @version 1.0
	 * @createTime 2014年3月25日,上午12:16:22
	 * @updateTime 2014年3月25日,上午12:16:22
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param text 需要绘制的文本
	 * @return 返回绘制的黑白文本对象
	 */
	public Bitmap makeBitmap(String text) {

		bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

		Canvas canvas = new Canvas(bitmap);
		canvas.drawColor(Color.WHITE);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(false);// 抗锯齿
		paint.setFilterBitmap(true);// 对位图进行滤波处理
		paint.setTextSize(height - 2); // 文本大小

		// 计算文本绘制范围，居中绘制
		float tX = (width - getFontlength(paint, text)) / 2;
		float tY = (height - getFontHeight(paint)) / 2 + getFontLeading(paint);
		canvas.drawText(text, tX, tY, paint);
		canvas.save();
		canvas.restore();
		return bitmap;

	}

	/**
	 * 生成二进制矩阵
	 * 
	 * @version 1.0
	 * @createTime 2014年3月25日,上午9:34:14
	 * @updateTime 2014年3月25日,上午9:34:14
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param bitmap
	 *            要生成点阵图的位图
	 * @return 192*8个二进制字符组成的点阵字符串;
	 */
	public String makeMatrix(Bitmap bitmap) {
		String martix = "";
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (bitmap.getPixel(x, y) < -888888) {
					martix += "1";
				} else {
					martix += "0";
				}
			}
		}
		return martix;

	}

	/**
	 * 生成矩阵字节数组（通过二进制字符串）
	 * 
	 * @version 1.0
	 * @createTime 2014年3月25日,下午12:01:25
	 * @updateTime 2014年3月25日,下午12:01:25
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param matrix 二进制字符串
	 * @return 转换后的字节数组
	 */
	public byte[] makeMatrixBytes(String matrix) {
		byte[] matrixBytes = new byte[width * height / 8];
		int byteIndex = 0;// 字节下标
		int bitIndex = 0;// 位下标
		for (int i = 0; i < matrix.length(); i++) {
			byteIndex = i / 8;
			bitIndex = i % 8;
			if (bitIndex == 0) {// 如果是新的字节，全部置为1
				matrixBytes[byteIndex] ^= bitwise[8];
			}
			matrixBytes[byteIndex] &= (matrix.charAt(i) == '1' ? bitwise[8] : bitwise[bitIndex]);
		}
		return matrixBytes;
	}

	/**
	 * 文字转位图矩阵字节数组
	 * 
	 * @version 1.0
	 * @createTime 2014年3月25日,下午1:24:34
	 * @updateTime 2014年3月25日,下午1:24:34
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param Text
	 *            需要 转换的文字
	 * @return 转换后的字节数组
	 */
	public byte[] textToMatrixBytes(String text) {
		makeBitmap(text);
		String matrix = makeMatrix(bitmap);
		return makeMatrixBytes(matrix);
	}

	/**
	 * 把字节数组转换成16进制字符串
	 * 
	 * @version 1.0
	 * @createTime 2014年3月25日,下午12:07:44
	 * @updateTime 2014年3月25日,下午12:07:44
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param bytes
	 *            字节数组
	 * @return 转换后的HEX字符串
	 */
	public String bytesToHexString(byte[] bytes) {
		if (bytes != null) {
			StringBuffer sb = new StringBuffer(bytes.length);
			String sTemp;
			for (int i = 0; i < bytes.length; i++) {
				sTemp = Integer.toHexString(0xFF & bytes[i]);
				if (sTemp.length() < 2)
					sb.append(0);
				sb.append(sTemp.toUpperCase());
			}
			return sb.toString();
		} else {
			return "";
		}

	}

	/**
	 * 获取指定笔和指定字符串的长度
	 * 
	 * @version 1.0
	 * @createTime 2014年3月25日,上午12:44:33
	 * @updateTime 2014年3月25日,上午12:44:33
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param paint
	 *            画笔
	 * @param text
	 *            文本
	 * @return 返回指定笔和指定字符串的长度
	 */
	public static float getFontlength(Paint paint, String text) {
		return paint.measureText(text);
	}

	/**
	 * 获取指定笔的文字高度
	 * 
	 * @version 1.0
	 * @createTime 2014年3月25日,上午12:45:13
	 * @updateTime 2014年3月25日,上午12:45:13
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param paint
	 *            画笔
	 * @return 返回指定笔的文字高度
	 */
	public static float getFontHeight(Paint paint) {
		FontMetrics fm = paint.getFontMetrics();
		return fm.descent - fm.ascent;
	}

	/**
	 * 获取指定笔离文字顶部的基准距离
	 * 
	 * @version 1.0
	 * @createTime 2014年3月25日,上午12:45:33
	 * @updateTime 2014年3月25日,上午12:45:33
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 * 
	 * @param paint
	 *            画笔
	 * @return 返回指定笔离文字顶部的基准距离
	 */
	public static float getFontLeading(Paint paint) {
		FontMetrics fm = paint.getFontMetrics();
		return fm.leading - fm.ascent;
	}

}
