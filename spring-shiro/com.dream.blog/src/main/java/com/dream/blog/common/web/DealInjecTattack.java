package com.dream.blog.common.web;

/**
 * 防止注入攻击
 * @return返回字符串
 * */
public class DealInjecTattack {
	
	public String dealScript(String msge) {  		
		msge = msge.replaceAll("<", "&lt;"); 
		msge = msge.replaceAll(">", "&gt;");
		msge = msge.replaceAll("\"", "&quot;"); 
		return msge;  
	}
}
