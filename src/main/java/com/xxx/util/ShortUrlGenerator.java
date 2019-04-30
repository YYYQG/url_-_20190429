package com.xxx.util;

import java.util.Arrays;

public interface ShortUrlGenerator {

    /**
     * 将字符串信息转换为32位字符串
     *
     * @param str 原始字符串
     * @return 转换后
     */
    String convert(String str);

    /**
     * 字符串缩短
     *
     * @param str    原始字符串
     * @param length 输出长度4~16之间，超出范围会自动修正
     * @return 缩短后字符串
     */
    default String[] shortString(String str, int length, char[] code) {
        // 限制长度范围
        if (length < 4) {
            length = 4;
        } else if (length > 16) {
            length = 16;
        }
        // 计算位移步长
        int step = 32 / length;

        // 数组下标范围
        long subHex = code.length - 1;

        // 加密32位摘要
        String md5Str = convert(str);
        String[] resStr = new String[4];
        // 摘要划分4段做备用筛选
        for (int i = 0; i < 4; i++) {
            String subStr = md5Str.substring(i * 8, i * 8 + 8);
            long hexIndex = Long.parseLong(subStr, 16);
            StringBuilder outChars = new StringBuilder();
            for (int j = 0; j < length; j++) {
                long index = subHex & hexIndex;
                outChars.append(code[(int) index]);
                hexIndex = hexIndex >> step;
            }
            resStr[i] = outChars.toString();
        }
        return resStr;
    }


}
