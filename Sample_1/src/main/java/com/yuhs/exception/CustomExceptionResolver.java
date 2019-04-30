package com.yuhs.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <property name="unauthorizedUrl" value="/page/refuse"/>
 * 上面的配置只处理perms，roles，ssl，rest，port情况，它属于AuthorizationFilter
 * anon，authcBasic，authc，user是AuthenticationFilter，
 * 所以unauthorizedUrl设置后不起作用，只会在控制台打印异常信息。
 * 下面的处理就是为了处理这种情况
 * Created by yuhaisheng on 2019/4/29.
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        if (e instanceof UnauthorizedException) {
            ModelAndView mv = new ModelAndView("403");
            return mv;
        }
        return null;
    }
}
