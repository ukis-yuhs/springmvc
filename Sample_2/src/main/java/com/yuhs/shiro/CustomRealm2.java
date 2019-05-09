package com.yuhs.shiro;

import com.yuhs.beans.SysPermission;
import com.yuhs.beans.SysUser;
import com.yuhs.dto.ActiveUser;
import com.yuhs.service.SysService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuhaisheng on 2019/5/5.
 */
public class CustomRealm2 extends AuthorizingRealm {

    @Autowired
    private SysService sysService;

    /**
     * 设置realm名字
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        super.setName("CustomRealm2");
    }

    /**
     * 用于认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.err.println("Realm 认证");
        //1.从token中取出 身份信息
        String userCode = (String) token.getPrincipal();

        System.err.println("userCode = " + userCode);

        //2.根据用户输入的usercode从数据库中查询
        SysUser sysUser = null;
        try {
            sysUser = sysService.findSystUserByUserCode(userCode);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //3.如果查询不到返回null
        if (sysUser == null) {
            return null;
        }
        String password = sysUser.getPassword();//数据库中取出密码
        String salt = sysUser.getSalt();//取出数据库中的盐

        //如果查询到返回认证信息AuthenticationInfo
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserid(sysUser.getId());
        activeUser.setUsercode(sysUser.getUsercode());
        activeUser.setUsername(sysUser.getUsername());
        //...
        //根据用户id取出菜单
        //通过SysService取出菜单
        List<SysPermission> menuList = null;
        try {
            menuList = sysService.findMenuListByUserId(sysUser.getId());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //将用户菜单设置到activeUser
        activeUser.setMenus(menuList);

        //4.如果查询到返回AuthenticationInfo
        //将activeUser设置到SimpleAuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                activeUser, password, ByteSource.Util.bytes(salt), this.getName()
        );
        return simpleAuthenticationInfo;
    }

    /**
     * 用于授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.err.println("Realm 授权");
        //1.从principals中取出 身份信息
        //将getPrimaryPrincipal方法返回值转为真是身份类型
        //（在上面的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中的身份信息）
        ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
        //2.根据身份信息获取权限信息
        List<SysPermission> sysPermissions = null;
        try {
            sysPermissions = sysService.findPermissionListByUserId(activeUser.getUserid());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        List<String> permissions = new ArrayList();
        if (sysPermissions != null && sysPermissions.size() != 0) {
            for (SysPermission sysPermission : sysPermissions) {
                //将数据库中权限标识符放入集合
                permissions.add(sysPermission.getPercode());
            }
        }
        //查到权限数据，返回授权信息（包括上面的permissions）
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将上面查询到授权信息填充到SimpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addStringPermissions(permissions);

        return simpleAuthorizationInfo;
    }

    /**
     * 清空缓存
     */
    public void clearCache() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
