package com.liur.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.liur.mybatisplus.entity.User;
import com.liur.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
    }

    @Test
    void insertUser() throws Exception {
        User user = new User();
        user.setUsername("张三");
        user.setAge(18);
        user.setEmail("1257064263@qq.com");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int insert = userMapper.insert(user);
        if(insert > 0){
            System.out.println("添加成功");
        }else{
            throw new Exception("添加失败!");
        }
    }

    @Test
    void selectUser(){
        List<User> users = userMapper.selectList(null);
        for (User user: users){
            System.out.println(user);
        }
    }
}
