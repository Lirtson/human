package com.liang.human.dao;

import com.liang.human.model.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    /*
    @Select("select * from user where id = #{id}")
    public User getById(@Param("id")long id);

    @Insert("insert into user values (#{username},)")
    public void createUser(@Param("username") String username, @Param("password") String password, @Param("role") int role);
     */

    @Insert("insert into user (username, password, role)values(#{username}, #{password}, #{role})")
    void createUser(UserVO user);
}
