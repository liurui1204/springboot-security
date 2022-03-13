package com.liur.springbootsecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liurui
 * @Description :
 * @create 2022-03-12-3:54 下午
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    //@PreAuthorize("hasAuthority('test')")
    public String hello(){
        return "hello";
    }
}
