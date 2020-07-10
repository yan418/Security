package com.dream.blog.common.exception;


/**
 * 处理异常
 * */
public class ServiceException extends RuntimeException{

	public ServiceException() {
		super();

	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		//super(message, cause, enableSuppression, writableStackTrace);
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);

	}

	public ServiceException(String message) {
		super(message);

	}

	public ServiceException(Throwable cause) {
		super(cause);

	}
	
}
