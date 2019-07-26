package com.yuhs.utils.bank;

/**
 * Created by yuhaisheng on 2019/6/27.
 */
public class BankCodeUtils {

    /**
     * 国内的银行卡号一般都是16到21位。
     * 其中普通借记卡一般是18到21位，信用卡都是16位。
     *
     * 卡号组成
     * 前6位是：发行者标识代码 Issuer Identification Number (IIN)。
     * 中间的位数是：个人账号标识（从卡号第7位开始）
     * 最后一位位数是校验位：将卡号前面的数字采用Luhn算法计算出信用卡或者借记卡的最后一位数字。
     *
     * SWIFT银行识别代码由以下几部分构成：(https://www.swift.com/zh-hans)
     * 银行代码（Bank Code）：由四位易于识别的银行行名字头缩写字母构成；
     * 国家代码（Country Code）：根据国际标准化组织的规定由两位字母构成；
     * 地区代码（Location Code）：由两位数字或字母构成，标明城市；
     * 分行代码（Branch Code）：由三位数字或字母构成，标明分支机构。
     *
     * Luhn算法
     * 1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
     * 2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，则将其减去9），再求和。
     * 3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
     *
     * 例如，卡号是：5432123456788881
     * 则奇数、偶数位分布：5432123456788881
     *      奇数: 4 2 2 4 6 8 8 1
     *      偶数:5 3 1 3 5 7 8 8
     * 奇数位和 (4+2+2+4+6+8+8+1) = 35
     * 偶数位乘以2（两位数减去9）的结果：1 6 2 6 1 5 7 7，求和=35。
     * 最后35+35=70 可以被10整除，认定校验通过。
     */

    /**
     * API接口
     *
     * 1.根据银行卡号码获取银行卡归属地信息接口
     *  返回结果
     *  bank：       标识码
     *  cardType：   银行卡类型（DC:借记卡，CC:信用卡）
     *  key：        银行卡号
     *  validated：  Bin号的合法
     * https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo=银行卡卡号&cardBinCheck=true
     * 2.根据银行卡归属标识码，查询银行logo图标接口
     * https://apimg.alipay.com/combo.png?d=cashier&t=CCB
     *
     * js
     * https://www.jlshare.top/bankCard
     */


    /**
     * 验证银行卡号
     * @param cardId 银行卡号
     * @return
     */
    public static boolean isCardCode(String cardId) {
        if (cardId == null || cardId.trim().length() == 0 || !cardId.matches("\\d+")) {
            return false;
        }
        String lastNum = cardId.substring(cardId.length() - 1 );
        String checkCode = String.valueOf(getCheckCode(cardId.substring(0, cardId.length() - 1)));
        if (!lastNum.equals(checkCode)) {
            return false;
        }
        return true;
    }

    /**
     * 给定银行卡号（未添加校验位）获取校验位
     * @param targetCode 银行卡号（未添加校验位）
     * @return
     */
    public static char getCheckCode(String targetCode) {
        char[] chs = targetCode.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        // +'0'，不是拼接，在Java和C#中是8+0的ASCII码得到8在ASCII中的编码值，然后通过(char)转成字符'8'
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

}
