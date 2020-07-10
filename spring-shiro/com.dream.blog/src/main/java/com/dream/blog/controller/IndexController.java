package com.dream.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	
		
	//登录入口
	@RequestMapping("/login")
	public String main(HttpServletRequest req,Model model){
		
		String execption = (String) req.getAttribute("shiroLoginFailure");
		System.out.println(execption);		
		if(UnknownAccountException.class.getName().equals(execption)) {
			model.addAttribute("errorMsg", "账号不存在");
			System.out.println("账号不存在");
		} else if(IncorrectCredentialsException.class.getName().equals(execption)){
			model.addAttribute("errorMsg", "密码错误");
			System.out.println("密码错误");
		} else {
			//model.addAttribute("errorMsg", "其他错误错误");
			System.out.println("其他错误错误");
		}
		
		return "/home/login";		
	}
	
	//首页入口
	@RequestMapping("/home")
	public String home(){	
		System.out.println("111");
		return "/home/main";		
	}
	
	//列表入口
	@RequestMapping("/list")
	public String list(){				
		return "/home/list";		
	}
	

	//列表入口
	@RequestMapping("/unauthor")
	public String unauthor(){				
		return "/home/unauthor";		
	}
	
	
//	//其他页面
//	@RequestMapping("/other")
//	public String other(String ticket){				
//		return "/home/empty_page";		
//	}
//	
//	//主页
//	@RequestMapping("/home")
//	public String home(String ticket){				
//		return "/home/index";		
//	}
//	
//	//第一页
//	@RequestMapping("/first")
//	public String first(String ticket){			
//		return "/home/home";		
//	}
//	
//	//手机端管理   - APP管理控制
//	@RequestMapping("/app-versions")
//	public String appVersions(String ticket){			
//		return "/versions/versions_list";		
//	}
//	@RequestMapping("/app-version-add")
//	public String versions_add(String ticket){			
//		return "/versions/versions_add";		
//	}
//	@RequestMapping("/app-version-info")
//	public String versions_info(String ticket){			
//		return "/versions/versions_info";		
//	}
//	@RequestMapping("/app-version-alter")
//	public String versions_alter(String ticket){			
//		return "/versions/versions_alter";		
//	}
	
}
