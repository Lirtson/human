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
        System.out.println("LOGIN");
        String token=userService.login(userVO);
        if(token==null)
            return AjaxResponse.fail();
        Map map = new HashMap<String, String>();
        map.put("token", token);
        return AjaxResponse.success(map);
    }
    /*试探登录
    @RequestMapping(value="/login",method=RequestMethod.OPTIONS)
    @ResponseBody
    public AjaxResponse loginTEST(){
        System.out.println("LOGIN1");
        return AjaxResponse.success();
    }
    */
    //试探get
    @RequestMapping(value="/testget",method=RequestMethod.GET)
    @ResponseBody
    public AjaxResponse getTEST(@RequestParam String s){
        System.out.println("get test:"+s);
        return AjaxResponse.success();
    }

    //试探post
    @RequestMapping(value="/testpost",method=RequestMethod.POST)
    @ResponseBody
    //@CrossOrigin(methods = {RequestMethod.POST},allowCredentials = "true")origins = "*"
    public AjaxResponse postTEST(){
        System.out.println("post test");
        return AjaxResponse.success();
    }
}
