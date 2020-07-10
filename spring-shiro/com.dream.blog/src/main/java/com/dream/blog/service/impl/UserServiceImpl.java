package com.dream.blog.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.blog.dao.UserDao;
import com.dream.blog.model.User;
import com.dream.blog.service.UserService;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public User findOne(String username) {
		User u = userDao.findOne(username);
		return u;
	}
	
	
}


