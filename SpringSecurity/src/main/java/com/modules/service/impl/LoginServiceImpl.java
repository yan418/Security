package com.modules.service.impl;

import com.config.exception.ServiceException;
import com.modules.dao.UserDao;
import com.modules.entities.Role;
import com.modules.entities.User;
import com.modules.service.LoginService;
import com.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s)  {
        User user = userDao.getUser(s);

        if(user==null){
            //return null;
            throw new UsernameNotFoundException("找不到该账户信息！");
        }
//      List<GrantedAuthority> list = new ArrayList<>();
//      getRoles(user,list);
//      org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),list);
        return user;
    }

    /**
     * 获取所属角色  权限如果前缀是ROLE_，security就会认为这是个角色信息，而不是权限，例如ROLE_MENBER就是MENBER角色，CAN_SEND就是CAN_SEND权限
     * @param user
     * @param list
     */
     public void getRoles(User user,List<GrantedAuthority> list){
        for (Role role:user.getRoles()) {
            System.out.println(role);
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getSn()));
        }
     }

}
