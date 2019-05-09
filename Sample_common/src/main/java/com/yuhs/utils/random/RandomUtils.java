package com.yuhs.utils.random;

import java.util.Random;

/**
 * Created by yuhaisheng on 2019/5/9.
 */
public class RandomUtils {
    /**
     * 随机生成指定为为整数
     * @param count
     * @return
     */
    public static String getRandomNo(int count) {
        String str = "";
        for (int i = 0; i < count; i++) {
            Random ra = new Random();
            int num = ra.nextInt(10);
            str = str + String.valueOf(num);
        }
        return str;
    }
}
