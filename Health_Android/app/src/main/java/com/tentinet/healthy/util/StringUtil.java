package com.tentinet.healthy.util;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;

/**
 * 字符串处理.
 *
 * @author paladin
 * @date 2014年9月11日
 * @Copyright: Copyright (c) 2014 Shenzhen Tentinet Technology Co., Ltd. All
 *             rights reserved.
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空.
	 * 
	 * @param string
	 *            字符串.
	 * @return 若字符串为空字符串或为空则返回true,否则返回false.
	 * @version 1.0
	 * @createTime 2014年9月11日,上午10:08:38
	 * @updateTime 2014年9月11日,上午10:08:38
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public static boolean isEmpty(String string) {
		if (null == string || "".equals(string) || "null".equals(string)) {
			return true;
		} else {
			return false;
		}
	}



	/**
	 * 局部放大彩色字体.
	 * 
	 * @param color
	 *            字体颜色.
	 * @param bigMessage
	 *            放大文本.
	 * @param normalMessage
	 *            普通文本.
	 * @return
	 * @version 1.0
	 * @createTime 2014年11月6日,下午6:39:44
	 * @updateTime 2014年11月6日,下午6:39:44
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public static String makeBigColorText(String color, String bigMessage, String normalMessage) {
		return "<font color=" + color + "><big><big>" + bigMessage + "</big></big></font>" + normalMessage;
	}

	/**
	 * 局部彩色字体.
	 * 
	 * @param color
	 *            字体颜色.
	 * @param colorMessage
	 *            彩色文本.
	 * @param normalMessage
	 *            普通文本.
	 * @return
	 * @version 1.0
	 * @createTime 2014年11月6日,下午6:39:44
	 * @updateTime 2014年11月6日,下午6:39:44
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public static String makeColorText(String color, String colorMessage, String normalMessage) {
		return "<font color=" + color + ">" + colorMessage + "</font>" + normalMessage;
	}

	/**
	 * 替换指定字符
	 * 
	 * @version 1.0
	 * @createTime 2014年12月3日,下午1:46:32
	 * @updateTime 2014年12月3日,下午1:46:32
	 * @createAuthor 王治粮
	 * @updateAuthor 王治粮
	 * @updateInfo
	 * @param text
	 *            原文本
	 * @param start
	 *            替换字符的起始位置
	 * @param end
	 *            替换字符的结束位置
	 * @param replace
	 *            替换的字符
	 * @return
	 */
	public static String replaceText(String text, int start, int end, String replace) {
		String startText = text.substring(0, start - 1);
		String endText = text.substring(end, text.length());
		return startText + replace + endText;
	}

	/**
	 * 局部放大彩色字体
	 * 
	 * @version 1.0
	 * @createTime 2014年11月18日,下午4:27:47
	 * @updateTime 2014年11月18日,下午4:27:47
	 * @createAuthor 王治粮
	 * @updateAuthor 王治粮
	 * @updateInfo
	 * @param message
	 *            文本内容
	 * @param color
	 *            改变字体的颜色
	 * @param size
	 *            改变字体的大小
	 * @param start
	 *            改变字体在文本中的开始位置
	 * @param end
	 *            改变字体在文本中的结束位置
	 * @return
	 */
	public static SpannableString makeColorText(String message, int color, int size, int start, int end) {
		SpannableString spannableString = new SpannableString(message);
		ForegroundColorSpan colorspan = new ForegroundColorSpan(color);
		AbsoluteSizeSpan sizespan = new AbsoluteSizeSpan(size);
		spannableString.setSpan(colorspan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		spannableString.setSpan(sizespan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return spannableString;
	}

	/**
	 * 去除小数末尾的.00
	 * 
	 * @version 1.0
	 * @createTime 2015-1-16,上午10:11:33
	 * @updateTime 2015-1-16,上午10:11:33
	 * @createAuthor 喻二博
	 * @updateAuthor 喻二博
	 * @updateInfo
	 * @param num
	 * @return
	 */
	public static String formatNumber(float num) {
		String result = String.valueOf(num);
		if (result.contains(".")) {
			String str[] = result.split("\\.");
			if (str != null && str.length > 1) {
				if ("0".equals(str[1]) || "00".equals(str[1])) {
					result = str[0];
				}
			}
		}
		return result;
	}

	/**
	 * 判断参数.
	 * 
	 * @param params
	 *            需要判断的字符串.
	 * @return 若为空则返回空字符串,否则返回原字符串.
	 * @version 1.0
	 * @createTime 2013年11月1日,下午12:01:50
	 * @updateTime 2013年11月1日,下午12:01:50
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public static String getParamsForString(String params) {
		if (null == params) {
			return "";
		} else {
			return params;
		}
	}

	/**
	 * 判断参数.
	 * 
	 * @param params
	 *            需要判断的字符串.
	 * @return 若为空则返回0字符串,否则返回原字符串.
	 * @version 1.0
	 * @createTime 2013年11月1日,下午12:01:50
	 * @updateTime 2013年11月1日,下午12:01:50
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public static String getParamsForNumber(String params) {
		if (TextUtils.isEmpty(params)) {
			return "0";
		} else {
			return params;
		}
	}

	/**
	 * 判断参数
	 * 
	 * @param params
	 *            需要判断的字符串
	 * @param number
	 *            为空时参数的默认值
	 * @return
	 * @version 1.0
	 * @createTime 2015年7月14日,上午9:59:37
	 * @updateTime 2015年7月14日,上午9:59:37
	 * @createAuthor 王治粮
	 * @updateAuthor 王治粮
	 * @updateInfo
	 * 
	 */
	public static String getParamsForNumber(String params, String number) {
		if (TextUtils.isEmpty(params)) {
			return number;
		} else {
			return params;
		}
	}


}
