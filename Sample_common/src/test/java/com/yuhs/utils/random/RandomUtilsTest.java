package com.yuhs.utils.random;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/5/9.
 */
public class RandomUtilsTest {
    /**
     * 随机生成指定位数整数
     */
    @Test
    public void testGetRandomNo() {
        System.err.println(RandomUtils.getRandomNo(10));
    }
}
