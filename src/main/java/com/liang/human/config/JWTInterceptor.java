package com.liang.human.config;
import com.liang.human.exception.CustomException;
import com.liang.human.exception.CustomExceptionType;
import com.liang.human.utils.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Component
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 允许跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 允许自定义请求头token(允许head跨域)
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");


        //后台管理页面产生的token
        String token = request.getHeader("authorization");

        //将其加入request
        request.setAttribute("token",token);

        //判断是否过期
        if(token==null)
            throw new CustomException(CustomExceptionType.NOT_LOGIN,"token不存在");
        Optional.ofNullable(token)
                .map(n -> {
                    try {
                        return JWTUtils.parseJWT(n);
                    } catch (Exception e) {
                        throw new CustomException(CustomExceptionType.NOT_LOGIN,"token不存在");
                    }
                });

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}