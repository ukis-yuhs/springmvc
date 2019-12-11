package com.yuhs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yuhaisheng on 2019/5/7.
 */
@Controller
public class IndexController {

    /**
     * 主页面跳转
     * @return
     */
    @RequestMapping("/")
    public String defaultPage(){
        return "index";
    }
}
