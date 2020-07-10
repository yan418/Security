package com.dream.blog.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.blog.dao.PermissionDao;
import com.dream.blog.model.Permission;
import com.dream.blog.service.PermissionService;




@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionDao permissionDao;

	
	@Override
	public int addPermission(Permission permission) {
		int row = permissionDao.addPermission(permission);
        if(row!=1){
            throw new RuntimeException("修改失败");
        }
	    
		return row;
	}


	@Override
	public List<String> findList() {
		List<String> list = permissionDao.findList();
		return list;
	}


	
}


