package com.yuhs.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yuhaisheng on 2019/5/19.
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 进入方法前被执行
     * 登录拦截，权限验证等
     * @param request
     * @param response
     * @param handler
     * @return boolean true 放行，false 拦截
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断用户是否登录
        Object object = request.getSession().getAttribute("UserInfo");
        if (object == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
        }
        return true;
    }

    /**
     * 方法执行之后，返回ModeAndView之后被执行
     * 设置页面的共用参数的
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 方法执行后被执行
     * 处理异常，清资源，记录日志等
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
