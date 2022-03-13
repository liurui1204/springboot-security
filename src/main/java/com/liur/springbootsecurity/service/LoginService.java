package com.liur.springbootsecurity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liur.springbootsecurity.common.ResponseResult;
import com.liur.springbootsecurity.entity.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author liurui
 * @Description :
 * @create 2022-03-12-2:53 下午
 */
public interface LoginService{
    ResponseResult login(String userName, String password);

}
