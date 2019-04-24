package com.yuhs.utils.zh;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by yuhaisheng on 2019/4/24.
 */
public class PinyinUtil {
    private static HanyuPinyinOutputFormat defaultFormat;

    static {
        defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    }

    /**
     * 将字符串中的中文转化为拼音,其他字符不变
     * @param chinese
     * @return
     */
    public static String getFullSpell(String chinese) {
        char[] inputs = chinese.trim().toCharArray();
        StringBuffer output = new StringBuffer();

        for (char input : inputs) {
            output.append(getFullSpellOfWord(input));
        }
        return output.toString();
    }

    /**
     * 将字符串中的中文转化为拼音,其他字符不变,指定分隔符
     * @param chinese
     * @param separator
     * @return
     */
    public static String getFullSpell(String chinese, String separator) {
        char[] inputs = chinese.trim().toCharArray();
        StringBuffer output = new StringBuffer();

        for (int i = 0; i < inputs.length; i++) {
            if (i == inputs.length) {
                output.append(getFullSpellOfWord(inputs[i]));
            } else {
                output.append(getFullSpellOfWord(inputs[i])).append(separator);
            }
        }
        return output.toString();
    }

    /**
     * 将字符串中的中文转化为拼音,其他字符不变,首字母大小
     * @param chinese
     * @return
     */
    public static String getFullSpellFirstUp(String chinese) {
        char[] inputs = chinese.trim().toCharArray();
        StringBuffer output = new StringBuffer();
        char[] cs= null;
        for (char input : inputs) {
            cs = getFullSpellOfWord(input).toCharArray();
            cs[0] -= 32;
            output.append(String.valueOf(cs));
        }
        return output.toString();
    }
    /**
     * 将字符串中的中文转化为拼音,其他字符不变,首字母大小,指定分割符
     * @param chinese
     * @return
     */
    public static String getFullSpellFirstUp(String chinese, String separator) {
        char[] inputs = chinese.trim().toCharArray();
        StringBuffer output = new StringBuffer();
        char[] cs= null;
        for (int i = 0; i < inputs.length; i++) {
            cs = getFullSpellOfWord(inputs[i]).toCharArray();
            cs[0] -= 32;
            if (i == inputs.length) {
                output.append(String.valueOf(cs));
            } else {
                output.append(String.valueOf(cs)).append(separator);
            }
        }
        return output.toString();
    }
    /**
     * 获取汉字串拼音首字母，英文字符不变
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    public static String getFirstSpell(String chinese) {
        char[] inputs = chinese.trim().toCharArray();
        StringBuffer output = new StringBuffer();
        for (char input : inputs) {
            String temp = getFullSpellOfWord(input);
            if (StringUtils.isNotBlank(temp)) {
                output.append(temp.charAt(0));
            }
        }
        return output.toString();
    }

    /**
     * 获取一个汉字全拼音，其他字符忽略
     * @param word
     * @return
     */
    public static String getFullSpellOfWord(char word) {
        String fullSpell = "";
        try {
            if (Character.toString(word).matches("[\\u4E00-\\u9FA5]+")) {
                String[] temp = PinyinHelper.toHanyuPinyinStringArray(word, defaultFormat);
                if (temp != null && temp.length > 0) {
                    fullSpell = temp[0];
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return fullSpell;
    }
}
