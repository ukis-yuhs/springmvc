package com.yuhs.utils.random;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/7/1.
 */
public class RandomPasswordUtilsTest {

    @Test
    public void testPassword() {
        RandomPasswordUtils randomPasswordUtils = new RandomPasswordUtils(6,3);
        System.out.println(randomPasswordUtils.generateRandomPassword());
    }
}
