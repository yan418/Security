package com.config.component;

import com.modules.entities.Role;
import com.modules.entities.User;
import com.modules.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 *  自定义的UserRealm
 *  配置Shiro
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    @Override
    public String getName() {
        return "UserRealm";
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("-----授权了-----AuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // info.addStringPermission("user:add");
        // 从数据库查权限
        Subject subject = SecurityUtils.getSubject();
        // 其实就是拿认证成功的时候的那个user
        User currentUser = (User) subject.getPrincipal();
        //角色列表
        List<String> ro = new ArrayList<>();
        List<Role> roles = currentUser.getRoles();
        for (Role e : roles){
            ro.add(e.getSn());
        }
        info.addRoles(ro);
        //info.addStringPermissions(ro);
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        System.out.println("-----认证了-----AuthenticationInfo");
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //用户名，密码去数据库取
        User user = userService.getAll(userToken.getUsername());
        if (user==null){  //没有这个人
            return null;  //其实就是抛出UnknownAccountException异常
        }
        // 判断用户信息是否正确;  1 实体类  2.数据库密码  3.盐值  4.方法名
        // 进行盐值加密
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername());
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),credentialsSalt ,getName());
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);
        return info;

    }

    //清除缓存
    public void clearCached() {
        //获取当前等的用户凭证，然后清除
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }

}
