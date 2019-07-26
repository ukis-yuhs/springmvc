package com.yuhs.utils.idCard;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 身份证号操作工具类
 * Created by yuhaisheng on 2019/6/26.
 */
public class IdCardUtils {

    /**
     * 身份证号码的规则:
     * 身份证号码共18位.
     * 前6位是地址码. 省
     * 关于全国行政区域代码可参照民政部网站（http://www.mca.gov.cn/article/sj/xzqh/2019/）
     * 7--14位是出生年月日
     * 后面4位是生成的代码】
     * 第15-17位表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配给女性。
     * 第17位用来做性别判定:偶数 -- 女 ; 奇数--- 男 .
     * 前17位的构成： 2(省)+2(市)+2(县)+4(年)+2(月)+2(日)+2(序号)+1(性别)
     * 第十八位数字的计算方法为：
     * <p>
     * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * 2.将这17位数字和系数相乘的结果相加。
     * 3.用加出来和除以11，看余数是多少？
     * 4余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     */

    // 行政区域代码（前6位是地址码. 省）
    private static Map<String, Integer> areaCode = new HashMap<String, Integer>();
    // 前17位权重
    private static final int[] WEIGHTED_CODE = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    // 第18位校验码
    private static final char[] CHECK_CODE = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    // 获取性别
    private static final String[] SEX_CODE = {"M", "W"};
    // 身份证验证
    private static final String REG_ID_CARD = "[1-9]\\d{5}[1-9]\\d{3}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9xX]";

    /**
     * 北京市行政区域代码
     */
    static {
        areaCode.put("东城区", 110101);
        areaCode.put("西城区", 110102);
        areaCode.put("崇文区", 110103);
        areaCode.put("宣武区", 110104);
        areaCode.put("朝阳区", 110105);
        areaCode.put("丰台区", 110106);
        areaCode.put("石景山区", 110107);
        areaCode.put("海淀区", 110108);
        areaCode.put("门头沟区", 110109);
        areaCode.put("房山区", 110111);
        areaCode.put("通州区", 110112);
        areaCode.put("顺义区", 110113);
        areaCode.put("昌平区", 110114);
        areaCode.put("大兴区", 110115);
        areaCode.put("怀柔区", 110116);
        areaCode.put("平谷区", 110117);
        areaCode.put("密云县", 110228);
        areaCode.put("延庆县", 110229);
    }

    /**
     * 随机身份证号码
     * @return
     */
    public static String generateIdCard() {
        StringBuilder idCode = new StringBuilder();
        idCode.append(generateAreaCode());                  // 6位
        idCode.append(generateBirthday());                  // 7-14位
        idCode.append(generateCode());                      // 15-17位
        idCode.append(generate18Code(idCode.toString()));   // 18位
        return idCode.toString();
    }

    /**
     * 将15位身份证号码转换位18位身份证号码
     *
     * @param idCardNo 15位身份证号码
     * @return
     */
    public static String convert15to18(String idCardNo) {
        //变换后身份证号码
        String finalID = null;
        //TODO 此方法转换默认都是20世纪出生人口的15位身份证号码变更
        String century = "19";
        if (idCardNo.length() == 18) {
            finalID = idCardNo;
        } else {
            // 通过加入世纪码, 变成17位的新号码本体.
            String tempNo = idCardNo.substring(0, 6) + century + idCardNo.substring(6);
            finalID = tempNo + generate18Code(tempNo);
        }
        if (!isIdCardNo(finalID)) {
            throw new IllegalArgumentException("您输入的身份证号码不正确:" + idCardNo);
        }
        return finalID;
    }

    /**
     * 将18位身份证号码转换为15位身份证号码
     *
     * @param idCardNo18 18位身份证号码
     * @return
     */
    public static String convert18to15(String idCardNo18) {
        if (!(isIdCardNo(idCardNo18) && idCardNo18.length() == 18)) {
            throw new IllegalArgumentException("身份证号参数格式不正确！");
        }
        return idCardNo18.substring(0, 6) + idCardNo18.substring(8, 17);
    }

    /**
     * 获取18位身份证号码的行政区域（可配合民政局网站数据获取地区名称）
     *
     * @param idCardNo18
     * @return
     */
    public static String getAreaCode(String idCardNo18) {
        return idCardNo18.substring(0, 6);
    }

    /**
     * 获取18位身份证号码的生年
     *
     * @param idCardNo18 18位身份证号码
     * @return
     */
    private static String getBirthYear(String idCardNo18) {
        return idCardNo18.substring(6, 10);
    }

    /**
     * 获取18位身份证号码的生年月日
     *
     * @param idCardNo18 18位身份证号码
     * @return
     */
    public static String getBirthday(String idCardNo18) {
        return idCardNo18.substring(6, 14);
    }

    /**
     * 获取18位身份证号码指定年，换算出的年龄（未具体到月日）
     *
     * @param idCardNo18 18位身份证号码
     * @return
     */
    public static int getAge(String idCardNo18) {
        idCardNo18 = convert15to18(idCardNo18);
        Calendar instance = Calendar.getInstance();
        int currentYear = instance.get(Calendar.YEAR);
        int age = currentYear - Integer.parseInt(getBirthYear(idCardNo18));
        return age;
    }

