package com.yuhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yuhaisheng on 2019/5/4.
 */
@Controller
@RequestMapping("userRole")
public class UserRoleController {

    /**
     * 用户角色关系初期表示画面
     * @return
     */
    @RequestMapping("init")
    public String userManger() {
        return "userRole";
    }
}
