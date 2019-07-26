package com.yuhs.utils.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * 发送邮件
 * Created by yuhaisheng on 2019/6/18.
 */
public class MailUtils_2 {

    private MimeMessage mimeMsg;
    private Session session;
    private Properties props;
    private String username;
    private String password;
    private Multipart mp;

    /**
     * 构造函数
     *
     * @param smtp     发送邮件的邮箱smtp地址，例如：smtp.126.com
     * @param from     发送邮件的邮箱地址
     * @param username 用户名
     * @param password 密码
     */
    public MailUtils_2(String smtp, String from, String username, String password) {
        this.username = username;
        this.password = password;
        props = System.getProperties();
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.auth", "true");
        try {
            session = Session.getDefaultInstance(props, null);
        } catch (Exception e) {
            // 获取邮件会话错误
            return;
        }
        try {
            mimeMsg = new MimeMessage(session);
        } catch (Exception e) {
            //创建MIME邮件对象失败
            return;
        }
        try {
            //发信人
            mimeMsg.setFrom(new InternetAddress(from));
        } catch (Exception e) {
            //发送邮件异常
            return;
        }
    }

    /**
     * 定义邮件主题
     *
     * @param mailSubject 邮件标题
     * @return
     */
    public boolean setSubject(String mailSubject) {
        try {
            mimeMsg.setSubject(mailSubject);
            return true;
        } catch (Exception e) {
            //定义邮件主题发生错误
            return false;
        }
    }

    /**
     * 定义邮件正文
     *
     * @param mailBody 邮件内容
     * @return
     */
    public boolean setBody(String mailBody) {
        try {
            BodyPart bp = new MimeBodyPart();
            bp.setContent(mailBody, "text/html;charset=UTF-8");
            mp = new MimeMultipart();
            mp.addBodyPart(bp);
            return true;
        } catch (Exception e) {
            //定义邮件正文时发生错误
            return false;
        }
    }

    /**
     * 定义收信人
     *
     * @param to 收件人
     * @return
     */
    public boolean setTo(String to) {
        if (to == null) {
            return false;
        }
        try {
            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            return true;
        } catch (Exception e) {
            //设置收件人异常
            return false;
        }
    }

    /**
     * 定义抄送人
     *
     * @param copyto 抄送人
     * @return
     */
    public boolean setCopyTo(String copyto) {
        if (copyto == null) {
            return false;
        }
        try {
            mimeMsg.setRecipients(Message.RecipientType.CC, (Address[]) InternetAddress.parse(copyto));
            return true;
        } catch (Exception e) {
            //设置抄送人异常
            return false;
        }
    }

    /**
     * 发送邮件
     *
     * @return
     */
    private boolean sendOut() {
        try {
            mimeMsg.setContent(mp);
            mimeMsg.saveChanges();
            //邮件发送中
            Session mailSession = Session.getInstance(props, null);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect((String) props.get("mail.smtp.host"), username, password);
            transport.sendMessage(mimeMsg, mimeMsg.getRecipients(Message.RecipientType.TO));
            //发送成功
            transport.close();
            return true;
        } catch (Exception e) {
            //发送失败
            return false;
        }
    }

    /**
     * 调用sendOut方法完成发送
     *
     * @param to      收件人
     * @param title   标题
     * @param content 正文
     * @return
     */
    public boolean send(String to, String title, String content) {
        if (!setSubject(title)) {
            return false;
        }
        if (!setBody(content)) {
            return false;
        }
        if (!setTo(to)) {
            return false;
        }
        if (!sendOut()) {
            return false;
        }
        return true;
    }

    /**
     * 调用sendOut方法完成发送
     *
     * @param to      收件人
     * @param copyto  抄送人
     * @param title   标题
     * @param content 正文
     * @return
     */
    public boolean send(String to, String copyto, String title, String content) {
        if (!setSubject(title)) {
            return false;
        }
        if (!setBody(content)) {
            return false;
        }
        if (!setTo(to)) {
            return false;
        }
        if (!setCopyTo(copyto)) {
            return false;
        }
        if (!sendOut()) {
            return false;
        }
        return true;
    }
}
