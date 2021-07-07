package com.liang.human.exception;

import com.liang.human.model.AjaxResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//全局异常处理捕获不到404异常
@RestController
public class NotFoundException implements ErrorController {

    private static final String ERROR_PATH = "/error";

    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public AjaxResponse error(){
        return AjaxResponse.fail(404);
    }
}
