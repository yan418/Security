package com.dream.blog.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.blog.common.web.JsonResult;
import com.dream.blog.common.web.service.PermissionName;


/**
 * 经理请求
 * @author Administrator
 *
 */

@RestController
public class ManagerController {
	
	
	@GetMapping("/manager/save")
	@RequiresPermissions("manager:save")
	@PermissionName("经理增加")
	public JsonResult save(){			
		System.out.println("经理增加操作");
		return new JsonResult("经理增加操作");
	}
		
	@GetMapping("/manager/edit")
	@RequiresPermissions("manager:edit")
	@PermissionName("经理修改")
	public JsonResult edit(){	
		System.out.println("经理修改操作");
		return new JsonResult("经理修改操作");			
	}
	
	@GetMapping("/manager/delete")
	@RequiresPermissions("manager:delete")
	@PermissionName("经理删除")
	public JsonResult delete(){	
		System.out.println("经理删除操作");
		return new JsonResult("经理删除操作");		
	}
	
}
