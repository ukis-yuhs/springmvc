package com.yuhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yuhaisheng on 2019/5/4.
 */
@Controller
@RequestMapping("rolePerm")
public class RolePermissionController {

    /**
     * 角色权限关系初期表示
     * @return
     */
    @RequestMapping("init")
    public String userManger() {
        return "rolePerm";
    }
}
