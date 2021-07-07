package com.liang.human.service;

import com.liang.human.dao.UserDao;
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
}
