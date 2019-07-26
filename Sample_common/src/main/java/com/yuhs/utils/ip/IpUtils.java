package com.yuhs.utils.ip;

import com.yuhs.utils.string.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by yuhaisheng on 2019/6/2.
 */
public class IpUtils {

    private static int IP_ARRAY_LENGTH = 4;

    /**
     * 获取客户端IP地址
     *
     * @param request http请求
     * @return
     * @throws Exception
     */
    public static String getRemoteAddress(HttpServletRequest request) throws Exception {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || ip.equalsIgnoreCase("unknown")) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取本机的ip地址
     *
     * @return
     * @throws UnknownHostException
     */
    public static String getLocalIP() throws UnknownHostException {
        InetAddress addr = InetAddress.getLocalHost();
        byte[] ipAddr = addr.getAddress();
        String ipAddrStr = "";
        for (int i = 0; i < ipAddr.length; i++) {
            if (i > 0) {
                ipAddrStr += ".";
            }
            ipAddrStr += ipAddr[i] & 0xFF;
        }
        return ipAddrStr;
    }

    /**
     * 将ip地址编码为数字
     *
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
     *
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
