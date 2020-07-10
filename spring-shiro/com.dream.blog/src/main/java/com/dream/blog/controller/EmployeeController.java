package com.dream.blog.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.blog.common.web.JsonResult;
import com.dream.blog.common.web.service.PermissionName;


/**
 * 员工请求
 * @author Administrator
 *
 */

@RestController
public class EmployeeController {
	
	
	@GetMapping("/app/save")
	@RequiresPermissions("emp:save")
	@PermissionName("员工增加")
	public JsonResult save(){			
		System.out.println("增加操作");
		return new JsonResult("增加操作");
	}
		
	/*
	 * shiro权限注解
	 */
	@GetMapping("/app/edit")
	@RequiresPermissions("emp:edit")
	@PermissionName("员工修改")
	public JsonResult edit(){	
		System.out.println("修改操作");
		return new JsonResult("修改操作");			
	}
	
	@GetMapping("/app/delete")
	@RequiresPermissions("emp:delete")
	@PermissionName("员工删除")
	public JsonResult delete(){	
		System.out.println("删除操作");
		return new JsonResult("删除操作");		
	}
	
}
