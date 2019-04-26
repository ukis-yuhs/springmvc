package com.yuhs.web;

import com.yuhs.exception.CustomException;
import com.yuhs.service.SysService;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yuhaisheng on 2019/4/25.
 */
@Controller
public class LoginController {
    @Autowired
    private SysService sysService;

//    /**
//     * 用户登录
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("login")
//    public String login(HttpSession httpSession, String usercode, String password) throws Exception {
//        //校验验证码，防止恶性攻击 TODO
//
//        //认证
//        ActiveUser activeUser = sysService.authenticat(usercode, password);
//        //httpSession保存
//        httpSession.setAttribute("activeUser", activeUser);
//        return "index";
//    }

    /**
     * 登录提交地址，和applicationContext-shiro.xml中配置的loginurl一致
     * @return
     */
    @RequestMapping("login")
    public String login(HttpServletRequest request) throws Exception {
        //如果登录失败从request中获取认证异常信息
        //shiroLoginFailure就是shiro异常类到全限定名
        String exceptionClassName = request.getParameter("shiroLoginFailure");
        //根据shiro返回到异常类路径判断，抛出指定异常信息
        if (exceptionClassName != null) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("账号不存在");
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                throw new CustomException("用户名/密码错误");
            } else {
                throw new Exception();//最终在异常处理器生成未知错误
            }
        }

        //此方法不出来登录成功，shiro认证成功会自动跳转到上一个路径
        //登录失败还到login页面
        return "index";
    }

//    @RequestMapping("logout")
//    public String logout(HttpSession httpSession) {
//        //session失效
//        httpSession.invalidate();
//        return "login";
//    }
}
