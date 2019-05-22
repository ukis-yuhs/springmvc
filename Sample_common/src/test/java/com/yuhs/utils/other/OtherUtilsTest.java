package com.yuhs.utils.other;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/5/17.
 */
public class OtherUtilsTest {

    /**
     * 计算机单位变换
     */
    @Test
    public void testTranCompSize() {
        System.out.println(OtherUtils.tranCompSize(1234567890));
    }

    @Test
    public void testTranCompSize2() {
        System.out.println(OtherUtils.TranCompSize2(1234567890));
    }
}
