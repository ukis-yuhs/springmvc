package com.yuhs.utils.bank;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/6/27.
 */
public class BankCodeUtilsTest {

    /**
     * 验证银行卡号码
     */
    @Test
    public void testIsCardCode() {
        boolean isCardCode = BankCodeUtils.isCardCode("5432123456788882");
        System.out.println("isCardCode = " + isCardCode);
    }

    /**
     * 获取末尾验证码
     */
    @Test
    public void testGetCheckCode() {
        char bit = BankCodeUtils.getCheckCode("543212345678888");
        System.out.println("bit = " + bit);
        //cn.hutool.http.HtmlUtil
        DateUtil.ageOfNow("1990-01-30");
    }
}
