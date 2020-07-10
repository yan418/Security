package com.modules.service.impl;

import com.modules.dao.UserDao;
import com.modules.entities.User;
import com.modules.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    @Override
    public User getAll(String username) {
        User user = userDao.getUser(username);
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


        return pass;
    }
}
