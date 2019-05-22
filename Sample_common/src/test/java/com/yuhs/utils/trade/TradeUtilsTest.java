package com.yuhs.utils.trade;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/5/21.
 */
public class TradeUtilsTest {

    /**
     * 获取流水号
     */
    @Test
    public void testGetSysJournalNo() {
        System.out.println(TradeUtils.getSysJournalNo());
        System.out.println(TradeUtils.getSysJournalNo(12,false));
        System.out.println(TradeUtils.getSysJournalNo(40,true));
    }

    /**
     * 金额元转分(保留两位小数)
     */
    @Test
    public void testMoneyYuanToFen() {
        System.out.println(TradeUtils.moneyYuanToFen("123"));
        System.out.println(TradeUtils.moneyYuanToFen("123.4"));
        System.out.println(TradeUtils.moneyYuanToFen("123.45"));
        System.out.println(TradeUtils.moneyYuanToFen("123.456"));
    }

    /**
     * 金额元转分(保留两位小数,四舍五入)
     */
    @Test
    public void testMoneyYuanToFenByRound() {
        System.out.println(TradeUtils.moneyYuanToFenByRound("123"));
        System.out.println(TradeUtils.moneyYuanToFenByRound("123.4"));
        System.out.println(TradeUtils.moneyYuanToFenByRound("123.45"));
        System.out.println(TradeUtils.moneyYuanToFenByRound("123.456"));
        System.out.println(TradeUtils.moneyYuanToFenByRound("123.996"));
    }

    /**
     * 金额分转元
     */
    @Test
    public void testMoneyFenToYuan() {
        System.out.println(TradeUtils.moneyFenToYuan("12345"));
        System.out.println(TradeUtils.moneyFenToYuan("1234"));
        System.out.println(TradeUtils.moneyFenToYuan("123"));
        System.out.println(TradeUtils.moneyFenToYuan("13"));
        System.out.println(TradeUtils.moneyFenToYuan("4"));
    }
}
