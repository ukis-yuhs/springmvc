package com.yuhs.service;

import com.yuhs.beans.SysPermission;
import com.yuhs.beans.SysUser;
import com.yuhs.dto.ActiveUser;

import java.util.List;

/**
 * 认证服务接口
 * Created by yuhaisheng on 2019/4/25.
 */
public interface SysService2 {

    /**
     * 根据用户的身份和密码进行认证，如果认证通过，返回用户身份信息
     * @param userCode
     * @param password
     * @return
     * @throws Exception
     */
    public ActiveUser authenticat(String userCode, String password) throws Exception;

    /**
     * 根据用户账号查询用户信息
     * @param userCode
     * @return
     */
    public SysUser findSystUserByUserCode(String userCode) throws Exception;

    /**
     * 根据用户账号查询Menu资源信息
     * @param userid
     * @return
     * @throws Exception
     */
    public List<SysPermission> findMenuListByUserId(String userid) throws Exception;
    /**
     * 根据用户账号查询url资源信息
     * @param userid
     * @return
     * @throws Exception
     */
    public List<SysPermission> findPermissionListByUserId(String userid) throws Exception;

}
