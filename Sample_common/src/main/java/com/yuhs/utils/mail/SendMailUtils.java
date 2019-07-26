package com.yuhs.utils.mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * 用Spring的邮件封装类JavaMailSenderImpl发送邮件
 * 依赖spring-context-suppor.jar
 * Created by yuhaisheng on 2019/6/14.
 */
public class SendMailUtils {

    private String MAIL_HOST;
    private String MAIL_PORT;
    private String MAIL_USERNAME;
    private String MAIL_PASSWORD;

    public void setMAIL_PASSWORD(String MAIL_PASSWORD) {
        this.MAIL_PASSWORD = MAIL_PASSWORD;
    }

    public void setMAIL_HOST(String MAIL_HOST) {
        this.MAIL_HOST = MAIL_HOST;
    }

    public void setMAIL_USERNAME(String MAIL_USERNAME) {
        this.MAIL_USERNAME = MAIL_USERNAME;
    }

    /**
     * 普通文本邮件
     *
     * @param to      收件地址
     * @param subject 邮件标题
     * @param text    邮件内容
     */
    public void simpleMailSend(String to, String subject, String text) {
        //创建邮件服务器
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(MAIL_HOST);
//      mailSender.setPort(MAIL_PORT); //端口号不需要设置
        mailSender.setUsername(MAIL_USERNAME);
        mailSender.setPassword(MAIL_PASSWORD);

        //加认证机制
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth", true);
        javaMailProperties.put("mail.smtp.starttls.enable", true);
        javaMailProperties.put("mail.smtp.timeout", 5000);
        mailSender.setJavaMailProperties(javaMailProperties);

        //创建邮件内容
        //发送文本消息用SimpleMailMessage类
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(MAIL_USERNAME); //From发件方的账户，和Username的值相同
        message.setTo(to);              //To收件方
        message.setSubject(subject);    //Subject邮件主题
        message.setText(text);          //Text邮件内容

        //发送邮件
        mailSender.send(message);
        return;
    }
}
