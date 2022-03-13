package com.liur.springbootsecurity.controller;

import com.liur.springbootsecurity.common.ResponseResult;
import com.liur.springbootsecurity.entity.SysUser;
import com.liur.springbootsecurity.service.LoginService;
import com.liur.springbootsecurity.service.impl.JwtUserDetailService;
import com.liur.springbootsecurity.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liurui
 * @Description :登陆接口
 * @create 2022-03-12-8:56 上午
 */
@RestController
public class LoginController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @GetMapping("/login")
    public ResponseResult login(String userName, String password, HttpServletRequest request){
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(userName);
        String code = jwtTokenUtil.generateToken(userDetails);
        System.out.println(userDetails.getUsername());
        return loginService.login(userName, password);
    }

}
