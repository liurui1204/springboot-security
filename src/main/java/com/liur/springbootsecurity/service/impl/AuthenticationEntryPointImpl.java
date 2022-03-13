package com.liur.springbootsecurity.service.impl;

import com.alibaba.fastjson.JSON;
import com.liur.springbootsecurity.common.ResponseResult;
import com.liur.springbootsecurity.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liurui
 * @Description :自定义认证失败
 * @create 2022-03-12-7:31 下午
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(),"认证失败,请重新登陆");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
