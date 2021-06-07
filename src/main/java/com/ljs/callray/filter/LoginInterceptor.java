package com.ljs.callray.filter;

import com.alibaba.fastjson.JSONObject;
import com.ljs.callray.controller.model.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * li
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(StringUtils.isBlank(token)){
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");

            JSONObject.toJSON(new Result<>(null,400,"用户未登录，请先登录"));
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
