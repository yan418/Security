package com.modules.dao;

import com.modules.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    User getUser(String username);

    User getUserById(Integer pid);

    String selectPassword(String username);


}