    /**
     * 获取18位身份证号码指定年，换算出的年龄（具体到月日）
     * @param idCardNo18 18位身份证号码
     * @return
     * @throws ParseException
     */
    public static  int age(String idCardNo18) throws ParseException {
        String birthday = getBirthday(convert15to18(idCardNo18));
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date birthDay = dateFormat.parse(birthday);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("Birthday is after date!");
        }

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);
        int age = year - cal.get(Calendar.YEAR);

        int monthBirth = cal.get(Calendar.MONTH);
        if (month == monthBirth) {
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
            if (dayOfMonth < dayOfMonthBirth) {
                // 如果生日在当月，但是未达到生日当天的日期，年龄减一
                age--;
            }
        } else if (month < monthBirth) {
            // 如果当前月份未达到生日的月份，年龄计算减一
            age--;
        }

        return age;
    }

    /**
     * 获取18位身份证号码到性别
     *
     * @param idCardNo18
     * @return
     */
    public static String getSex(String idCardNo18) {
        idCardNo18 = convert15to18(idCardNo18);
        // 定位到倒数第二位
        char index = idCardNo18.charAt(16);
        return SEX_CODE[index % 2];
    }

    /**
     * 生成第18位校验码
     *
     * @param tempNo 前17位临时码
     * @return
     */
    public static char generate18Code(String tempNo) {
        if (tempNo.length() < 17) {
            throw new IllegalArgumentException("位数不足17位");
        }
        // 计算前17位乘权重所得和
        int checkSum = 0;
        for (int i = 0; i < WEIGHTED_CODE.length; i++) {
            int ai = Integer.parseInt("" + tempNo.charAt(i)); // 位于i位置的数值
            checkSum = checkSum + ai * WEIGHTED_CODE[i];
        }
        // 求余数（取得校验码索引值）获取校验码
        return CHECK_CODE[checkSum % 11];
    }

    /**
     * 获取行政区域码
     *
     * @return
     */
    public static int generateAreaCode() {
        int index = (int) (Math.random() * areaCode.size());
        Collection<Integer> values = areaCode.values();
        return (Integer) values.toArray()[index];
    }

    /**
     * 获取生日
     * @return
     */
    public static String generateBirthday() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(Calendar.YEAR, (int) (Math.random() * 60) + 1950);
        birthday.set(Calendar.MONTH, (int) (Math.random() * 12));
        birthday.set(Calendar.DATE, (int) (Math.random() * 31));

        StringBuilder builder = new StringBuilder();
        builder.append(birthday.get(Calendar.YEAR));
        long month = birthday.get(Calendar.MONTH) + 1L;
        if (month < 10) {
            builder.append("0");
        }
        builder.append(month);
        long date = birthday.get(Calendar.DATE);
        if (date < 10) {
            builder.append("0");
        }
        builder.append(date);
        return builder.toString();
    }

    /**
     * 随机第15-17位
     * @return
     */
    public static String generateCode() {
        int code = (int) (Math.random() * 1000);
        if (code < 10) {
            return "00" + code;
        } else if (code < 100) {
            return "0" + code;
        } else {
            return "" + code;
        }
    }

    /**
     * 判断是否是合法的日期
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static boolean isDate(int year, int month, int day) {// day=29|30|31
        // 日期口诀: 一三五七八十腊（12月），三十一天永不差；平年二月二十八；闰年二月把一加 ；四六九冬（11月）三十日
        // 闰年:四年一闰,百年不闰,四百年再闰.
        if (month == 2) {// 当月份=2时
            if (day == 29 && !(year % 4 == 0 && year % 100 != 0 | year % 400 == 0)) {
                return false;
            }
        }
        // 天数是31天,月份却是:4|6|9|11
        if (day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            return false;
        }
        // 其它正常情况
        return true;
    }

    /**
     * 验证身份证号码的正确性
     * @param idCardNO
     * @return
     */
    public static boolean isIdCardNo(String idCardNO) {
        // 正则匹配
        if (!idCardNO.matches(REG_ID_CARD)) {
            System.out.println("正则不匹配");
            return false;
        }
        // TODO 可添加行政区域验证（1-6位）

        // 年月日验证（7-14）
        int year = Integer.parseInt(idCardNO.substring(6, 10));
        int month = Integer.parseInt(idCardNO.substring(10, 12));
        int day = Integer.parseInt(idCardNO.substring(12, 14));
        if (day > 28 && !isDate(year, month, day)) {
            System.out.println("生年月日不是正确的日期");
            return false;
        }

        // 获取加权后的总和
        int sum = 0;
        idCardNO = idCardNO.trim();// 去空格
        for (int i = 0; i < WEIGHTED_CODE.length; i++) {
            // 获取身份证前17位的每一位,转换为数字
            int j = Integer.parseInt(idCardNO.substring(i, i + 1));
            // 获取加权后所得的和
            sum += (j * WEIGHTED_CODE[i]);
        }

        // 比较输入身份证与所得校验码比较(忽略大小写)
        if (!idCardNO.substring(17).equalsIgnoreCase(String.valueOf(CHECK_CODE[sum % 11]))) {
            System.out.println("校验不成功");
            return false;
        }
        return true;
    }

    /**
     * 获取权重数组
     * @return
     */
    public static int[] getWeightedCode() {
        int[] wi = new int[17];
        // 获取加权码wi:7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
        for (int i = 1; i < 18; i++) {
            // 获取加权数字
            int winum = (int) (Math.pow(2, i) % 11);
            // 将加权数反向存入数组
            wi[wi.length - i] = winum;
        }
        return wi;
    }
}

