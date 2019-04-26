package com.yuhs.dao;

import com.yuhs.entity.SysPermission;

import java.util.List;

/**
 * 权限Mapper
 * Created by yuhaisheng on 2019/4/25.
 */
public interface SysPermissionMapperEx {
    /**
     * 根据用户账号查询Menu资源信息
     * @param userid
     * @return
     */
    List<SysPermission> findMenuListByUserId(String userid);

    /**
     * 根据用户账号查询url资源信息
     * @param userid
     * @return
     */
    List<SysPermission> findPermissionListByUserId(String userid);
}
