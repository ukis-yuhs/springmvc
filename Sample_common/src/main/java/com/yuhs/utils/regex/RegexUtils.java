package com.yuhs.utils.regex;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式生成工具类
 * Created by yuhaisheng on 2019/6/13.
 */
public class RegexUtils {

    //记录拼接的表达式
    private StringBuffer sb = new StringBuffer();

    //正则表达式的特殊字符，需要进行转义处理
    private String expectChar = ".+*\\$^?{}()[]\\|";

    /**
     * 匹配汉字
     */
    public static RegexUtils chinese = new RegexUtils("[\u4e00-\u9fa5]");

    /**
     * 行首
     */
    public static RegexUtils lineHead = new RegexUtils("$");

    /**
     * 行尾
     */
    public static RegexUtils lineTail = new RegexUtils("^");

    /**
     * 匹配除换行外的所有字符
     */
    public static RegexUtils anyButLine = new RegexUtils(".");

    /**
     * 匹配数字
     */
    public static RegexUtils num = new RegexUtils("[0-9]");

    /**
     * 匹配大写字母
     */
    public static RegexUtils upperLetter = new RegexUtils("[A-Z]");

    /**
     * 匹配小写字母
     */
    public static RegexUtils lowLetter = new RegexUtils("[a-z]");

    /**
     * 匹配大小写字母
     */
    public static RegexUtils letter = new RegexUtils("[a-zA-Z]");

    /**
     * 匹配小写字母和数字
     */
    public static RegexUtils lowLetterAndNum = new RegexUtils("[a-z0-9]");


    /**
     * 匹配大写字母和数字
     */
    public static RegexUtils upperLetterAndNum = new RegexUtils("[A-Z0-9]");


    /**
     * 匹配大小写字母和数字
     */
    public static RegexUtils letterAndNum = new RegexUtils("[a-zA-Z0-9]");

    /**
     * 匹配大小写字母、数字、下划线
     */
    public static RegexUtils letterAndNumAndUnderLine = new RegexUtils("[a-zA-Z0-9_]");

    /**
     * 匹配一个单词的边界
     */
    public static RegexUtils boundary = new RegexUtils("\\b");

    /**
     * 匹配一个非单词的边界
     */
    public static RegexUtils notBoundary = new RegexUtils("\\B");

    /**
     * 匹配任何空白字符，包括空格、制表符、换页符等。与 [ \f\n\r\t\v] 等效。
     */
    public static RegexUtils blank = new RegexUtils("\\s");

    /**
     * 匹配任何非空白字符。与 [^ \f\n\r\t\v] 等效。
     */
    public static RegexUtils notBlank = new RegexUtils("\\s");

    /**
     * 匹配任何字类字符，包括下划线。与"[A-Za-z0-9_]"等效。
     */
    public static RegexUtils anyChar = new RegexUtils("\\w");

    /**
     * 与任何非单词字符匹配。与"[^A-Za-z0-9_]"等效。
     */
    public static RegexUtils notAnyChar = new RegexUtils("\\W");

    /**
     * 无参构造
     */
    public RegexUtils() {
    }

    /**
     * 正则表达式构造
     *
     * @param regex 正则表达式
     */
    public RegexUtils(String regex) {
        sb = new StringBuffer(regex);
    }

    /**
     * RegexUtils构造
     *
     * @param regex 正则表达式
     */
    public RegexUtils(RegexUtils regex) {
        sb = new StringBuffer(regex.toString());
    }

    /**
     * 最短匹配
     */
    public void minMatch() {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("?");
    }

    /**
     * 重复0-N次，等效于 {0,}
     */
    public void repeatZeroOrMore() {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("*");
    }

    /**
     * 重复0或1次，等效于 {0,1}或?
     */
    public void repeatZeroOrOne() {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("?");
    }

    /**
     * 重复1-N次，等效于 {1,}
     */
    public void repeatOneOrMore() {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("+");
    }

    /**
     * 重复num次
     *
     * @param num 次数
     */
    public void repeat(int num) {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("{" + num + "}");
    }

    /**
     * 重复min-max次
     *
     * @param min 最小
     * @param max 最大
     */
    public void repeat(int min, int max) {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("{" + min + "," + max + "}");
    }

