package com.modules.dao;

import com.modules.entities.User;

public interface UserDao {

    User getUser(String username);

    User getUserById(Integer pid);

    String selectPassword(String username);


}
