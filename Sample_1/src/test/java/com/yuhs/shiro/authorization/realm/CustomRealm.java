package com.yuhs.shiro.authorization.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Realm
 * Created by yuhaisheng on 2019/4/25.
 */
public class CustomRealm extends AuthorizingRealm {

    /**
     * 设置realm名字
     * @param name
     */
    @Override
    public void setName(String name) {
        super.setName("CustomRealm");
    }
    /**
     * 用于认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        //1.从token中取出 身份信息
        String userCode = (String)token.getPrincipal();
        //2.根据用户输入的usercode从数据库中查询
        //模拟数据库查询结果

        //3.如果查询不到返回null

        //模拟数据库得到的密码
        String password = "123";
        //4.如果查询到返回AuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userCode,password,this.getName()
        );
        return simpleAuthenticationInfo;
    }

    /**
     * 用于授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {

        //1.从principals中取出 身份信息
        //将getPrimaryPrincipal方法返回值转为真是身份类型
        //（在上面的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中的身份信息）
        String userCode = (String)principals.getPrimaryPrincipal();
        //2.根据身份信息获取权限信息
        //模拟数据库查询结果
        List<String> permissions = new ArrayList();
        permissions.add("user:create");
        permissions.add("items:add");

        //查到权限数据，返回授权信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }
}
