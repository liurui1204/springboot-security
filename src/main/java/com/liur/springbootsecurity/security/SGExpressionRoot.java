package com.liur.springbootsecurity.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Permissions;
import java.util.Collection;
import java.util.List;

/**
 * @author liurui
 * @Description :自定义权限校验方法
 * @create 2022-03-12-7:49 下午
 */
@Component("ex")
public class SGExpressionRoot {
    public boolean hashAuthority(String authority){
        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetail userDetail = (SecurityUserDetail) authentication.getPrincipal();
        Collection<? extends GrantedAuthority> permissions = userDetail.getAuthorities();
        //判断用户权限集合中是否存在authority
        return permissions.contains(authentication);
    }
}
