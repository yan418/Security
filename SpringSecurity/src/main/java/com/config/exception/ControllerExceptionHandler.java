package com.config.exception;

import com.common.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;




/**
 * 借助此类实现控制层统�?异常处理
 * 使用@ControllerAdvice注解标识要此�?
 * 处理SpringMVC中控制的异常
 * 
 * @ControllerAdvice �?般用于定义@Controller对象的一些全�?特�??
 * 
 * */
@ControllerAdvice
public class ControllerExceptionHandler {

	/**
	 * 通过@ExceptionHandler注解声明要此方法能处理的异常的具体类�?
	 * */
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult handleServiceException(ServiceException e){
		e.printStackTrace();
		return new JsonResult(e);
	}
	
}
