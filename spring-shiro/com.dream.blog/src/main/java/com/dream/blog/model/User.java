package com.dream.blog.model;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -9078277616249884549L;

	private Integer id;
	//用户名
	private String username;
	//密码
	private String password;
	//缓存
	private String ticket;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", ticket=" + ticket + "]";
	}
	
	
}
