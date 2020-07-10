package com.dream.blog.common.web;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取现在时间  
 * @return返回长时间格式 yyyy-MM-dd HH:mm:ss 
 * */
public class GetCurrentTime {

	public String getNowDate() {  
		  Date currentTime = new Date();  
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		  String dateString = formatter.format(currentTime);  
		  return dateString;  
	}
	
}
