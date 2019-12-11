package com.yuhs.interceptor;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传拦截器
 * Created by yuhaisheng on 2019/4/23.
 */
public class FileUploadInterceptor implements HandlerInterceptor {
    private  long maxSize;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (httpServletRequest != null && ServletFileUpload.isMultipartContent(httpServletRequest)) {
            ServletRequestContext servletRequestContext = new ServletRequestContext(httpServletRequest);
            long requestSize = servletRequestContext.contentLength();
            if (requestSize > maxSize) {
                // 抛出异常
                throw new MaxUploadSizeExceededException(maxSize);
            }
        }
        //true表示拦截器放行
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }
}
