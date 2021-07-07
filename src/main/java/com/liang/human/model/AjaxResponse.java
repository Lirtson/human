package com.liang.human.model;

import com.liang.human.exception.CustomException;
import lombok.Data;

@Data
public class AjaxResponse {
    private int code;
    private Object data;

    private AjaxResponse() {
    }

    public static AjaxResponse success() {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setCode(200);
        return resultBean;
    }

    public static AjaxResponse success(Object data) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setCode(200);
        resultBean.setData(data);
        return resultBean;
    }

    //设置返回码
    public static AjaxResponse fail(int code){
        AjaxResponse resultBean=new AjaxResponse();
        resultBean.setCode(code);
        return resultBean;
    }
    public static AjaxResponse fail(){
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setCode(400);
        return resultBean;
    }

    //请求出现异常时的响应数据封装
    public static AjaxResponse error(CustomException e) {
        AjaxResponse resultBean = new AjaxResponse();
        resultBean.setCode(e.getCode());
        return resultBean;
    }

}