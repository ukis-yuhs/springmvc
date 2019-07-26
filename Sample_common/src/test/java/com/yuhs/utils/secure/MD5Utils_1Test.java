package com.yuhs.utils.secure;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/5/13.
 */
public class MD5Utils_1Test {
    @Test
    public void test() {
        MD5Utils_1 m = new MD5Utils_1();
//        if (Array.getLength(args) == 0) { // 如果没有参数，执行标准的Test Suite

            System.out.println("MD5 Test suite:");
            System.out.println("MD5(\"\"):" + m.getMD5ofStr(""));
            System.out.println("MD5(\"a\"):" + m.getMD5ofStr("a"));
            System.out.println("MD5(\"abc\"):" + m.getMD5ofStr("abc"));
            System.out.println("MD5(\"message digest\"):"
                    + m.getMD5ofStr("message digest"));
            System.out.println("MD5(\"abcdefghijklmnopqrstuvwxyz\"):"
                    + m.getMD5ofStr("abcdefghijklmnopqrstuvwxyz"));
            System.out
                    .println("MD5(\"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789\"):"
                            + m
                            .getMD5ofStr("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"));
//        } else {
//            System.out.println("MD5(" + args[0] + ")=" + m.getMD5ofStr(args[0]));
//        }
    }
}
