package com.yuhs.utils.mail;

import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by yuhaisheng on 2019/6/14.
 */
public class SendMailUtilsTest {

    SendMailUtils sendMailUtils = null;
    @Before
    public void before() {
        sendMailUtils = new SendMailUtils();
        sendMailUtils.setMAIL_HOST("smtp.163.com");
        sendMailUtils.setMAIL_USERNAME("haisheng023@163.com");
        sendMailUtils.setMAIL_PASSWORD("xxxx");
    }

    /**
     * 普通文本邮件测试
     */
    @Test
    public void testSimpleMailSend() {
        String context = "用Spring的邮件封装类JavaMailSenderImpl发送邮件测试文本";
        try {
            String text = new String(context.getBytes("utf-8"), "utf-8");
            sendMailUtils.simpleMailSend("haisheng.yu@nttdata.com","测试普通文本邮件", text);
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

}
