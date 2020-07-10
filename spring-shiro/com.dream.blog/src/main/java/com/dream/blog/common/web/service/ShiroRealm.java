package com.dream.blog.common.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.dream.blog.dao.PermissionDao;
import com.dream.blog.dao.RoleDao;
import com.dream.blog.model.User;
import com.dream.blog.service.UserService;

public class ShiroRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "ShiroRealm";
	}
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		System.out.println("授权");
		
		User u = (User) principals.getPrimaryPrincipal();
		//权限列表
		List<String> permission = new ArrayList<String>();
		//角色列表
		List<String> roles = new ArrayList<>();
		
		System.out.println(u);
		//System.out.println(u.getId());
		
		if("admin".equals(u.getUsername())) {
			//admin 拥有全部权限
			permission.add("*:*");
			//admin 拥有全部角色   查询所有角色
            roles = roleDao.findSn();
		}else{
            //查询该用户的全部角色
			roles = roleDao.findSnById(u.getId());
			//查询该用户的全部权限
			permission = permissionDao.findResourceByUserId(u.getId());
			System.out.println(roles);
			System.out.println(permission);
		}	
		/** 静态操作  
		User u = (User) principals.getPrimaryPrincipal();
		List<String> permission = new ArrayList<String>();		
		if("yan".equals(u.getUsername())) {
			permission.add("emp:edit");
		}else if("admin".equals(u.getUsername())) {
			permission.add("*:*");
		}	
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permission);
		*/
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permission);
		info.addRoles(roles);
		return info;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		System.out.println("认证");	
		String username = (String) token.getPrincipal();	
		System.out.println(username);
		
		User u = userService.findOne(username);
		if(u == null) {
			return null;
		}
		//这个对象判断用户信息是否正确;  1 实体类  2.数据库密码  3.盐值  4.方法名
		//盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(u.getUsername());
		System.out.println(credentialsSalt);
		SimpleAuthenticationInfo info =new SimpleAuthenticationInfo(u,u.getPassword(),credentialsSalt ,getName());
		//SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(u,u.getPassword(),getName());
		return info;
	}
	
	//清除缓存
    public void clearCached() {
        //获取当前等的用户凭证，然后清除
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

	
}
