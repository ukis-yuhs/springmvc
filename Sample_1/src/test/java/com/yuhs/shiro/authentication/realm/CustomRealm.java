package com.yuhs.shiro.authentication.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

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
//        String password = "123456";
        String password = "0659c7992e268962384eb17fafe88364";
        String salt = "abc";
        //3.如果查询不到返回null
        if (!userCode.equals("zhangsan")) {
            return null;
        }
        //4.如果查询到返回AuthenticationInfo
//        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
//                userCode,password,this.getName()
//        );
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userCode,password, ByteSource.Util.bytes(salt),this.getName()
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

        return null;
    }


}
