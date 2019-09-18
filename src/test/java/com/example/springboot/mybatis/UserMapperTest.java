package com.example.springboot.mybatis;

import com.example.springboot.mybatis.entity.User;
import com.example.springboot.mybatis.mapper.UserMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest()
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    // 每次执行Test之前先删除表，创建表
    @Before
    public void before() throws Exception {
        userMapper.dropTable();
        userMapper.createTable();
    }

    // 打印出class com.sun.proxy.$Proxy66表示spring注入通过jdk动态代理获取接口的子类
    @Test
    public void proxy() throws Exception {
        System.out.println("--------1-----------------------------------------------------------------proxy:"+userMapper.getClass());
    }

    @Test
    public void save() throws Exception {
        for (int i = 0; i < 10; i++) {
            User user = new User("jege" + i, 25 + i);
            userMapper.insert(user);
            System.out.println("----2-------------------------------------------------------------------------save");
        }
    }

    @Test
    public void all() throws Exception {
        save();
        assertThat(userMapper.findAll()).hasSize(10);
        System.out.println("------3-----------------------------------------------------------------------all");
    }

    @Test
    public void findByName() throws Exception {
        save();
        assertThat(userMapper.findByNameLike("jege%")).hasSize(10);
        System.out.println("---------4--------------------------------------------------------------------findByName");
    }

    // 每次执行Test之后清空数据
    @After
    public void destroy() throws Exception {
        userMapper.deleteAll();
    }

}

