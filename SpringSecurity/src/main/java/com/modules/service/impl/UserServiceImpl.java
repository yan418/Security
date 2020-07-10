package com.modules.service.impl;

import com.modules.dao.UserDao;
import com.modules.entities.User;
import com.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;



    @Override
    public User getAll(String username) {
        System.out.println(username);
        System.out.println("---");

        User user = userDao.getUser(username);
        System.out.println(user);
        if (user == null) {
            throw new UsernameNotFoundException("不存在该用户!");
        }
        return user;
    }

    @Override
    public User user(Integer pid) {
        return userDao.getUserById(pid);
    }

    @Override
    public String selectPassword(String username) {
        return userDao.selectPassword(username);
    }

    @Override
    public String addPass(String pass) {

        String encode = new BCryptPasswordEncoder().encode(pass);
        System.out.println(encode);
        return pass;
    }
}
