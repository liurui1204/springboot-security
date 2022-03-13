package com.liur.springbootsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author liurui
 * @Description :
 * @create 2022-03-12-7:38 下午
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * .allowedOriginPatterns("*") //允许跨域的请求域名
     * .allowCredentials(true) //是否允许cookie
     * .allowedMethods("GET","POST","DELETE","PUT")//设置允许的请求方法
     * .allowedHeaders("*")//设置允许的header属性
     * .maxAge(3600);//跨域允许时间
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        //设置允许跨域的路径
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("GET","POST","DELETE","PUT")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
