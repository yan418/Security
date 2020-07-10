package com.dream.blog.dao;

import java.util.List;

import com.dream.blog.model.Permission;

public interface PermissionDao {
	
	 public int addPermission(Permission permission);
	 
	 public List<String> findList();
	 
	 public List<String> findResourceByUserId(Integer id);
	 	 
}
