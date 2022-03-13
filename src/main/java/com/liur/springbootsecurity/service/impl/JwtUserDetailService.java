package com.liur.springbootsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liur.springbootsecurity.mapper.MenuMapper;
import com.liur.springbootsecurity.mapper.SysUserMapper;
import com.liur.springbootsecurity.entity.SysUser;
import com.liur.springbootsecurity.security.SecurityUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author liurui
 * @Description : 真正的认证
 * @create 2022-03-11-10:49 下午
 */
@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private SysUserMapper userDAO;

    @Autowired
    private MenuMapper menuMapper;
    /**
     * 通过用户的唯一表示，读取用户的信息,并将用户信息封装到userDetail
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName,username);
        SysUser user = userDAO.selectOne(wrapper);
        //如果查询不到数据抛异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误");
        }
        //System.out.println(user.toString());
        //根据用户查询权限信息，添加到权限集合
        List<GrantedAuthority> authorityList = new ArrayList<>();
//        List<String> permissionKeyList = menuMapper.selectPermsByUserId(user.getId());
//        if(permissionKeyList != null && permissionKeyList.size() > 0){
//            for(String auth : permissionKeyList){
//                authorityList.add(new SimpleGrantedAuthority(auth));
//            }
//        }
        authorityList.add(new SimpleGrantedAuthority("sys:manage"));
        return new SecurityUserDetail(user, authorityList);
    }
}
