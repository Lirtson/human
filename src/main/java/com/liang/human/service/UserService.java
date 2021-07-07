package com.liang.human.service;

import com.liang.human.dao.UserDao;
import com.liang.human.domain.User;
import com.liang.human.exception.CustomException;
import com.liang.human.exception.CustomExceptionType;
import com.liang.human.model.UserVO;
import com.liang.human.utils.JWTUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Resource
    UserDao userDao;
    //注册
    public void register(String username,String password,int role){
        UserVO user=new UserVO(username,password,role);
        /*
        System.out.println(isExist);
        if(isExist!=null){
            System.out.println("存在");
            throw new CustomException(CustomExceptionType.FORBID_WRITE,"该用户名已存在");
        }
        else{
            System.out.println("来添加了");
            userRepository.save(userPO);
        }
         */
        userDao.createUser(user);
    }
    //token
    public String getToken(Long userId) {
        //存入JWT的payload中生成token
        Map claims = new HashMap<String,Integer>();
        claims.put("user_userId",userId);
        String subject = "use";
        String token = null;
        try {
            //该token过期时间
            token = JWTUtils.createJWT(claims, subject,1000*60*60*24);//设置过期时间为1天
        } catch (Exception e) {
            throw new RuntimeException("创建Token失败");
        }
        return token;
    }
    //登录，返回token
    public String login(UserVO userVo) {
        if(userVo == null) {
            throw new CustomException(CustomExceptionType.SYSTEM_ERROR,"服务端异常");
        }
        User user = userDao.getByName(userVo.getUsername());
        if(user == null) {
            throw new CustomException(CustomExceptionType.NOT_LOGIN,"账号不存在");
        }
        //验证密码
        String inputPass=userVo.getPassword();
        int inputRole=userVo.getRole();
        String password=user.getPassword();
        int role=user.getRole();
        if(!(inputPass.equals(password))||!(inputRole==role)) {
            throw new CustomException(CustomExceptionType.NOT_LOGIN,"密码或角色错误");
        }
        long userId=user.getId();//获取用户id
        //生成token
        String token= getToken(userId);
        return token;
    }
}
