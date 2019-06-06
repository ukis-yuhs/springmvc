package com.yuhs.utils.math;

import com.yuhs.utils.string.StringUtils;

import java.math.BigDecimal;

/**
 * 数学运算辅助类
 * Created by yuhaisheng on 2019/5/23.
 */
public class MathUtil {

    /**
     * 将字符串转换为BigDecimal，一般用于数字运算时
     *
     * @param target 字符串
     * @return BigDecimal(target为empty时返回nul)
     */
    public static BigDecimal toBigDecimal(String target) {
        if (StringUtils.isEmpty(target)) {
            return null;
        }
        return new BigDecimal(target);
    }

    /**
     * 将字符串转换为double，如果失败返回默认值
     *
     * @param target       字符串
     * @param defaultValue 失败时返回的默认值
     * @return double
     */
    public static double toDouble(String target, double defaultValue) {
        if (target == null) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(target);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换为float，如果失败返回默认值
     *
     * @param target       字符串
     * @param defaultValue 失败时返回的默认值
     * @return float
     */
    public static float toFloat(String target, float defaultValue) {
        if (target == null) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(target);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换为long，如果失败返回默认值
     *
     * @param target       字符串
     * @param defaultValue 失败时返回的默认值
     * @return long
     */
    public static long toLong(String target, long defaultValue) {
        if (target == null) {
            return defaultValue;
        }
        try {
            return Long.parseLong(target);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转换为int，如果失败返回默认值。
     *
     * @param target       字符串
     * @param defaultValue 失败时返回的默认值
     * @return int
     */
    public static int toInt(String target, int defaultValue) {
        if (target == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(target);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 获取两个float值中最大值
     *
     * @param floatNumOne float值1
     * @param floatNumTwo float值2
     * @return 最大的值
     */
    public static float getMax(float floatNumOne, float floatNumTwo) {
        if (Float.isNaN(floatNumOne)) {
            return floatNumTwo;
        } else if (Float.isNaN(floatNumTwo)) {
            return floatNumOne;
        } else {
            return Math.max(floatNumOne, floatNumTwo);
        }
    }

    /**
     * 获取两个double值中最大值
     *
     * @param doubleNumOne double值1
     * @param doubleNumTwo double值2
     * @return 最大的值
     */
    public static double getMax(double doubleNumOne, double doubleNumTwo) {
        if (Double.isNaN(doubleNumOne)) {
            return doubleNumTwo;
        } else if (Double.isNaN(doubleNumTwo)) {
            return doubleNumOne;
        } else {
            return Math.max(doubleNumOne, doubleNumTwo);
        }
    }

    /**
     * 获取两个float值中最小值
     *
     * @param floatNumOne float值1
     * @param floatNumTwo float值2
     * @return 最小的值
     */
    public static float getMin(float floatNumOne, float floatNumTwo) {
        if (Float.isNaN(floatNumOne)) {
            return floatNumTwo;
        } else if (Float.isNaN(floatNumTwo)) {
            return floatNumOne;
        } else {
            return Math.min(floatNumOne, floatNumTwo);
        }
    }

    /**
     * 获取两个double值中最小值
     *
     * @param doubleNumOne double值1
     * @param doubleNumTwo double值2
     * @return 最小的值
     */
    public static double getMin(double doubleNumOne, double doubleNumTwo) {
        if (Double.isNaN(doubleNumOne)) {
            return doubleNumTwo;
        } else if (Double.isNaN(doubleNumTwo)) {
            return doubleNumOne;
        } else {
            return Math.min(doubleNumOne, doubleNumTwo);
        }
    }

    /**
     * 得到float数组中最大值
     *
     * @param array float数组（数组不能为null，也不能为空）
     * @return 得到数组中最大值
     */
    public static float getMax(float[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        // Finds and returns max
        float max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = getMax(array[i], max);
        }
        return max;
    }

    /**
     * 得到double数组中最大值
     *
     * @param array double数组（数组不能为null，也不能为空）
     * @return 得到数组中最大值
     */
    public static double getMax(double[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("The Array must not be null");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        // Finds and returns max
        double max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = getMax(array[i], max);
        }
        return max;
    }

    /**
     * 得到float数组中最小值
     *
     * @param array float数组（数组不能为null，也不能为空）
     * @return 得到数组中最小值
     */
    public static float getMin(float[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("数组不能为null。");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("数组不能为空。");
        }
        // Finds and returns min
        float min = array[0];
        for (int i = 1; i < array.length; i++) {
            min = getMin(array[i], min);
        }
        return min;
    }

    /**
     * 得到double数组中最小值
     *
     * @param array double数组（数组不能为null，也不能为空）
     * @return 得到数组中最小值
     */
    public static double getMin(double[] array) {
        // Validates input
        if (array == null) {
            throw new IllegalArgumentException("数组不能为null。");
        } else if (array.length == 0) {
            throw new IllegalArgumentException("数组不能为空。");
        }
        // Finds and returns min
        double min = array[0];
        for (int i = 1; i < array.length; i++) {
            min = getMin(array[i], min);
        }
        return min;
    }

    /**
     * 返回first除以second的double的商
     *
     * @param first        double值1
     * @param second       double值2
     * @param scale        保留小数点的位数
     * @param roundingMode 小数模式。若不想选择可填写-1，会默认使用BigDecimal.ROUND_HALF_UP，即5舍6入。<br/>
     *                     BigDecimal.ROUND_CEILING 如果 BigDecimal 是正的，则做 ROUND_UP 操作；如果为负，则做 ROUND_DOWN 操作。<br/>
     *                     BigDecimal.ROUND_DOWN 从不在舍弃(即截断)的小数之前增加数字。<br/>
     *                     BigDecimal.ROUND_FLOOR 如果 BigDecimal 为正，则作 ROUND_UP ；如果为负，则作 ROUND_DOWN 。<br/>
     *                     BigDecimal.ROUND_HALF_DOWN 若舍弃部分> .5，则作 ROUND_UP；否则，作 ROUND_DOWN 。<br/>
     *                     BigDecimal.ROUND_HALF_EVEN 如果舍弃部分左边的数字为奇数，则作 ROUND_HALF_UP ；如果它为偶数，则作 ROUND_HALF_DOWN 。<br/>
     *                     BigDecimal.ROUND_HALF_UP 若舍弃部分>=.5，则作 ROUND_UP ；否则，作 ROUND_DOWN 。<br/>
     *                     BigDecimal.ROUND_UNNECESSARY 该“伪舍入模式”实际是指明所要求的操作必须是精确的，，因此不需要舍入操作。<br/>
     *                     BigDecimal.ROUND_UP 总是在非 0 舍弃小数(即截断)之前增加数字。<br/>
     * @return
     */
    public static double divideDouble(double first, double second, int scale, int roundingMode) {
        BigDecimal b1 = new BigDecimal(first);
        BigDecimal b2 = new BigDecimal(second);
        if (roundingMode == -1) {
            roundingMode = BigDecimal.ROUND_HALF_EVEN;
        }
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

    /**
     * 返回两个double的乘积
     *
     * @param first  double值1
     * @param second double值2
     * @return double
     */
    public static double multiplyDouble(double first, double second) {
        BigDecimal b1 = new BigDecimal(first);
        BigDecimal b2 = new BigDecimal(second);
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 返回两个double的差值
     *
     * @param first  double值1
     * @param second double值2
     * @return double
     */
    public static double subtractDouble(double first, double second) {
        BigDecimal b1 = new BigDecimal(first);
        BigDecimal b2 = new BigDecimal(second);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 返回两个double的和值
     *
     * @param first
     * @param second
     * @return double
     */
    public static double sumDouble(double first, double second) {
        BigDecimal b1 = new BigDecimal(first);
        BigDecimal b2 = new BigDecimal(second);
        return b1.add(b2).doubleValue();
    }

    /**
     * 格式化double指定位数小数。例如将11.123格式化为11.1
     *
     * @param value    原double数字
     * @param decimals 小数位数
     * @return 式化后的double，注意为硬格式化不存在四舍五入
     */
    public static String formatDouble(double value, int decimals) {
        String doubleStr = "" + value;
        int index = doubleStr.indexOf(".") != -1 ? doubleStr.indexOf(".")
                : doubleStr.indexOf(",");
        // Decimal point can not be found...
        if (index == -1)
            return doubleStr;
        // Truncate all decimals
        if (decimals == 0) {
            return doubleStr.substring(0, index);
        }
        int len = index + decimals + 1;
        if (len >= doubleStr.length())
            len = doubleStr.length();
        double d = Double.parseDouble(doubleStr.substring(0, len));
        return String.valueOf(d);
    }

    /**
     * 生成一个指定位数的随机数，并将其转换为字符串作为函数的返回值
     *
     * @param numberLength 随机数的位数
     * @return 注意随机数可能以0开头
     */
    public static String randomNumber(int numberLength) {
        // 记录生成的每一位随机数
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numberLength; i++) {
            // 每次生成一位,随机生成一个0-10之间的随机数,不含10。
            Double ranDouble = Math.floor(Math.random() * 10);
            sb.append(ranDouble.intValue());
        }
        return sb.toString();
    }

    /**
     * 生成一个在最大数和最小数之间的随机数。会出现最小数，但不会出现最大数
     * @param minNum 最小数
     * @param maxNum 最大数
     * @return int
     */
    public static int randomNumber(int minNum, int maxNum) {
        if (maxNum <= minNum) {
            throw new RuntimeException("maxNum必须大于minNum!");
        }
        //计算出来差值
        int subtract = maxNum - minNum;
        Double ranDouble = Math.floor(Math.random() * subtract);
        return ranDouble.intValue() + minNum;
    }

}
