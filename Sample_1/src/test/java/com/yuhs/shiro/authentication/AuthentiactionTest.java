package com.yuhs.shiro.authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by yuhaisheng on 2019/4/25.
 */
public class AuthentiactionTest {

    @Test
    public void testLoginAndLogout() {
        //创建securityManager工厂，通过ini配置文件创建securityManager工厂
        //"classpath:shiro.ini"
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_1/src/test/java/com/yuhs/shiro/shiro.ini"
        );
        //创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //将securityManager设置档期的运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //从SecurityUtils创建一个subject
        Subject subject = SecurityUtils.getSubject();
        //在认证提交前准备token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123456");
        //执行认证
        try {
            subject.login(token);
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
        }
        //是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过 = " + isAuthenticated);

        //退出操作
        subject.logout();
        //是否认证通过
        isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过 = " + isAuthenticated);
    }

    @Test
    public void testCustomRealm() {
        //创建securityManager工厂，通过ini配置文件创建securityManager工厂
        //"classpath:shiro.ini"
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_1/src/test/java/com/yuhs/shiro/realm/realm.ini"
        );
        //创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //将securityManager设置档期的运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //从SecurityUtils创建一个subject
        Subject subject = SecurityUtils.getSubject();
        //在认证提交前准备token令牌
//        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123456");
//        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan1","123456");
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123456");
        //执行认证
        try {
            subject.login(token);
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
        }
        //是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过 = " + isAuthenticated);
    }
}
