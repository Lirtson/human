package com.liang.human.dao;

import com.liang.human.model.EvaluationVO;
import com.liang.human.model.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EvaluationDao {
    //获取评论区
    @Select("select e.id as contentId,e.content as content,u.username as contentUser,u.role as contentRole from evaluation e left join user u on e.uid = u.id")
    List<EvaluationVO> listCommentsVo();
    //发布一条评论
    @Insert("insert into evaluation (content,uid)values(#{content}, #{uid})")
    void addOne(@Param("content") String content, @Param("uid") Long uid);
}
