package com.yuhs.utils.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO 未测试
 * 使用正则校验数据
 * Created by yuhaisheng on 2019/6/12.
 */
public class RegexVerifyUtils {
    /**
     * 匹配非负整数（正整数 + 0)
     */
    public static final String non_negative_integers_regexp = "^\\d+$";

    /**
     * 匹配不包括零的非负整数（正整数 > 0)
     */
    public static final String non_zero_negative_integers_regexp = "^[1-9]+\\d*$";

    /**
     * 匹配非正整数（负整数 + 0）
     */
    public static final String non_positive_integers_regexp = "^((-\\d+)|(0+))$";

    /**
     * 匹配负整数
     */
    public static final String negative_integers_regexp = "^-[0-9]*[1-9][0-9]*$";

    /**
     * 匹配整数
     */
    public static final String integer_regexp = "^-?\\d+$";

    /**
     * 匹配非负浮点数（正浮点数 + 0）
     */
    public static final String non_negative_rational_numbers_regexp = "^\\d+(\\.\\d+)?$";

    /**
     * 匹配正浮点数
     */
    public static final String positive_rational_numbers_regexp = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";

    /**
     * 匹配非正浮点数（负浮点数 + 0）
     */
    public static final String non_positive_rational_numbers_regexp = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";

    /**
     * 匹配负浮点数
     */
    public static final String negative_rational_numbers_regexp = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";

    /**
     * 匹配浮点数
     */
    public static final String rational_numbers_regexp = "^(-?\\d+)(\\.\\d+)?$";

    /**
     * 匹配由26个英文字母组成的字符串
     */
    public static final String letter_regexp = "^[A-Za-z]+$";

    /**
     * 匹配由26个英文字母的大写组成的字符串
     */
    public static final String upward_letter_regexp = "^[A-Z]+$";

    /**
     * 匹配由26个英文字母的小写组成的字符串
     */
    public static final String lower_letter_regexp = "^[a-z]+$";

    /**
     * 匹配由数字和26个英文字母组成的字符串
     */
    public static final String letter_number_regexp = "^[A-Za-z0-9]+$";

    /**
     * 匹配由数字、26个英文字母或者下划线组成的字符串
     */
    public static final String letter_number_underline_regexp = "^\\w+$";

    /**
     * 链接正则表达式
     */
    private static final String REGEX_URL = "(http:|https:)//[^[A-Za-z0-9\\._\\?%&+\\-=/#]]*";

    /**
     * 正则表达式校验字符串是否包含指定规则的字符串
     * @param text 内容
     * @param patternStr 正则表达式
     * @return true，false
     */
    public static boolean isRegexContains(String text, String patternStr) {
        Pattern pat = Pattern.compile(patternStr);
        Matcher matcher = pat.matcher(text);
        return matcher.find();
    }

    /**
     * 根据传入的正则表达式和字符串进行校验
     *
     * @param text       内容
     * @param patternStr 正则表达式
     * @return true，false
     */
    public static boolean chkTextBypattern(String text, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
        return pattern.matcher(text).matches();
    }

    /**
     * 检查是否为URL
     * 包括Http, Ftp, News, Nntpurl, Telnet, Gopher, Wais, Mailto, File, Prosperurl, Otherurl
     *
     * @param url 网址，不仅仅http或者https
     * @return true，false
     */
    public static boolean chkAllUrl(String url) {
        String regex = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "[a-z]{2,6})" // first level domain- .com or .museum
                + "(:[0-9]{1,4})?" // 端口- :80
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(url).matches();
    }

    /**
     * 判断是否为IPV4地址
     *
     * @param text 内容
     * @return true，false
     */
    public static boolean chkIPV4(String text) {
        Pattern pattern = Pattern
                .compile("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
        return pattern.matcher(text).matches();
    }

    /**
     * 检查是否为邮箱地址
     *
     * @param email 邮箱地址
     * @return true，false
     */
    public static boolean chkEmail(String email) {
        Pattern pattern = Pattern
                .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        return pattern.matcher(email).matches();
    }

    /**
     * 判断字符串是不是纯数字([0-9]+)
     *
     * @param target 字符串
     * @return true，false
     */
    public static boolean isNumeric(String target) {
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(target).matches();
    }

    /**
     * 判断字符串只含有字母和数字中一种或两种
     *
     * @param target 字符串
     * @return true，false
     */
    public static boolean isNumerAndEnglish(String target) {
        byte[] bytes = target.getBytes();
        for (byte temp : bytes) {
            if (temp < 48 || (temp > 57 && temp < 65)
                    || (temp > 90 && temp < 97) || temp > 122) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串中是否包含http链接
     * @param target
     * @return
     */
    public static boolean existUrl(String target) {
        return isRegexContains(target, REGEX_URL);
    }
}
