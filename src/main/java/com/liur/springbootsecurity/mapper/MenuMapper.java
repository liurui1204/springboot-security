package com.liur.springbootsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liur.springbootsecurity.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liurui
 * @Description :菜单持久层
 * @create 2022-03-12-7:16 下午
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsByUserId(long id);
}
