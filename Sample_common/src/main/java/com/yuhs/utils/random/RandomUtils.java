package com.yuhs.utils.random;

import com.yuhs.utils.string.StringUtils;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by yuhaisheng on 2019/5/9.
 */
public class RandomUtils {

    public static final char[] ALLOWED_CHARACTORS = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c',
            'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')' };

    /**
     * 伪随机生成指定为为整数
     *
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

    /**
     * 字符串以指定字符分隔,然后随机获取一个字符串
     * @param string 字符串
     * @param split 指定字符分隔
     * @return
     */
    public static String getStrsRandomStr(String string, String split) {
        if(StringUtils.isEmpty(string)) {
            return "";
        }
        int len = string.length();
        String curString = null;
        if(split.equals(string.substring(len -1, len))) {
            curString = string.substring(0, len - 1);
        } else {
            curString = string;
        }
        String[] arr = curString.split(split);
        if(arr.length == 0) {
            return "";
        }
        int index = (int) (Math.random() * arr.length);
        if(index >= arr.length) {
            index = arr.length - 1;
        }
        return arr[index];
    }

    /**
     * 获取UUID
     * @return
     */
    public static String uuidFull() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取UUID，去除"-"
     * @return
     */
    public static String uuid() {
        return uuidFull().replaceAll("-", "");
    }

    /**
     * 根据当前日期和5为随机数生成唯一编号(生成的长度为18位数字)
     * @return
     */
    public static String timeRandom() {
        return timeRandom(null);
    }

    /**
     * 根据日期和5位随机数生成唯一编号(生成的长度为18位数字)
     * @param date
     * @return
     */
    public static String timeRandom(Date date) {
        int numLen = 5;
        if (date == null) {
            date = new Date();
        }
        long time = date.getTime();
        //java1.7开始使用
        int num = (int) (100000 * ThreadLocalRandom.current().nextDouble());
        String random = String.valueOf(num);
        int rLen = random.length();
        if (rLen < numLen) {
            for (int i = 0; i < numLen - rLen; i++) {
                int cldNum = (int) (10 * ThreadLocalRandom.current().nextDouble());
                random = random + cldNum;
            }
        }
        System.out.println("time = " + String.valueOf(time) + " random = " + random);
        String no = String.valueOf(time) + random;
        return no;
    }

    /**
     * 随机密码生成
     * @param passwordLength
     * @return
     */
    public static String generatePassword(int passwordLength) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int index = 0; index < passwordLength; ++index) {
            stringBuffer.append(ALLOWED_CHARACTORS[random.nextInt(ALLOWED_CHARACTORS.length)]);
        }
        return stringBuffer.toString();
    }
}
