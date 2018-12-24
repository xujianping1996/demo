package com.jianping.demo.service;

import com.jianping.demo.entity.User;

public interface UserService {
	public User selectUserByLoginName(String username);
}
