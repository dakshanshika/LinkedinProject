package com.demo.linkedinProject.postsService.contextHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthContextHolder implements HandlerInterceptor {

    private ThreadLocal<String> userIdThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId= request.getHeader("X-User-Id");
        userIdThreadLocal.set(userId);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        userIdThreadLocal.remove();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    public String getUserIdFromCH(){
        return userIdThreadLocal.get();
    }
}
