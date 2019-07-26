package com.yuhs.utils.regex;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/6/13.
 */
public class RegexUtilsTest {
    @Test
    public void testCheckEMail() {
        //验证邮箱总规则
        //邮箱名允许大小写字母数字下划线，域名可以含数字、大小写字母、点、下划线，如果有些邮箱要求不同，可自行修改
        RegexUtils regexUtils = new RegexUtils();
        //@之前规则
        //允许大小写字母和下划线
        RegexUtils before = new RegexUtils(RegexUtils.letterAndNumAndUnderLine);
        //允许重复1-N次
        before.repeatOneOrMore();

        //将@之前的规则追加到总规则
        regexUtils.append(before);
        //追加上@符号
        regexUtils.append("@");

        //@之后到最后一个域名点之前的规则
        //允许大小写字母和下划线
        RegexUtils after = new RegexUtils(RegexUtils.letterAndNumAndUnderLine);
        //允许点，防止邮箱二级域名,如：@vip.qq.com
        after.or(".");
        //域名中允许横线
        after.or("-");
        //允许重复1-N次
        after.repeatOneOrMore();

        //追加到总规则
        regexUtils.append(after);

        //顶级域名前的点
        regexUtils.append(".");
        //顶级域名的规则
        RegexUtils last = new RegexUtils(RegexUtils.lowLetter);//顶级域名只允许小写字母
        //允许重复1-N次
        last.repeatOneOrMore();

        //追加到总规则
        regexUtils.append(last);

        //打印总正则
        //[a-zA-Z0-9_]+@[a-zA-Z0-9_\.-]+\.[a-z]+
        System.out.println(regexUtils);

    }

    @Test
    public void test() {
        RegexUtils regexUtils = new RegexUtils("[1-9][0-9]*");
//        Pattern p = Pattern.compile(regexUtils.toString());
//        Matcher m = p.matcher("1");
        System.out.println(regexUtils.isMatches("023"));
        System.out.println(regexUtils.replaceMatches("sdfsdf564165werfds57sdfsdf151wer46554", "-"));
        System.out.println(regexUtils.getMatches("sdfsdf564165werfds57sdfsdf151wer46554").size());
        for(String a : regexUtils.getMatches("sdfsdf564165werfds57sdfsdf151wer46554")){
            System.out.println(a);
        }
    }
}
