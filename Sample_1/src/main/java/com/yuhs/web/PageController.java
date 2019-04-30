package com.yuhs.web;

import com.yuhs.dto.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yuhaisheng on 2019/4/28.
 */
@Controller
@RequestMapping("page")
public class PageController {

    /**
     * 无权访问页面
     * @return
     */
    @RequestMapping("/refuse")
    public String refuse() {
        return "403";
    }
    /**
     * 登录页面表示
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 平台首页
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model) {

        //从shiro到session中取activeUser
        Subject subject = SecurityUtils.getSubject();
        //取出身份信息
        ActiveUser activeUser = (ActiveUser)subject.getPrincipal();
        //通过model传递到页面
        model.addAttribute("activeUser", activeUser);

        return "index";
    }

    /**
     * 图书管理页面
     * @return
     */
    @RequiresPermissions("book:query") //执行page方法需要[book:query]权限
    @RequestMapping("book")
    public String book() {
        return "B0001";
    }
}
