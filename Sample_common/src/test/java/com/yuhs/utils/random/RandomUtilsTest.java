package com.yuhs.utils.random;

import org.junit.Test;

import java.util.Date;

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

    /**
     * UUID
     */
    @Test
    public void testUuidFull() {
        System.out.println(RandomUtils.uuidFull());
    }

    /**
     * UUID去除"-"
     */
    @Test
    public void testUuid() {
        System.out.println(RandomUtils.uuid());
    }

    /**
     * 根据当前日期和5为随机数生成唯一编号(生成的长度为18位数字)
     */
    @Test
    public void testCurrentTimeRandom() {
        System.out.println(RandomUtils.timeRandom());
    }

    /**
     * 根据日期和5为随机数生成唯一编号(生成的长度为18位数字)
     */
    @Test
    public void testTimeRandom() {
        System.out.println(RandomUtils.timeRandom(new Date()));
    }

    /**
     * 随机密码生成
     */
    @Test
    public void testMakeRandomPassword() {
        for (int i = 0; i < 20; i++) {
            System.out.println(RandomUtils.generatePassword(6));
        }
    }
}
