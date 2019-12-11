package com.yuhs.shiro;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义FormAuthenticationFilter，完成认证前进行验证码校验
 * Created by yuhaisheng on 2019/4/29.
 */
public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {
    /**
     * 原FormAuthenticationFilter的认证方法
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(
            ServletRequest request, ServletResponse response) throws Exception {
//        //在这里进行验证码校验
//        //从session中获取验证码
//        HttpServletRequest httpServletRequest =(HttpServletRequest)request;
//        string validateCode = (string)httpServletRequest.getSession().getAttribute("validateCode");
//
//        //取出页面输入的验证码
//        string randomcode = httpServletRequest.getParameter("randomcode");
//        if (randomcode != null && validateCode != null && !validateCode.equals(randomcode)) {
//            //如果校验失败，将验证码错误信息，通过shiroLoginFailure设置到request中
//            httpServletRequest.setAttribute("shiroLoginFailure", "randomcodeError");
//            //拒绝访问，不在校验账号和密码
//            return true;
//        }
        //如果失败，将验证码失败信息，通过shiroLoginFailure设置到request中
        return super.onAccessDenied(request, response);
    }
}
