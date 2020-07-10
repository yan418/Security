package com.dream.blog.service;

import java.util.List;

import com.dream.blog.model.Permission;


public interface PermissionService {

	public int addPermission(Permission permission);
	
	public List<String> findList();
	
}
