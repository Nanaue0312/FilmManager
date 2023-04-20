package com.filmmanage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.filmmanage.constant.UserConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * @Date 2023/2/3
 * @Author zcy Description:
 */
@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    /**
     * 自定义Interceptor
     *
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler chosen handler to execute, for type and/or instance evaluation
     * @return {@code true} if the execution chain should proceed with the next interceptor or the handler itself. Else,
     *         DispatcherServlet assumes that this interceptor has already dealt with the response itself.
     * @throws Exception in case of errors
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        log.info("个人拦截器工作");
        Object userLoginState = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        log.info(request.getRequestURI());
        if (userLoginState == null) {
            response.sendRedirect("/user/toLogin");
            return false;
        }
        return true;
    }
}
