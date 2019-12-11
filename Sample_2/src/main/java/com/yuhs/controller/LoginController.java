package com.yuhs.controller;

import com.yuhs.exception.CustomException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yuhaisheng on 2019/5/4.
 */
@Controller
@RequestMapping("user")
public class LoginController {

    @RequestMapping("/login")
    public String login(HttpServletRequest request) throws Exception {
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("用户名/密码错误");
            } else if ("randomcodeError".equals(exceptionClassName)) {
                throw new CustomException("验证码错误");
            }else {
                throw new Exception();//最终在异常处理器生成未知错误
            }
        }
        //此方法不出来登录成功，shiro认证成功会自动跳转到上一个路径
        //登录失败还到login页面
        return "login";
    }
}
