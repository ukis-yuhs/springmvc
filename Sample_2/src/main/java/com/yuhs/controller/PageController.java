package com.yuhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yuhaisheng on 2019/5/6.
 */
@Controller
@RequestMapping("page")
public class PageController {

    /**
     * 登录页面跳转
     * @return
     */
    @RequestMapping("login")
    public String login() {
        return "login";
    }

    /**
     * 主页面跳转
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    /**
     * 无权访问页面
     * @return
     */
    @RequestMapping("refuse")
    public String refuse() {
        return "refuse";
    }
}
