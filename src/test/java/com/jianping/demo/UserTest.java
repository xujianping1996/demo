package com.jianping.demo;

import org.springframework.beans.factory.annotation.Autowired;

import com.jianping.demo.entity.User;
import com.jianping.demo.service.UserService;

public class UserTest {
	@Autowired
	private UserService userService;
	
}
