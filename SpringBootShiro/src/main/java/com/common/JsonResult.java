package com.common;


/**
 * 封装页面所有的返回值 类型
 * */
public class JsonResult {

	public static final Integer SUCCESS=1; 
	public static final Integer ERROR=0; 
	/*状态：（SUCCESS ,ERROR）*/
	private Integer state;
	/*状态信息*/
	private String message;
	/*具体数据*/
	private Object data;
	
	public JsonResult() {
		this.state=SUCCESS;
	}
	
	public JsonResult(String message) {
		this();
		this.message=message;
		this.state=ERROR;
		
	}
	
	public JsonResult(Object data) {
		this();
		this.data=data;
	}
	
	public JsonResult(Throwable exp) {
		this.state=ERROR;
		this.message=exp.getMessage();
	}

	
	public Integer getState() {
		return state;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}
	
}
