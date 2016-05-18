package com.tentinet.healthy.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 数据类型转换函数集
 *
 * @author 王治粮
 * @date 2015/12/29  16:23
 * @Copyright: Copyright (c) 2013 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class DataConverter {

    /**
     * 把16进制字符串转换成字节数组
     *
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * 十六进制字符串转二进制压缩成bcd??
     *
     * @param str 十六进制串
     * @return
     */
    public static byte[] hex2byte(String str) { // 字符串转二进??
        int len = str.length();
        String stmp = null;
        if (len % 2 != 0) {
            len += 1;
        }
        byte bt[] = new byte[len / 2];
        for (int n = 0; n < len / 2; n++) {
            stmp = str.substring(n * 2, n * 2 + 2);
            bt[n] = (byte) (Integer.parseInt(stmp, 16));
        }
        return bt;
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray, int len) {
        StringBuffer sb = new StringBuffer(len);
        String sTemp;
        for (int i = 0; i < len; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        if (bArray != null) {
            StringBuffer sb = new StringBuffer(bArray.length);
            String sTemp;
            for (int i = 0; i < bArray.length; i++) {
                sTemp = Integer.toHexString(0xFF & bArray[i]);
                if (sTemp.length() < 2)
                    sb.append(0);
                sb.append(sTemp.toUpperCase());
            }
            return sb.toString();
        } else {
            return "";
        }

    }

    //转化字符串为十六进制编码
    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    /**
     * 字节数据转换16进制字符串
     *
     * @param b
     * @return
     */
    public static final String byteToHexString(byte b) {
        StringBuffer sb = new StringBuffer();
        String sTemp;
        sTemp = Integer.toHexString(0xFF & b);
        if (sTemp.length() < 2)
            sb.append(0);
        sb.append(sTemp.toUpperCase());
        return sb.toString();
    }

    /**
     * 把字节数组转换为对象
     *
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static final Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = new ObjectInputStream(in);
        Object o = oi.readObject();
        oi.close();
        return o;
    }

    /**
     * 把可序列化对象转换成字节数组
     *
     * @param s
     * @return
     * @throws IOException
     */
    public static final byte[] objectToBytes(Serializable s) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream ot = new ObjectOutputStream(out);
        ot.writeObject(s);
        ot.flush();
        ot.close();
        return out.toByteArray();
    }

    public static final String objectToHexString(Serializable s) throws IOException {
        return bytesToHexString(objectToBytes(s));
    }

    public static final Object hexStringToObject(String hex) throws IOException, ClassNotFoundException {
        return bytesToObject(hexStringToByte(hex));
    }

    /**
     * @函数功能: BCD码转为10进制串(阿拉伯数据)
     * @输入参数: BCD码
     * @输出结果: 10进制串
     */
    public static String bcd2Str(byte[] bytes) {
        StringBuffer temp = new StringBuffer(bytes.length * 2);

        for (int i = 0; i < bytes.length; i++) {
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
            temp.append((byte) (bytes[i] & 0x0f));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1) : temp.toString();
    }

    /**
     * ASC转换成String
     *
     * @param b
     * @return
     */
    public static String ASC2String(byte[] b) { // 二行制转字符串
        String trackData = "";
        for (int n = 0; n < b.length; n++) {
            trackData += (char) (b[n] & 0XFF);
        }
        return trackData.toUpperCase(); // String.toUpperCase 返回大写字母
    }

    /**
     * ASC转换成String
     *
     * @param s
     * @return
     */
    public static byte[] String2ASC(String s) { // 二行制转字符串

        return s.getBytes(); //
    }

    /**
     * @函数功能: 10进制串转为BCD码
     * @输入参数: 10进制串
     * @输出结果: BCD码
     */
    public static byte[] str2bcd(String s) {
        if (s.length() % 2 != 0) {
            s = "0" + s;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i += 2) {
            int high = cs[i] - 48;
            int low = cs[i + 1] - 48;
            baos.write(high << 4 | low);
        }
        return baos.toByteArray();
    }

    /**
     * @param b :BCD字节
     * @return：字符串
     */
    public static String bcd2string(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            int h = ((b[i] & 0xff) >> 4) + 48;
            sb.append((char) h);
            int l = (b[i] & 0x0f) + 48;
            sb.append((char) l);
        }
        return sb.toString();
    }

    /**
     * @param str :ASC码字符串
     * @return：补齐的??。串长不??6的??数补齐??F??
     */
    public static String add16FF(String str) {
        int iLen, iAddLen;
        str = str.trim();
        iLen = str.length();
        iLen %= 16;
        iAddLen = 16 - iLen;
        String strAdd = "";
        for (int i = 0; i < iAddLen; i++)
            strAdd += "F";
        return str + strAdd;
    }

    /**
     * @param a :ASC码字符串
     * @return：补齐的串 。串长不足16的倍数补齐“F”
     */
    public static byte asscii_to_num(byte a) {
        byte x;
        x = a;
        if (x >= 0x30 && x <= 0x39)
            x = (byte) (x - 0x30);
        else if (x >= 0x41 && x <= 0x46)
            x = (byte) (x - 0x37);
        else if (x >= 0x61 && x <= 0x66)
            x = (byte) (x - 0x57);
        else
            x = 0;
        return x;
    }

    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[2];
        result[0] = (byte) ((i >> 8) & 0xFF);
        result[1] = (byte) (i & 0xFF);
        return result;
    }

    public static byte[] intToByteArray2(int i) {
        byte[] result = new byte[4];
        result[0] = (byte) ((i >> 24) & 0xFF);
        result[1] = (byte) ((i >> 16) & 0xFF);
        result[2] = (byte) ((i >> 8) & 0xFF);
        result[3] = (byte) (i & 0xFF);
        return result;
    }

    public static int bytes2int(byte[] b) {
        int temp = 0;
        int a1, a2;
        a1 = (int) (b[0]);
        a2 = (int) (b[1]);
        if (a1 < 0)
            a1 += 256;
        if (a2 < 0)
            a2 += 256;
        temp = a1 << 8;
        temp |= a2 | a1;
        return temp;
    }

    public static byte[] int2bytes(int num) {
        byte[] b = new byte[2];
        b[0] = (byte) (num);
        num >>= 8;
        b[1] = (byte) (num);
        return b;
    }

    // Java 中 byte 和 int 之间的转换源码：

    /**
     * byte 与 int 的相互转换
     *
     * @param x
     * @return
     * @version 1.0
     * @createTime 2015-10-27,下午4:29:29
     * @updateTime 2015-10-27,下午4:29:29
     * @createAuthor Jerry
     * @updateAuthor Jerry
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static byte intToByte(int x) {
        return (byte) x;
    }

    // Java 中 byte 和 int 之间的转换源码：

    /**
     * byte 与 int 的相互转换
     *
     * @param b
     * @return
     * @version 1.0
     * @createTime 2015-10-27,下午4:29:29
     * @updateTime 2015-10-27,下午4:29:29
     * @createAuthor Jerry
     * @updateAuthor Jerry
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static int byteToInt(byte b) {
        // Java 总是??byte 当做有符处理；我们可以??过将其和 0xFF 进行二进制与得到它的无符??
        return b & 0xFF;
    }

    /**
     * 16进制字符串转换为byte
     *
     * @param paramString
     * @return
     * @version 1.0
     * @createTime 2014-3-21,下午2:16:38
     * @updateTime 2014-3-21,下午2:16:38
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不??)
     */
    public static byte hexStr2Byte(String paramString) {
        byte mbyte;

        mbyte = (byte) Integer.parseInt(paramString.substring(0, 2), 16);
        return mbyte;
    }

    /**
     * 16进制字符串转换为byte数组
     *
     * @param paramString
     * @return
     * @version 1.0
     * @createTime 2014-3-21,下午2:16:38
     * @updateTime 2014-3-21,下午2:16:38
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不??)
     */
        public static byte[] hexStr2Bytes(String paramString) {
        int len = paramString.length() / 2;
        byte[] mbytes = new byte[len];
        for (int i = 0; i < len; i++) {
            try {
                mbytes[i] = (byte) Integer.parseInt(paramString.substring(i * 2, i * 2 + 2), 16);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mbytes;
    }

    /**
     * 16进制字符串转换为int数组
     *
     * @param paramString
     * @return
     * @version 1.0
     * @createTime 2014-3-21,下午2:16:38
     * @updateTime 2014-3-21,下午2:16:38
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不??)
     */
    public static int[] hexStr2Ints(String paramString) {
        int len = paramString.length() / 2;
        int[] mints = new int[len];
        for (int i = 0; i < len; i++) {
            try {
                mints[i] = Integer.parseInt(paramString.substring(i * 2, i * 2 + 2), 16);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mints;
    }

    /**
     * 16进制字符串转换为byte数组
     *
     * @param hex
     * @return
     * @version 1.0
     * @createTime 2014-3-21,下午2:16:38
     * @updateTime 2014-3-21,下午2:16:38
     * @createAuthor wutianlin
     * @updateAuthor wutianlin
     * @updateInfo (此处输入修改内容, 若无修改可不??)
     */
    public static byte[] parseHexStringToBytes(final String hex) {
        byte[] bytes = new byte[hex.length() / 2]; // every two letters in the string are one byte finally
        String part = "";
        for (int i = 0; i < bytes.length; ++i) {
            part = "0x" + hex.substring(i * 2, i * 2 + 2);
            bytes[i] = Long.decode(part).byteValue();
        }
        return bytes;
    }
}
