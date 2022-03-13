package com.liur.springbootsecurity.security;

import com.liur.springbootsecurity.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

/**
 * @author liurui
 * @Description :用来存取用户信息和权限
 * @create 2022-03-11-10:52 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
public class SecurityUserDetail implements UserDetails {

    private SysUser sysUser;
    //存储权限信息
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * 有参构造
     * @param user
     * @param authorityList
     */
    public SecurityUserDetail(SysUser user, List<GrantedAuthority> authorityList) {
        this.authorities = authorityList;
        this.sysUser = user;
        this.setSysUser(sysUser);
        this.setAuthorities(authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return this.sysUser.getUserName();
    }

    /**
     * 账户是否过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 是否禁用
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
