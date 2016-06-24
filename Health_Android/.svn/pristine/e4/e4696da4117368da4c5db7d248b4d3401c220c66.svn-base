package com.tentinet.healthy.util;

import java.io.UnsupportedEncodingException;


public class HexBytesUtils {
	/**
	 * int2bytes(int n) 整形转化为长度为2的byte数组
	 *
	 * @version 1.0
	 * @createTime 2014-3-18,下午5:40:29
	 * @updateTime 2014-3-18,下午5:40:29
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param n 整形值
	 * @return byte数组
	 */
	public static byte[] int2bytes(int n) {   
		  byte[] ab = new byte[2];   
		  ab[0] = (byte) (0xff & n);   
		  ab[1] = (byte) ((0xff00 & n) >> 8);  
		  return ab;   
	}
	
	/**
	 * 16进制字符串转换为byte数组
	 *
	 * @version 1.0
	 * @createTime 2014-3-21,下午2:16:38
	 * @updateTime 2014-3-21,下午2:16:38
	 * @createAuthor wutianlin
	 * @updateAuthor wutianlin
	 * @updateInfo (此处输入修改内容,若无修改可不写.)
	 *
	 * @param paramString
	 * @return
	 */
	 public static byte[] hexStr2Bytes(String paramString){
		 int len = paramString.length()/2;
		 byte[] mbytes = new byte[len];
		 for(int i=0;i<len;i++){
			 mbytes[i] = (byte)Integer.parseInt(paramString.substring(i*2, i*2+2), 16);
		 }
		 return mbytes;
	 }
	 
	 /**
	  * 将字符串转换为utf-8编码byte数组
	  *Unicode的编解码时可逆的,中英文占两个字节
	  * @version 1.0
	  * @createTime 2014-3-27,上午10:21:35
	  * @updateTime 2014-3-27,上午10:21:35
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param str 字符串
	  * @return byte数组
	  */
	 public static byte[] getUnicodeBytes(String str){
		 byte []tmp;
		 try {
			tmp = str.getBytes("Unicode");
		} catch (UnsupportedEncodingException e) {
			tmp = new byte[]{};
			e.printStackTrace();
		}
		 return tmp;
	 }
	 
	 /**
	  * 将byte数组转换为字符串
	  *Unicode的编解码时可逆的
	  * @version 1.0
	  * @createTime 2014-3-27,上午10:22:21
	  * @updateTime 2014-3-27,上午10:22:21
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param bytes byte数组
	  * @return 字符串
	  */
	 public static String getUnicodeString(byte[] bytes){
		 String str;
		 try {
			str = new String(bytes,"Unicode");
		} catch (UnsupportedEncodingException e) {
			str="";
			e.printStackTrace();
		}
		return str;
	 }
	 
	 /**
	  * 根据地址的字符串，返回6位byte数组
	  *
	  * @version 1.0
	  * @createTime 2014-3-28,上午9:15:13
	  * @updateTime 2014-3-28,上午9:15:13
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param address
	  * @return
	  */
	 public static byte[] getMacAddressBytes(String address){
		 String units[] = address.trim().split(":");
		 byte[] adds = new byte[6];
		 if(units.length==6){
			 for(int i=0;i<6;i++){
				 adds[i] = (byte)Integer.parseInt(units[i],16);
			 }
		 }
		 return adds;		 
	 }
	 
	 /**
	  * 通过获取到byte数组
	  * 解析配对手环的名称
	  *
	  * @version 1.0
	  * @createTime 2014-3-28,上午9:41:48
	  * @updateTime 2014-3-28,上午9:41:48
	  * @createAuthor wutianlin
	  * @updateAuthor wutianlin
	  * @updateInfo (此处输入修改内容,若无修改可不写.)
	  *
	  * @param data
	  * @return
	  */
	 public static String getMacAndNameStrFromBytes(byte[] data){

		 StringBuilder builder = new StringBuilder(6);
		 for(int i=2;i<8;i++){
			 builder.append(String.format("%02X", data[i]));
			 if(i!=7){
				 builder.append(":");
			 }
		 }
		 byte[] tmp = new byte[data.length-8];
		 for(int i=0;i<tmp.length;i++){
			 tmp[i] = data[8+i];
		 }
		 return builder.toString()+HexBytesUtils.getUnicodeString(tmp);
	 }
}
