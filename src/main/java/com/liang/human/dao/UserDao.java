package com.liang.human.dao;

import com.liang.human.domain.User;
import com.liang.human.model.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Insert("insert into user (username, password, role)values(#{username}, #{password}, #{role})")
    void createUser(UserVO user);

    @Select("select * from user where username = #{username}")
    User getByName(@Param("username")String username);

    @Select("select * from user where id = #{id}")
    User getById(@Param("id")int id);
}
