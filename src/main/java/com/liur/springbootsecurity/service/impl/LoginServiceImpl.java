package com.liur.springbootsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liur.springbootsecurity.common.ResponseResult;
import com.liur.springbootsecurity.entity.SysUser;
import com.liur.springbootsecurity.mapper.SysUserMapper;
import com.liur.springbootsecurity.security.SecurityUserDetail;
import com.liur.springbootsecurity.service.LoginService;
import com.liur.springbootsecurity.util.JwtUtil;
import com.liur.springbootsecurity.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author liurui
 * @Description :
 * @create 2022-03-12-2:55 下午
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 登陆业务层
     * @param userName
     * @param password
     * @return
     */
    @Override
    public ResponseResult login(String userName, String password){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userName,
                        password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authentication)){
            throw new RuntimeException("用户名或密码错误");
        }
        //使用userid生成token
        SecurityUserDetail userDetail = (SecurityUserDetail) authentication.getPrincipal();
        String userId = userDetail.getSysUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis中
        redisUtil.setCacheObject("login:"+userId,userDetail);
        //吧token相应给前端
        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);
        return new ResponseResult(200,"登陆成功",map);
    }

    /**
     * 退出登陆
     * @return
     */
    public ResponseResult logout(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetail userDetail = (SecurityUserDetail) authentication.getPrincipal();
        String userId = userDetail.getSysUser().getId().toString();
        //删除缓存
        redisUtil.deleteObject("login:"+userId);
        return new ResponseResult(200,"退出成功");
    }
}
