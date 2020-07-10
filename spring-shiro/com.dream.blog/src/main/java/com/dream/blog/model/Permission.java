package com.dream.blog.model;

import java.io.Serializable;

public class Permission implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6051777616249884557L;
	//权限名称
	private String name;
	//资源表达式xx:xx  比如：employee:list
    private String resource;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	@Override
	public String toString() {
		return "Permission [name=" + name + ", resource=" + resource + "]";
	} 
	 
}
