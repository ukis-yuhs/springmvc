package com.yuhs.shiro.authorization;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * 授权测试
 * Created by yuhaisheng on 2019/4/25.
 */
public class AuthorizationTest {

    /**
     * 角色授权，资源授权测试
     */
    @Test
    public void testAuthorization(){
        // 创建SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_1/src/test/java/com/yuhs/shiro/authorization/shiro-permission.ini"
        );
        // 创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        // 将SecurityManager设置到系统运行环境，和Spring后将SecurityManager配置spring容器中，一般单例管理
        SecurityUtils.setSecurityManager(securityManager);
        //创建subject
        Subject subject = SecurityUtils.getSubject();

        //创建token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        //执行认证
        try {
            subject.login(token);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        //认证通过后执行授权
        //基于角色的授权
        //hasRole传入角色标识符
        boolean ishasRole = subject.hasRole("role1");
        System.out.println("ishasRole > " + ishasRole);
        boolean hasAllRoles = subject.hasAllRoles(Arrays.asList("role1","role2"));
        System.out.println("hasAllRoles > " + hasAllRoles);

        //使用check方法进行授权，如果授权不通过会抛出异常
        subject.checkRole("rolw2");

        //基于资源的授权
        //isPermitted传入权限标识符
        boolean isPermitted = subject.isPermitted("user:create");
        System.out.println("isPermitted > " + isPermitted);
        boolean isPermittedAll = subject.isPermittedAll("user:create","user:delete");
        System.out.println("isPermittedAll > " + isPermittedAll);

        subject.checkPermission("item:create:1");
    }

    @Test
    public void testCustomRealm() {
        //创建securityManager工厂，通过ini配置文件创建securityManager工厂
        //"classpath:shiro.ini"
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(
                "/Users/yuhaisheng/Documents/ideaProjects/springmvc/Sample_1/src/test/java/com/yuhs/shiro/authorization/realm/realm.ini"
        );
        //创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //将securityManager设置档期的运行环境中
        SecurityUtils.setSecurityManager(securityManager);
        //从SecurityUtils创建一个subject
        Subject subject = SecurityUtils.getSubject();
        //在认证提交前准备token令牌
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","123");
        //执行认证
        try {
            subject.login(token);
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
        }
        //是否认证通过
        boolean isAuthenticated = subject.isAuthenticated();
        System.out.println("是否认证通过 = " + isAuthenticated);

        //基于资源的授权
        //isPermitted传入权限标识符
        boolean isPermitted = subject.isPermitted("user:create");
        System.out.println("isPermitted > " + isPermitted);
        boolean isPermittedAll = subject.isPermittedAll("user:create","user:delete");
        System.out.println("isPermittedAll > " + isPermittedAll);

        subject.checkPermission("items:add:1");
    }
}
