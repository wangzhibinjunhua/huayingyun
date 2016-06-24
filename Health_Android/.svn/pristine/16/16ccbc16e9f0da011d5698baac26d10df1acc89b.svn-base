package com.tentinet.healthy.util;

import java.util.Calendar;
import java.util.Locale;

/**
 * 命令封装类
 *
 * @author yeqing
 * @version 1.0
 * @Description
 * @date 2015-8-20
 * @Copyright: Copyright (c) 2015 Shenzhen Utoow Technology Co., Ltd. All rights
 * reserved.
 */
public class CommandBytesUtil {
    // 数据封装 (V1.0.1):
    // 开始标识 数据长度 命令类 命令 参数 结束校验
    // BYTE[0] BYTE[1] BYTE[2] BYTE[3] BYTE[4] BYTE[n] BYTE[n+1]
    // 0xFC Command Class Command Parameters Check

    /**
     * 获取命令数组
     *
     * @return
     * @version 1.0
     * @createTime 2015-8-20,上午11:04:39
     * @updateTime 2015-8-20,上午11:04:39
     * @createAuthor yeqing
     * @updateAuthor yeqing
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static byte[] getCommandBytes(Byte commandClass, Byte command) {
        // // 开始标识
        // byte startOne = (byte) 0x06;
        // 开始标识
        byte start = (byte) 0xFC;
        // 数据长度
        byte length = 0x05;
        // 结束校验
        byte check = (byte) (start + length + commandClass + command);

        byte[] commands = {start, length, commandClass, command, check};
        return commands;
    }

    /**
     * 获取蓝牙计，血糖计检验命令
     *
     * @return
     * @version 1.0
     * @createTime 2015-8-20,上午11:04:39
     * @updateTime 2015-8-20,上午11:04:39
     * @createAuthor yeqing
     * @updateAuthor yeqing
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public static String getCheckHexString() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        year = Integer.parseInt((year + "").substring(2, 4));
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int start = 90;//(对应协议5A已转为十进制了)
        int length = 11;//(对应协议0B已转为十进制了)
        int type = 5;//(对应协议05已转为十进制了)
        //校验码（数值之和）
        int check = year + month + day + minute + hour + start + length + type;
        String tmp = Integer.toHexString(check);
        String f = "5A0B05" + getDataHexString(year) + getDataHexString(month) + getDataHexString(day) +
                getDataHexString(hour) + getDataHexString(minute) + tmp + "0000";
        return f.toLowerCase(Locale.getDefault());
    }

    /**
     * 耳温枪开始串
     */
    public static final String EAR_START="B0";
    /**
     * 耳温枪结束串
     */
    public static final String EAR_END="B7";


    public static String getDataHexString(int val) {
        String tmpStr = "";
        if (val < 10) {
            tmpStr = ("0" + val);
        } else {
            tmpStr = Integer.toHexString(val);
            if (tmpStr.length() == 1) {
                tmpStr = "0" + tmpStr;
            }
        }
        return tmpStr;
    }
}
