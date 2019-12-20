package com.han.product.server.exception;

import java.util.List;

import com.han.result.Result;
import com.han.result.SystemConstant;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e){
        e.printStackTrace();
        if(e instanceof ProductException){//属于订单异常
            ProductException productException= (ProductException) e;
            return Result.ERROR(productException.getCodeMsg());
        }else if(e instanceof BindException){//参数绑定（校验）异常
            BindException bindException= (BindException) e;
            List<ObjectError> allErrors = bindException.getAllErrors();
            ObjectError error = allErrors.get(0);
            String defaultMessage = error.getDefaultMessage();
            return Result.ERROR(SystemConstant.PARAM_ERROR.fillArgs(defaultMessage));//返回格式化之后异常错误提示
        }else{
            return Result.ERROR();//系统异常
        }
    }
}
