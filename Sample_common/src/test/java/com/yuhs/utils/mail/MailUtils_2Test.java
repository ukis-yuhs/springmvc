package com.yuhs.utils.mail;

import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/6/18.
 */
public class MailUtils_2Test {

    @Test
    public void test() {
        // smtp服务器
        String smtp = "smtp.163.com";
        // 邮件显示名称
        String from = "haisheng023@163.com";
        // 发件人真实的账户名
        String username = "haisheng023@163.com";
        // 发件人密码
        String password = "xxxx";

        MailUtils_2 theMail = new MailUtils_2(smtp, from, username, password);
        String to = "haisheng.yu@nttdata.com";// 收件人的邮件地址
        String copyto = "haisheng023@163.com";// 抄送人邮件地址
        String subject = "测试MailUtils_2Test邮件";// 邮件标题
        String content = "测试内容";// 邮件内容
        boolean bool = theMail.send(to, copyto, subject, content);
        System.out.println(bool);
    }
}
