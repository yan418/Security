package com.config.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  异常处理类
 *  处理，请求没有权限，跳转到403页面
 *
 *
 */
@ControllerAdvice
public class HandlerControllerException{

    @ExceptionHandler(AccessDeniedException.class)
    public String handlerAccessDeniedException(){
        return "error/403";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handlerRuntimeException(){
        return "error/500";
    }

//    @ExceptionHandler(UsernameNotFoundException.class)
//    public String UsernameNotFoundException(){
//        return "error/403";
//    }

//    @ExceptionHandler(RuntimeException.class)
//    public String handException(RuntimeException e){
//        if(e instanceof AccessDeniedException){
//            return "error/403";
//        }
//        return "error/500";
//    }

}
