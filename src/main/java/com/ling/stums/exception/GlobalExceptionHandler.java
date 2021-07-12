package com.ling.stums.exception;

import com.ling.stums.dto.responresult.AjaxResult;
import com.ling.stums.dto.responresult.Code;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public AjaxResult<Object> handler(RuntimeException e){
        e.printStackTrace();
        log.error("运行时异常-----------------------[{}]",e);
        return new AjaxResult<>(Code.ERROR,e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public AjaxResult<Object> handler(IllegalArgumentException e){
        e.printStackTrace();
        log.error("Assert异常-----------------------[{}]",e);
        return new AjaxResult<>(Code.ERROR,e.getMessage());
    }
}
