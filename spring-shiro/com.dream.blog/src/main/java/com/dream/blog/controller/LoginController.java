package com.dream.blog.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import com.dream.blog.common.web.JsonResult;
import com.dream.blog.common.web.config.MD5Encrypt;
import com.dream.blog.common.web.service.PermissionName;
import com.dream.blog.model.Permission;
import com.dream.blog.service.PermissionService;



@Controller
public class LoginController {
	
	//springmvc在启动时候将所有贴有请求映射标签：RequestMapper方法收集起来封装到该对象中
	@Autowired
	private RequestMappingHandlerMapping rmhm;
	
	@Autowired
	private PermissionService permissionService;
	

	/**
	 * 添加权限列表
	 */
	@RequestMapping(value = "/reload",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult reload(){	
		
		//将系统中所有权限表达式加载进入数据库
        //0：从数据库中查询出所有权限表达式，然后对比，如果已经存在了，跳过，不存在添加
        List<String> resourcesList = permissionService.findList();
        //1:获取controller中所有带有@RequestMapper标签的方法
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = rmhm.getHandlerMethods();
            
        Collection<HandlerMethod> methods = handlerMethods.values();
        //2：遍历所有方法，判断当前方法是否贴有@RequiresPermissions权限控制标签
        for (HandlerMethod method : methods) {
           
            RequiresPermissions anno = method.getMethodAnnotation(RequiresPermissions.class);
            //System.out.println(anno);
            if(anno != null){
                //3：如果有，解析得到权限表达式，封装成Permission对象保存到Permission表中
                //权限表达式   只有一条权限的情况下 是数组【0】
                String resource = anno.value()[0];
                System.out.println(resource);
                //去除重复的   数组里包含值，就跳出循环
                if(resourcesList.contains(resource)){
                    continue;
                }
                Permission p = new Permission();
                p.setResource(resource);
                //获取  注解PermissionName 这里的值
                String name=method.getMethodAnnotation(PermissionName.class).value();
                //设置权限名称
                p.setName(name);
                //System.out.println(p);
                permissionService.addPermission(p);
            }
        }
		
		return new JsonResult("添加成功");		 
		
	}
	
	
	/**
	 * 进程加密
	 * @return VersionManagement
	 */
	@GetMapping("/app/doEncrypt")
	@ResponseBody
	public JsonResult doEncrypt(String username,String password){	
		Md5Hash hash = MD5Encrypt.doEncrypt(username, password);
		System.out.println(hash);
		return new JsonResult(hash); 		
	}
}
