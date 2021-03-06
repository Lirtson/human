package com.liang.human.exception;

import com.liang.human.model.AjaxResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice//标记这是个全局异常处理类
public class WebExceptionHandler {


    @ExceptionHandler(CustomException.class)//针对这个异常，要做下面的事情
    @ResponseBody
    //@ResponseStatus(code=HttpStatus.BAD_REQUEST)
    public AjaxResponse customerException(CustomException e) {
        /*if(e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode()){
                 //400异常不需要持久化，将异常信息以友好的方式告知用户就可以
                //TODO 将500异常信息持久化处理，方便运维人员处理
        }*/
        return AjaxResponse.error(e);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(code= HttpStatus.SERVICE_UNAVAILABLE )
    public AjaxResponse exception(Exception e) {
        //TODO 将异常信息持久化处理，方便运维人员处理

        //没有被程序员发现，并转换为CustomException的异常，都是其他异常或者未知异常.

        //这里改成了500
        return AjaxResponse.error(new CustomException(CustomExceptionType.SYSTEM_ERROR,"未知异常"));
    }


}
