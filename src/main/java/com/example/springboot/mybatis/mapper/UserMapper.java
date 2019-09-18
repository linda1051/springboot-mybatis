package com.example.springboot.mybatis.mapper;

import com.example.springboot.mybatis.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Delete("drop table if exists t_user")
    void dropTable();

    @Insert("create table t_user (id bigint primary key auto_increment, age integer, name varchar(255))")
    void createTable();

    @Insert("insert into t_user(name,age) values(#{name},#{age})")
    void insert(User user);

    @Select("select id,name,age from t_user")
    List<User> findAll();

    @Select("select id,name,age from t_user where name like #{name}")
    List<User> findByNameLike(String name);

    @Delete("delete from t_user")
    void deleteAll();

}
