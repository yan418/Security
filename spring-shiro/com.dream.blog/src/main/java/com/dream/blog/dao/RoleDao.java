package com.dream.blog.dao;


import java.util.List;


public interface RoleDao {
	

	 public List<String> findSn();
	 
	 public List<String> findSnById(Integer id);
	 
}
