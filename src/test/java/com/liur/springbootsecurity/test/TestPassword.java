package com.liur.springbootsecurity.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author liurui
 * @Description :测试使用passwordEncoder进行密码的加密和验证
 * @create 2022-03-13-9:50 上午
 */
@SpringBootTest
public class TestPassword {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
        System.out.println(passwordEncoder.matches("1234", encode));
    }
}
