package com.yuhs.utils.string;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/5/17.
 */
public class StringUtilsTest {
    /**
     * 测试字符串是否为空
     */
    @Test
    public void testIsEmpty(){
        System.out.println(StringUtils.isEmpty(null));
        System.out.println(StringUtils.isEmpty(""));
        System.out.println(StringUtils.isEmpty("0"));
    }

    /**
     * 测试字符串是否为空格
     */
    @Test
    public void testIsAllSpace() {
        System.out.println(StringUtils.isAllSpace(null));
        System.out.println(StringUtils.isAllSpace(""));
        System.out.println(StringUtils.isAllSpace("abc"));
        System.out.println(StringUtils.isAllSpace("             "));
    }

    /**
     * 获取一个字符串的简明效果
     */
    @Test
    public void testGetStringSimple() {
        System.out.println(StringUtils.getStringSimple("12345678901"));
        System.out.println(StringUtils.getStringSimple("abcdefghijk"));
    }

    /**
     * 字符串右补空格
     */
    @Test
    public void testRightPadForByte() {
        System.out.println(StringUtils.rightPadForByte("ABC",12));
    }

    /**
     * 字符串右补字符
     */
    @Test
    public void testRightPadForByte0() {
        System.out.println(StringUtils.rightPadForByte("ABC",12,32));
        System.out.println(StringUtils.rightPadForByte("ABC",12,48));
        System.out.println(StringUtils.rightPadForByte("ABC",12,64));
    }

    /**
     * 字符串左补空格
     */
    @Test
    public void testLeftPadForByte() {
        System.out.println(StringUtils.leftPadForByte("ABC",12));
    }

    /**
     * 字符串左补字符
     */
    @Test
    public void testLeftPadForByte0() {
        System.out.println(StringUtils.leftPadForByte("ABC",12,32));
        System.out.println(StringUtils.leftPadForByte("ABC",12,48));
        System.out.println(StringUtils.leftPadForByte("ABC",12,64));
    }
}
