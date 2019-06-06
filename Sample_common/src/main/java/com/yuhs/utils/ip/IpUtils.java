package com.yuhs.utils.ip;

import com.yuhs.utils.string.StringUtils;

/**
 * Created by yuhaisheng on 2019/6/2.
 */
public class IpUtils {

    private static int IP_ARRAY_LENGTH = 4;

    /**
     * 将ip地址编码为数字
     * @param ipString IP地址
     * @return
     */
    public static long encodeIp(String ipString) {
        long ipNumber = 0L;
        if (!StringUtils.isEmpty(ipString)) {
            String[] ipArray = ipString.split("\\.");
            if (ipArray.length == IP_ARRAY_LENGTH) {
                ipNumber = Long.parseLong(ipArray[0]) << 24
                        | Long.parseLong(ipArray[1]) << 16
                        | Long.parseLong(ipArray[2]) << 8
                        | Long.parseLong(ipArray[3]);
            }
        }
        return ipNumber;
    }

    /**
     * 将编码过的数字解码为ip地址
     * @param ip ip地址转换后的数字
     * @return
     */
    public static String decodeIp(long ip) {
        if (ip <= 0L) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(ip >> 24 & 0xFF).append(".").append(ip >> 16 & 0xFF)
                .append(".").append(ip >> 8 & 0xFF).append(".")
                .append(ip & 0xFF);
        return sb.toString();
    }
}
