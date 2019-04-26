package com.yuhs.service.impl;

import com.yuhs.dao.SysPermissionMapperEx;
import com.yuhs.dao.SysUserMapper;
import com.yuhs.dto.ActiveUser;
import com.yuhs.entity.SysPermission;
import com.yuhs.entity.SysUser;
import com.yuhs.entity.SysUserExample;
import com.yuhs.exception.CustomException;
import com.yuhs.service.SysService;
import com.yuhs.utils.secure.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuhaisheng on 2019/4/25.
 */
@Service
public class SysServiceImpl implements SysService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysPermissionMapperEx sysPermissionMapperEx;

    /**
     * 根据用户的身份和密码进行认证，如果认证通过，返回用户身份信息
     * @param userCode
     * @param password
     * @return
     * @throws Exception
     */
    public ActiveUser authenticat(String userCode, String password) throws Exception {
        //根据用户账号查询用户信息
        SysUser sysUser = findSystUserByUserCode(userCode);
        if (sysUser == null) {
            //抛出异常
            throw new CustomException("用户账号不存在");
        }

        //数据库密码（md5密码）
        String password_db = sysUser.getPassword();
        //对输入对密码和数据库密码进行对比，如果一致，认证通过
        //对页面输入对密码进行md5加密
        String password_input_md5 = MD5Utils.getMd5Sum(password);
        if (!password_input_md5.equalsIgnoreCase(password_db)) {
            throw new CustomException("输入密码不正确");
        }
        //得到用户id
        String userid = sysUser.getId();
        //根据用户id查询菜单
        List<SysPermission> menuList = this.findMenuListByUserId(userid);
        //根据用户id查询url
        List<SysPermission> urlList = this.findPermissionListByUserId(userid);


        //认证通过，返回用户身份信息
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUserid(sysUser.getId());
        activeUser.setUsercode(sysUser.getUsercode());
        activeUser.setUsername(sysUser.getUsername());

        //设定权限（菜单，url）
        activeUser.setMenus(menuList);
        activeUser.setPermissions(urlList);

        return activeUser;
    }

    /**
     * 根据用户账号查询用户信息
     * @param userCode
     * @return
     */
    public SysUser findSystUserByUserCode(String userCode) throws Exception {
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andUsercodeEqualTo(userCode);
        List<SysUser> sysUsers = sysUserMapper.selectByExample(sysUserExample);
        if (sysUsers != null && sysUsers.size() == 0) {
            return sysUsers.get(0);
        } else {
            return null;
        }
    }

    /**
     * 根据用户账号查询Menu资源信息
     * @param userid
     * @return
     * @throws Exception
     */
    public List<SysPermission> findMenuListByUserId(String userid) throws Exception {
        return sysPermissionMapperEx.findMenuListByUserId(userid);
    }
    /**
     * 根据用户账号查询url资源信息
     * @param userid
     * @return
     * @throws Exception
     */
    public List<SysPermission> findPermissionListByUserId(String userid) throws Exception {
        return  sysPermissionMapperEx.findPermissionListByUserId(userid);
    }
}
