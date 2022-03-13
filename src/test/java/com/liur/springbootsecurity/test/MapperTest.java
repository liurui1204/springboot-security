package com.liur.springbootsecurity.test;

import com.liur.springbootsecurity.mapper.SysUserMapper;
import com.liur.springbootsecurity.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author liurui
 * @Description :测试持久层
 * @create 2022-03-12-1:33 下午
 */
@SpringBootTest
public class MapperTest {
    @Autowired
    private SysUserMapper sysUserDAO;

    @Test
    public void testSysUserDAO(){
        List<SysUser> userList = sysUserDAO.selectList(null);
        System.out.println(userList);
    }
}
