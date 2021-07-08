package com.liang.human.controller;

import com.liang.human.config.UserContext;
import com.liang.human.domain.User;
import com.liang.human.exception.CustomException;
import com.liang.human.model.AjaxResponse;
import com.liang.human.model.EvaluationVO;
import com.liang.human.model.UserVO;
import com.liang.human.service.EvaluationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EvaluationController {
    @Resource
    EvaluationService evaluationService;
    //获取评论
    @GetMapping("/comments")
    @ResponseBody
    public AjaxResponse getComments(){
        try{
            List<EvaluationVO> list=evaluationService.getComments();
            return AjaxResponse.success(list);
        }catch (CustomException e){
            return AjaxResponse.error(e);
        }
    }
    //发布评论
    @PostMapping("/comment")
    @ResponseBody
    public AjaxResponse addComment(@RequestParam String content){
        try{
            User user= UserContext.getUser();//获取当前用户
            if(user==null){
                return AjaxResponse.fail();
            }
            System.out.println("content:"+content);
            evaluationService.addComment(content,user.getId());//当前用户发布了一条评论
        }catch (CustomException e){
            return AjaxResponse.error(e);
        }
        return AjaxResponse.success();
    }
}
