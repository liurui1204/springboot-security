package com.liur.springbootsecurity.config;

import com.liur.springbootsecurity.security.JwtAuthenticationEntryPoint;
import com.liur.springbootsecurity.security.JwtAuthorizationTokenFilter;
import com.liur.springbootsecurity.service.impl.AccessDeniedHandlerImpl;
import com.liur.springbootsecurity.service.impl.AuthenticationEntryPointImpl;
import com.liur.springbootsecurity.service.impl.JwtUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author liurui
 * @Description :securityConfig配置类
 * @create 2022-03-11-10:02 下午
 */
@Configuration
@EnableWebSecurity //这个注解表示开启security
@EnableGlobalMethodSecurity(prePostEnabled = true) //保证post之前的注解可以使用
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Autowired
    private JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Autowired
    private AuthenticationSuccessHandler successHandler;

    /**
     * 配置拦截
     * @param security
     */
    @Override
    public void configure(HttpSecurity security) throws Exception {
        //exceptionHandling().authenticationEntryPoint()
        //这里如果没有凭证，可以进行一些操作
        security.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/login","/hello").permitAll()
                .antMatchers("/sysUser/test").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").anonymous()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        security.cors();//允许跨域
        //配置认证失败
        //security.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        //定制我们自己的session策略；调整为spring security不创建和使用session
        //.anyRequest().authenticated()剩下所有的请求都需要验证
        // 禁用spring security自带的跨域处理，防止csdf攻击
        //.csrf().disable()
        //是为了方便后面写前后端分离时，前端传过来的第一个验证请求，这样做会减少这种请求的时间和资源的使用
        //.antMatchers(HttpMethod.OPTIONS,"/**").anonymous()，
        //主要是用于JWT验证。
        security.addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        //认证成功处理器
        security.formLogin().successHandler(successHandler);
    }

    /**
     * 访问一些静态的东西
     * @param webSecurity
     */
    @Override
    public void configure(WebSecurity webSecurity){

    }

    /**
     * 密码加密存储
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
