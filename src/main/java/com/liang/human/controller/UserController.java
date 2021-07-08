package com.liang.human.controller;

import com.liang.human.exception.CustomException;
import com.liang.human.model.AjaxResponse;
import com.liang.human.model.UserVO;
import com.liang.human.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Resource
    UserService userService;

    //注册
    @PostMapping("/register")
    @ResponseBody
    public AjaxResponse register(@RequestBody UserVO userVO){
        try{
            userService.register(userVO.getUsername(),userVO.getPassword(),userVO.getRole());
        }catch (CustomException e){
            return AjaxResponse.error(e);
        }
        return AjaxResponse.success();
    }
    //登录
    @PostMapping("/login")
    @ResponseBody
    public AjaxResponse login(@RequestBody UserVO userVO){
        String token=userService.login(userVO);
        if(token==null)
            return AjaxResponse.fail();
        Map map = new HashMap<String, String>();
        map.put("token", token);
        return AjaxResponse.success(map);
    }
}