    /**
     * 至少重复num次
     *
     * @param num 次数
     */
    public void repeatMin(int num) {
        //判断最外面是否是中括号,不是加上中括号
        sb = addMidBracketIfNo(sb);
        sb.append("{" + num + ",}");
    }

    /**
     * 若字符串两边不是中括号增加上中括号
     *
     * @param target
     * @return
     */
    private StringBuffer addMidBracketIfNo(StringBuffer target) {
        if (!chkMidBracket(target)) {
            return addMinBrackets(target);
        } else {
            return sb;
        }
    }

    /**
     * 字符串两边加上()
     *
     * @param target 字符串
     * @return
     */
    private StringBuffer addMinBrackets(StringBuffer target) {
        return new StringBuffer("(" + target + ")");
    }

    /**
     * 字符串两边加上[]
     *
     * @param target 字符串
     * @return
     */
    private StringBuffer addMidBrackets(StringBuffer target) {
        return new StringBuffer("[" + target + "]");
    }

    /**
     * 去掉字符串两边的[]
     *
     * @param target 字符串
     * @return
     */
    private String removeMidBrackets(StringBuffer target) {
        return target.toString().replaceAll("^\\[", "").replaceAll("\\]$", "");
    }

    /**
     * 对字符串里面的特殊字符进行处理
     *
     * @param target 源字符串
     * @return
     */
    private String handleExpectChar(String target) {
        StringBuffer sbTemp = new StringBuffer();
        char[] arr = target.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (expectChar.indexOf(arr[i]) != -1) {
                sbTemp.append("\\" + arr[i]);
            } else {
                sbTemp.append(arr[i]);
            }
        }
        return sbTemp.toString();
    }

    /**
     * 判断字符串最外围是否为中括号
     *
     * @param target
     * @return
     */
    private boolean chkMidBracket(StringBuffer target) {
        if ("[".equals(target.substring(0, 1)) && "]".equals(target.substring(target.length() - 1))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 追加一个正则表达式
     *
     * @param regex 正则表达式
     */
    public void append(RegexUtils regex) {
        sb.append(regex.toString());
    }

    /**
     * 追加一个正则表达式
     *
     * @param regex 正则表达式
     */
    public void append(String regex) {
        sb.append(handleExpectChar(regex));
    }

    /**
     * 或一个正则
     *
     * @param regex 正则表达式
     */
    public void or(RegexUtils regex) {
        or(regex.toString());
    }

    /**
     * 或一个正则表达式
     *
     * @param regex 正则表达式
     */
    public void or(String regex) {
        //最外层为中括号
        if (chkMidBracket(sb)) {
            //首先去掉两边的中括号
            sb = new StringBuffer(removeMidBrackets(sb));
        }
        if (regex.length() > 1) {
            //字符串用|
            sb.append("|" + handleExpectChar(regex));
        } else {
            //非字符串直接追加
            sb.append(handleExpectChar(regex));
        }
        //追加上中括号
        sb = new StringBuffer(addMidBrackets(sb));
    }

    /**
     * 对自己进行否处理
     */
    public void not() {
        sb = new StringBuffer("[^" + sb + "]");
    }

    /**
     * 返回正则表达式
     *
     * @return
     */
    public String toString() {
        return sb.toString();
    }

    /**
     * 校验字符串(chkStr)是否符合当前正则
     *
     * @param chkStr 被校验的字符串
     * @return
     */
    public boolean isMatches(String chkStr) {
        Pattern p = Pattern.compile(this.toString());
        Matcher m = p.matcher(chkStr);
        return m.matches();
    }

    /**
     * 提取字符串(sourceStr)符合当前正则的子字符串集合
     *
     * @param sourceStr 被提取的字符串
     * @return
     */
    public List<String> getMatches(String sourceStr) {
        Pattern p = Pattern.compile(this.toString());
        Matcher m = p.matcher(sourceStr);
        List<String> strList = new LinkedList<String>();
        while (m.find()) {
            strList.add(m.group());
        }
        return strList;
    }

    /**
     * 替换字符串(sourceStr)符合当前正则的子字符串为replaceStr
     *
     * @param sourceStr  源字符串
     * @param replaceStr 替换的字符串
     * @return
     */
    public String replaceMatches(String sourceStr, String replaceStr) {
        return sourceStr.replaceAll(this.toString(), replaceStr);
    }

}
