package com.yuhs.utils.ip;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/6/2.
 */
public class IpUtilsTest {
    @Test
    public void testEncodeIp() {
        long ipNum = IpUtils.encodeIp("192.168.0.109");
        System.out.println(ipNum);
        System.out.println(Long.parseLong("192") << 24);
        System.out.println(Long.parseLong("168") << 18);
        System.out.println(Long.parseLong("0") << 8);
        System.out.println(Long.parseLong("109"));
    }

    @Test
    public void test() {
        String ip =  IpUtils.decodeIp(3232235629l);
        System.out.println(ip);
    }
}
