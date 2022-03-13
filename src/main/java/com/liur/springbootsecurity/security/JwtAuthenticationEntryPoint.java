package com.liur.springbootsecurity.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liurui
 * @Description :认证入口点
 * @create 2022-03-11-10:42 下午
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        System.out.println("JwtAuthenticationEntryPoint:"+authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"没有凭证");
    }
}
