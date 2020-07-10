package com.modules.service;

import com.modules.entities.User;

public interface UserService {

    User getAll(String username);

    User user(Integer pid);

    String selectPassword(String username);

    String addPass(String pass);

}
